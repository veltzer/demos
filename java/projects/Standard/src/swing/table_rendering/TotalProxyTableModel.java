package swing.table_rendering;

import java.math.BigDecimal;

import javax.swing.table.TableModel;

@SuppressWarnings("serial")
public class TotalProxyTableModel extends ProxyTableModel {
	private int totalColumn, descriptionColumn;
	private String totalString;

	public TotalProxyTableModel(TableModel model, int totalColumn,
			int descriptionColumn, String totalString) {
		super(model);
		this.totalColumn = totalColumn;
		this.descriptionColumn = descriptionColumn;
		this.totalString = totalString;
	}

	public int getRowCount() {
		return (super.getRowCount() + 1);
	}

	public Object getValueAt(int row, int col) {
		if (row == super.getRowCount()) {
			if (col == descriptionColumn) {
				return totalString;
			}
			if (col == totalColumn) {
				BigDecimal total = new BigDecimal(0);
				for (int iter = 0; iter < row; iter++) {
					total = total.add(((Currency) getValueAt(iter, col))
							.getValue());
				}
				return new Currency(total);
			}
			return "";
		} else {
			return (super.getValueAt(row, col));
		}
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if (rowIndex == getRowCount()) {
			return false;
		}
		return (super.isCellEditable(rowIndex, columnIndex));
	}
}
