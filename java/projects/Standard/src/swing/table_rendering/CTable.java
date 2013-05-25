package swing.table_rendering;

import javax.swing.JTable;
import javax.swing.table.TableModel;

public class CTable extends JTable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TableColorModel colors = new DefaultTableColorModel();
	private TableAlignmentModel align;
	private VCellRenderer renderer;
	private SpanModel span = new SpanModel() {
		public boolean spanRight(int row, int column) {
			return false;
		}

		public boolean spanBottom(int row, int column) {
			return false;
		}

		public boolean isSpanRoot(int row, int column) {
			return false;
		}
	};

	public CTable(TableModel model) {
		super(model);
		renderer = new VCellRenderer();
		setDefaultRenderer(Object.class, renderer);
		setShowGrid(false);
		setIntercellSpacing(new java.awt.Dimension(0, 0));
	}

	public void setTableColorModel(TableColorModel colors) {
		this.colors = colors;
	}

	public TableColorModel getTableColorModel() {
		return colors;
	}

	public TableAlignmentModel getAlignmentModel() {
		return align;
	}

	public void setAlignmentModel(TableAlignmentModel align) {
		this.align = align;
	}

	public SpanModel getSpan() {
		return span;
	}

	public void setSpan(SpanModel span) {
		this.span = span;
	}
}
