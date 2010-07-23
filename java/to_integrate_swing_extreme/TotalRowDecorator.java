import javax.swing.table.TableModel;


public class TotalRowDecorator extends TableModelDecorator {

	private int colToSum;
	
	public TotalRowDecorator(TableModel model, int col) {
		super(model);
		this.colToSum = col;
		// TODO Auto-generated constructor stub
	}
	
	public int getRowCount() {
		return super.getRowCount() + 1;
	}
	
	public Object getValueAt(int row, int col) {
		if (row == getRowCount() - 1) {
			long retVal = 0;
			if (col == colToSum){	
				for (int i = 0 ; i < getRowCount() - 1 ; i++) {
					retVal += (Long)getValueAt(i, col);
				}
			} else {
				return "Total";
			}
			return retVal;
		}
	
	return super.getValueAt(row, col);
	}

	
}
