package swing.layout_managers;

import java.awt.Component;
import java.awt.Insets;
import java.awt.Rectangle;
import java.io.Serializable;
import java.util.StringTokenizer;

/**
 * Defines constraints for components that are layed out with the FormLayout.
 * Defines the components display area: grid&nbsp;x, grid&nbsp;y, grid width
 * (column span), grid height (row span), horizontal alignment and vertical
 * alignment. <p> Most methods return <em>this</em> object to enable method
 * chaining. <p> You can set optional insets in a constructor. This is useful if
 * you need to use a pixel-size insets to align perceived component bounds with
 * pixel data, for example an icon. Anyway, this is rarely used. The insets
 * don't affect the size computation for columns and rows. I consider renaming
 * the insets to offsets to better indicate the motivation for this option. <p>
 * <strong>Examples</strong>:<br> The following cell constraints locate a
 * component in the third column of the fifth row; column and row span are 1;
 * the component will be aligned with the column's right-hand side and the row's
 * bottom. <pre> CellConstraints cc = new CellConstraints(); cc.xy(3, 5);
 * cc.xy(3, 5, CellConstraints.RIGHT, CellConstraints.BOTTOM); cc.xy(3, 5,
 * &quot;right, bottom&quot;); cc.xyw(3, 5, 1); cc.xyw(3, 5, 1,
 * CellConstraints.RIGHT, CellConstraints.BOTTOM); cc.xyw(3, 5, 1, &quot;right,
 * bottom&quot;); cc.xywh(3, 5, 1, 1); cc.xywh(3, 5, 1, 1,
 * CellConstraints.RIGHT, CellConstraints.BOTTOM); cc.xywh(3, 5, 1, 1,
 * &quot;right, bottom&quot;); </pre> See also the examples in the
 * {@link FormLayout} class comment. <p> TODO: Consider renaming the inset to
 * offsets.
 * @author Mark Veltzer <mark@veltzer.net>
 */
@SuppressWarnings("serial")
public final class CellConstraints implements Cloneable, Serializable {

	// Alignment Constants *************************************************

	/*
	 * Implementation Note: Do not change the order of the following constants.
	 * The serialization of class Alignment is ordinal-based and relies on it.
	 */

	/**
	 * Use the column's or row's default alignment.
	 */
	public static final Alignment DEFAULT = new Alignment("default",
			Alignment.BOTH);

	/**
	 * Fill the cell either horizontally or vertically.
	 */
	public static final Alignment FILL = new Alignment("fill", Alignment.BOTH);

	/**
	 * Put the component in the left.
	 */
	public static final Alignment LEFT = new Alignment("left",
			Alignment.HORIZONTAL);

	/**
	 * Put the component in the right.
	 */
	public static final Alignment RIGHT = new Alignment("right",
			Alignment.HORIZONTAL);

	/**
	 * Put the component in the center.
	 */
	public static final Alignment CENTER = new Alignment("center",
			Alignment.BOTH);

	/**
	 * Put the component in the top.
	 */
	public static final Alignment TOP = new Alignment("top", Alignment.VERTICAL);

	/**
	 * Put the component in the bottom.
	 */
	public static final Alignment BOTTOM = new Alignment("bottom",
			Alignment.VERTICAL);

	/**
	 * An array of all enumeration values used to canonicalize deserialized
	 * alignments.
	 */
	private static final Alignment[] VALUES = {
			DEFAULT, FILL, LEFT, RIGHT, CENTER, TOP, BOTTOM
	};

	/**
	 * A reusable <code>Insets</code> object to reduce object instantiation.
	 */
	private static final Insets EMPTY_INSETS = new Insets(0, 0, 0, 0);

	// Fields ***************************************************************

	/**
	 * Describes the component's horizontal grid origin (starts at 1).
	 */
	private int gridX;

	/**
	 * Describes the component's vertical grid origin (starts at 1).
	 */
	private int gridY;

	/**
	 * Describes the component's horizontal grid extend (number of cells).
	 */
	private int gridWidth;

	/**
	 * Describes the component's vertical grid extent (number of cells).
	 */
	private int gridHeight;

	/**
	 * Describes the component's horizontal alignment.
	 */
	private Alignment hAlign;

	/**
	 * Describes the component's vertical alignment.
	 */
	private Alignment vAlign;

	/**
	 * Describes the component's <code>Insets</code> in it's display area.
	 */
	private Insets insets;

	// Instance Creation ****************************************************

	/**
	 * Constructs a default instance of <code>CellConstraints</code>.
	 */
	public CellConstraints() {
		this(1, 1);
	}

	/**
	 * Constructs an instance of <code>CellConstraints</code> for the given cell
	 * position. <p> <strong>Examples:</strong> <pre> new CellConstraints(1, 3);
	 * new CellConstraints(1, 3); </pre>
	 * @param gridX the component's horizontal grid origin
	 * @param gridY the component's vertical grid origin
	 */
	public CellConstraints(int igridX, int igridY) {
		this(igridX, igridY, 1, 1);
	}

	/**
	 * Constructs an instance of <code>CellConstraints</code> for the given cell
	 * position, anchor, and fill. <p> <strong>Examples:</strong> <pre> new
	 * CellConstraints(1, 3, CellConstraints.LEFT, CellConstraints.BOTTOM); new
	 * CellConstraints(1, 3, CellConstraints.CENTER, CellConstraints.FILL);
	 * </pre>
	 * @param gridX the component's horizontal grid origin
	 * @param gridY the component's vertical grid origin
	 * @param hAlign the component's horizontal alignment
	 * @param vAlign the component's vertical alignment
	 */
	public CellConstraints(int igridX, int igridY, Alignment ihAlign,
			Alignment ivAlign) {
		this(igridX, igridY, 1, 1, ihAlign, ivAlign, EMPTY_INSETS);
	}

	/**
	 * Constructs an instance of <code>CellConstraints</code> for the given cell
	 * position and size. <p> <strong>Examples:</strong> <pre> new
	 * CellConstraints(1, 3, 2, 1); new CellConstraints(1, 3, 7, 3); </pre>
	 * @param gridX the component's horizontal grid origin
	 * @param gridY the component's vertical grid origin
	 * @param gridWidth the component's horizontal extent
	 * @param gridHeight the component's vertical extent
	 */
	public CellConstraints(int igridX, int igridY, int igridWidth,
			int igridHeight) {
		this(igridX, igridY, igridWidth, igridHeight, DEFAULT, DEFAULT);
	}

	/**
	 * Constructs an instance of <code>CellConstraints</code> for the given cell
	 * position and size, anchor, and fill. <p> <strong>Examples:</strong> <pre>
	 * new CellConstraints(1, 3, 2, 1, CellConstraints.LEFT,
	 * CellConstraints.BOTTOM); new CellConstraints(1, 3, 7, 3,
	 * CellConstraints.CENTER, CellConstraints.FILL); </pre>
	 * @param gridX the component's horizontal grid origin
	 * @param gridY the component's vertical grid origin
	 * @param gridWidth the component's horizontal extent
	 * @param gridHeight the component's vertical extent
	 * @param hAlign the component's horizontal alignment
	 * @param vAlign the component's vertical alignment
	 */
	public CellConstraints(int igridX, int igridY, int igridWidth,
			int igridHeight, Alignment ihAlign, Alignment ivAlign) {
		this(igridX, igridY, igridWidth, igridHeight, ihAlign, ivAlign,
				EMPTY_INSETS);
	}

	/**
	 * Constructs an instance of <code>CellConstraints</code> for the complete
	 * set of available properties. <p> <strong>Examples:</strong> <pre> new
	 * CellConstraints(1, 3, 2, 1, CellConstraints.LEFT, CellConstraints.BOTTOM,
	 * new Insets(0, 1, 0, 3)); new CellConstraints(1, 3, 7, 3,
	 * CellConstraints.CENTER, CellConstraints.FILL, new Insets(0, 1, 0, 0));
	 * </pre>
	 * @param gridX the component's horizontal grid origin
	 * @param gridY the component's vertical grid origin
	 * @param gridWidth the component's horizontal extent
	 * @param gridHeight the component's vertical extent
	 * @param hAlign the component's horizontal alignment
	 * @param vAlign the component's vertical alignment
	 * @param insets the component's display area <code>Insets</code>
	 * @throws IndexOutOfBoundsException if the grid origin or extent is
	 * negative
	 * @throws NullPointerException if the horizontal or vertical alignment is
	 * null
	 * @throws IllegalArgumentException if an alignment orientation is invalid
	 */
	public CellConstraints(int igridX, int igridY, int igridWidth,
			int igridHeight, Alignment ihAlign, Alignment ivAlign,
			Insets iinsets) {
		setGridX(igridX);
		setGridY(igridY);
		setGridWidth(igridWidth);
		setGridHeight(igridHeight);
		hAlign = ihAlign;
		vAlign = ivAlign;
		insets = iinsets;
		if (getGridX() <= 0) {
			throw new IndexOutOfBoundsException(
					"The grid x must be a positive number.");
		}
		if (getGridY() <= 0) {
			throw new IndexOutOfBoundsException(
					"The grid y must be a positive number.");
		}
		if (getGridWidth() <= 0) {
			throw new IndexOutOfBoundsException(
					"The grid width must be a positive number.");
		}
		if (getGridHeight() <= 0) {
			throw new IndexOutOfBoundsException(
					"The grid height must be a positive number.");
		}
		if (hAlign == null) {
			throw new NullPointerException(
					"The horizontal alignment must not be null.");
		}
		if (vAlign == null) {
			throw new NullPointerException(
					"The vertical alignment must not be null.");
		}
		ensureValidOrientations(hAlign, vAlign);
	}

	/**
	 * Constructs an instance of <code>CellConstraints</code> from the given
	 * encoded string properties. <p> <strong>Examples:</strong> <pre> new
	 * CellConstraints(&quot;1, 3&quot;); new CellConstraints(&quot;1, 3, left,
	 * bottom&quot;); new CellConstraints(&quot;1, 3, 2, 1, left, bottom&quot;);
	 * new CellConstraints(&quot;1, 3, 2, 1, l, b&quot;); </pre>
	 * @param encodedConstraints the constraints encoded as string
	 */
	public CellConstraints(String encodedConstraints) {
		this();
		initFromConstraints(encodedConstraints);
	}

	// Setters **************************************************************

	/**
	 * Sets row and column origins; sets width and height to 1; uses the default
	 * alignments. <p> <strong>Examples:</strong> <pre> cc.xy(1, 1); cc.xy(1,
	 * 3); </pre>
	 * @param col the new column index
	 * @param row the new row index
	 * @return this
	 */
	public CellConstraints xy(int col, int row) {
		return xywh(col, row, 1, 1);
	}

	/**
	 * Sets row and column origins; sets width and height to 1; decodes
	 * horizontal and vertical alignments from the given string. <p>
	 * <strong>Examples:</strong> <pre> cc.xy(1, 3, &quot;left, bottom&quot;);
	 * cc.xy(1, 3, &quot;l, b&quot;); cc.xy(1, 3, &quot;center, fill&quot;);
	 * cc.xy(1, 3, &quot;c, f&quot;); </pre>
	 * @param col the new column index
	 * @param row the new row index
	 * @param encodedAlignments describes the horizontal and vertical alignments
	 * @return this
	 * @throws IllegalArgumentException if an alignment orientation is invalid
	 */
	public CellConstraints xy(int col, int row, String encodedAlignments) {
		return xywh(col, row, 1, 1, encodedAlignments);
	}

	/**
	 * Sets the row and column origins; sets width and height to 1; set
	 * horizontal and vertical alignment using the specified objects. <p>
	 * <strong>Examples:</strong> <pre> cc.xy(1, 3, CellConstraints.LEFT,
	 * CellConstraints.BOTTOM); cc.xy(1, 3, CellConstraints.CENTER,
	 * CellConstraints.FILL); </pre>
	 * @param col the new column index
	 * @param row the new row index
	 * @param colAlign horizontal component alignment
	 * @param rowAlign vertical component alignment
	 * @return this
	 */
	public CellConstraints xy(int col, int row, Alignment colAlign,
			Alignment rowAlign) {
		return xywh(col, row, 1, 1, colAlign, rowAlign);
	}

	/**
	 * Sets the row, column, width, and height; uses a height (row span) of 1
	 * and the horizontal and vertical default alignments. <p>
	 * <strong>Examples:</strong> <pre> cc.xyw(1, 3, 7); cc.xyw(1, 3, 2); </pre>
	 * @param col the new column index
	 * @param row the new row index
	 * @param colSpan the column span or grid width
	 * @return this
	 */
	public CellConstraints xyw(int col, int row, int colSpan) {
		return xywh(col, row, colSpan, 1, DEFAULT, DEFAULT);
	}

	/**
	 * Sets the row, column, width, and height; decodes the horizontal and
	 * vertical alignments from the given string. The row span (height) is set
	 * to 1. <p> <strong>Examples:</strong> <pre> cc.xyw(1, 3, 7, &quot;left,
	 * bottom&quot;); cc.xyw(1, 3, 7, &quot;l, b&quot;); cc.xyw(1, 3, 2,
	 * &quot;center, fill&quot;); cc.xyw(1, 3, 2, &quot;c, f&quot;); </pre>
	 * @param col the new column index
	 * @param row the new row index
	 * @param colSpan the column span or grid width
	 * @param encodedAlignments describes the horizontal and vertical alignments
	 * @return this
	 * @throws IllegalArgumentException if an alignment orientation is invalid
	 */
	public CellConstraints xyw(int col, int row, int colSpan,
			String encodedAlignments) {
		return xywh(col, row, colSpan, 1, encodedAlignments);
	}

	/**
	 * Sets the row, column, width, and height; sets the horizontal and vertical
	 * aligment using the specified alignment objects. The row span (height) is
	 * set to 1. <p> <strong>Examples:</strong> <pre> cc.xyw(1, 3, 2,
	 * CellConstraints.LEFT, CellConstraints.BOTTOM); cc.xyw(1, 3, 7,
	 * CellConstraints.CENTER, CellConstraints.FILL); </pre>
	 * @param col the new column index
	 * @param row the new row index
	 * @param colSpan the column span or grid width
	 * @param colAlign horizontal component alignment
	 * @param rowAlign vertical component alignment
	 * @return this
	 * @throws IllegalArgumentException if an alignment orientation is invalid
	 */
	public CellConstraints xyw(int col, int row, int colSpan,
			Alignment colAlign, Alignment rowAlign) {
		return xywh(col, row, colSpan, 1, colAlign, rowAlign);
	}

	/**
	 * Sets the row, column, width, and height; uses default alignments. <p>
	 * <strong>Examples:</strong> <pre> cc.xywh(1, 3, 2, 1); cc.xywh(1, 3, 7,
	 * 3); </pre>
	 * @param col the new column index
	 * @param row the new row index
	 * @param colSpan the column span or grid width
	 * @param rowSpan the row span or grid height
	 * @return this
	 */
	public CellConstraints xywh(int col, int row, int colSpan, int rowSpan) {
		return xywh(col, row, colSpan, rowSpan, DEFAULT, DEFAULT);
	}

	/**
	 * Sets the row, column, width, and height; decodes the horizontal and
	 * vertical alignments from the given string. <p> <strong>Examples:</strong>
	 * <pre> cc.xywh(1, 3, 2, 1, &quot;left, bottom&quot;); cc.xywh(1, 3, 2, 1,
	 * &quot;l, b&quot;); cc.xywh(1, 3, 7, 3, &quot;center, fill&quot;);
	 * cc.xywh(1, 3, 7, 3, &quot;c, f&quot;); </pre>
	 * @param col the new column index
	 * @param row the new row index
	 * @param colSpan the column span or grid width
	 * @param rowSpan the row span or grid height
	 * @param encodedAlignments describes the horizontal and vertical alignments
	 * @return this
	 * @throws IllegalArgumentException if an alignment orientation is invalid
	 */
	public CellConstraints xywh(int col, int row, int colSpan, int rowSpan,
			String encodedAlignments) {
		CellConstraints result = xywh(col, row, colSpan, rowSpan);
		result.setAlignments(encodedAlignments);
		return result;
	}

	/**
	 * Sets the row, column, width, and height; sets the horizontal and vertical
	 * aligment using the specified alignment objects. <p>
	 * <strong>Examples:</strong> <pre> cc.xywh(1, 3, 2, 1,
	 * CellConstraints.LEFT, CellConstraints.BOTTOM); cc.xywh(1, 3, 7, 3,
	 * CellConstraints.CENTER, CellConstraints.FILL); </pre>
	 * @param col the new column index
	 * @param row the new row index
	 * @param colSpan the column span or grid width
	 * @param rowSpan the row span or grid height
	 * @param colAlign horizontal component alignment
	 * @param rowAlign vertical component alignment
	 * @return this
	 * @throws IllegalArgumentException if an alignment orientation is invalid
	 */
	public CellConstraints xywh(int col, int row, int colSpan, int rowSpan,
			Alignment colAlign, Alignment rowAlign) {
		this.setGridX(col);
		this.setGridY(row);
		this.setGridWidth(colSpan);
		this.setGridHeight(rowSpan);
		this.hAlign = colAlign;
		this.vAlign = rowAlign;
		ensureValidOrientations(hAlign, vAlign);
		return this;
	}

	// Parsing and Decoding String Descriptions *****************************

	/**
	 * Decodes and returns the grid bounds and alignments for this constraints
	 * as an array of six integers. The string representation is a comma
	 * separated sequence, one of <pre> "x, y" "x, y, w, h"
	 * "x, y, hAlign, vAlign" "x, y, w, h, hAlign, vAlign" </pre>
	 * @param encodedConstraints represents horizontal and vertical alignment
	 * @throws IllegalArgumentException if the encoded constraints do not follow
	 * the constraint syntax
	 */
	private void initFromConstraints(String encodedConstraints) {
		StringTokenizer tokenizer = new StringTokenizer(encodedConstraints,
				" ,");
		int argCount = tokenizer.countTokens();
		if (!(argCount == 2 || argCount == 4 || argCount == 6)) {
			throw new IllegalArgumentException(
					"You must provide 2, 4 or 6 arguments.");
		}
		Integer nextInt = decodeInt(tokenizer.nextToken());
		if (nextInt == null) {
			throw new IllegalArgumentException(
					"First cell constraint element must be a number.");
		}
		setGridX(nextInt.intValue());
		if (getGridX() <= 0) {
			throw new IndexOutOfBoundsException(
					"The grid x must be a positive number.");
		}

		nextInt = decodeInt(tokenizer.nextToken());
		if (nextInt == null) {
			throw new IllegalArgumentException(
					"Second cell constraint element must be a number.");
		}
		setGridY(nextInt.intValue());
		if (getGridY() <= 0) {
			throw new IndexOutOfBoundsException(
					"The grid y must be a positive number.");
		}

		if (!tokenizer.hasMoreTokens()) {
			return;
		}

		String token = tokenizer.nextToken();
		nextInt = decodeInt(token);
		if (nextInt != null) {
			// Case: "x, y, w, h" or
			// "x, y, w, h, hAlign, vAlign"
			setGridWidth(nextInt.intValue());
			if (getGridWidth() <= 0) {
				throw new IndexOutOfBoundsException(
						"The grid width must be a positive number.");
			}
			nextInt = decodeInt(tokenizer.nextToken());
			if (nextInt == null) {
				throw new IllegalArgumentException(
						"Fourth cell constraint element must be like third.");
			}
			setGridHeight(nextInt.intValue());
			if (getGridHeight() <= 0) {
				throw new IndexOutOfBoundsException(
						"The grid height must be a positive number.");
			}

			if (!tokenizer.hasMoreTokens()) {
				return;
			}
			token = tokenizer.nextToken();
		}

		hAlign = decodeAlignment(token);
		vAlign = decodeAlignment(tokenizer.nextToken());
		ensureValidOrientations(hAlign, vAlign);
	}

	/**
	 * Decodes a string description for the horizontal and vertical alignment
	 * and set the alignment values. <p> Valid horizontal aligmnents are: left,
	 * middle, right, default, and fill. Valid vertical alignments are: top,
	 * center, bottom, default, and fill. The anchor's string representation
	 * abbreviates the alignment: l, m, r, d, f, t, c, and b. <p> Anchor
	 * examples: "mc" is centered, "lt" is northwest, "mt" is north, "rc" east.
	 * "md" is horizontally centered and uses the row's default alignment. "dt"
	 * is on top of the cell and uses the column's default alignment. <p>
	 * @param encodedAlignments represents horizontal and vertical alignment
	 * @throws IllegalArgumentException if an alignment orientation is invalid
	 */
	private void setAlignments(String encodedAlignments) {
		StringTokenizer tokenizer = new StringTokenizer(encodedAlignments, " ,");
		hAlign = decodeAlignment(tokenizer.nextToken());
		vAlign = decodeAlignment(tokenizer.nextToken());
		ensureValidOrientations(hAlign, vAlign);
	}

	/**
	 * Decodes an integer string representation and returns the associated
	 * Integer or null in case of an invalid number format.
	 * @param token the encoded integer
	 * @return the decoded Integer or null
	 */
	private Integer decodeInt(String token) {
		try {
			return Integer.decode(token);
		} catch (NumberFormatException e) {
			return null;
		}
	}

	/**
	 * Parses an alignment string description and returns the corresponding
	 * alignment value.
	 * @param encodedAlignment the encoded alignment
	 * @return the associated <code>Alignment</code> instance
	 */
	private Alignment decodeAlignment(String encodedAlignment) {
		return Alignment.valueOf(encodedAlignment);
	}

	/**
	 * Checks and verifies that this constraints object has valid grid index
	 * values, i. e. the display area cells are inside the form's grid.
	 * @param colCount number of columns in the grid
	 * @param rowCount number of rows in the grid
	 * @throws IndexOutOfBoundsException if the display area described by this
	 * constraints object is not inside the grid
	 */
	public void ensureValidGridBounds(int colCount, int rowCount) {
		if (getGridX() <= 0) {
			throw new IndexOutOfBoundsException("The column index "
					+ getGridX() + " must be positive.");
		}
		if (getGridX() > colCount) {
			throw new IndexOutOfBoundsException("The column index "
					+ getGridX() + " must be less than or equal to " + colCount
					+ ".");
		}
		if (getGridX() + getGridWidth() - 1 > colCount) {
			throw new IndexOutOfBoundsException("The grid width "
					+ getGridWidth() + " must be less than or equal to "
					+ (colCount - getGridX() + 1) + ".");
		}
		if (getGridY() <= 0) {
			throw new IndexOutOfBoundsException("The row index " + getGridY()
					+ " must be positive.");
		}
		if (getGridY() > rowCount) {
			throw new IndexOutOfBoundsException("The row index " + getGridY()
					+ " must be less than or equal to " + rowCount + ".");
		}
		if (getGridY() + getGridHeight() - 1 > rowCount) {
			throw new IndexOutOfBoundsException("The grid height "
					+ getGridHeight() + " must be less than or equal to "
					+ (rowCount - getGridY() + 1) + ".");
		}
	}

	/**
	 * Checks and verifies that the horizontal alignment is a horizontal and the
	 * vertical alignment is vertical.
	 * @param horizontalAlignment the horizontal alignment
	 * @param verticalAlignment the vertical alignment
	 * @throws IllegalArgumentException if an alignment is invalid
	 */
	private void ensureValidOrientations(Alignment horizontalAlignment,
			Alignment verticalAlignment) {
		if (!horizontalAlignment.isHorizontal()) {
			throw new IllegalArgumentException(
					"The horizontal alignment must be one of: left, center, right, fill, default.");
		}
		if (!verticalAlignment.isVertical()) {
			throw new IllegalArgumentException(
					"The vertical alignment must be one of: top, center, botto, fill, default.");
		}
	}

	// Settings Component Bounds ********************************************

	/**
	 * Sets the component's bounds using the given component and cell bounds.
	 * @param c the component to set bounds
	 * @param layout the FormLayout instance that computes the bounds
	 * @param cellBounds the cell's bounds
	 * @param minWidthMeasure measures the minimum width
	 * @param minHeightMeasure measures the minimum height
	 * @param prefWidthMeasure measures the preferred width
	 * @param prefHeightMeasure measures the preferred height
	 */
	void setBounds(Component c, FormLayout layout, Rectangle cellBounds,
			FormLayout.Measure minWidthMeasure,
			FormLayout.Measure minHeightMeasure,
			FormLayout.Measure prefWidthMeasure,
			FormLayout.Measure prefHeightMeasure) {
		ColumnSpec colSpec;
		if (getGridWidth() == 1) {
			colSpec = layout.getColumnSpec(getGridX());
		} else {
			colSpec = null;
		}
		RowSpec rowSpec;
		if (getGridHeight() == 1) {
			rowSpec = layout.getRowSpec(getGridY());
		} else {
			rowSpec = null;
		}
		Alignment concreteHAlign = concreteAlignment(hAlign, colSpec);
		Alignment concreteVAlign = concreteAlignment(vAlign, rowSpec);
		Insets concreteInsets = EMPTY_INSETS;
		int cellX = cellBounds.x + concreteInsets.left;
		int cellY = cellBounds.y + concreteInsets.top;
		int cellW = cellBounds.width - concreteInsets.left
				- concreteInsets.right;
		int cellH = cellBounds.height - concreteInsets.top
				- concreteInsets.bottom;
		int compW = componentSize(c, colSpec, cellW, minWidthMeasure,
				prefWidthMeasure);
		int compH = componentSize(c, rowSpec, cellH, minHeightMeasure,
				prefHeightMeasure);
		int x = origin(concreteHAlign, cellX, cellW, compW);
		int y = origin(concreteVAlign, cellY, cellH, compH);
		int w = extent(concreteHAlign, cellW, compW);
		int h = extent(concreteVAlign, cellH, compH);
		c.setBounds(x, y, w, h);
	}

	/**
	 * Computes and returns the concrete alignment. Takes into account the cell
	 * alignment and <i>the</i> <code>FormSpec</code> if applicable. <p> If this
	 * constraints object doesn't belong to a single column or row, the
	 * <code>formSpec</code> parameter is <code>null</code>. In this case the
	 * cell alignment is answered, but <code>DEFAULT</code> is mapped to
	 * <code>FILL</code>. <p> If the cell belongs to a single column or row, we
	 * use the cell alignment, unless it is <code>DEFAULT</code>, where the
	 * alignment is inherited from the column or row resp.
	 * @param cellAlignment this cell's aligment
	 * @param formSpec the associated column or row specification
	 * @return the concrete alignment
	 */
	private Alignment concreteAlignment(Alignment cellAlignment,
			FormSpec formSpec) {
		if (formSpec == null) {
			if (cellAlignment == DEFAULT) {
				return FILL;
			} else {
				return cellAlignment;
			}
		} else {
			return usedAlignment(cellAlignment, formSpec);
		}
	}

	/**
	 * Returns the alignment used for a given form constraints object. The cell
	 * alignment overrides the column or row default, unless it is
	 * <code>DEFAULT</code>. In the latter case, we use the column or row
	 * alignment.
	 * @param cellAlignment this cell constraint's alignment
	 * @param formSpec the associated column or row specification
	 * @return the alignment used
	 */
	private Alignment usedAlignment(Alignment cellAlignment, FormSpec formSpec) {
		if (cellAlignment != CellConstraints.DEFAULT) {
			// Cell alignments other than DEFAULT override col/row alignments
			return cellAlignment;
		}
		FormSpec.DefaultAlignment defaultAlignment = formSpec
				.getDefaultAlignment();
		if (defaultAlignment == FormSpec.FILL_ALIGN) {
			return FILL;
		}
		if (defaultAlignment == ColumnSpec.LEFT) {
			return LEFT;
		} else if (defaultAlignment == FormSpec.CENTER_ALIGN) {
			return CENTER;
		} else if (defaultAlignment == ColumnSpec.RIGHT) {
			return RIGHT;
		} else if (defaultAlignment == RowSpec.TOP) {
			return TOP;
		} else {
			return BOTTOM;
		}
	}

	/**
	 * Computes and returns the pixel size of the given component using the
	 * given form specification, measures, and cell size.
	 * @param component the component to measure
	 * @param formSpec the specification of the component's column/row
	 * @param minMeasure the measure for the minimum size
	 * @param prefMeasure the measure for the preferred size
	 * @param cellSize the cell size
	 * @return the component size as measured or a constant
	 */
	private int componentSize(Component component, FormSpec formSpec,
			int cellSize, FormLayout.Measure minMeasure,
			FormLayout.Measure prefMeasure) {
		if (formSpec == null) {
			return prefMeasure.sizeOf(component);
		} else if (formSpec.getSize() == Sizes.MINIMUM) {
			return minMeasure.sizeOf(component);
		} else if (formSpec.getSize() == Sizes.PREFERRED) {
			return prefMeasure.sizeOf(component);
		} else { // default mode
			return Math.min(cellSize, prefMeasure.sizeOf(component));
		}
	}

	/**
	 * Computes and returns the component's pixel origin.
	 * @param alignment the component's alignment
	 * @param cellOrigin the origin of the display area
	 * @param cellSize the extent of the display area
	 * @param componentSize
	 * @return the component's pixel origin
	 */
	private int origin(Alignment alignment, int cellOrigin, int cellSize,
			int componentSize) {
		if (alignment == RIGHT || alignment == BOTTOM) {
			return cellOrigin + cellSize - componentSize;
		} else if (alignment == CENTER) {
			return cellOrigin + (cellSize - componentSize) / 2;
		} else { // left, top, fill
			return cellOrigin;
		}
	}

	/**
	 * Returns the component's pixel extent.
	 * @param alignment the component's alignment
	 * @param cellSize the size of the display area
	 * @param componentSize the component's size
	 * @return the component's pixel extent
	 */
	private int extent(Alignment alignment, int cellSize, int componentSize) {
		if (alignment == FILL) {
			return cellSize;
		} else {
			return componentSize;
		}
	}

	// Misc *****************************************************************

	/**
	 * Creates a copy of this cell constraints object.
	 * @return a copy of this cell constraints object
	 */
	public Object clone() {
		try {
			CellConstraints c = (CellConstraints) super.clone();
			c.insets = (Insets) insets.clone();
			return c;
		} catch (CloneNotSupportedException e) {
			// This shouldn't happen, since we are Cloneable.
			throw new InternalError();
		}
	}

	/**
	 * Constructs and returns a string representation of this constraints
	 * object.
	 * @return string representation of this constraints object
	 */
	public String toString() {
		StringBuffer buffer = new StringBuffer("CellConstraints");
		buffer.append("[x=");
		buffer.append(getGridX());
		buffer.append("; y=");
		buffer.append(getGridY());
		buffer.append("; w=");
		buffer.append(getGridWidth());
		buffer.append("; h=");
		buffer.append(getGridHeight());
		buffer.append("; hAlign=");
		buffer.append(hAlign);
		buffer.append("; vAlign=");
		buffer.append(vAlign);
		if (!(EMPTY_INSETS.equals(insets))) {
			buffer.append("; insets=");
			buffer.append(insets);
		}

		buffer.append(']');
		return buffer.toString();
	}

	/**
	 * Returns a short string representation of this constraints object.
	 * @return a short string representation of this constraints object
	 */
	public String toShortString() {
		return toShortString(null);
	}

	/**
	 * Returns a short string representation of this constraints object. This
	 * method can use the given <code>FormLayout</code> to display extra
	 * information how default alignments are mapped to concrete alignments.
	 * Therefore it asks the related column and row as specified by this
	 * constraints object.
	 * @param layout the layout to be presented as a string
	 * @return a short string representation of this constraints object
	 */
	public String toShortString(FormLayout layout) {
		StringBuffer buffer = new StringBuffer("(");
		buffer.append(formatInt(getGridX()));
		buffer.append(", ");
		buffer.append(formatInt(getGridY()));
		buffer.append(", ");
		buffer.append(formatInt(getGridWidth()));
		buffer.append(", ");
		buffer.append(formatInt(getGridHeight()));
		buffer.append(", \"");
		buffer.append(hAlign.abbreviation());
		if (hAlign == DEFAULT && layout != null) {
			buffer.append('=');
			ColumnSpec colSpec;
			if (getGridWidth() == 1) {
				colSpec = layout.getColumnSpec(getGridX());
			} else {
				colSpec = null;
			}
			buffer.append(concreteAlignment(hAlign, colSpec).abbreviation());
		}
		buffer.append(", ");
		buffer.append(vAlign.abbreviation());
		if (vAlign == DEFAULT && layout != null) {
			buffer.append('=');
			RowSpec rowSpec;
			if (getGridHeight() == 1) {
				rowSpec = layout.getRowSpec(getGridY());
			} else {
				rowSpec = null;
			}
			buffer.append(concreteAlignment(vAlign, rowSpec).abbreviation());
		}
		buffer.append("\"");
		if (!(EMPTY_INSETS.equals(insets))) {
			buffer.append(", ");
			buffer.append(insets);
		}

		buffer.append(')');
		return buffer.toString();
	}

	// Helper Class *********************************************************

	/**
	 * An ordinal-based serializable typesafe enumeration for component
	 * alignment types as used by the {@link FormLayout}.
	 */
	public static final class Alignment implements Serializable {
		private static final int HORIZONTAL = 0;
		private static final int VERTICAL = 1;
		private static final int BOTH = 2;

		private final transient String name;
		private final transient int orientation;

		private Alignment(String iname, int iorientation) {
			name = iname;
			orientation = iorientation;
		}

		static Alignment valueOf(String nameOrAbbreviation) {
			String str = nameOrAbbreviation.toLowerCase();
			if (str.equals("d") || str.equals("default")) {
				return DEFAULT;
			} else if (str.equals("f") || str.equals("fill")) {
				return FILL;
			} else if (str.equals("c") || str.equals("center")) {
				return CENTER;
			} else if (str.equals("l") || str.equals("left")) {
				return LEFT;
			} else if (str.equals("r") || str.equals("right")) {
				return RIGHT;
			} else if (str.equals("t") || str.equals("top")) {
				return TOP;
			} else if (str.equals("b") || str.equals("bottom")) {
				return BOTTOM;
			} else {
				throw new IllegalArgumentException(
						"Invalid alignment "
								+ nameOrAbbreviation
								+ ". Must be one of: left, center, right, top, bottom, "
								+ "fill, default, l, c, r, t, b, f, d.");
			}
		}

		/**
		 * Returns this Alignment's name.
		 * @return this alignment's name.
		 */
		public String toString() {
			return name;
		}

		/**
		 * Returns the first character of this Alignment's name. Used to
		 * identify it in short format strings.
		 * @return the name's first character.
		 */
		public char abbreviation() {
			return name.charAt(0);
		}

		private boolean isHorizontal() {
			return orientation != VERTICAL;
		}

		private boolean isVertical() {
			return orientation != HORIZONTAL;
		}

		// Serialization
		// *********************************************************

		private static int nextOrdinal = 0;

		private final int ordinal = nextOrdinal++;

		private Object readResolve() {
			return VALUES[ordinal]; // Canonicalize
		}

	}

	/**
	 * Returns an integer that has a minimum of two characters.
	 * @param number the number to format
	 * @return a string representation for a number with a minum of two chars
	 */
	private String formatInt(int number) {
		String str = Integer.toString(number);
		if (number < 10) {
			return " " + str;
		} else {
			return str;
		}
	}

	public int getGridHeight() {
		return gridHeight;
	}

	public void setGridHeight(int igridHeight) {
		gridHeight = igridHeight;
	}

	public int getGridWidth() {
		return gridWidth;
	}

	public void setGridWidth(int igridWidth) {
		gridWidth = igridWidth;
	}

	public int getGridX() {
		return gridX;
	}

	public void setGridX(int igridX) {
		gridX = igridX;
	}

	public int getGridY() {
		return gridY;
	}

	public void setGridY(int igridY) {
		gridY = igridY;
	}

}
