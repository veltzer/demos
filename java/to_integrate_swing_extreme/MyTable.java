import javax.swing.JTable;
import javax.swing.table.TableModel;


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

	public void setTableColorModel(TableColorModel colorModel) {
		this.colorModel = colorModel;
	}
	
	
}
