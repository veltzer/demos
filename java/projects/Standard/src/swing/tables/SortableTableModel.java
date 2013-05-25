package swing.tables;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

@SuppressWarnings("serial")
public class SortableTableModel extends ProxyTableModel {
	private int[] sortedOffsets;
	private int column;
	private Comparator<Object> comparator;
	private boolean ascending;

	public SortableTableModel(TableModel model) {
		super(model);
		model.addTableModelListener(new TableModelListener() {
			public void tableChanged(TableModelEvent e) {
				sort(column, comparator, ascending);
			}
		});
	}

	private int translate(int row) {
		if (sortedOffsets == null) {
			return (row);
		}
		return (sortedOffsets[row]);
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		return (getModel().getValueAt(translate(rowIndex), columnIndex));
	}

	private Object getRealValueAt(int rowIndex, int columnIndex) {
		return (getModel().getValueAt(rowIndex, columnIndex));
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return (getModel().isCellEditable(translate(rowIndex), columnIndex));
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		getModel().setValueAt(aValue, translate(rowIndex), columnIndex);
	}

	public void sort(int column, Comparator<Object> comparator,
			boolean ascending) {
		if (comparator == null) {
			if (Number.class.isAssignableFrom(getColumnClass(column))) {
				comparator = new Comparator<Object>() {
					public int compare(Object o1, Object o2) {
						return (((Number) o1).intValue() - ((Number) o2)
								.intValue());
					}
				};
			} else {
				comparator = new FallbackComparator();
			}
		}

		this.column = column;
		this.comparator = comparator;
		this.ascending = ascending;
		int rowCount = getRowCount();
		List<Integer> rows = new ArrayList<Integer>(rowCount);
		for (int iter = 0; iter < rowCount; iter++) {
			rows.add(new Integer(iter));
		}

		Collections.sort(rows, new TableComparator(comparator, column,
				ascending));

		if ((sortedOffsets == null) || (sortedOffsets.length != rowCount)) {
			sortedOffsets = new int[rowCount];
		}
		Iterator<Integer> current = rows.iterator();
		for (int counter = 0; counter < rowCount; counter++) {
			sortedOffsets[counter] = current.next().intValue();
		}
	}

	class FallbackComparator implements Comparator<Object> {
		private Collator c = Collator.getInstance();

		private String getData(Object o) {
			if (o != null) {
				return (o.toString());
			} else {
				return ("");
			}
		}

		public int compare(Object o1, Object o2) {
			int value = c.compare(getData(o1), getData(o2));
			return (value);
		}
	}

	class TableComparator implements Comparator<Object> {
		private Comparator<Object> comparator;
		private int column;
		private boolean ascending;

		public TableComparator(Comparator<Object> comparator, int column,
				boolean ascending) {
			this.comparator = comparator;
			this.column = column;
			this.ascending = ascending;
		}

		private Object getRowData(Object row) {
			row = getRealValueAt(((Number) row).intValue(), column);
			return (row);
		}

		public int compare(Object o1, Object o2) {
			int value = comparator.compare(getRowData(o1), getRowData(o2));
			if (ascending) {
				return (value);
			}
			return (value * -1);
		}
	}
}
