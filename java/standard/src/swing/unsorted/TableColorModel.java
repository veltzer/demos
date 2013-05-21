package swing.unsorted;
import java.awt.Color;


public interface TableColorModel {
	
	
	public Color getBgColor(Object value, int row, int col);
	public Color getFgColor(Object value, int row, int col);
	public Color getSelectedBgColor(Object value, int row, int col);
	public Color getSelectedFgColor(Object value, int row, int col);
}
