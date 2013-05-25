package swing.advanced_swing;

import javax.swing.table.TableModel;

public class FilterTableModel extends ProxyTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int[] rows;

	public FilterTableModel(TableModel model) {
		this(model, null);
	}

	public FilterTableModel(TableModel model, int[] rows) {
		super(model);
		this.rows = rows;
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if (rows == null) {
			super.setValueAt(aValue, rowIndex, columnIndex);
		} else {
			super.setValueAt(aValue, rows[rowIndex], columnIndex);
		}
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		if (rows == null) {
			return (super.getValueAt(rowIndex, columnIndex));
		} else {
			return (super.getValueAt(rows[rowIndex], columnIndex));
		}
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if (rows == null) {
			return (super.isCellEditable(rowIndex, columnIndex));
		} else {
			return (super.isCellEditable(rows[rowIndex], columnIndex));
		}
	}

	public int getRowCount() {
		if (rows == null) {
			return (super.getRowCount());
		} else {
			return (rows.length);
		}
	}

	public void updateRows(int[] rows) {
		this.rows = rows;
		fireTableDataChanged();
	}

	public int getRow(int row) {
		if (rows != null) {
			return (rows[row]);
		}
		return (row);
	}
}
