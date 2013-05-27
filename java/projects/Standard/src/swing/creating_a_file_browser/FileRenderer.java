package swing.creating_a_file_browser;

import java.awt.Component;
import java.io.File;

import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

/**
 * Description of the Class
 * @author Mark Veltzer <mark@veltzer.net>
 */
@SuppressWarnings("serial")
public class FileRenderer extends DefaultTreeCellRenderer {
	/**
	 * Gets the treeCellRendererComponent attribute of the FileRenderer object
	 * @param tree Description of the Parameter
	 * @param value Description of the Parameter
	 * @param selected Description of the Parameter
	 * @param expanded Description of the Parameter
	 * @param leaf Description of the Parameter
	 * @param row Description of the Parameter
	 * @param hasFocus Description of the Parameter
	 * @return The treeCellRendererComponent value
	 */
	public Component getTreeCellRendererComponent(JTree tree, Object value,
			boolean selected, boolean expanded, boolean leaf, int row,
			boolean hasFocus) {
		value = ((File) value).getName();
		Component cmp = super.getTreeCellRendererComponent(tree, value,
				selected, expanded, leaf, row, hasFocus);
		return (cmp);
	}
}
