package swing.layout_managers.htmllayout;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Label;
import java.util.Hashtable;

class Cell {
	private int hfill = HtmlLayout.LEFT;
	private int vfill = HtmlLayout.CENTER;

	private String name;
	private HtmlLayout nested;
	private String labelText;

	private Component comp;
	private int row, col;
	private int rowspan = 1, colspan = 1;
	private int hgap, vgap;
	private int hpad, vpad;

	Cell(int hg, int vg, int hp, int vp) {
		setHgap(hg);
		setVgap(vg);
		setHpad(hp);
		setVpad(vp);
	}

	void addToNameTable(Hashtable<String, Cell> nameToCell) {
		if (getName() != null) {
			if (nameToCell.put(getName(), this) != null) {
				throw new BadTableHtmlException("Duplicate component name: "
						+ getName());
			}
		} else if (getNested() != null) {
			getNested().addCellsToTable(nameToCell);
		}
	}

	private int reqwidth, reqheight;

	void addLabels(Container parent) {
		if (getLabelText() != null && getComp() == null) {
			setComp(new Label(getLabelText()));
			parent.add(getComp(), HtmlLayout.ANONLABELNAME);
		} else if (getNested() != null) {
			getNested().addLabels(parent);
		}
	}

	void finalLayout(int[] xpos, int[] ypos) {
		if (getComp() == null && getNested() == null) {
			return;
		}

		int r = getRow() + getRowspan();
		int c = getCol() + getColspan();

		int ll = xpos[getCol()];
		int lt = ypos[getRow()];
		int mr = xpos[c];
		int mb = ypos[r];

		int left = ll;
		int right = mr;
		int top = lt;
		int bottom = mb;

		if (getCol() != 0) {
			left += getHgap();
		}
		if (getRow() != 0) {
			top += getVgap();
		}

		if (getHfill() != HtmlLayout.MAX && getHfill() != HtmlLayout.FIT) {
			left = HtmlLayout.calcTopOrLeft(left, right, reqwidth, getHfill());
			right = left + reqwidth;
		}

		if (getVfill() != HtmlLayout.MAX && getVfill() != HtmlLayout.FIT) {
			top = HtmlLayout.calcTopOrLeft(top, bottom, reqheight, getVfill());
			bottom = top + reqheight;
		}

		position(ll, lt, mr, mb, left, top, right, bottom);

	}

	private void position(int ll, int lt, int mr, int mb, int l, int t, int r,
			int b) {
		if (l < ll) {
			l = ll;
		}
		if (t < lt) {
			t = lt;
		}
		if (r > mr) {
			r = mr;
		}
		if (b > mb) {
			b = mb;
		}

		if (getComp() != null) {
			getComp().setBounds(l, t, r - l, b - t);
		} else {
			getNested().layout(t, b, l, r);
		}
	}

	void firstXLayout(int[] xpos, boolean[] wantX) {
		addToXTable(xpos);

		if (getHfill() == HtmlLayout.MAX) {
			for (int i = getCol(); i < getCol() + getColspan(); i++) {
				wantX[i] = true;
			}
		}
	}

	void firstYLayout(int[] ypos, boolean[] wantY) {
		addToYTable(ypos);

		if (getVfill() == HtmlLayout.MAX) {
			for (int i = getRow(); i < getRow() + getRowspan(); i++) {
				wantY[i] = true;
			}
		}
	}

	void updateSize(int whichSize) {
		Dimension d = getSize(whichSize);
		reqwidth = d.width;
		reqheight = d.height;
	}

	private void squeeze(int[] pos, int[][] touch, int[] count, int[] limit,
			int start, int end, int size) {

		int availsize = pos[end] - pos[start];
		if (availsize > size) {
			int mylimit = availsize - size;

			if (mylimit < limit[end]) {
				limit[end] = mylimit;
			}

		} else {
			touch[end][count[end]++] = start;
		}
	}

	void squeezeX(int[] xpos, int[][] touch, int[] count, int[] limit) {
		squeeze(xpos, touch, count, limit, getCol(), getCol() + getColspan(),
				reqwidth + (getCol() == 0 ? 0 : getHgap()));

	}

	void squeezeY(int[] ypos, int[][] touch, int[] count, int[] limit) {
		squeeze(ypos, touch, count, limit, getRow(), getRow() + getRowspan(),
				reqheight + (getRow() == 0 ? 0 : getVgap()));
	}

	void addToXTable(int[] xpos) {
		int c = getCol() + getColspan();
		int right = xpos[getCol()] + reqwidth + (getCol() == 0 ? 0 : getHgap());

		if (xpos[c] < right) {
			xpos[c] = right;
		}
	}

	void addToYTable(int[] ypos) {
		int r = getRow() + getRowspan();
		int bottom = ypos[getRow()] + reqheight
				+ (getRow() == 0 ? 0 : getVgap());

		if (ypos[r] < bottom) {
			ypos[r] = bottom;
		}
	}

	static final Insets ZERO_INSETS = new Insets(0, 0, 0, 0);

	Dimension getSize(int whichSize) {
		if (getNested() != null) {
			return getNested().layoutSize(ZERO_INSETS, whichSize);
		}

		if (getComp() == null || !getComp().isVisible()) {
			return new Dimension(0, 0);
		}

		Dimension d;
		if (whichSize == HtmlLayout.MIN) {
			d = getComp().getMinimumSize();
		} else if (whichSize == HtmlLayout.PREF) {
			d = getComp().getPreferredSize();
		} else {
			throw new IllegalArgumentException("Bad whichSize " + whichSize);
		}

		if (getVpad() != 0 || getHpad() != 0) {
			d = new Dimension(d.width + getHpad(), d.height + getVpad());
		}

		return d;
	}

	private String descString() {
		return " pos = "
				+ getRow()
				+ ", "
				+ getCol()
				+ " span = "
				+ getRowspan()
				+ ", "
				+ getColspan()
				+ " fill = "
				+ getHfill()
				+ ", "
				+ getVfill()
				+ " gap = "
				+ getHgap()
				+ ", "
				+ getVgap()
				+ " pad = "
				+ getHpad()
				+ ", "
				+ getVpad()
				+ (getName() != null ? (" name = " + getName())
						: (getNested() != null ? (" nested = ")
								: (getLabelText() != null ? (" label = " + getLabelText())
										: " empty ")));
	}

	void dump(int space) {
		for (int i = 0; i < space; i++) {
			System.err.print(' ');
		}

		System.err.println("Cell" + descString());

		if (getNested() != null) {
			getNested().dump(space + 3);
		}
	}

	public String toString() {
		return "[Cell pos = " + descString()
				+ (getNested() != null ? getNested().toString() : "") + "]";
	}

	public int getCol() {
		return col;
	}

	public void setCol(int icol) {
		col = icol;
	}

	public int getColspan() {
		return colspan;
	}

	public void setColspan(int icolspan) {
		colspan = icolspan;
	}

	public Component getComp() {
		return comp;
	}

	public void setComp(Component icomp) {
		comp = icomp;
	}

	public int getHfill() {
		return hfill;
	}

	public void setHfill(int ihfill) {
		hfill = ihfill;
	}

	public int getVfill() {
		return vfill;
	}

	public void setVfill(int ivfill) {
		vfill = ivfill;
	}

	public int getHgap() {
		return hgap;
	}

	public void setHgap(int ihgap) {
		hgap = ihgap;
	}

	public int getVgap() {
		return vgap;
	}

	public void setVgap(int ivgap) {
		vgap = ivgap;
	}

	public int getHpad() {
		return hpad;
	}

	public void setHpad(int ihpad) {
		hpad = ihpad;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int irow) {
		row = irow;
	}

	public int getRowspan() {
		return rowspan;
	}

	public void setRowspan(int irowspan) {
		rowspan = irowspan;
	}

	public String getName() {
		return name;
	}

	public void setName(String iname) {
		name = iname;
	}

	public String getLabelText() {
		return labelText;
	}

	public void setLabelText(String ilabelText) {
		labelText = ilabelText;
	}

	public HtmlLayout getNested() {
		return nested;
	}

	public void setNested(HtmlLayout inested) {
		nested = inested;
	}

	public int getVpad() {
		return vpad;
	}

	public void setVpad(int ivpad) {
		vpad = ivpad;
	}
}
