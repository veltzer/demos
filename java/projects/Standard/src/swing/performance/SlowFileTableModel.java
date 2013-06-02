package swing.performance;

import java.awt.Image;
import java.io.File;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class SlowFileTableModel extends AbstractTableModel {
	private static final String[] COLUMN_NAMES = {
			"Type", "Name", "Size", "Content"
	};
	private static final Class<?>[] COLUMN_CLASS = {
			File.class, String.class, Long.class, Icon.class
	};
	private static final Icon LOADING = new ImageIcon("loading.gif");
	private static final Icon NONE = new ImageIcon("none.gif");
	private Icon[] icons = new Icon[0];
	private File[] files = new File[0];

	public void setDirectory(File directory) {
		files = directory.listFiles();
		icons = new Icon[files.length];
		fireTableDataChanged();
	}

	public int getColumnCount() {
		return (COLUMN_NAMES.length);
	}

	public String getColumnName(int offset) {
		return (COLUMN_NAMES[offset]);
	}

	public int getRowCount() {
		return files.length;
	}

	public Object getValueAt(int row, int column) {
		if (column == 0) {
			return files[row];
		}
		// if this is the name column
		if (column == 1) {
			return files[row].getName();
		}
		if (column == 2) {
			return new Long(files[row].length());
		}

		if (icons[row] == null) {
			// perform async load
			icons[row] = LOADING;
			new Thread(new IconLoader(files[row], row)).start();
		}
		return (icons[row]);
	}

	public Class<?> getColumnClass(int columnIndex) {
		return (COLUMN_CLASS[columnIndex]);
	}

	public boolean isCellEditable(int row, int column) {
		return (column == 1);
	}

	public void setValueAt(Object value, int row, int column) {
		File newFile = new File(files[row].getParent(), (String) value);
		files[row].renameTo(newFile);
		files[row] = newFile;
	}

	class IconLoader implements Runnable {
		private File file;
		private int offset;

		IconLoader(File ifile, int ioffset) {
			file = ifile;
			offset = ioffset;
		}

		public void run() {
			try {
				ImageIcon icon = new ImageIcon(file.getAbsolutePath());
				icon = new ImageIcon(icon.getImage().getScaledInstance(16, 16,
						Image.SCALE_SMOOTH));
				icons[offset] = icon;
			} catch (Exception notAnImage) {
				icons[offset] = NONE;
			}
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					fireTableRowsUpdated(offset, offset);
				}
			});
		}

	}
}
