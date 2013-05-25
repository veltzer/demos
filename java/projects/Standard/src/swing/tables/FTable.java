package swing.tables;

import java.awt.event.AdjustmentListener;
import java.util.Arrays;

import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

@SuppressWarnings("serial")
public class FTable extends JSplitPane {
	private JScrollPane frozenPane;
	private JScrollPane notFrozenPane;

	private int[] getNotFrozen(int count, int[] frozenColumns) {
		int[] notFrozen = new int[count - frozenColumns.length];
		int current = 0;
		for (int iter = 0; iter < count; iter++) {
			if (Arrays.binarySearch(frozenColumns, iter) < 0) {
				notFrozen[current] = iter;
				current++;
			}
		}
		return (notFrozen);
	}

	public FTable(TableModel model, int[] frozenColumns) {
		setDividerSize(2);
		setContinuousLayout(true);
		int[] notFrozen = getNotFrozen(model.getColumnCount(), frozenColumns);

		JTable frozenTable = new JTable(new HiddenColumnsProxy(model,
				frozenColumns));
		JTable notFrozenTable = new JTable(new HiddenColumnsProxy(model,
				notFrozen));
		setBorder(frozenTable.getBorder());
		frozenTable.setBorder(null);
		notFrozenTable.setBorder(null);
		frozenTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		notFrozenTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		frozenPane = new JScrollPane(frozenTable,
				JScrollPane.VERTICAL_SCROLLBAR_NEVER,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		notFrozenPane = new JScrollPane(notFrozenTable,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		setOrientation(HORIZONTAL_SPLIT);
		setLeftComponent(frozenPane);
		setRightComponent(notFrozenPane);
		notFrozenPane.getVerticalScrollBar().addAdjustmentListener(
				new AdjustmentListener() {
					public void adjustmentValueChanged(
							java.awt.event.AdjustmentEvent ev) {
						frozenPane.getVerticalScrollBar().setValue(
								ev.getValue());
					}
				});
		frozenPane.getVerticalScrollBar().addAdjustmentListener(
				new AdjustmentListener() {
					public void adjustmentValueChanged(
							java.awt.event.AdjustmentEvent ev) {
						notFrozenPane.getVerticalScrollBar().setValue(
								ev.getValue());
					}
				});
		frozenTable.setSelectionModel(notFrozenTable.getSelectionModel());
	}
}
