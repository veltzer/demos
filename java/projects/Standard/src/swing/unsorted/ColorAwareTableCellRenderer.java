package swing.unsorted;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

public class ColorAwareTableCellRenderer extends DefaultTableCellRenderer
		implements TableCellRenderer {

	private static final long serialVersionUID = 1L;

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {

		super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
				row, column);

		MyTable myTable = (MyTable) table;

		if (isSelected) {
			setBackground(myTable.getTableColorModel().getSelectedBgColor(
					value, row, column));
			setForeground(myTable.getTableColorModel().getSelectedFgColor(
					value, row, column));
		} else {
			setBackground(myTable.getTableColorModel().getBgColor(value,
					row, column));
			setForeground(myTable.getTableColorModel().getFgColor(value,
					row, column));
		}
		return this;
	}

}
