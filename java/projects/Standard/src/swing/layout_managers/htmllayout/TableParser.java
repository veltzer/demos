package swing.layout_managers.htmllayout;

class TableParser {
	private Scanner in;

	private int rows = -1, cols = -1;
	private int hgap, vgap;
	private int hpad, vpad;
	private int horz = HtmlLayout.MAX, vert = HtmlLayout.MAX;
	private Cell[][] cells;

	private int curRow, curCol;
	// private int gridcount;
	private int cellCount;
	private boolean[][] taken;

	TableParser(Scanner iin, boolean eatTable, TableParser parent) {
		setIn(iin);

		if (eatTable) {
			if (getIn().scan() != Scanner.LT || getIn().scanU() != Scanner.STR
					|| !getIn().getCurrentString().equals("TABLE")) {
				error("description must start with TABLE tag");
			}
		}

		if (parent != null) {
			hgap = parent.hgap;
			vgap = parent.vgap;
			hpad = parent.hpad;
			vpad = parent.vpad;
		}

		parseTable();
		finishTable();
	}

	static final String[] VALUES = {
			"HORZ", "VERT", "ROWS", "COLS", "HGAP", "VGAP", "HPAD", "VPAD",
			"COLSPAN", "ROWSPAN", "COMPONENT"
	};

	int lookup(String[] poss, String value) {
		for (int i = 0; i < poss.length; i++) {
			if (poss[i].equals(value)) {
				return i;
			}
		}

		error("Invalid value " + value);
		return -1;
	}

	// hack, the value of the key returned by parsePair
	private int pairValue;

	int parsePair() {
		int key = lookup(VALUES, getIn().getCurrentString());

		scan(Scanner.EQ);
		scan(Scanner.STR);

		if (key == 10) { // component
			return key;
		}

		if (key < 2) { // horz and vert
			String sval = getIn().getCurrentString().toUpperCase();

			int val = lookup(HtmlLayout.ALIGNNAMES, sval);

			if ((key == 0 && val > HtmlLayout.MAX) // for horz
					|| (key == 1 && val < HtmlLayout.CENTER)) { // for vert
				error(sval + " illegal value for " + VALUES[key]);
			}

			pairValue = val;
			return key;
		}

		// everything else needs an int
		try {
			pairValue = Integer.parseInt(getIn().getCurrentString());
		} catch (NumberFormatException nfe) {
			error(VALUES[key] + " value must be an integer");
		}

		return key;
	}

	void parseTable() {
		int tok;

		while ((tok = getIn().scanU()) == Scanner.STR) {
			switch (parsePair()) {
			case 0:
				setHorz(pairValue);
				break;
			case 1:
				setVert(pairValue);
				break;
			case 2:
				setRows(pairValue);
				break;
			case 3:
				setCols(pairValue);
				break;
			case 4:
				hgap = pairValue;
				break;
			case 5:
				vgap = pairValue;
				break;
			case 6:
				hpad = pairValue;
				break;
			case 7:
				vpad = pairValue;
				break;
			default:
				error("Invalid attribute for TABLE");
			}
		}

		if (tok != Scanner.GT) {
			error("wrong token");
		}

		if (getCols() < 1 || getRows() < 1) {
			error("must specif positive rows and columns for TABLE");
		}

		setCells(new Cell[getRows()][getCols()]);
		taken = new boolean[getRows()][getCols()];

		while (getIn().scan() == Scanner.LT && getIn().scanU() == Scanner.STR) {
			if (getIn().getCurrentString().equals("TR")) {
				if (parseTR()) {
					// </Table>
					return;
				}
			} else if (getIn().getCurrentString().equals("/TABLE")) {
				scan(Scanner.GT);
				return;
			} else {
				error("Unexpected tag " + getIn().getCurrentString());
			}
		}

		scan(Scanner.EOF);
	}

	// return true if the table should close
	// leaves input "clean" (no leftover tag parts)
	boolean parseTR() {
		int tok;
		int curRowVGap = vgap;

		if (!(curRow < getRows())) {
			error("Excess rows in table");
		}

		while ((tok = getIn().scanU()) == Scanner.STR) {
			switch (parsePair()) {
			case 5:
				curRowVGap = pairValue;
				break;
			default:
				error("Invalid attribute for TR");
			}
		}

		if (tok != Scanner.GT) {
			error("wrong token");
		}

		while (getIn().scan() == Scanner.LT && getIn().scanU() == Scanner.STR) {
			if (getIn().getCurrentString().equals("TD")) {
				parseTD(curRowVGap);
				if (getIn().getLastTok() != Scanner.STR) {
					continue;
				}
			}

			if (getIn().getCurrentString().equals("TR")) {
				finishRow(curRowVGap);
				return parseTR();

			} else if (getIn().getCurrentString().equals("/TR")) {
				scan(Scanner.GT);
				finishRow(curRowVGap);
				return false;

			} else if (getIn().getCurrentString().equals("/TABLE")) {
				scan(Scanner.GT);
				finishRow(curRowVGap);
				return true;

			} else {
				error("Unexpected tag " + getIn().getCurrentString());
			}
		}

		scan(Scanner.EOF);
		finishRow(curRowVGap);
		return true;
	}

	private void finishRow(int curRowVGap) {
		while (curCol < getCols()) {
			if (!taken[curRow][curCol]) {
				Cell cell = new Cell(hgap, curRowVGap, 0, 0);

				addCell(cell);
			} else {
				curCol++;
			}
		}
		curCol = 0;
		curRow++;
	}

	private void finishTable() {
		while (curRow < getRows()) {
			finishRow(vgap);
		}
	}

	// leaves /table and /tr as the last token if it encounteers them
	// otherwise (in the case of a /td) leaves > as the last token
	void parseTD(int curRowVGap) {
		Cell c = new Cell(hgap, curRowVGap, hpad, vpad);

		int tok;

		while ((tok = getIn().scanU()) == Scanner.STR) {
			switch (parsePair()) {
			case 0:
				c.setHfill(pairValue);
				break;
			case 1:
				c.setVfill(pairValue);
				break;
			case 4:
				c.setHgap(pairValue);
				break;
			case 5:
				c.setVgap(pairValue);
				break;
			case 6:
				c.setHpad(pairValue);
				break;
			case 7:
				c.setVpad(pairValue);
				break;
			case 8:
				if (pairValue < 1) {
					error("colspan must be >= 1");
				}

				c.setColspan(pairValue);
				break;
			case 9:
				if (pairValue < 1) {
					error("rowspan must be >= 1");
				}

				c.setRowspan(pairValue);
				break;
			case 10:
				c.setName(getIn().getCurrentString());
				break;
			default:
				error("Invalid attribute for TR");
			}
		}

		if (tok != Scanner.GT) {
			error("wrong token");
		}

		addCell(c);

		tok = getIn().scan();
		if (tok == Scanner.STR) {
			if (c.getName() != null) {
				error("TDs can only have a component or text");
			}

			c.setLabelText(getIn().getCurrentString());
			tok = getIn().scan();
		}

		while (true) {
			if (tok == Scanner.EOF) {
				return;
			}

			if (tok != Scanner.LT || getIn().scanU() != Scanner.STR) {
				error("Parse error");
			}

			if (getIn().getCurrentString().equals("TD")) {
				parseTD(curRowVGap);
				return;
			} else if (getIn().getCurrentString().equals("/TD")) {
				scan(Scanner.GT);
				return;
			} else if (getIn().getCurrentString().equals("TABLE")) {
				if (c.getName() != null || c.getLabelText() != null) {
					error("TDs can't have a component or text with a TABLE");
				}

				c.setNested(new HtmlLayout(this));

				tok = getIn().scan();
			} else {
				return;
			}
		}
	}

	private void addCell(Cell c) {
		while (taken[curRow][curCol]) {
			curCol++;
			if (curCol == getCols()) {
				error("excess elements");
			}
		}

		c.setRow(curRow);
		c.setCol(curCol);

		if (c.getRow() + c.getRowspan() > getRows()
				|| c.getCol() + c.getColspan() > getCols()) {
			error("element exceeds table bounds");
		}

		for (int j = c.getRow(); j < c.getRow() + c.getRowspan(); j++) {
			for (int i = c.getCol(); i < c.getCol() + c.getColspan(); i++) {
				if (taken[j][i]) {
					error("table elements overlap");
				}

				taken[j][i] = true;
			}
		}

		curCol += c.getColspan();

		getCells()[c.getRow()][c.getCol()] = c;
		setCellCount(getCellCount() + 1);
	}

	void error(String message) {
		String context = getIn().getSource().substring(
				Math.max(0, getIn().getPos() - 10),
				Math.min(getIn().getPos() + 1, getIn().getSource().length()));

		throw new BadTableHtmlException("Bad html at or before character "
				+ getIn().getPos() + " : " + message + " : " + context + " <--");
	}

	void scan(int tok) {
		if (getIn().scan() != tok) {
			error("wrong token");
		}
	}

	public int getVert() {
		return vert;
	}

	public void setVert(int ivert) {
		vert = ivert;
	}

	public int getHorz() {
		return horz;
	}

	public void setHorz(int ihorz) {
		horz = ihorz;
	}

	public int getCols() {
		return cols;
	}

	public void setCols(int icols) {
		cols = icols;
	}

	public int getCellCount() {
		return cellCount;
	}

	public void setCellCount(int icellCount) {
		cellCount = icellCount;
	}

	public Cell[][] getCells() {
		return cells;
	}

	public void setCells(Cell[][] icells) {
		cells = icells;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int irows) {
		rows = irows;
	}

	public Scanner getIn() {
		return in;
	}

	public void setIn(Scanner iin) {
		in = iin;
	}
}
