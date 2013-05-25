package swing.advanced_swing;

import javax.swing.table.TableModel;

public class ReadOnlyTableModel extends ProxyTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ReadOnlyTableModel(TableModel model) {
		super(model);
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return (false);
	}
}
