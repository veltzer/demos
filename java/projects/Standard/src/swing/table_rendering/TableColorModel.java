package swing.table_rendering;

import java.awt.Color;

public interface TableColorModel {
	public Color getForeground(int row, int column);

	public Color getBackground(int row, int column);

	public Color getSelectedForeground(int row, int column);

	public Color getSelectedBackground(int row, int column);
}
