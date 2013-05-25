package swing.unsorted;

import java.io.File;

import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class FileTableModel extends AbstractTableModel {
	private File[] fileList = new File[0];

	public void setDirectory(File dir) {
		fileList = dir.listFiles();
		fireTableStructureChanged();
	}

	public int getColumnCount() {
		// System.out.println("getColumnCount()");
		return 2;
	}

	public int getRowCount() {
		// System.out.println("getRowCount()");
		return fileList.length;

	}

	public Object getValueAt(int row, int col) {
		// System.out.println("getValueAt(" + row + "," + col +")");
		if (col == 0) {
			return fileList[row].getName();
		} else {
			return fileList[row].length();
		}
	}

	@Override
	public String getColumnName(int col) {
		// System.out.println("getColumnName(" + col + ")");
		if (col == 0) {
			return "Name";
		} else {
			return "Size";
		}
	}

	public Class<?> getColumnClass(int col) {
		if (col == 0) {
			return String.class;
		} else {
			return Long.class;
		}
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return true;
	}

	@Override
	public void setValueAt(Object value, int rowIndex, int columnIndex) {
		System.out.println("Settign the value of " + rowIndex + " to " + value);
	}

}
