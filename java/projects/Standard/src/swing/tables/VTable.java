package swing.tables;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JLabel;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

@SuppressWarnings("serial")
public class VTable extends JTable {
	private boolean[] ascending = null;
	private int[] columns = null;
	private TableModel model;
	private MultiSortableTableModel sortModel;
	private HiddenColumnsProxy hiddenModel;

	private boolean toggleAscending(int column) {
		for (int iter = 0; iter < columns.length; iter++) {
			if (columns[iter] == column) {
				ascending[iter] = !ascending[iter];
				return true;
			}
		}
		return false;
	}

	private TableModel wrapModel(TableModel m) {
		model = m;
		sortModel = new MultiSortableTableModel(m);
		int[] icolumns = new int[m.getColumnCount()];
		for (int iter = 0; iter < icolumns.length; iter++) {
			icolumns[iter] = iter;
		}
		hiddenModel = new HiddenColumnsProxy(sortModel, icolumns);
		return (hiddenModel);
	}

	private void columnsPopup(MouseEvent ev) {
		JPopupMenu menu = new JPopupMenu();
		JCheckBoxMenuItem[] columnMenus = new JCheckBoxMenuItem[model
				.getColumnCount()];
		for (int iter = 0; iter < columnMenus.length; iter++) {
			boolean selected = isTheColumnSelected(iter);
			String columnName = model.getColumnName(iter);
			if (selected) {
				columnMenus[iter] = new JCheckBoxMenuItem(new HideAction(
						columnName, iter));
			} else {
				columnMenus[iter] = new JCheckBoxMenuItem(new ShowAction(
						columnName, iter));
			}
			columnMenus[iter].setSelected(selected);
			menu.add(columnMenus[iter]);
		}
		menu.show((Component) ev.getSource(), ev.getX(), ev.getY());
	}

	public VTable(TableModel imodel) {
		super(imodel);
		setModel(wrapModel(imodel));
		getTableHeader().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent ev) {
				if (SwingUtilities.isRightMouseButton(ev)) {
					columnsPopup(ev);
					return;
				}
				int column = convertColumnIndexToModel(columnAtPoint(ev
						.getPoint()));
				if ((columns != null)
						&& (ev.getModifiers() & KeyEvent.SHIFT_MASK) == KeyEvent.SHIFT_MASK) {
					if (!toggleAscending(column)) {
						int[] icolumns = new int[VTable.this.columns.length + 1];
						boolean[] iascending = new boolean[VTable.this.ascending.length + 1];
						System.arraycopy(VTable.this.columns, 0, icolumns, 0,
								icolumns.length - 1);
						System.arraycopy(VTable.this.ascending, 0, iascending,
								0, iascending.length - 1);
						icolumns[columns.length - 1] = column;
						iascending[iascending.length - 1] = true;
						VTable.this.columns = columns;
						VTable.this.ascending = ascending;
					}
				} else {
					columns = new int[] {
						column
					};
					ascending = new boolean[] {
						true
					};
				}
				sortModel.sort(columns, ascending);
			}
		});
		getTableHeader().setDefaultRenderer(new HeaderRenderer());
	}

	private boolean isTheColumnSelected(int column) {
		int[] icolumns = hiddenModel.getColumns();
		for (int iter = 0; iter < icolumns.length; iter++) {
			if (icolumns[iter] == column) {
				return true;
			}
		}
		return false;
	}

	private int getPos(int column) {
		if (columns != null) {
			for (int iter = 0; iter < columns.length; iter++) {
				if (column == columns[iter]) {
					return iter;
				}
			}
		}
		return -1;
	}

	class HeaderRenderer extends DefaultTableCellRenderer {
		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {
			JLabel l = (JLabel) super.getTableCellRendererComponent(table,
					value, isSelected, hasFocus, row, column);
			if (table != null) {
				JTableHeader header = table.getTableHeader();
				if (header != null) {
					l.setForeground(header.getForeground());
					l.setBackground(header.getBackground());
					l.setFont(header.getFont());
				}
			}
			l.setHorizontalTextPosition(SwingConstants.CENTER);
			l.setHorizontalAlignment(SwingConstants.CENTER);

			int pos = getPos(convertColumnIndexToModel(column));
			if (pos > -1) {
				l.setIcon(new ArrowIcon(l.getFont().getSize(), pos));
			} else {
				l.setIcon(null);
			}
			setBorder(UIManager.getBorder("TableHeader.cellBorder"));
			return (l);
		}
	}

	private static final Color FILL_COLOR = new Color(
			Color.GRAY.getRGB() | 0x7F000000);

	class ArrowIcon implements Icon {
		private int size;
		private int position;

		public ArrowIcon(int isize, int iposition) {
			size = isize;
			position = iposition;
		}

		public int getIconHeight() {
			return size;
		}

		public int getIconWidth() {
			return size;
		}

		public void paintIcon(Component c, java.awt.Graphics g, int x, int y) {
			Polygon p;
			int[] xarray = new int[] {
					0, size, size / 2
			};
			if (ascending[position]) {
				p = new Polygon(xarray, new int[] {
						1, 1, size
				}, 3);
			} else {
				p = new Polygon(xarray, new int[] {
						size, size, 1
				}, 3);
			}
			Color color = FILL_COLOR;
			Color outline = Color.BLACK;
			for (int iter = 0; iter < position; iter++) {
				color = color.brighter();
				outline = outline.brighter();
			}
			Graphics2D g2d = (Graphics2D) g;
			g2d.setColor(color);
			g2d.fill(p);
			g2d.setColor(outline);
			g2d.draw(p);
		}
	}

	class HideAction extends AbstractAction {
		private int column;

		public HideAction(String name, int icolumn) {
			putValue(NAME, name);
			column = icolumn;
		}

		public void actionPerformed(ActionEvent e) {
			int[] icolumns = hiddenModel.getColumns();
			int counter = 0;
			int[] newColumns = new int[icolumns.length - 1];
			for (int iter = 0; iter < icolumns.length; iter++) {
				if (icolumns[iter] != column) {
					newColumns[counter] = icolumns[iter];
					counter++;
				}
			}
			hiddenModel.setColumns(newColumns);
			sortModel.cancel();
		}
	}

	class ShowAction extends AbstractAction {
		private int column;

		public ShowAction(String name, int icolumn) {
			putValue(NAME, name);
			column = icolumn;
		}

		public void actionPerformed(ActionEvent e) {
			int[] icolumns = hiddenModel.getColumns();
			int[] newColumns = new int[icolumns.length + 1];
			System.arraycopy(icolumns, 0, newColumns, 0, icolumns.length);
			newColumns[icolumns.length] = column;
			hiddenModel.setColumns(newColumns);
			sortModel.cancel();
		}
	}
}
