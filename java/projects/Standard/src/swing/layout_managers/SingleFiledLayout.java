package swing.layout_managers;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;

/**
 * SingleFiledLayout lays out components singled filed. This layout manager is
 * like FlowLayout except that all components are placed in a single row or
 * column.
 * @author Mark Veltzer <mark@veltzer.net>
 */

@SuppressWarnings("serial")
public class SingleFiledLayout implements java.awt.LayoutManager,
		java.io.Serializable {
	/** Align components in a column */
	public static final int COLUMN = 0;

	/** Align components in a row */
	public static final int ROW = 1;

	/** Left justify components */
	public static final int LEFT = 0;

	/** Top justify components */
	public static final int TOP = 0;

	/** Center components */
	public static final int CENTER = 1;

	/** Full justify components */
	public static final int FULL = 2;

	/** Bottom justify components */
	public static final int BOTTOM = 3;

	/** Right justify components */
	public static final int RIGHT = 4;

	/** Default gap -- derived classes may override */
	public static final int DEFAULT_GAP = 5;

	/** ROW or COLUMN -- should the components be aligned in a row or column */
	private int orientation;

	/**
	 * LEFT, TOP, CENTER, FULL, BOTTOM, RIGHT -- how should components of
	 * different sizes be aligned
	 */
	private int justification;

	/** Space between components in pixels */
	private int gap;

	/**
	 * Constructs an instance of SingleFiledLayout that will align components in
	 * a column using the default gap and LEFT justification.
	 */

	public SingleFiledLayout() {
		this(COLUMN, LEFT, DEFAULT_GAP);
	}

	/**
	 * Constructs an instance of SingleFiledLayout using the default gap and
	 * LEFT or TOP justification.
	 * @param orientation ROW or COLUMN -- should the components be aligned in a
	 * row or column
	 */

	public SingleFiledLayout(int iorientation) {
		this(iorientation, LEFT, DEFAULT_GAP);
	}

	/**
	 * Constructs an instance of SingleFiledLayout.
	 * @param orientation ROW or COLUMN -- should the components be aligned in a
	 * row or column
	 * @param justification LEFT, TOP, CENTER, FULL, BOTTOM, RIGHT -- how should
	 * components of different sizes be aligned
	 * @param gap space between components in pixels
	 */

	public SingleFiledLayout(int iorientation, int ijustification, int igap) {
		// Validate parameters
		if (iorientation != ROW) {
			iorientation = COLUMN;
		}

		if ((ijustification != CENTER) && (ijustification != FULL)
				&& (ijustification != RIGHT)) {
			ijustification = LEFT;
		}

		if (igap < 0) {
			igap = 0;
		}

		// Copy parameters
		orientation = iorientation;
		justification = ijustification;
		gap = igap;
	}

	// ******************************************************************************
	// ** java.awt.event.LayoutManager methods ***
	// ******************************************************************************

	private static final String ERR_STRING1 = "bad justification";

	/**
	 * To lay out the specified container using this layout. This method
	 * repositions the components in the specified target container. <p> User
	 * code should not have to call this method directly. </p>
	 * @param container container being served by this layout manager
	 */

	public void layoutContainer(Container container) {
		// Use preferred size to get maximum width or height
		Dimension size = container.getSize();

		// Get the container's insets
		Insets inset = container.getInsets();

		// Start at top left of container
		int x = inset.left;
		int y = inset.top;

		// Get the components inside the container
		Component[] component = container.getComponents();

		// Components arranged in a column
		if (orientation == COLUMN) {
			// Add each component
			for (int counter = 0; counter < component.length; counter++) {
				// Use preferred size of component
				Dimension d = component[counter].getPreferredSize();

				// Align component
				switch (justification) {
				case LEFT:
					x = inset.left;
					break;

				case CENTER:
					x = ((size.width - d.width) >> 1) + inset.left
							- inset.right;
					break;

				case FULL:
					x = inset.left;
					d.width = size.width - inset.left - inset.right;
					break;

				case RIGHT:
					x = size.width - d.width - inset.right;
					break;
				default:
					throw new RuntimeException(ERR_STRING1);
				}

				// Set size and location
				component[counter].setBounds(x, y, d.width, d.height);

				// Increment y
				y += d.height + gap;
			}
		} else {
			// Components arranged in a row
			// Add each component
			for (int counter = 0; counter < component.length; counter++) {
				// Use preferred size of component
				Dimension d = component[counter].getPreferredSize();

				// Align component
				switch (justification) {
				case TOP:
					y = inset.top;
					break;

				case CENTER:
					y = ((size.height - d.height) >> 1) + inset.top
							- inset.bottom;
					break;

				case FULL:
					y = inset.top;
					d.height = size.height - inset.top - inset.bottom;
					break;

				case BOTTOM:
					y = size.height - d.height - inset.bottom;
					break;
				default:
					throw new RuntimeException(ERR_STRING1);
				}

				// Set size and location
				component[counter].setBounds(x, y, d.width, d.height);

				// Increment x
				x += d.width + gap;
			}
		}
	}

	/**
	 * Determines the preferred size of the container argument using this
	 * layout. The preferred size is the smallest size that, if used for the
	 * container's size, will ensure that no component is truncated when the
	 * component is it's preferred size.
	 * @param container container being served by this layout manager
	 * @return a dimension indicating the container's preferred size
	 */

	public Dimension preferredLayoutSize(Container container) {
		int totalWidth = 0; // Width of all components
		int totalHeight = 0; // Height of all components

		// Get the components inside the container
		Component[] component = container.getComponents();

		// Components arranged in a column
		if (orientation == COLUMN) {
			// Add each component
			for (int counter = 0; counter < component.length; counter++) {
				Dimension d = component[counter].getPreferredSize();

				if (totalWidth < d.width) {
					totalWidth = d.width;
				}

				totalHeight += d.height + gap;
			}

			// Subtract extra gap
			totalHeight -= gap;
		} else {
			// Components arranged in a row
			// Add each component
			for (int counter = 0; counter < component.length; counter++) {
				Dimension d = component[counter].getPreferredSize();

				totalWidth += d.width + gap;

				if (totalHeight < d.height) {
					totalHeight = d.height;
				}
			}

			// Subtract extra gap
			totalWidth -= gap;
		}

		// Add insets to preferred size
		Insets inset = container.getInsets();
		totalWidth += inset.left + inset.right;
		totalHeight += inset.top + inset.bottom;

		return new Dimension(totalWidth, totalHeight);
	}

	/**
	 * Determines the minimum size of the container argument using this layout.
	 * The minimum size is the smallest size that, if used for the container's
	 * size, will ensure that no component is truncated. The minimum size is the
	 * preferred size.
	 * @param container container being served by this layout manager
	 * @return a dimension indicating the container's minimum size
	 */

	public Dimension minimumLayoutSize(Container container) {
		int totalWidth = 0; // Width of all components
		int totalHeight = 0; // Height of all components

		// Get the components inside the container
		Component[] component = container.getComponents();

		// Components arranged in a column
		if (orientation == COLUMN) {
			// Add each component
			for (int counter = 0; counter < component.length; counter++) {
				Dimension d = component[counter].getMinimumSize();

				if (totalWidth < d.width) {
					totalWidth = d.width;
				}

				totalHeight += d.height + gap;
			}

			// Subtract extra gap
			totalHeight -= gap;
		} else {
			// Components arranged in a row
			// Add each component
			for (int counter = 0; counter < component.length; counter++) {
				Dimension d = component[counter].getMinimumSize();

				totalWidth += d.width + gap;

				if (totalHeight < d.height) {
					totalHeight = d.height;
				}
			}

			// Subtract extra gap
			totalWidth = -gap;
		}

		// Add insets to preferred size
		Insets inset = container.getInsets();
		totalWidth += inset.left + inset.right;
		totalHeight += inset.top + inset.bottom;

		return new Dimension(totalWidth, totalHeight);
	}

	/**
	 * Adds the specified component with the specified name to the layout.
	 * @param name dummy parameter
	 * @param component component to add
	 */

	public void addLayoutComponent(String name, Component component) {
	}

	/**
	 * Removes the specified component with the specified name from the layout.
	 * @param component component being removed
	 */

	public void removeLayoutComponent(Component component) {
	}

}
