package swing.unsorted;

import javax.swing.JTable;
import javax.swing.table.TableModel;

@SuppressWarnings("serial")
public class MyTable extends JTable {
	private TableColorModel colorModel;

	public MyTable(TableModel model) {
		super(model);
		colorModel = new ZebraColorModel();
		setDefaultRenderer(Object.class, new ColorAwareTableCellRenderer());
		setDefaultRenderer(Long.class, new FileSizeTableCellRenderer());
	}

	public TableColorModel getTableColorModel() {
		return colorModel;
	}

	public void setTableColorModel(TableColorModel icolorModel) {
		colorModel = icolorModel;
	}
}
