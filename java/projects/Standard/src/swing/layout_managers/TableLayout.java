package swing.layout_managers;

import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * TableLayout is a layout manager that arranges components in rows and columns
 * like a spreadsheet. TableLayout allows each row or column to be a different
 * size. A row or column can be given an absolute size in pixels, a percentage
 * of the available space, or it can grow and shrink to fill the remaining space
 * after other rows and columns have been resized. <p> Using spreadsheet
 * terminology, a cell is the intersection of a row and column. Cells have
 * finite, non-negative sizes measured in pixels. The dimensions of a cell
 * depend solely upon the dimensions of its row and column. </p> <p> A component
 * occupies a rectangular group of one or more cells. The component can be
 * aligned in four ways within that cell. </p> <p> A component can be stretched
 * horizontally to fit the cell set (full justification), or it can be placed in
 * the center of the cell. The component could also be left justified or right
 * justified. Similarly, the component can be full, center, top, or bottom
 * justified along the <pre> public static void main (String args[]) { // Create
 * a frame Frame frame = new Frame("Example of TableLayout"); frame.setBounds
 * (100, 100, 300, 300); <spc> // Create a TableLayout for the frame double
 * border = 10; double size[][] = {{border, 0.10, 20, TableLayout.FILL, 20,
 * 0.20, border}, // Columns {border, 0.20, 20, TableLayout.FILL, 20, 0.20,
 * border}}; // Rows <spc> frame.setLayout (new TableLayout(size)); <spc> //
 * Create some buttons String label[] = {"Top", "Bottom", "Left", "Right",
 * "Center", "Overlap"}; Button button[] = new Button[label.length]; <spc> for
 * (int i = 0; i < label.length; i++) button[i] = new Button(label[i]); <spc> //
 * Add buttons frame.add (button[0], "1, 1, 5, 1"); // Top frame.add (button[1],
 * "1, 5, 5, 5"); // Bottom frame.add (button[2], "1, 3"); // Left frame.add
 * (button[3], "5, 3"); // Right frame.add (button[4], "3, 3, c, c"); // Center
 * frame.add (button[5], "3, 3, 3, 5"); // Overlap <spc> // Allow user to close
 * the window to terminate the program frame.addWindowListener (new
 * WindowListener() { public void windowClosing (WindowEvent e) { System.exit
 * (0); } <spc> public void windowOpened (WindowEvent e) {} public void
 * windowClosed (WindowEvent e) {} public void windowIconified (WindowEvent e)
 * {} public void windowDeiconified (WindowEvent e) {} public void
 * windowActivated (WindowEvent e) {} public void windowDeactivated (WindowEvent
 * e) {} } ); <spc> // Show frame frame.show(); } </pre>
 * @author Mark Veltzer <mark@veltzer.net>
 */

@SuppressWarnings("serial")
public class TableLayout implements java.awt.LayoutManager2,
		java.io.Serializable {

	/*
	 * Note: In this file, a cr refers to either a column or a row. cr[C] always
	 * means column and cr[R] always means row. A cr size is either a column
	 * width or a row Height. TableLayout views columns and rows as being
	 * conceptually symmetric. Therefore, much of the code applies to both
	 * columns and rows, and the use of the cr terminology eliminates
	 * redundancy. Also, for ease of reading, z always indicates a parameter
	 * whose value is either C or R.
	 */

	/** Default row/column size */
	private static final double[][] DEFAULT_SIZE = {
			{}, {}
	};

	/** Indicates a column */
	protected static final int C = 0;

	/** Indicates a row */
	protected static final int R = 1;

	/** Used to minimize reflection calls */
	private static boolean checkForComponentOrientationSupport = true;

	/**
	 * Method used to get component orientation while preserving compatability
	 * with earlier versions of java.awt.Container. Necessary for supporting
	 * older JDKs and MicroEdition versions of Java.
	 */
	private static Method methodGetComponentOrientation;

	/** Sizes of crs expressed in absolute and relative terms */
	private double[][] crSpec = {
			null, null
	};

	/** Sizes of crs in pixels */
	private int[][] crSize = {
			null, null
	};

	/**
	 * Offsets of crs in pixels. The left boarder of column n is at
	 * crOffset[C][n] and the right boarder is at cr[C][n + 1] for all columns
	 * including the last one. crOffset[C].length = crSize[C].length + 1
	 */
	private int[][] crOffset = {
			null, null
	};

	/** List of components and their sizes */
	private LinkedList<Entry> list;

	/**
	 * Indicates whether or not the size of the cells are known for the last
	 * known size of the container. If dirty is true or the container has been
	 * resized, the cell sizes must be recalculated using calculateSize.
	 */
	private boolean dirty;

	/** Previous known width of the container */
	private int oldWidth;

	/** Previous known height of the container */
	private int oldHeight;

	/** Horizontal gap between columns */
	private int hGap;

	/** Vertical gap between rows */
	private int vGap;

	// ******************************************************************************
	// ** Constructors ***
	// ******************************************************************************

	/**
	 * Constructs an instance of TableLayout. This TableLayout will have no
	 * columns or rows. This constructor is most useful for bean-oriented
	 * programming and dynamically adding columns and rows.
	 */

	public TableLayout() {
		init(DEFAULT_SIZE[C], DEFAULT_SIZE[R]);
	}

	/**
	 * Constructs an instance of TableLayout.
	 * @param size widths of columns and heights of rows in the format, {{col0,
	 * col1, col2, ..., colN}, {row0, row1, row2, ..., rowM}} If this parameter
	 * is invalid, the TableLayout will have exactly one row and one column.
	 */

	public TableLayout(double[][] size) {
		// Make sure columns and rows and nothing else is specified
		if ((size != null) && (size.length == 2)) {
			init(size[C], size[R]);
		} else {
			throw new IllegalArgumentException(
					"Parameter size should be an array, a[2], where a[0] is the "
							+ "is an array of column widths and a[1] is an array or row "
							+ "heights.");
		}
	}

	/**
	 * Constructs an instance of TableLayout.
	 * @param col widths of columns in the format, {{col0, col1, col2, ...,
	 * colN}
	 * @param row heights of rows in the format, {{row0, row1, row2, ..., rowN}
	 */

	public TableLayout(double[] col, double[] row) {
		init(col, row);
	}

	/**
	 * Initializes the TableLayout for all constructors.
	 * @param col widths of columns in the format, {{col0, col1, col2, ...,
	 * colN}
	 * @param row heights of rows in the format, {{row0, row1, row2, ..., rowN}
	 */

	protected void init(double[] col, double[] row) {
		// Check parameters
		if (col == null) {
			throw new IllegalArgumentException("Parameter col cannot be null");
		}

		if (row == null) {
			throw new IllegalArgumentException("Parameter row cannot be null");
		}

		// Create new rows and columns
		crSpec[C] = new double[col.length];
		crSpec[R] = new double[row.length];

		// Copy rows and columns
		System.arraycopy(col, 0, crSpec[C], 0, crSpec[C].length);
		System.arraycopy(row, 0, crSpec[R], 0, crSpec[R].length);

		// Make sure rows and columns are valid
		for (int counter = 0; counter < crSpec[C].length; counter++) {
			if ((crSpec[C][counter] < 0.0)
					&& (crSpec[C][counter] != TableLayoutConstants.FILL)
					&& (crSpec[C][counter] != TableLayoutConstants.PREFERRED)
					&& (crSpec[C][counter] != TableLayoutConstants.MINIMUM)) {
				crSpec[C][counter] = 0.0;
			}
		}

		for (int counter = 0; counter < crSpec[R].length; counter++) {
			if ((crSpec[R][counter] < 0.0)
					&& (crSpec[R][counter] != TableLayoutConstants.FILL)
					&& (crSpec[R][counter] != TableLayoutConstants.PREFERRED)
					&& (crSpec[R][counter] != TableLayoutConstants.MINIMUM)) {
				crSpec[R][counter] = 0.0;
			}
		}

		// Create an empty list of components
		list = new LinkedList<Entry>();

		// Indicate that the cell sizes are not known
		dirty = true;
	}

	// ******************************************************************************
	// ** Get/Set methods ***
	// ******************************************************************************

	/**
	 * Gets the constraints of a given component.
	 * @param component desired component
	 * @return If the given component is found, the constraints associated with
	 * that component. If the given component is null or is not found, null is
	 * returned.
	 */

	public TableLayoutConstraints getConstraints(Component component) {
		ListIterator<Entry> iterator = list.listIterator(0);

		while (iterator.hasNext()) {
			Entry entry = iterator.next();

			if (entry.component == component) {
				return new TableLayoutConstraints(entry.cr1[C], entry.cr1[R],
						entry.cr2[C], entry.cr2[R], entry.alignment[C],
						entry.alignment[R]);
			}
		}

		return null;
	}

	/**
	 * Sets the constraints of a given component.
	 * @param component desired component. This parameter cannot be null.
	 * @param constraint new set of constraints. This parameter cannot be null.
	 * @return If the given component is found, the constraints associated with
	 * that component. If the given component is null or is not found, null is
	 * returned.
	 */

	public void setConstraints(Component component,
			TableLayoutConstraints constraint) {
		// Check parameters
		if (component == null) {
			throw new IllegalArgumentException(
					"Parameter component cannot be null.");
		} else if (constraint == null) {
			throw new IllegalArgumentException(
					"Parameter constraint cannot be null.");
		}

		// Find and update constraints for the given component
		ListIterator<Entry> iterator = list.listIterator(0);

		while (iterator.hasNext()) {
			Entry entry = iterator.next();

			if (entry.component == component) {
				iterator.set(new Entry(component, constraint));
			}
		}
	}

	/**
	 * Adjusts the number and sizes of rows in this layout. After calling this
	 * method, the caller should request this layout manager to perform the
	 * layout. This can be done with the following code: <pre>
	 * layout.layoutContainer(container); container.repaint(); </pre> or <pre>
	 * window.pack() </pre> If this is not done, the changes in the layout will
	 * not be seen until the container is resized.
	 * @param column widths of each of the columns
	 * @see #getColumn
	 */

	public void setColumn(double[] column) {
		setCr(C, column);
	}

	/**
	 * Adjusts the number and sizes of rows in this layout. After calling this
	 * method, the caller should request this layout manager to perform the
	 * layout. This can be done with the following code: <code>
	 * layout.layoutContainer(container); container.repaint(); </code> or <pre>
	 * window.pack() </pre> If this is not done, the changes in the layout will
	 * not be seen until the container is resized.
	 * @param row heights of each of the rows. This parameter cannot be null.
	 * @see #getRow
	 */

	public void setRow(double[] row) {
		setCr(R, row);
	}

	/**
	 * Sets the sizes of rows or columns for the methods setRow or setColumn.
	 * @param z indicates row or column
	 * @param size new cr size
	 */

	protected void setCr(int z, double[] size) {
		// Copy crs
		crSpec[z] = new double[size.length];
		System.arraycopy(size, 0, crSpec[z], 0, crSpec[z].length);

		// Make sure rows are valid
		for (int counter = 0; counter < crSpec[z].length; counter++) {
			if ((crSpec[z][counter] < 0.0)
					&& (crSpec[z][counter] != TableLayoutConstants.FILL)
					&& (crSpec[z][counter] != TableLayoutConstants.PREFERRED)
					&& (crSpec[z][counter] != TableLayoutConstants.MINIMUM)) {
				crSpec[z][counter] = 0.0;
			}
		}

		// Indicate that the cell sizes are not known
		dirty = true;
	}

	/**
	 * Adjusts the width of a single column in this layout. After calling this
	 * method, the caller should request this layout manager to perform the
	 * layout. This can be done with the following code: <code>
	 * layout.layoutContainer(container); container.repaint(); </code> or <pre>
	 * window.pack() </pre> If this is not done, the changes in the layout will
	 * not be seen until the container is resized.
	 * @param i zero-based index of column to set. If this parameter is not
	 * valid, an ArrayOutOfBoundsException will be thrown.
	 * @param size width of the column. This parameter cannot be null.
	 * @see #getColumn
	 */

	public void setColumn(int i, double size) {
		setCr(C, i, size);
	}

	/**
	 * Adjusts the height of a single row in this layout. After calling this
	 * method, the caller should request this layout manager to perform the
	 * layout. This can be done with the following code: <code>
	 * layout.layoutContainer(container); container.repaint(); </code> or <pre>
	 * window.pack() </pre> If this is not done, the changes in the layout will
	 * not be seen until the container is resized.
	 * @param i zero-based index of row to set. If this parameter is not valid,
	 * an ArrayOutOfBoundsException will be thrown.
	 * @param size height of the row. This parameter cannot be null.
	 * @see #getRow
	 */

	public void setRow(int i, double size) {
		setCr(R, i, size);
	}

	/**
	 * Sets the sizes of rows or columns for the methods setRow or setColumn.
	 * @param z indicates row or column
	 * @param i indicates which cr to resize
	 * @param size new cr size
	 */

	protected void setCr(int z, int i, double size) {
		// Make sure size is valid
		if ((size < 0.0) && (size != TableLayoutConstants.FILL)
				&& (size != TableLayoutConstants.PREFERRED)
				&& (size != TableLayoutConstants.MINIMUM)) {
			size = 0.0;
		}

		// Copy new size
		crSpec[z][i] = size;

		// Indicate that the cell sizes are not known
		dirty = true;
	}

	/**
	 * Gets the sizes of columns in this layout.
	 * @return widths of each of the columns
	 * @see #setColumn
	 */

	public double[] getColumn() {
		// Copy columns
		double[] column = new double[crSpec[C].length];
		System.arraycopy(crSpec[C], 0, column, 0, column.length);

		return column;
	}

	/**
	 * Gets the height of a single row in this layout.
	 * @return height of the requested row
	 * @see #setRow
	 */

	public double[] getRow() {
		// Copy rows
		double[] row = new double[crSpec[R].length];
		System.arraycopy(crSpec[R], 0, row, 0, row.length);

		return row;
	}

	/**
	 * Gets the width of a single column in this layout.
	 * @param i zero-based index of row to get. If this parameter is not valid,
	 * an ArrayOutOfBoundsException will be thrown.
	 * @return width of the requested column
	 * @see #setRow
	 */

	public double getColumn(int i) {
		return crSpec[C][i];
	}

	/**
	 * Gets the sizes of a row in this layout.
	 * @param i zero-based index of row to get. If this parameter is not valid,
	 * an ArrayOutOfBoundsException will be thrown.
	 * @return height of each of the requested row
	 * @see #setRow
	 */

	public double getRow(int i) {
		return crSpec[R][i];
	}

	/**
	 * Gets the number of columns in this layout.
	 * @return the number of columns
	 */

	public int getNumColumn() {
		return crSpec[C].length;
	}

	/**
	 * Gets the number of rows in this layout.
	 * @return the number of rows
	 */

	public int getNumRow() {
		return crSpec[R].length;
	}

	/**
	 * Gets the horizontal gap between colunns.
	 * @return the horizontal gap in pixels
	 */

	public int getHGap() {
		return hGap;
	}

	/**
	 * Gets the vertical gap between rows.
	 * @return the vertical gap in pixels
	 */

	public int getVGap() {
		return vGap;
	}

	/**
	 * Sets the horizontal gap between colunns.
	 * @param hGap the horizontal gap in pixels
	 */

	public void setHGap(int ihGap) {
		if (ihGap >= 0) {
			hGap = ihGap;
		} else {
			throw new IllegalArgumentException(
					"Parameter hGap must be non-negative.");
		}
	}

	/**
	 * Sets the vertical gap between rows.
	 * @param vGap the horizontal gap in pixels
	 */

	public void setVGap(int ivGap) {
		if (ivGap >= 0) {
			vGap = ivGap;
		} else {
			throw new IllegalArgumentException(
					"Parameter vGap must be non-negative.");
		}
	}

	// ******************************************************************************
	// ** Insertion/Deletion methods ***
	// ******************************************************************************

	/**
	 * Inserts a column in this layout. All components to the right of the
	 * insertion point are moved right one column. The container will need to be
	 * laid out after this method returns. See <code>setColumn</code>.
	 * @param i zero-based index at which to insert the column
	 * @param size size of the column to be inserted
	 * @see #setColumn
	 * @see #deleteColumn
	 */

	public void insertColumn(int i, double size) {
		insertCr(C, i, size);
	}

	/**
	 * Inserts a row in this layout. All components below the insertion point
	 * are moved down one row. The container will need to be laid out after this
	 * method returns. See <code>setRow</code>.
	 * @param i zero-based index at which to insert the row
	 * @param size size of the row to be inserted
	 * @see #setRow
	 * @see #deleteRow
	 */

	public void insertRow(int i, double size) {
		insertCr(R, i, size);
	}

	/**
	 * Inserts a cr for the methods insertRow or insertColumn.
	 * @param z indicates row or column
	 * @param i zero-based index at which to insert the cr
	 * @param size size of cr being inserted
	 */

	public void insertCr(int z, int i, double size) {
		// Make sure position is valid
		if ((i < 0) || (i > crSpec[z].length)) {
			throw new IllegalArgumentException("Parameter i is invalid. i = "
					+ i + ". Valid range is [0, " + crSpec[z].length + "].");
		}

		// Make sure row size is valid
		if ((size < 0.0) && (size != TableLayoutConstants.FILL)
				&& (size != TableLayoutConstants.PREFERRED)
				&& (size != TableLayoutConstants.MINIMUM)) {
			size = 0.0;
		}

		// Copy crs
		double[] cr = new double[crSpec[z].length + 1];
		System.arraycopy(crSpec[z], 0, cr, 0, i);
		System.arraycopy(crSpec[z], i, cr, i + 1, crSpec[z].length - i);

		// Insert cr
		cr[i] = size;
		crSpec[z] = cr;

		// Move all components that are below the new cr
		ListIterator<Entry> iterator = list.listIterator(0);

		while (iterator.hasNext()) {
			// Get next entry
			Entry entry = iterator.next();

			// Is the first cr below the new cr
			if (entry.cr1[z] >= i) {
				// Move first cr
				entry.cr1[z]++;
			}

			// Is the second cr below the new cr
			if (entry.cr2[z] >= i) {
				// Move second cr
				entry.cr2[z]++;
			}
		}

		// Indicate that the cell sizes are not known
		dirty = true;
	}

	/**
	 * Deletes a column in this layout. All components to the right of the
	 * deletion point are moved left one column. The container will need to be
	 * laid out after this method returns. See <code>setColumn</code>.
	 * @param i zero-based index of column to delete
	 * @see #setColumn
	 * @see #deleteColumn
	 */

	public void deleteColumn(int i) {
		deleteCr(C, i);
	}

	/**
	 * Deletes a row in this layout. All components below the deletion point are
	 * moved up one row. The container will need to be laid out after this
	 * method returns. See <code>setRow</code>. There must be at least two rows
	 * in order to delete a row.
	 * @param i zero-based index of row to delete
	 * @see #setRow
	 * @see #deleteRow
	 */

	public void deleteRow(int i) {
		deleteCr(R, i);
	}

	/**
	 * Deletes a cr for the methods deleteRow or deleteColumn.
	 * @param z indicates row or column
	 * @param i zero-based index of cr to delete
	 */

	protected void deleteCr(int z, int i) {
		// Make sure position is valid
		if ((i < 0) || (i >= crSpec[z].length)) {
			throw new IllegalArgumentException("Parameter i is invalid. i = "
					+ i + ". Valid range is [0, " + (crSpec[z].length - 1)
					+ "].");
		}

		// Copy rows
		double[] cr = new double[crSpec[z].length - 1];
		System.arraycopy(crSpec[z], 0, cr, 0, i);
		System.arraycopy(crSpec[z], i + 1, cr, i, crSpec[z].length - i - 1);

		// Delete row
		crSpec[z] = cr;

		// Move all components that are to below the row deleted
		ListIterator<Entry> iterator = list.listIterator(0);

		while (iterator.hasNext()) {
			// Get next entry
			Entry entry = iterator.next();

			// Is the first row below the new row
			if (entry.cr1[z] > i) {
				// Move first row
				entry.cr1[z]--;
			}

			// Is the second row below the new row
			if (entry.cr2[z] > i) {
				// Move second row
				entry.cr2[z]--;
			}
		}

		// Indicate that the cell sizes are not known
		dirty = true;
	}

	// ******************************************************************************
	// ** Misc methods ***
	// ******************************************************************************

	/**
	 * Converts this TableLayout to a string.
	 * @return a string representing the columns and row sizes in the form
	 * "{{col0, col1, col2, ..., colN}, {row0, row1, row2, ..., rowM}}"
	 */

	public String toString() {
		int counter;

		String value = "TableLayout {{";

		if (crSpec[C].length > 0) {
			for (counter = 0; counter < crSpec[C].length - 1; counter++) {
				value += crSpec[C][counter] + ", ";
			}

			value += crSpec[C][crSpec[C].length - 1] + "}, {";
		} else {
			value += "}, {";
		}

		if (crSpec[R].length > 0) {
			for (counter = 0; counter < crSpec[R].length - 1; counter++) {
				value += crSpec[R][counter] + ", ";
			}

			value += crSpec[R][crSpec[R].length - 1] + "}}";
		} else {
			value += "}}";
		}

		return value;
	}

	/**
	 * Determines whether or not there are any components with invalid
	 * constraints. An invalid constraint is one that references a non-existing
	 * row or column. For example, on a table with five rows, row -1 and row 5
	 * are both invalid. Valid rows are 0 through 4, inclusively.
	 * @return a list of TableLayout.Entry instances refering to the invalid
	 * constraints and corresponding components
	 * @see #getOverlappingEntry
	 */

	public java.util.List<Object> getInvalidEntry() {
		LinkedList<Object> listInvalid = new LinkedList<Object>();

		try {
			ListIterator<Entry> iterator = list.listIterator(0);

			while (iterator.hasNext()) {
				Entry entry = iterator.next();

				if ((entry.cr1[R] < 0) || (entry.cr1[C] < 0)
						|| (entry.cr2[R] >= crSpec[R].length)
						|| (entry.cr2[C] >= crSpec[C].length)) {
					listInvalid.add(entry.copy());
				}
			}
		} catch (CloneNotSupportedException error) {
			throw new RuntimeException("Unexpected CloneNotSupportedException");
		}

		return listInvalid;
	}

	/**
	 * Gets a list of overlapping components and their constraints. Two
	 * components overlap if they cover at least one common cell.
	 * @return a list of zero or more TableLayout.Entry instances
	 * @see #getInvalidEntry
	 */

	public java.util.List<Object> getOverlappingEntry() {
		LinkedList<Object> listOverlapping = new LinkedList<Object>();

		try {
			// Count contraints
			int numEntry = list.size();

			// If there are no components, they can't be overlapping
			if (numEntry == 0) {
				return listOverlapping;
			}

			// Put entries in an array
			Entry[] entry = list.toArray(new Entry[numEntry]);

			// Check all components
			for (int knowUnique = 1; knowUnique < numEntry; knowUnique++) {
				for (int checking = knowUnique - 1; checking >= 0; checking--) {
					if (((entry[checking].cr1[C] >= entry[knowUnique].cr1[C])
							&& (entry[checking].cr1[C] <= entry[knowUnique].cr2[C])
							&& (entry[checking].cr1[R] >= entry[knowUnique].cr1[R]) && (entry[checking].cr1[R] <= entry[knowUnique].cr2[R]))
							|| ((entry[checking].cr2[C] >= entry[knowUnique].cr1[C])
									&& (entry[checking].cr2[C] <= entry[knowUnique].cr2[C])
									&& (entry[checking].cr2[R] >= entry[knowUnique].cr1[R]) && (entry[checking].cr2[R] <= entry[knowUnique].cr2[R]))) {
						listOverlapping.add(entry[checking].copy());
					}
				}
			}
		} catch (CloneNotSupportedException error) {
			throw new RuntimeException("Unexpected CloneNotSupportedException");
		}

		return listOverlapping;
	}

	// ******************************************************************************
	// ** Calculation methods ***
	// ******************************************************************************

	/**
	 * Calculates the sizes of the rows and columns based on the absolute and
	 * relative sizes specified in <code>crSpec[R]</code> and
	 * <code>crSpec[C]</code> and the size of the container. The result is
	 * stored in <code>crSize[R]</code> and <code>crSize[C]</code>.
	 * @param container container using this TableLayout
	 */

	protected void calculateSize(Container container) {
		// Get the container's insets
		Insets inset = container.getInsets();

		// Get the size of the container's available space
		Dimension d = container.getSize();
		int availableWidth = d.width - inset.left - inset.right;
		int availableHeight = d.height - inset.top - inset.bottom;

		// Compensate for horiztonal and vertical gaps
		if (crSpec[C].length > 0) {
			availableWidth -= hGap * (crSpec[C].length - 1);
		}

		if (crSpec[R].length > 0) {
			availableHeight -= vGap * (crSpec[R].length - 1);
		}

		// Create array to hold actual sizes in pixels
		crSize[C] = new int[crSpec[C].length];
		crSize[R] = new int[crSpec[R].length];

		// Assign absolute sizes (must be done before assignPrefMinSize)
		availableWidth = assignAbsoluteSize(C, availableWidth);
		availableHeight = assignAbsoluteSize(R, availableHeight);

		// Assign preferred and minimum sizes (must be done after
		// assignAbsoluteSize)
		availableWidth = assignPrefMinSize(C, availableWidth,
				TableLayoutConstants.MINIMUM);
		availableWidth = assignPrefMinSize(C, availableWidth,
				TableLayoutConstants.PREFERRED);
		availableHeight = assignPrefMinSize(R, availableHeight,
				TableLayoutConstants.MINIMUM);
		availableHeight = assignPrefMinSize(R, availableHeight,
				TableLayoutConstants.PREFERRED);

		// Assign relative sizes
		availableWidth = assignRelativeSize(C, availableWidth);
		availableHeight = assignRelativeSize(R, availableHeight);

		// Assign fill sizes
		assignFillSize(C, availableWidth);
		assignFillSize(R, availableHeight);

		// Calculate cr offsets for effeciency
		calculateOffset(C, inset);
		calculateOffset(R, inset);

		// Indicate that the size of the cells are known for the container's
		// current size
		dirty = false;
		oldWidth = d.width;
		oldHeight = d.height;
	}

	/**
	 * Assigns absolute sizes.
	 * @param z indicates row or column
	 * @param availableSize amount of space available in the container
	 * @return the amount of space available after absolute crs have been
	 * assigned sizes
	 */

	protected int assignAbsoluteSize(int z, int availableSize) {
		int numCr = crSpec[z].length;

		for (int counter = 0; counter < numCr; counter++) {
			if ((crSpec[z][counter] >= 1.0) || (crSpec[z][counter] == 0.0)) {
				crSize[z][counter] = (int) (crSpec[z][counter] + 0.5);
				availableSize -= crSize[z][counter];
			}
		}

		return availableSize;
	}

	/**
	 * Assigns relative sizes.
	 * @param z indicates row or column
	 * @param availableSize amount of space available in the container
	 * @return the amount of space available after relative crs have been
	 * assigned sizes
	 */

	protected int assignRelativeSize(int z, int availableSize) {
		int relativeSize = (availableSize < 0) ? 0 : availableSize;
		int numCr = crSpec[z].length;

		for (int counter = 0; counter < numCr; counter++) {
			if ((crSpec[z][counter] > 0.0) && (crSpec[z][counter] < 1.0)) {
				crSize[z][counter] = (int) (crSpec[z][counter] * relativeSize + 0.5);

				availableSize -= crSize[z][counter];
			}
		}

		return availableSize;
	}

	/**
	 * Assigns FILL sizes.
	 * @param z indicates row or column
	 * @param availableSize amount of space available in the container
	 * @return the amount of space available after fill crs have been assigned
	 * sizes
	 */

	protected void assignFillSize(int z, int availableSize) {
		// Skip if there is no more space to allocate
		if (availableSize <= 0) {
			return;
		}

		// Count the number of "fill" cells
		int numFillSize = 0;
		int numCr = crSpec[z].length;

		for (int counter = 0; counter < numCr; counter++) {
			if (crSpec[z][counter] == TableLayoutConstants.FILL) {
				numFillSize++;
			}
		}

		// If numFillSize is zero, the if statement below will always evaluate
		// to
		// false and the division will not occur.

		// If there are more than one "fill" cell, slack may occur due to
		// rounding
		// errors
		int slackSize = availableSize;

		// Assign "fill" cells equal amounts of the remaining space
		for (int counter = 0; counter < numCr; counter++) {
			if (crSpec[z][counter] == TableLayoutConstants.FILL) {
				crSize[z][counter] = availableSize / numFillSize;
				slackSize -= crSize[z][counter];
			}
		}

		// Assign one pixel of slack to each FILL cr, starting at the last one,
		// until all slack has been consumed
		for (int counter = numCr - 1; (counter >= 0) && (slackSize > 0); counter--) {
			if (crSpec[z][counter] == TableLayoutConstants.FILL) {
				crSize[z][counter]++;
				slackSize--;
			}
		}
	}

	/**
	 * Calculates the offset of each cr.
	 * @param z indicates row or column
	 */

	protected void calculateOffset(int z, Insets inset) {
		int numCr = crSpec[z].length;

		crOffset[z] = new int[numCr + 1];
		crOffset[z][0] = (z == C) ? inset.left : inset.top;

		for (int counter = 0; counter < numCr; counter++) {
			crOffset[z][counter + 1] = crOffset[z][counter]
					+ crSize[z][counter];
		}
	}

	/**
	 * Assigned widths to preferred and minimum size columns and rows. This
	 * reduces the available width and height. Minimum widths/heights must be
	 * calculated first because they affect preferred widths/heights, but not
	 * vice versa. The end result is that any component contained wholly or
	 * partly in a column/row of minimum/preferred width will get at least its
	 * minimum/preferred width, respectively.
	 * @param z indicates row or column
	 * @param availableSize amount of space available in the container
	 * @param typeOfSize indicates preferred or minimum
	 * @return the amount of space available after absolute crs have been
	 * assigned sizes
	 */

	protected int assignPrefMinSize(int z, int availableSize, double typeOfSize) {
		// Get variables referring to columns or rows (crs)
		int numCr = crSpec[z].length;

		// Address every cr
		for (int counter = 0; counter < numCr; counter++) {
			// Is the current cr a preferred/minimum (based on typeOfSize) size
			if (crSpec[z][counter] == typeOfSize) {
				// Assume a maximum width of zero
				int maxSize = 0;

				// Find maximum preferred/min width of all components completely
				// or partially contained within this cr
				ListIterator<Entry> iterator = list.listIterator(0);

				nextComponent: while (iterator.hasNext()) {
					Entry entry = iterator.next();

					// Skip invalid entries
					if ((entry.cr1[z] < 0) || (entry.cr2[z] >= numCr)) {
						continue nextComponent;
					}

					// Find the maximum desired size of this cr based on all crs
					// the current component occupies
					if ((entry.cr1[z] <= counter) && (entry.cr2[z] >= counter)) {
						// Setup size and number of adjustable crs
						Dimension p = (typeOfSize == TableLayoutConstants.PREFERRED) ? entry.component
								.getPreferredSize() : entry.component
								.getMinimumSize();

						int size = (p == null) ? 0 : ((z == C) ? p.width
								: p.height);
						int numAdjustable = 0;

						// Calculate for preferred size
						if (typeOfSize == TableLayoutConstants.PREFERRED) {
							// Consider all crs this component occupies
							for (int entryCr = entry.cr1[z]; entryCr <= entry.cr2[z]; entryCr++) {
								// Subtract absolute, relative, and minumum cr
								// sizes, which have already been calculated
								if ((crSpec[z][entryCr] >= 0.0)
										|| (crSpec[z][entryCr] == TableLayoutConstants.MINIMUM)) {
									size -= crSize[z][entryCr];
								} else if (crSpec[z][entryCr] == TableLayoutConstants.PREFERRED) {
									// Count preferred/min width columns
									numAdjustable++;
								} else if (crSpec[z][entryCr] == TableLayoutConstants.FILL) {
									// Skip any component that occupies a fill
									// cr
									// because the fill should fulfill the size
									// requirements
									continue nextComponent;
								}
							}
						} else {
							// Calculate for minimum size
							// Consider all crs this component occupies
							for (int entryCr = entry.cr1[z]; entryCr <= entry.cr2[z]; entryCr++) {
								// Subtract absolute and relative cr sizes,
								// which
								// have already been calculated
								if (crSpec[z][entryCr] >= 0.0) {
									size -= crSize[z][entryCr];
								} else if ((crSpec[z][entryCr] == TableLayoutConstants.PREFERRED)
										|| (crSpec[z][entryCr] == TableLayoutConstants.MINIMUM)) {
									// Count preferred/min width columns
									numAdjustable++;
								} else if (crSpec[z][entryCr] == TableLayoutConstants.FILL) {
									// Skip any component that occupies a fill
									// cr
									// because the fill should fulfill the size
									// requirements
									continue nextComponent;
								}
							}
						}

						// Divide the size evenly among the adjustable crs
						size = (int) Math.ceil(size / (double) numAdjustable);

						// Take the maximumn size
						if (maxSize < size) {
							maxSize = size;
						}
					}
				}

				// Assign preferred size
				crSize[z][counter] = maxSize;

				// Reduce available size
				availableSize -= maxSize;
			}
		}

		return availableSize;
	}

	// ******************************************************************************
	// ** java.awt.event.LayoutManager methods ***
	// ******************************************************************************

	/**
	 * To lay out the specified container using this layout. This method
	 * reshapes the components in the specified target container in order to
	 * satisfy the constraints of all components. <p> User code should not have
	 * to call this method directly. </p>
	 * @param container container being served by this layout manager
	 */

	public void layoutContainer(Container container) {
		// Calculate sizes if container has changed size or components were
		// added
		Dimension d = container.getSize();

		if (dirty || (d.width != oldWidth) || (d.height != oldHeight)) {
			calculateSize(container);
		}

		// Get components
		Component[] component = container.getComponents();

		// Layout components
		for (int counter = 0; counter < component.length; counter++) {
			try {
				// Get the entry for the next component
				ListIterator<Entry> iterator = list.listIterator(0);
				Entry entry = null;

				while (iterator.hasNext()) {
					entry = iterator.next();

					if (entry.component == component[counter]) {
						break;
					} else {
						entry = null;
					}
				}

				// Skip any components that have not been place in a specific
				// cell,
				// setting the skip component's bounds to zero
				if (entry == null) {
					component[counter].setBounds(0, 0, 0, 0);
					continue;
				}

				// The following block of code has been optimized so that the
				// preferred size of the component is only obtained if it is
				// needed. There are components in which the getPreferredSize
				// method is extremely expensive, such as data driven controls
				// with a large amount of data.

				// Get the preferred size of the component
				int preferredWidth = 0;
				int preferredHeight = 0;

				if ((entry.alignment[C] != TableLayoutConstants.FULL)
						|| (entry.alignment[R] != TableLayoutConstants.FULL)) {
					Dimension preferredSize = component[counter]
							.getPreferredSize();

					preferredWidth = preferredSize.width;
					preferredHeight = preferredSize.height;
				}

				// Calculate the coordinates and size of the component
				int[] value = calculateSizeAndOffset(entry, preferredWidth,
						true);
				int x = value[0];
				int w = value[1];
				value = calculateSizeAndOffset(entry, preferredHeight, false);
				int y = value[0];
				int h = value[1];

				// Compensate for component orientation.
				ComponentOrientation co = getComponentOrientation(container);

				if (!co.isLeftToRight()) {
					x = d.width - x - w;
				}

				// Move and resize component
				component[counter].setBounds(x, y, w, h);
			} catch (Exception error) {
				// If any error occurs, set the bounds of this component to zero
				// and continue
				component[counter].setBounds(0, 0, 0, 0);
				continue;
			}
		}
	}

	/**
	 * Gets the container's component orientation. If a JDK that does not
	 * support component orientation is being used, then null is returned.
	 * @param container Container whose orientation is being queried
	 * @return the container's orientation or null if no orientation is
	 * supported
	 */

	protected ComponentOrientation getComponentOrientation(Container container) {
		// This method is implemented to only get the class and method objects
		// once so as to reduce expensive reflection operations. If the
		// reflection
		// fails, then component orientation is not supported.

		ComponentOrientation co = null;

		try {
			if (checkForComponentOrientationSupport) {
				methodGetComponentOrientation = Class.forName(
						"java.awt.Container").getMethod(
						"getComponentOrientation", new Class[0]);

				checkForComponentOrientationSupport = false;
			}

			if (methodGetComponentOrientation != null) {
				co = (ComponentOrientation) methodGetComponentOrientation
						.invoke(container, new Object[0]);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return co;
	}

	/**
	 * Calculates the vertical/horizontal offset and size of a component.
	 * @param entry entry containing component and contraints
	 * @param preferredSize previously calculated preferred width/height of
	 * component
	 * @param isColumn if true, this method is being called to calculate the
	 * offset/size of a column. if false,... of a row.
	 * @return an array, a, of two integers such that a[0] is the offset and
	 * a[1] is the size
	 */

	protected int[] calculateSizeAndOffset(Entry entry, int preferredSize,
			boolean isColumn) {
		// Get references to cr properties
		int[] icrOffset = isColumn ? crOffset[C] : crOffset[R];
		int entryAlignment = isColumn ? entry.alignment[C] : entry.alignment[R];

		// Determine cell set size
		int cellSetSize = isColumn ? icrOffset[entry.cr2[C] + 1]
				- icrOffset[entry.cr1[C]] : icrOffset[entry.cr2[R] + 1]
				- icrOffset[entry.cr1[R]];

		// Determine the size of the component
		int size;

		if ((entryAlignment == TableLayoutConstants.FULL)
				|| (cellSetSize < preferredSize)) {
			size = cellSetSize;
		} else {
			size = preferredSize;
		}

		// Since the component orientation is adjusted for in the
		// layoutContainer
		// method, we can treat leading justification as left justification and
		// trailing justification as right justification.
		if (isColumn && (entryAlignment == TableLayoutConstants.LEADING)) {
			entryAlignment = TableLayoutConstants.LEFT;
		}

		if (isColumn && (entryAlignment == TableLayoutConstants.TRAILING)) {
			entryAlignment = TableLayoutConstants.RIGHT;
		}

		// Determine offset
		int offset;

		switch (entryAlignment) {
		case TableLayoutConstants.LEFT: // Align left/top side along left edge
										// of cell
			offset = icrOffset[isColumn ? entry.cr1[C] : entry.cr1[R]];
			break;

		case TableLayoutConstants.RIGHT: // Align right/bottom side along right
											// edge of cell
			offset = icrOffset[(isColumn ? entry.cr2[C] : entry.cr2[R]) + 1]
					- size;
			break;

		case TableLayoutConstants.CENTER: // Center justify component
			offset = icrOffset[isColumn ? entry.cr1[C] : entry.cr1[R]]
					+ ((cellSetSize - size) >> 1);
			break;

		case TableLayoutConstants.FULL: // Align left/top side along left/top
										// edge of cell
			offset = icrOffset[isColumn ? entry.cr1[C] : entry.cr1[R]];
			break;

		default: // This is a never should happen case, but just in case
			offset = 0;
		}

		// Compensate for gaps
		if (isColumn) {
			offset += hGap * entry.cr1[C];
			size += hGap * (entry.cr2[C] - entry.cr1[C]);
		} else {
			offset += vGap * entry.cr1[R];
			size += vGap * (entry.cr2[R] - entry.cr1[R]);
		}

		// Package return values
		int[] value = {
				offset, size
		};
		return value;
	}

	/**
	 * Determines the preferred size of the container argument using this
	 * layout. The preferred size is the smallest size that, if used for the
	 * container's size, will ensure that all components are at least as large
	 * as their preferred size. This method cannot guarantee that all components
	 * will be their preferred size. For example, if component A and component B
	 * are each allocate half of the container's width and component A wants to
	 * be 10 pixels wide while component B wants to be 100 pixels wide, they
	 * cannot both be accommodated. Since in general components rather be larger
	 * than their preferred size instead of smaller, component B's request will
	 * be fulfilled. The preferred size of the container would be 200 pixels.
	 * @param container container being served by this layout manager
	 * @return a dimension indicating the container's preferred size
	 */

	public Dimension preferredLayoutSize(Container container) {
		return calculateLayoutSize(container, TableLayoutConstants.PREFERRED);
	}

	/**
	 * Determines the minimum size of the container argument using this layout.
	 * The minimum size is the smallest size that, if used for the container's
	 * size, will ensure that all components are at least as large as their
	 * minimum size. This method cannot guarantee that all components will be
	 * their minimum size. For example, if component A and component B are each
	 * allocate half of the container's width and component A wants to be 10
	 * pixels wide while component B wants to be 100 pixels wide, they cannot
	 * both be accommodated. Since in general components rather be larger than
	 * their minimum size instead of smaller, component B's request will be
	 * fulfilled. The minimum size of the container would be 200 pixels.
	 * @param container container being served by this layout manager
	 * @return a dimension indicating the container's minimum size
	 */

	public Dimension minimumLayoutSize(Container container) {
		return calculateLayoutSize(container, TableLayoutConstants.MINIMUM);
	}

	/**
	 * Calculates the preferred or minimum size for the methods
	 * preferredLayoutSize and minimumLayoutSize.
	 * @param container container whose size is being calculated
	 * @param typeOfSize indicates preferred or minimum
	 * @return a dimension indicating the container's preferred or minimum size
	 */

	protected Dimension calculateLayoutSize(Container container,
			double typeOfSize) {
		// Get preferred/minimum sizes
		Entry[] entryList = list.toArray(new Entry[list.size()]);
		int numEntry = entryList.length;
		Dimension[] prefMinSize = new Dimension[numEntry];

		for (int i = 0; i < numEntry; i++) {
			prefMinSize[i] = (typeOfSize == TableLayoutConstants.PREFERRED) ? entryList[i].component
					.getPreferredSize() : entryList[i].component
					.getMinimumSize();
		}

		// Calculate sizes
		int width = calculateLayoutSize(container, C, typeOfSize, entryList,
				prefMinSize);

		int height = calculateLayoutSize(container, R, typeOfSize, entryList,
				prefMinSize);

		// Compensate for container's insets
		Insets inset = container.getInsets();
		width += inset.left + inset.right;
		height += inset.top + inset.bottom;

		return new Dimension(width, height);
	}

	/**
	 * Calculates the preferred or minimum size for the method
	 * calculateLayoutSize(Container container, double typeOfSize). This method
	 * is passed the preferred/minimum sizes of the components so that the
	 * potentially expensive methods getPreferredSize()/getMinimumSize() are not
	 * called twice for the same component.
	 * @param container container whose size is being calculated
	 * @param typeOfSize indicates preferred or minimum
	 * @param entryList list of Entry objects
	 * @param prefMinSize list of preferred or minimum sizes
	 * @return a dimension indicating the container's preferred or minimum size
	 */

	protected int calculateLayoutSize(Container container, int z,
			double typeOfSize, Entry[] entryList, Dimension[] prefMinSize) {
		Dimension size; // Preferred/minimum size of current component
		int scaledSize = 0; // Preferred/minimum size of scaled components
		int temp; // Temporary variable used to compare sizes
		int counter; // Counting variable

		// Get number of crs
		int numCr = crSpec[z].length;

		// Determine percentage of space allocated to fill components. This is
		// one minus the sum of all scalable components.
		double fillSizeRatio = 1.0;
		int numFillSize = 0;

		for (counter = 0; counter < numCr; counter++) {
			if ((crSpec[z][counter] > 0.0) && (crSpec[z][counter] < 1.0)) {
				fillSizeRatio -= crSpec[z][counter];
			} else if (crSpec[z][counter] == TableLayoutConstants.FILL) {
				numFillSize++;
			}
		}

		// Adjust fill ratios to reflect number of fill rows/columns
		if (numFillSize > 1) {
			fillSizeRatio /= numFillSize;
		}

		// Cap fill ratio bottoms to 0.0
		if (fillSizeRatio < 0.0) {
			fillSizeRatio = 0.0;
		}

		// Create array to hold actual sizes in pixels
		crSize[z] = new int[numCr];

		// Assign absolute sizes (must be done before assignPrefMinSize)
		// This is done to calculate absolute cr sizes
		assignAbsoluteSize(z, 0);

		// Assign preferred and minimum sizes (must be done after
		// assignAbsoluteSize)
		// This is done to calculate preferred/minimum cr sizes
		assignPrefMinSize(z, 0, typeOfSize);

		int[] crPrefMin = new int[numCr];

		for (counter = 0; counter < numCr; counter++) {
			if ((crSpec[z][counter] == TableLayoutConstants.PREFERRED)
					|| (crSpec[z][counter] == TableLayoutConstants.MINIMUM)) {
				crPrefMin[counter] = crSize[z][counter];
			}
		}

		// Find maximum preferred/minimum size of all scaled components
		int numColumn = crSpec[C].length;
		int numRow = crSpec[R].length;
		int numEntry = entryList.length;

		for (int entryCounter = 0; entryCounter < numEntry; entryCounter++) {
			// Get next entry
			Entry entry = entryList[entryCounter];

			// Make sure entry is in valid rows and columns
			if ((entry.cr1[C] < 0) || (entry.cr1[C] >= numColumn)
					|| (entry.cr2[C] >= numColumn) || (entry.cr1[R] < 0)
					|| (entry.cr1[R] >= numRow) || (entry.cr2[R] >= numRow)) {
				// Skip the bad component
				continue;
			}

			// Get preferred/minimum size of current component
			size = prefMinSize[entryCounter];

			// ----------------------------------------------------------------------

			// Calculate portion of component that is not absolutely sized
			int scalableSize = (z == C) ? size.width : size.height;

			for (counter = entry.cr1[z]; counter <= entry.cr2[z]; counter++) {
				if (crSpec[z][counter] >= 1.0) {
					scalableSize -= crSpec[z][counter];
				} else if ((crSpec[z][counter] == TableLayoutConstants.PREFERRED)
						|| (crSpec[z][counter] == TableLayoutConstants.MINIMUM)) {
					scalableSize -= crPrefMin[counter];
				}
			}

			// ----------------------------------------------------------------------

			// Determine total percentage of scalable space that the component
			// occupies by adding the relative columns and the fill columns
			double relativeSize = 0.0;

			for (counter = entry.cr1[z]; counter <= entry.cr2[z]; counter++) {
				// Cr is scaled
				if ((crSpec[z][counter] > 0.0) && (crSpec[z][counter] < 1.0)) {
					// Add scaled size to relativeWidth
					relativeSize += crSpec[z][counter];
				} else if ((crSpec[z][counter] == TableLayoutConstants.FILL)
						&& (fillSizeRatio != 0.0)) {
					// Cr is fill
					// Add fill size to relativeWidth
					relativeSize += fillSizeRatio;
				}
			}

			// Determine the total scaled size as estimated by this component
			if (relativeSize == 0) {
				temp = 0;
			} else {
				temp = (int) (scalableSize / relativeSize + 0.5);
			}

			// ----------------------------------------------------------------------

			// If the container needs to be bigger, make it so
			if (scaledSize < temp) {
				scaledSize = temp;
			}
		}

		// totalSize is the scaledSize plus the sum of all absolute sizes and
		// all
		// preferred sizes
		int totalSize = scaledSize;

		for (counter = 0; counter < numCr; counter++) {
			// Is the current cr an absolute size
			if (crSpec[z][counter] >= 1.0) {
				totalSize += (int) (crSpec[z][counter] + 0.5);
			} else if ((crSpec[z][counter] == TableLayoutConstants.PREFERRED)
					|| (crSpec[z][counter] == TableLayoutConstants.MINIMUM)) {
				// Is the current cr a preferred/minimum size
				// Add preferred/minimum width
				totalSize += crPrefMin[counter];
			}
		}

		// Compensate for horizontal and vertical gap
		if (numCr > 0) {
			totalSize += ((z == C) ? hGap : vGap) * (numCr - 1);
		}

		return totalSize;
	}

	/**
	 * Adds the specified component with the specified name to the layout.
	 * @param name indicates entry's position and anchor
	 * @param component component to add
	 */

	public void addLayoutComponent(String name, Component component) {
		addLayoutComponent(component, name);
	}

	// ******************************************************************************
	// ** java.awt.event.LayoutManager2 methods ***
	// ******************************************************************************

	/**
	 * Adds the specified component with the specified name to the layout.
	 * @param component component to add
	 * @param constraint indicates entry's position and alignment
	 */

	public void addLayoutComponent(Component component, Object constraint) {
		if (constraint instanceof String) {
			// Create an entry to associate component with its constraints
			constraint = new TableLayoutConstraints((String) constraint);

			// Add component and constraints to the list
			list.add(new Entry(component, (TableLayoutConstraints) constraint));

			// Indicate that the cell sizes are not known
			dirty = true;
		} else if (constraint instanceof TableLayoutConstraints) {
			// Add component and constraints to the list
			list.add(new Entry(component, (TableLayoutConstraints) constraint));

			// Indicate that the cell sizes are not known
			dirty = true;
		} else if (constraint == null) {
			throw new IllegalArgumentException(
					"No constraint for the component");
		} else {
			throw new IllegalArgumentException(
					"Cannot accept a constraint of class "
							+ constraint.getClass());
		}
	}

	/**
	 * Removes the specified component from the layout.
	 * @param component component being removed
	 */

	public void removeLayoutComponent(Component component) {
		// Remove the component
		ListIterator<Entry> iterator = list.listIterator(0);

		while (iterator.hasNext()) {
			Entry entry = iterator.next();

			if (entry.component == component) {
				iterator.remove();
			}
		}

		// Indicate that the cell sizes are not known since
		dirty = true;
	}

	/**
	 * Returns the maximum dimensions for this layout given the components in
	 * the specified target container.
	 * @param target the component which needs to be laid out
	 * @return unconditionally, a Dimension of Integer.MAX_VALUE by
	 * Integer.MAX_VALUE since TableLayout does not limit the maximum size of a
	 * container
	 */

	public Dimension maximumLayoutSize(Container target) {
		return new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
	}

	/**
	 * Returns the alignment along the x axis. This specifies how the component
	 * would like to be aligned relative to other components. The value should
	 * be a number between 0 and 1 where 0 represents alignment along the
	 * origin, 1 is aligned the furthest away from the origin, 0.5 is centered,
	 * etc.
	 * @return unconditionally, 0.5
	 */

	public float getLayoutAlignmentX(Container parent) {
		return 0.5f;
	}

	/**
	 * Returns the alignment along the y axis. This specifies how the component
	 * would like to be aligned relative to other components. The value should
	 * be a number between 0 and 1 where 0 represents alignment along the
	 * origin, 1 is aligned the furthest away from the origin, 0.5 is centered,
	 * etc.
	 * @return unconditionally, 0.5
	 */

	public float getLayoutAlignmentY(Container parent) {
		return 0.5f;
	}

	/**
	 * Invalidates the layout, indicating that if the layout manager has cached
	 * information it should be discarded.
	 */

	public void invalidateLayout(Container target) {
		dirty = true;
	}

	// ******************************************************************************
	// *** Inner Class ***
	// ******************************************************************************

	// The following inner class is used to bind components to their constraints
	public static class Entry implements Cloneable {
		/** Component bound by the constraints */
		private Component component;

		/** Cell in which the upper-left corner of the component lies */
		private int[] cr1;

		/** Cell in which the lower-right corner of the component lies */
		private int[] cr2;

		/** Horizontal and vertical alignment */
		private int[] alignment;

		/**
		 * Constructs an Entry that binds a component to a set of constraints.
		 * @param component component being bound
		 * @param constraint constraints being applied
		 */

		public Entry(Component icomponent, TableLayoutConstraints constraint) {
			int[] icr1 = {
					constraint.getCol1(), constraint.getRow1()
			};
			int[] icr2 = {
					constraint.getCol2(), constraint.getRow2()
			};
			int[] ialignment = {
					constraint.gethAlign(), constraint.getvAlign()
			};

			cr1 = icr1;
			cr2 = icr2;
			alignment = ialignment;
			component = icomponent;
		}

		/**
		 * Copies this Entry.
		 */

		public Object copy() throws CloneNotSupportedException {
			return clone();
		}

		/**
		 * Gets the string representation of this Entry.
		 * @return a string in the form
		 * "(col1, row1, col2, row2, vAlign, hAlign) component"
		 */

		public String toString() {
			TableLayoutConstraints c = new TableLayoutConstraints(cr1[C],
					cr1[R], cr2[C], cr2[R], alignment[C], alignment[R]);

			return "(" + c + ") " + component;
		}
	}

}
