package swing.table_rendering;

import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class BusinessTableModel extends AbstractTableModel {
	private static final String[] COLUMN_NAMES = {
			"Deal", "In Progress", "Net Worth"
	};
	private Object[] deals = new Object[] {
			"First", "Another Deal", "Yet Another", "And last one"
	};
	private Object[] inProgress = new Object[] {
			Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, Boolean.FALSE
	};
	private Object[] worth = new Object[] {
			new Currency(300000), new Currency(125000), new Currency(77777),
			new Currency(9999777)
	};

	public int getColumnCount() {
		return (COLUMN_NAMES.length);
	}

	public String getColumnName(int offset) {
		return (COLUMN_NAMES[offset]);
	}

	public int getRowCount() {
		return deals.length;
	}

	public Object getValueAt(int row, int column) {
		if (column == 0) {
			return deals[row];
		}
		if (column == 1) {
			return inProgress[row];
		}
		return worth[row];
	}

	public Class<?> getColumnClass(int columnIndex) {
		return (Object.class);
	}
}
