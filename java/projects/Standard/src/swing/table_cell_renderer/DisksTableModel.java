package swing.table_cell_renderer;

import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class DisksTableModel extends AbstractTableModel {
	private static final String[] COLUMN_NAMES = {
			"Name", "Size", "Used"
	};
	private static final Class<?>[] COLUMN_CLASS = {
			String.class, Long.class, Long.class
	};

	public int getColumnCount() {
		return (COLUMN_NAMES.length);
	}

	public String getColumnName(int offset) {
		return (COLUMN_NAMES[offset]);
	}

	public int getRowCount() {
		return Disk.getDisks().size();
	}

	private static final String ERR_STRING1 = "bad column name";

	public Object getValueAt(int row, int column) {
		// name
		if (column == 0) {
			return Disk.getDisks().get(row).getName();
		}
		// size
		if (column == 1) {
			return Disk.getDisks().get(row).getSize();
		}
		// used
		if (column == 2) {
			return Disk.getDisks().get(row).getUsed();
		}
		throw new RuntimeException(ERR_STRING1);
	}

	public Class<?> getColumnClass(int columnIndex) {
		return (COLUMN_CLASS[columnIndex]);
	}

	public boolean isCellEditable(int row, int column) {
		return false;
	}

	public void setValueAt(Object value, int row, int column) {
	}
}
