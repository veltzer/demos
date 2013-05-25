package swing.unsorted;

import java.awt.Color;

public class ZebraColorModel implements TableColorModel {

	public Color getBgColor(Object value, int row, int col) {
		if (row % 2 == 0) {
			return Color.YELLOW;
		} else {
			return Color.WHITE;
		}
	}

	public Color getFgColor(Object value, int row, int col) {
		return Color.BLACK;
	}

	public Color getSelectedBgColor(Object value, int row, int col) {
		if (row % 2 == 0) {
			return Color.RED;
		} else {
			return Color.GRAY;
		}
	}

	public Color getSelectedFgColor(Object value, int row, int col) {
		return Color.BLACK;
	}

}
