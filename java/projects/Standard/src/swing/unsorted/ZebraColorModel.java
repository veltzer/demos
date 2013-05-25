package swing.unsorted;

import java.awt.Color;

public class ZebraColorModel implements TableColorModel {

	public Color getBgColor(Object value, int row, int col) {
		return row % 2 == 0 ? Color.YELLOW : Color.WHITE;
	}

	public Color getFgColor(Object value, int row, int col) {
		return Color.BLACK;
	}

	public Color getSelectedBgColor(Object value, int row, int col) {
		return row % 2 == 0 ? Color.RED : Color.GRAY;
	}

	public Color getSelectedFgColor(Object value, int row, int col) {
		return Color.BLACK;
	}

}
