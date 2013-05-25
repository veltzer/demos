package swing.table_rendering;

import javax.swing.table.TableModel;

public class TotalRowSpanModel implements SpanModel {
	private TableModel model;

	public TotalRowSpanModel(TableModel imodel) {
		model = imodel;
	}

	public boolean spanBottom(int row, int column) {
		return false;
	}

	public boolean spanRight(int row, int column) {
		return ((row == model.getRowCount() - 1) && (column < 1));
	}

	public boolean isSpanRoot(int row, int column) {
		return ((column == 0) && (row == model.getRowCount() - 1));
	}
}
