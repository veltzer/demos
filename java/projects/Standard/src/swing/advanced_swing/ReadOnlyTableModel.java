package swing.advanced_swing;

import javax.swing.table.TableModel;

@SuppressWarnings("serial")
public class ReadOnlyTableModel extends ProxyTableModel {
	public ReadOnlyTableModel(TableModel model) {
		super(model);
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return (false);
	}
}
