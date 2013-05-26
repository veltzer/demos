package swing.creating_a_file_browser;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * Description of the Class
 * @author Mark Veltzer <mark@veltzer.net>
 */
@SuppressWarnings("serial")
public class FileSizeRenderer extends DefaultTableCellRenderer {
	/**
	 * Description of the Field
	 */
	private static final String[] SUFFIX = {
			"b", "kb", "mb", "gb", "tb", "pb"
	};
	/**
	 * Description of the Field
	 */
	private static final long[] SIZES = {
			1024, 1024 * 1024, 1024 * 1024 * 1024, 1024 * 1024 * 1024 * 1024,
			1024 * 1024 * 1024 * 1024 * 1024,
			1024 * 1024 * 1024 * 1024 * 1024 * 1024
	};

	/**
	 * Gets the tableCellRendererComponent attribute of the FileSizeRenderer
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
		long size = ((Number) value).longValue();
		for (int iter = 0; iter < SIZES.length; iter++) {
			if (size < SIZES[iter]) {
				if (iter != 0) {
					size = size / SIZES[iter - 1];
				}
				value = size + SUFFIX[iter];
				break;
			}
		}
		Component cmp = super.getTableCellRendererComponent(table, value,
				selected, hasFocus, row, column);
		return (cmp);
	}
}
