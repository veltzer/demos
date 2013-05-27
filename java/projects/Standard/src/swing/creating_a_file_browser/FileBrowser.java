package swing.creating_a_file_browser;

import java.io.File;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

/**
 * @author Mark Veltzer <mark@veltzer.net>
 */
@SuppressWarnings("serial")
public class FileBrowser extends JSplitPane implements TreeSelectionListener {
	private FileTableModel tableModel;

	/**
	 * Constructor for the FileBrowser object
	 */
	public FileBrowser() {
		super(JSplitPane.HORIZONTAL_SPLIT);
		tableModel = new FileTableModel();
		JTable table = new JTable(tableModel);
		JTree tree = new JTree(new FileTreeModel());
		setRightComponent(new JScrollPane(table));
		setLeftComponent(new JScrollPane(tree));
		tree.setRootVisible(false);
		tree.getSelectionModel().addTreeSelectionListener(this);
		tree.setCellRenderer(new FileRenderer());
		table.setDefaultRenderer(Long.class, new FileSizeRenderer());
		table.setDefaultRenderer(File.class, new FileTypeRenderer());
	}

	/**
	 * Description of the Method
	 * @param ev Description of the Parameter
	 */
	public void valueChanged(TreeSelectionEvent ev) {
		if (ev.getPath() != null) {
			Object directory = ev.getPath().getLastPathComponent();
			tableModel.setDirectory((File) directory);
		}
	}

	/**
	 * The main program for the FileBrowser class
	 * @param argv The command line arguments
	 */
	public static void main(String[] argv) {
		JFrame frm = new JFrame();
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.getContentPane().add("Center", new FileBrowser());
		frm.pack();
		frm.setVisible(true);
	}
}
