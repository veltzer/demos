package swing.containers2;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTree;

public final class TabDemo {
	private JTabbedPane pane = new JTabbedPane();

	private TabDemo() {
		JTable table = new JTable(new Object[][] {
				{
						"Amy", "Fowler"
				}, {
						"James", "Gosling"
				}, {
						"Jeff", "Dinkins"
				}, {
						"Steve", "Wilson"
				}
		}, new Object[] {
				"Given Name", "Surname"
		});
		JTree tree = new JTree(new Object[] {
				"One Node", "Another Node", "Yet Another Node",
		});
		pane.addTab("Table", new JScrollPane(table));
		pane.addTab("Tree", new JScrollPane(tree));
	}

	public static void main(String[] argv) {
		TabDemo tabs = new TabDemo();
		JFrame frm = new JFrame();
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.getContentPane().add("Center", tabs.pane);
		frm.pack();
		frm.setVisible(true);
	}
}
