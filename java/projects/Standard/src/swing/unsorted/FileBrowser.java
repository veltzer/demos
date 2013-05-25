package swing.unsorted;

import java.io.File;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

@SuppressWarnings("serial")
public class FileBrowser extends JSplitPane implements TreeSelectionListener {
	private FileTableModel tableModel;

	public FileBrowser() {
		super(JSplitPane.HORIZONTAL_SPLIT);

		tableModel = new FileTableModel();
		JTable table = new JTable(new TotalRowDecorator(tableModel, 1));
		JTree tree = new JTree(new DirTreeModel());

		setRightComponent(new JScrollPane(table));
		setLeftComponent(new JScrollPane(tree));
		tree.setRootVisible(false);
		tree.getSelectionModel().addTreeSelectionListener(this);
	}

	public void valueChanged(TreeSelectionEvent ev) {
		if (ev.getPath() != null) {
			Object directory = ev.getPath().getLastPathComponent();
			tableModel.setDirectory((File) directory);
		}
	}

	public static void main(String[] argv) {
		JFrame frm = new JFrame();
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.getContentPane().add("Center", new FileBrowser());
		frm.pack();
		frm.setVisible(true);
	}
}
