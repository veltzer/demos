package swing.layout_managers;

import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;

public class NaturalGridLayout implements LayoutManager {
	private int rows;
	private int columns;
	private static final GetSize PREFERRED_SIZE = new GetSize() {
		public Dimension getSize(Component cmp) {
			return (cmp.getPreferredSize());
		}
	};

	private static final GetSize MINIMUM_SIZE = new GetSize() {
		public Dimension getSize(Component cmp) {
			return (cmp.getMinimumSize());
		}
	};

	public NaturalGridLayout(int irows, int icolumns) {
		rows = irows;
		columns = icolumns;
	}

	public void addLayoutComponent(String name, Component comp) {
	}

	public void layoutContainer(Container parent) {
		boolean rToL = ComponentOrientation.RIGHT_TO_LEFT.equals(parent
				.getComponentOrientation());
		Insets insets = parent.getInsets();
		Dimension size = parent.getSize();

		int componentWidth = (size.width - (insets.left + insets.right))
				/ columns;
		int componentHeight = (size.height - (insets.top + insets.bottom))
				/ rows;

		int currentComponent = 0;
		for (int rowIter = 0; rowIter < rows; rowIter++) {
			for (int columnIter = 0; columnIter < columns; columnIter++) {
				Component comp = parent.getComponent(currentComponent);
				placeComponent(rToL, comp, rowIter, columnIter, componentWidth,
						componentHeight);
				currentComponent++;
				if (currentComponent >= parent.getComponentCount()) {
					break;
				}
			}
		}
	}

	private void placeComponent(boolean rToL, Component comp, int row,
			int column, int componentWidth, int componentHeight) {
		Dimension componentSize = comp.getPreferredSize();
		if (componentSize.height > componentHeight) {
			componentSize.height = componentHeight;
		}
		if (componentSize.width > componentWidth) {
			componentSize.width = componentWidth;
		}
		int x = componentWidth * column;
		int y = componentHeight * row;
		if (rToL) {
			// we need to inverse the x position
			x = comp.getParent().getWidth() - x - componentSize.width;
		}
		comp.setBounds(x, y, componentSize.width, componentSize.height);
	}

	public Dimension minimumLayoutSize(Container parent) {
		return (layoutSize(parent, MINIMUM_SIZE));
	}

	public Dimension preferredLayoutSize(Container parent) {
		return (layoutSize(parent, PREFERRED_SIZE));
	}

	public void removeLayoutComponent(Component comp) {
	}

	interface GetSize {
		Dimension getSize(Component cmp);
	}

	private Dimension layoutSize(Container parent, GetSize sizer) {
		Insets insets = parent.getInsets();
		int width = 0;
		int height = 0;
		int currentWidth = 0;
		int[] heights = new int[columns];

		int currentComponent = 0;
		for (int rowIter = 0; rowIter < rows; rowIter++) {
			for (int columnIter = 0; columnIter < columns; columnIter++) {
				Component comp = parent.getComponent(currentComponent);
				Dimension d = sizer.getSize(comp);
				currentWidth += d.width;
				heights[columnIter] += d.height;
				currentComponent++;
				if (currentComponent >= parent.getComponentCount()) {
					break;
				}
			}
			width = Math.max(width, currentWidth);
		}

		for (int iter = 0; iter < columns; iter++) {
			height = Math.max(height, heights[iter]);
		}
		return (new Dimension(insets.left + insets.right + width, insets.top
				+ insets.bottom + height));
	}
}
