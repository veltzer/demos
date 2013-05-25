package swing.table_rendering;

import java.awt.Color;

public interface TableColorModel {
	Color getForeground(int row, int column);

	Color getBackground(int row, int column);

	Color getSelectedForeground(int row, int column);

	Color getSelectedBackground(int row, int column);
}
