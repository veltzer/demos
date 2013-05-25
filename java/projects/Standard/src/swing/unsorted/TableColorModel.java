package swing.unsorted;

import java.awt.Color;

public interface TableColorModel {

	Color getBgColor(Object value, int row, int col);

	Color getFgColor(Object value, int row, int col);

	Color getSelectedBgColor(Object value, int row, int col);

	Color getSelectedFgColor(Object value, int row, int col);
}
