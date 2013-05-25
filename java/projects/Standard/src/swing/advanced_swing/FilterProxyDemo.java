package swing.advanced_swing;

import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

@SuppressWarnings("serial")
public class FilterProxyDemo extends JPanel {

	public FilterProxyDemo() {
		FileTableModel m = new FileTableModel();
		File directory = new File(".");
		m.setDirectory(directory);
		FilterTableModel model = new FilterTableModel(m);
		int[] rows = new int[Math.min(5, directory.list().length)];
		for (int iter = 0; iter < rows.length; iter++) {
			rows[iter] = iter;
		}
		model.updateRows(rows);
		add(new JScrollPane(new JTable(model)));
	}

	public static void main(String[] argv) {
		FilterProxyDemo layout = new FilterProxyDemo();
		JFrame frm = new JFrame();
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.getContentPane().add("Center", layout);
		frm.pack();
		frm.setVisible(true);
	}
}
