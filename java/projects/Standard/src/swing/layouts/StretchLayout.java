package swing.layouts;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;

public class StretchLayout implements LayoutManager {
	private static final int SPACING = 4;

	public static final int TOP_ALIGNMENT = 0x01;

	public static final int BOTTOM_ALIGNMENT = 0x02;

	private int alignment;

	/**
	 * This is a stateless layout.
	 */
	public StretchLayout() {
		this(TOP_ALIGNMENT);
	}

	public StretchLayout(int ialignment) {
		alignment = ialignment;
	}

	public void addLayoutComponent(String name, Component comp) {
	}

	public void layoutContainer(Container parent) {
		Insets insets = parent.getInsets();
		Dimension size = parent.getSize();

		int yPos = 0;
		if (alignment == BOTTOM_ALIGNMENT) {
			yPos = size.height - insets.bottom;
			for (int i = 0; i < parent.getComponentCount(); ++i) {
				Component c = parent.getComponent(i);
				yPos -= c.getPreferredSize().height;
				if (i > 0) {
					yPos -= SPACING;
				}
			}
		}
		for (int i = 0; i < parent.getComponentCount(); ++i) {
			Component c = parent.getComponent(i);
			c.setBounds(0, yPos, size.width - (insets.left + insets.right),
					c.getPreferredSize().height);
			yPos += c.getPreferredSize().height + SPACING;
		}
	}

	public Dimension minimumLayoutSize(Container parent) {
		Dimension result = new Dimension(0, 0);
		for (int i = 0; i < parent.getComponentCount(); ++i) {
			Component c = parent.getComponent(i);
			result.width = Math.max(result.width, c.getMinimumSize().width);
			if (i > 0) {
				result.height += c.getMinimumSize().height + SPACING;
			}
		}
		return result;
	}

	public Dimension preferredLayoutSize(Container parent) {
		Dimension result = new Dimension(0, 0);
		for (int i = 0; i < parent.getComponentCount(); ++i) {
			Component c = parent.getComponent(i);
			result.width = Math.max(result.width, c.getPreferredSize().width);
			if (i > 0) {
				result.height += c.getPreferredSize().height + SPACING;
			}
		}
		return result;
	}

	public void removeLayoutComponent(Component comp) {
	}

}
