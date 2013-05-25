package swing.unsorted;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

@SuppressWarnings("serial")
public class TableModelDecorator extends AbstractTableModel {
	private TableModel model;

	public TableModelDecorator(TableModel imodel) {
		super();
		model = imodel;
	}

	public void addTableModelListener(TableModelListener arg0) {
		model.addTableModelListener(arg0);
	}

	public Class<?> getColumnClass(int arg0) {
		return model.getColumnClass(arg0);
	}

	public int getColumnCount() {
		return model.getColumnCount();
	}

	public String getColumnName(int arg0) {
		return model.getColumnName(arg0);
	}

	public int getRowCount() {
		return model.getRowCount();
	}

	public Object getValueAt(int arg0, int arg1) {
		return model.getValueAt(arg0, arg1);
	}

	public boolean isCellEditable(int arg0, int arg1) {
		return model.isCellEditable(arg0, arg1);
	}

	public void removeTableModelListener(TableModelListener arg0) {
		model.removeTableModelListener(arg0);
	}

	public void setValueAt(Object arg0, int arg1, int arg2) {
		model.setValueAt(arg0, arg1, arg2);
	}
}
