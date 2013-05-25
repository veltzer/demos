package swing.table_rendering;

import java.awt.Color;

import javax.swing.table.TableModel;

public class FinanceColorModel extends DefaultTableColorModel {
	private static final Color LIGHT_YELLOW = Color.YELLOW.brighter()
			.brighter();
	private TableModel model;

	public FinanceColorModel(TableModel imodel) {
		model = imodel;
	}

	public Color getBackground(int row, int column) {
		if (row % 2 == 1) {
			return (LIGHT_YELLOW);
		} else {
			return (super.getBackground(row, column));
		}
	}

	public Color getForeground(int row, int column) {
		if (column == 2) {
			Object value = model.getValueAt(row, column);
			if (value instanceof Currency) {
				Currency c = (Currency) value;
				if (c.getValue().doubleValue() < 500000) {
					return Color.RED;
				}
			}
		}
		return (super.getForeground(row, column));
	}

	public Color getSelectedForeground(int row, int column) {
		if (column == 2) {
			Currency c = (Currency) model.getValueAt(row, column);
			if (c.getValue().doubleValue() < 500000) {
				return Color.RED;
			}
		}
		return (super.getSelectedForeground(row, column));
	}
}
