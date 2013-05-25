package swing.unsorted;

import java.io.File;
import java.io.FileFilter;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

public class DirTreeModel implements TreeModel, FileFilter {

	private Object parent;
	private Object[] files;
	private static final String ROOT = "";

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

	public Object getChild(Object iparent, int index) {
		updateFiles(iparent);
		return (files[index]);
	}

	public int getChildCount(Object iparent) {
		updateFiles(iparent);
		return (files.length);
	}

	public int getIndexOfChild(Object iparent, Object child) {
		updateFiles(iparent);
		for (int iter = 0; iter < files.length; iter++) {
			if (files[iter].equals(child)) {
				return (iter);
			}
		}
		return -1;
	}

	public Object getRoot() {
		return ROOT;
	}

	public boolean isLeaf(Object iparent) {
		updateFiles(iparent);
		if (iparent instanceof File) {
			return !((File) iparent).isDirectory();
		}
		return false;
	}

	public void valueForPathChanged(TreePath path, Object newValue) {
	}

	public void addTreeModelListener(TreeModelListener listener) {
	}

	public void removeTreeModelListener(TreeModelListener listener) {
	}

	public boolean accept(File pathname) {
		return (pathname.isDirectory());
	}
}
