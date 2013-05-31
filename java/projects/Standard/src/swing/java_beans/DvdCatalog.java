package swing.java_beans;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.ExceptionListener;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.text.JTextComponent;

@SuppressWarnings("serial")
public class DvdCatalog extends JFrame implements Runnable {
	private JTextComponent title;
	private JTextComponent by;
	private JTextComponent year;
	private JTextComponent featuring;
	private JTextComponent keywords;
	private JToggleButton dvd;
	private JToggleButton cd;
	private JTable table;

	private DvdModel tableModel;

	private static final Icon DVD_ICON = new ImageIcon(
			DvdCatalog.class.getResource("/dvd.gif"));
	private static final Icon CD_ICON = new ImageIcon(
			DvdCatalog.class.getResource("/cd.gif"));
	private static final Icon MINI_DISC = new ImageIcon(
			DvdCatalog.class.getResource("/minicd.gif"));
	private static final Icon[] ICONS = new Icon[] {
			DVD_ICON, CD_ICON, MINI_DISC
	};
	private static final File CD_DATA_FILE = new File("dvd_collection.xml");

	public DvdCatalog() throws IOException {
		super("DVD/CD Catalog");
		if (CD_DATA_FILE.exists()) {
			XMLDecoder d = new XMLDecoder(new BufferedInputStream(
					new FileInputStream(CD_DATA_FILE)));
			tableModel = (DvdModel) d.readObject();
			d.close();
		} else {
			tableModel = new DvdModel();
		}
	}

	public static void main(String[] argv) throws Exception {
		SwingUtilities.invokeLater(new DvdCatalog());
	}

	public void run() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		JTabbedPane pane = new JTabbedPane();
		pane.add("DVD/CD", createAddDvdPane());
		pane.add("Disc List", createTablePane());
		getContentPane().add("Center", pane);
		setLocation(100, 100);
		setSize(800, 600);
		invalidate();
		validate();
		setVisible(true);
	}

	private JComponent createTablePane() {
		JPanel b = new JPanel(new java.awt.BorderLayout());
		table = new JTable(tableModel);
		IconRenderer r = new IconRenderer();
		table.setDefaultRenderer(Integer.class, r);
		b.add("Center", new JScrollPane(table));

		JPanel removePane = new JPanel(new java.awt.FlowLayout());
		JButton remove = new JButton(new RemoveAction());
		removePane.add(remove);
		b.add("South", removePane);
		return (b);
	}

	private JComponent createAddDvdPane() {
		JPanel pane = new JPanel();
		pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));

		title = new JTextField(15);
		by = new JTextField(15);
		year = new JTextField(15);
		featuring = new JTextField(15);
		keywords = new JTextField(15);

		JPanel pairPanel = new JPanel(new GridLayout(5, 3));
		pairPanel.add(new JLabel("Title"));
		pairPanel.add(title);
		pairPanel.add(new JLabel("By"));
		pairPanel.add(by);
		pairPanel.add(new JLabel("Featuring"));
		pairPanel.add(featuring);
		pairPanel.add(new JLabel("Year"));
		pairPanel.add(year);
		pairPanel.add(new JLabel("Keywords"));
		pairPanel.add(keywords);
		pane.add(pairPanel);

		ButtonGroup group = new ButtonGroup();
		dvd = new JRadioButton("DVD", true);
		cd = new JRadioButton("CD", false);
		JRadioButton minicd = new JRadioButton("MiniCD", false);
		group.add(dvd);
		group.add(cd);
		group.add(minicd);
		pane.add(createGroup(dvd, cd, minicd));

		JButton btn = new JButton("Add");
		pane.add(btn);
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				tableModel.add(title.getText(), by.getText(),
						featuring.getText(), year.getText(),
						keywords.getText(), dvd.isSelected(), cd.isSelected());
				title.setText("");
				by.setText("");
				featuring.setText("");
				year.setText("");
				keywords.setText("");
			}
		});
		return (pane);
	}

	private JComponent createGroup(JComponent a, JComponent b, JComponent c) {
		Box box = new Box(BoxLayout.LINE_AXIS);
		box.add(Box.createGlue());
		box.add(a);
		box.add(Box.createGlue());
		box.add(Box.createHorizontalStrut(10));
		box.add(b);
		box.add(Box.createGlue());
		box.add(Box.createHorizontalStrut(10));
		box.add(c);
		box.add(Box.createGlue());
		return (box);
	}

	static class IconRenderer extends DefaultTableCellRenderer {
		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {
			super.getTableCellRendererComponent(table, "", isSelected,
					hasFocus, row, column);
			setIcon(ICONS[((Number) value).intValue()]);
			return (this);
		}
	}

	public static class DvdModel extends AbstractTableModel implements
			TableModel, Serializable, ExceptionListener {
		static final transient String[] COLUMN_NAMES = {
				"Title", "By", "Featuring", "Year", "Keywords", "Media"
		};
		static final transient Class<?>[] COLUMN_CLASSES = {
				String.class, String.class, String.class, String.class,
				String.class, Integer.class
		};
		private List<Object[]> tableData = new ArrayList<Object[]>();

		public List<Object[]> getTableData() {
			return (tableData);
		}

		public void setTableData(List<Object[]> itableData) {
			tableData = itableData;
		}

		public void add(String title, String by, String featuring, String year,
				String keywords, boolean isDvd, boolean isCd) {
			if (isDvd) {
				tableData.add(new Object[] {
						title, by, featuring, year, keywords, new Integer(0)
				});
			} else {
				if (isCd) {
					tableData.add(new Object[] {
							title, by, featuring, year, keywords,
							new Integer(1)
					});
				} else {
					tableData.add(new Object[] {
							title, by, featuring, year, keywords,
							new Integer(2)
					});
				}
			}
			fireTableRowsInserted(tableData.size() - 1, tableData.size() - 1);
			store();
		}

		public void remove(int row) {
			tableData.remove(row);
			fireTableRowsDeleted(row, row);
			store();
		}

		private void store() {
			try {
				XMLEncoder enc = new XMLEncoder(new BufferedOutputStream(
						new FileOutputStream(CD_DATA_FILE)));
				enc.setExceptionListener(this);
				enc.writeObject(this);
				enc.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		public Class<?> getColumnClass(int param) {
			return (COLUMN_CLASSES[param]);
		}

		public int getColumnCount() {
			return (COLUMN_NAMES.length);
		}

		public String getColumnName(int param) {
			return (COLUMN_NAMES[param]);
		}

		public int getRowCount() {
			return (tableData.size());
		}

		public Object getValueAt(int row, int column) {
			return (tableData.get(row)[column]);
		}

		public boolean isCellEditable(int param, int param1) {
			return (false);
		}

		public void setValueAt(Object obj, int param, int param2) {
			// the table is not editable at the moment
		}

		public void exceptionThrown(Exception e) {
			throw new RuntimeException(e);
		}
	}

	class RemoveAction extends AbstractAction implements ListSelectionListener {
		public RemoveAction() {
			putValue(NAME, "Remove");
			table.getSelectionModel().addListSelectionListener(this);
		}

		public void actionPerformed(java.awt.event.ActionEvent actionEvent) {
			tableModel.remove(table.getSelectedRow());
		}

		public void valueChanged(
				javax.swing.event.ListSelectionEvent listSelectionEvent) {
			setEnabled(table.getSelectedRowCount() == 1);
		}

	}
}
