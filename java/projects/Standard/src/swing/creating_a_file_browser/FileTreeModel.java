package swing.creating_a_file_browser;

import java.io.File;
import java.io.FileFilter;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

/**
 * Description of the Class
 * @author Mark Veltzer <mark@veltzer.net>
 */
public class FileTreeModel implements TreeModel, FileFilter {
	/**
	 * Description of the Field
	 */
	private Object parent;
	/**
	 * Description of the Field
	 */
	private Object[] files;
	/**
	 * Description of the Field
	 */
	private static final String ROOT = "";

	/**
	 * Description of the Method
	 * @param parent Description of the Parameter
	 */
	private void updateFiles(Object iparent) {
		// updates the cached fields for the performance
		// of the tree
		if (parent == iparent) {
			return;
		}
		if (iparent == ROOT) {
			files = File.listRoots();
		} else {
			files = ((File) iparent).listFiles(this);
		}
		parent = iparent;
	}

	/**
	 * Gets the child attribute of the FileTreeModel object
	 * @param parent Description of the Parameter
	 * @param index Description of the Parameter
	 * @return The child value
	 */
	public Object getChild(Object iparent, int index) {
		updateFiles(iparent);
		return (files[index]);
	}

	/**
	 * Gets the childCount attribute of the FileTreeModel object
	 * @param parent Description of the Parameter
	 * @return The childCount value
	 */
	public int getChildCount(Object iparent) {
		updateFiles(iparent);
		return (files.length);
	}

	/**
	 * Gets the indexOfChild attribute of the FileTreeModel object
	 * @param parent Description of the Parameter
	 * @param child Description of the Parameter
	 * @return The indexOfChild value
	 */
	public int getIndexOfChild(Object iparent, Object child) {
		updateFiles(iparent);
		for (int iter = 0; iter < files.length; iter++) {
			if (files[iter].equals(child)) {
				return (iter);
			}
		}
		return (-1);
	}

	/**
	 * Gets the root attribute of the FileTreeModel object
	 * @return The root value
	 */
	public Object getRoot() {
		return ROOT;
	}

	/**
	 * Gets the leaf attribute of the FileTreeModel object
	 * @param parent Description of the Parameter
	 * @return The leaf value
	 */
	public boolean isLeaf(Object iparent) {
		updateFiles(iparent);
		if (iparent instanceof File) {
			return !((File) iparent).isDirectory();
		}
		return false;
	}

	/**
	 * Description of the Method
	 * @param path Description of the Parameter
	 * @param newValue Description of the Parameter
	 */
	public void valueForPathChanged(TreePath path, Object newValue) {
	}

	/**
	 * Adds a feature to the TreeModelListener attribute of the FileTreeModel
	 * object
	 * @param listener The feature to be added to the TreeModelListener
	 * attribute
	 */
	public void addTreeModelListener(TreeModelListener listener) {
	}

	/**
	 * Description of the Method
	 * @param listener Description of the Parameter
	 */
	public void removeTreeModelListener(TreeModelListener listener) {
	}

	/**
	 * Description of the Method
	 * @param pathname Description of the Parameter
	 * @return Description of the Return Value
	 */
	public boolean accept(File pathname) {
		return (pathname.isDirectory());
	}
}
