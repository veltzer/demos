package swing.unsorted;

import java.awt.Component;

import javax.swing.JTable;

@SuppressWarnings("serial")
public class FileSizeTableCellRenderer extends ColorAwareTableCellRenderer {
	private static String[] suffixes = {
			"B", "K", "M", "G"
	};

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
				row, column);
		long newValue = (Long) value;
		int i = 0;
		while (newValue > 1024 && i < suffixes.length) {
			newValue /= 1024;
			i++;
		}
		this.setText(newValue + suffixes[i]);
		return this;
	}
}
