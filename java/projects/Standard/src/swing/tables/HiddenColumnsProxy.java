package swing.tables;

import javax.swing.table.TableModel;

@SuppressWarnings("serial")
public class HiddenColumnsProxy extends ProxyTableModel {
	private int[] realColumns;

	public HiddenColumnsProxy(TableModel model, int[] irealColumns) {
		super(model);
		this.realColumns = irealColumns;
	}

	public int[] getColumns() {
		return realColumns;
	}

	public int getRealOffset(int column) {
		for (int iter = 0; iter < realColumns.length; iter++) {
			if (realColumns[iter] == column) {
				return iter;
			}
		}
		return -1;
	}

	public void setColumns(int[] columns) {
		realColumns = columns;
		fireTableStructureChanged();
	}

	public Class<?> getColumnClass(int columnIndex) {
		return (super.getColumnClass(realColumns[columnIndex]));
	}

	public int getColumnCount() {
		return (realColumns.length);
	}

	public String getColumnName(int columnIndex) {
		return (super.getColumnName(realColumns[columnIndex]));
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		return (super.getValueAt(rowIndex, realColumns[columnIndex]));
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return (super.isCellEditable(rowIndex, realColumns[columnIndex]));
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		super.setValueAt(aValue, rowIndex, realColumns[columnIndex]);
	}
}
