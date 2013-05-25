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
public class MultiSortableTableModel extends ProxyTableModel {
	private int[] sortedOffsets;
	private int[] columns;
	private boolean[] ascending;

	public MultiSortableTableModel(TableModel model) {
		super(model);
		model.addTableModelListener(new TableModelListener() {
			public void tableChanged(TableModelEvent e) {
				sort(columns, ascending);
			}
		});
	}

	public void cancel() {
		sortedOffsets = null;
		fireTableDataChanged();
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

	public void sort(int[] icolumns, boolean[] iascending) {
		ArrayList<Comparator<Object>> comparators = new ArrayList<Comparator<Object>>(
				icolumns.length);
		for (int compIter = 0; compIter < icolumns.length; compIter++) {
			if (Number.class
					.isAssignableFrom(getColumnClass(icolumns[compIter]))) {
				comparators.add(new Comparator<Object>() {
					public int compare(Object o1, Object o2) {
						return (((Number) o1).intValue() - ((Number) o2)
								.intValue());
					}
				});
			} else {
				comparators.add(new FallbackComparator());
			}
		}

		columns = icolumns;
		ascending = iascending;
		int rowCount = getRowCount();
		List<Integer> rows = new ArrayList<Integer>(rowCount);
		for (int iter = 0; iter < rowCount; iter++) {
			rows.add(new Integer(iter));
		}

		Collections.sort(rows, new TableComparator(comparators, columns,
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
		private ArrayList<Comparator<Object>> comparators;
		private int[] columns;
		private boolean[] ascending;

		public TableComparator(ArrayList<Comparator<Object>> icomparators,
				int[] icolumns, boolean[] iascending) {
			comparators = icomparators;
			columns = icolumns;
			ascending = iascending;
		}

		private Object getRowData(Object row, int offset) {
			row = getRealValueAt(((Number) row).intValue(), columns[offset]);
			return (row);
		}

		public int compare(Object o1, Object o2) {
			int value = 0;
			for (int iter = 0; iter < comparators.size(); iter++) {
				value = comparators.get(iter).compare(getRowData(o1, iter),
						getRowData(o2, iter));
				if (value != 0) {
					if (!ascending[iter]) {
						value *= -1;
						return value;
					}
					return (value);
				}
			}
			if (ascending[ascending.length - 1]) {
				return (value);
			}
			return (value * -1);
		}
	}
}
