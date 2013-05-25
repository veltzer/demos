package swing.unsorted;

import java.awt.Color;

public class ZebraColorModel implements TableColorModel {

	public Color getBgColor(Object value, int row, int col) {
		if (row % 2 == 0) {
			return Color.Yellow;
		} else {
			return Color.White;
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
