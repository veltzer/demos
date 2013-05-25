package swing.table_rendering;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

@SuppressWarnings("serial")
public abstract class ProxyTableModel extends AbstractTableModel {
	private TableModel model;

	public ProxyTableModel(TableModel imodel) {
		model = imodel;
	}

	public TableModel getModel() {
		return (model);
	}

	public void addTableModelListener(TableModelListener l) {
		super.addTableModelListener(l);
		model.addTableModelListener(l);
	}

	public Class<?> getColumnClass(int columnIndex) {
		return (model.getColumnClass(columnIndex));
	}

	public int getColumnCount() {
		return (model.getColumnCount());
	}

	public String getColumnName(int columnIndex) {
		return (model.getColumnName(columnIndex));
	}

	public int getRowCount() {
		return (model.getRowCount());
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		return (model.getValueAt(rowIndex, columnIndex));
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return (model.isCellEditable(rowIndex, columnIndex));
	}

	public void removeTableModelListener(TableModelListener l) {
		model.removeTableModelListener(l);
		super.removeTableModelListener(l);
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		model.setValueAt(aValue, rowIndex, columnIndex);
	}
}
