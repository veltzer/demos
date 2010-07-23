import java.awt.Component;

import javax.swing.DefaultCellEditor;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;


public class ColorAwareTableCellRenderer extends DefaultTableCellRenderer implements
		TableCellRenderer {

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		
		super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		
		MyTable myTable = (MyTable)table;
		
		if (isSelected) {
			this.setBackground(myTable.getTableColorModel().getSelectedBgColor(value, row, column));
			this.setForeground(myTable.getTableColorModel().getSelectedFgColor(value, row, column));
		} else {
			this.setBackground(myTable.getTableColorModel().getBgColor(value, row, column));
			this.setForeground(myTable.getTableColorModel().getFgColor(value, row, column));
		}
		
		return this;
	}

}
