package swing.advanced_swing;

import java.io.File;

import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class FileTableModel extends AbstractTableModel {
	private static final String[] COLUMN_NAMES = {
			"Type", "Name", "Size"
	};
	private static final Class<?>[] COLUMN_CLASS = {
			File.class, String.class, Long.class
	};
	private File[] files = new File[0];

	public void setDirectory(File directory) {
		files = directory.listFiles();
		fireTableDataChanged();
	}

	public int getColumnCount() {
		return (COLUMN_NAMES.length);
	}

	public String getColumnName(int offset) {
		return (COLUMN_NAMES[offset]);
	}

	public int getRowCount() {
		return files.length;
	}

	public Object getValueAt(int row, int column) {
		if (column == 0) {
			return files[row];
		}
		// if this is the name column
		if (column == 1) {
			return files[row].getName();
		}
		return new Long(files[row].length());
	}

	public Class<?> getColumnClass(int columnIndex) {
		return (COLUMN_CLASS[columnIndex]);
	}

	public boolean isCellEditable(int row, int column) {
		return (column == 1);
	}

	public void setValueAt(Object value, int row, int column) {
		File newFile = new File(files[row].getParent(), (String) value);
		files[row].renameTo(newFile);
		files[row] = newFile;
	}
}
