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

	private void updateFiles(Object parent) {
		// updates the cached fields for the performance
		// of the tree
		if(this.parent == parent) {
			return;
		}
		if(parent == ROOT) {
			files = File.listRoots();

		} else {
			files = ((File)parent).listFiles(this);
		}
		this.parent = parent;
	}


	public Object getChild(Object parent, int index) {
		updateFiles(parent);
		return(files[index]);
	}

	public int getChildCount(Object parent) {
		updateFiles(parent);
		return(files.length);
	}


	public int getIndexOfChild(Object parent, Object child) {
		updateFiles(parent);
		for(int iter=0 ; iter < files.length ; iter++) {
			if(files[iter].equals(child)) {
				return(iter);
			}
		}
		return(-1);
	}

	public Object getRoot() {
		return ROOT;
	}

	public boolean isLeaf(Object parent) {
		updateFiles(parent);
		if(parent instanceof File) {
			return !((File)parent).isDirectory();
		}
		return false;
	}

	public void valueForPathChanged(TreePath path, Object newValue) {}
	public void addTreeModelListener(TreeModelListener listener) {}
	public void removeTreeModelListener(TreeModelListener listener) {}

	public boolean accept(File pathname) {
		return(pathname.isDirectory());
	}
}
