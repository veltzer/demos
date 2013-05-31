package swing.layout_managers;

import java.util.NoSuchElementException;
import java.util.StringTokenizer;

/**
 * TableLayoutConstraints binds components to their constraints.
 * @author Mark Veltzer <mark@veltzer.net>
 */

public class TableLayoutConstraints {

	private static final String ERR_STRING1 = "bad data";

	/** Cell in which the upper left corner of the component lays */
	private int col1, row1;

	/** Cell in which the lower right corner of the component lays */
	private int col2, row2;

	/** Horizontal justification if component occupies just one cell */
	private int hAlign;

	/** Verical justification if component occupies just one cell */
	private int vAlign;

	/**
	 * Constructs an TableLayoutConstraints with the default settings. This
	 * constructor is equivalent to TableLayoutConstraints(0, 0, 0, 0, FULL,
	 * FULL).
	 */

	public TableLayoutConstraints() {
		setCol1(0);
		setRow1(0);
		setCol2(0);
		setRow2(0);
		sethAlign(TableLayoutConstants.FULL);
		setvAlign(TableLayoutConstants.FULL);
	}

	/**
	 * Constructs an TableLayoutConstraints from a string.
	 * @param constraints indicates TableLayoutConstraints's position and
	 * justification as a string in the form "row, column" or "row, column,
	 * horizontal justification, vertical justification" or "row 1, column 1,
	 * row 2, column 2". It is also acceptable to delimit the paramters with
	 * spaces instead of commas.
	 */

	public TableLayoutConstraints(String constraints) {
		// Use default values for any parameter not specified or specified
		// incorrectly. The default parameters place the component in a single
		// cell at column 0, row 0. The component is fully justified.
		setCol1(0);
		setRow1(0);
		setCol2(0);
		setRow2(0);
		sethAlign(TableLayoutConstants.FULL);
		setvAlign(TableLayoutConstants.FULL);

		// Parse constraints using spaces or commas
		StringTokenizer st = new StringTokenizer(constraints, ", ");
		int numToken = st.countTokens();

		try {
			// Check constraints
			if ((numToken != 2) && (numToken != 4) && (numToken != 6)) {
				throw new RuntimeException(ERR_STRING1);
			}

			// Get the first column (assume component is in only one column)
			String tokenA = st.nextToken();
			setCol1(new Integer(tokenA).intValue());
			setCol2(getCol1());

			// Get the first row (assume component is in only one row)
			String tokenB = st.nextToken();
			setRow1(new Integer(tokenB).intValue());
			setRow2(getRow1());

			// Get next two tokens
			tokenA = st.nextToken();
			tokenB = st.nextToken();

			try {
				// Attempt to use tokens A and B as col2 and row2
				setCol2(new Integer(tokenA).intValue());
				setRow2(new Integer(tokenB).intValue());

				// Get next two tokens
				tokenA = st.nextToken();
				tokenB = st.nextToken();
			} catch (NumberFormatException error) {
				setCol2(getCol1());
				setRow2(getRow1());
			}

			// Check if token means horizontally justification the component
			if ((tokenA.equalsIgnoreCase("L"))
					|| (tokenA.equalsIgnoreCase("LEFT"))) {
				sethAlign(TableLayoutConstants.LEFT);
			} else if ((tokenA.equalsIgnoreCase("C"))
					|| (tokenA.equalsIgnoreCase("CENTER"))) {
				sethAlign(TableLayoutConstants.CENTER);
			} else if ((tokenA.equalsIgnoreCase("F"))
					|| (tokenA.equalsIgnoreCase("FULL"))) {
				sethAlign(TableLayoutConstants.FULL);
			} else if ((tokenA.equalsIgnoreCase("R"))
					|| (tokenA.equalsIgnoreCase("RIGHT"))) {
				sethAlign(TableLayoutConstants.RIGHT);
			} else if ((tokenA.equalsIgnoreCase("LD"))
					|| (tokenA.equalsIgnoreCase("LEADING"))) {
				sethAlign(TableLayoutConstants.LEADING);
			} else if ((tokenA.equalsIgnoreCase("TL"))
					|| (tokenA.equalsIgnoreCase("TRAILING"))) {
				sethAlign(TableLayoutConstants.TRAILING);
			} else {
				throw new RuntimeException(ERR_STRING1);
			}

			// Check if token means horizontally justification the component
			if ((tokenB.equalsIgnoreCase("T"))
					|| (tokenB.equalsIgnoreCase("TOP"))) {
				setvAlign(TableLayoutConstants.TOP);
			} else if ((tokenB.equalsIgnoreCase("C"))
					|| (tokenB.equalsIgnoreCase("CENTER"))) {
				setvAlign(TableLayoutConstants.CENTER);
			} else if ((tokenB.equalsIgnoreCase("F"))
					|| (tokenB.equalsIgnoreCase("FULL"))) {
				setvAlign(TableLayoutConstants.FULL);
			} else if ((tokenB.equalsIgnoreCase("B"))
					|| (tokenB.equalsIgnoreCase("BOTTOM"))) {
				setvAlign(TableLayoutConstants.BOTTOM);
			} else {
				throw new RuntimeException(ERR_STRING1);
			}
		} catch (NoSuchElementException e) {
			throw new RuntimeException(e);
		} catch (RuntimeException e) {
			throw new IllegalArgumentException(
					"Expected constraints in one of the following formats:\n"
							+ " col1, row1\n col1, row1, col2, row2\n"
							+ " col1, row1, hAlign, vAlign\n"
							+ " col1, row1, col2, row2, hAlign, vAlign\n"
							+ "Constraints provided '" + constraints + "'");
		}

		// Make sure row2 >= row1
		if (getRow2() < getRow1()) {
			setRow2(getRow1());
		}

		// Make sure col2 >= col1
		if (getCol2() < getCol1()) {
			setCol2(getCol1());
		}
	}

	/**
	 * Constructs an TableLayoutConstraints a set of constraints.
	 * @param col1 column where upper-left cornor of the component is placed
	 * @param row1 row where upper-left cornor of the component is placed
	 * @param col2 column where lower-right cornor of the component is placed
	 * @param row2 row where lower-right cornor of the component is placed
	 * @param hAlign horizontal justification of a component in a single cell
	 * @param vAlign vertical justification of a component in a single cell
	 */

	public TableLayoutConstraints(int icol1, int irow1, int icol2, int irow2,
			int ihAlign, int ivAlign) {
		setCol1(icol1);
		setRow1(irow1);
		setCol2(icol2);
		setRow2(irow2);

		if ((ihAlign == TableLayoutConstants.LEFT)
				|| (ihAlign == TableLayoutConstants.RIGHT)
				|| (ihAlign == TableLayoutConstants.CENTER)
				|| (ihAlign == TableLayoutConstants.FULL)
				|| (ihAlign == TableLayoutConstants.TRAILING)) {
			sethAlign(ihAlign);
		} else {
			sethAlign(TableLayoutConstants.FULL);
		}

		if ((ivAlign == TableLayoutConstants.LEFT)
				|| (ivAlign == TableLayoutConstants.RIGHT)
				|| (ivAlign == TableLayoutConstants.CENTER)) {
			setvAlign(ivAlign);
		} else {
			setvAlign(TableLayoutConstants.FULL);
		}
	}

	/**
	 * Gets a string representation of this TableLayoutConstraints.
	 * @return a string in the form "row 1, column 1, row 2, column 2,
	 * horizontal justification, vertical justification"
	 */

	public String toString() {
		StringBuffer buffer = new StringBuffer();

		buffer.append(getCol1());
		buffer.append(", ");
		buffer.append(getRow1());
		buffer.append(", ");

		buffer.append(getCol2());
		buffer.append(", ");
		buffer.append(getRow2());
		buffer.append(", ");

		final String[] h = {
				"left", "center", "full", "right", "leading", "trailing"
		};
		final String[] v = {
				"top", "center", "full", "bottom"
		};

		buffer.append(h[gethAlign()]);
		buffer.append(", ");
		buffer.append(v[getvAlign()]);

		return buffer.toString();
	}

	public int getCol1() {
		return col1;
	}

	public void setCol1(int icol1) {
		col1 = icol1;
	}

	public int getCol2() {
		return col2;
	}

	public void setCol2(int icol2) {
		col2 = icol2;
	}

	public int gethAlign() {
		return hAlign;
	}

	public void sethAlign(int ihAlign) {
		hAlign = ihAlign;
	}

	public int getRow1() {
		return row1;
	}

	public void setRow1(int irow1) {
		row1 = irow1;
	}

	public int getRow2() {
		return row2;
	}

	public void setRow2(int irow2) {
		row2 = irow2;
	}

	public int getvAlign() {
		return vAlign;
	}

	public void setvAlign(int ivAlign) {
		vAlign = ivAlign;
	}

}
