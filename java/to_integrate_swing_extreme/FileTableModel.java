import java.io.File;
import java.util.List;

import javax.swing.table.AbstractTableModel;


public class FileTableModel extends AbstractTableModel {

	private File dir;
	private File[] fileList = new File[0];
	
	public void setDirectory(File dir) {
		this.dir = dir;
		fileList = dir.listFiles();
		fireTableStructureChanged();
	}
	
	
	
	public int getColumnCount() {
		//System.out.println("getColumnCount()");
		// TODO Auto-generated method stub
		return 2;
	}

	public int getRowCount() {
//		System.out.println("getRowCount()");
		// TODO Auto-generated method stub
		return fileList.length;

	}

	public Object getValueAt(int row, int col) {
//		System.out.println("getValueAt(" + row + "," + col +")");
		if (col == 0)
			return fileList[row].getName();
		else
			return fileList[row].length();
	}



	@Override
	public String getColumnName(int col) {
//		System.out.println("getColumnName(" + col + ")");
		// TODO Auto-generated method stub
		if (col == 0)
			return "Name";
		else
			return "Size";
	}
	
	public Class getColumnClass(int col) {
		if (col == 0) 
			return String.class;
		else
			return Long.class;
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
