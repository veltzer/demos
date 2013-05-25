package swing.unsorted;

import javax.swing.table.TableModel;

/**
 * This class adds a totals row to whatever model it is getting. Notice that
 * this is indeed a decorator because it extends the TableModelDecorator class
 * which is a decorator.
 */

@SuppressWarnings("serial")
public class TotalRowDecorator extends TableModelDecorator {
	private int colToSum;

	public TotalRowDecorator(TableModel model, int col) {
		super(model);
		colToSum = col;
	}

	public int getRowCount() {
		return super.getRowCount() + 1;
	}

	public Object getValueAt(int row, int col) {
		if (row == getRowCount() - 1) {
			long retVal = 0;
			if (col == colToSum) {
				for (int i = 0; i < getRowCount() - 1; i++) {
					retVal += (Long) getValueAt(i, col);
				}
			} else {
				return "Total";
			}
			return retVal;
		}
		return super.getValueAt(row, col);
	}
}
