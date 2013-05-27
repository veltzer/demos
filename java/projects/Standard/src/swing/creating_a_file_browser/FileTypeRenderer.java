package swing.creating_a_file_browser;

import java.awt.Component;
import java.io.File;

import javax.swing.JTable;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * Description of the Class
 * @author Mark Veltzer <mark@veltzer.net>
 */
@SuppressWarnings("serial")
public class FileTypeRenderer extends DefaultTableCellRenderer {
	/**
	 * Description of the Field
	 */
	private static FileSystemView fileSystem = FileSystemView
			.getFileSystemView();

	/**
	 * Gets the tableCellRendererComponent attribute of the FileTypeRenderer
	 * object
	 * @param table Description of the Parameter
	 * @param value Description of the Parameter
	 * @param selected Description of the Parameter
	 * @param hasFocus Description of the Parameter
	 * @param row Description of the Parameter
	 * @param column Description of the Parameter
	 * @return The tableCellRendererComponent value
	 */
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean selected, boolean hasFocus, int row, int column) {
		Component cmp = super.getTableCellRendererComponent(table, "",
				selected, hasFocus, row, column);
		setIcon(fileSystem.getSystemIcon((File) value));
		return (cmp);
	}
}
