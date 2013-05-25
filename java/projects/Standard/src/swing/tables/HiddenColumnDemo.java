package swing.tables;

import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

@SuppressWarnings("serial")
public class HiddenColumnDemo extends JPanel {
	public HiddenColumnDemo() {
		FileTableModel m = new FileTableModel();
		File directory = new File(".");
		m.setDirectory(directory);
		TableModel model = new HiddenColumnsProxy(m, new int[] {
				0, 1
		});
		add(new JScrollPane(new JTable(model)));
	}

	public static void main(String[] argv) {
		HiddenColumnDemo layout = new HiddenColumnDemo();
		JFrame frm = new JFrame();
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.getContentPane().add("Center", layout);
		frm.pack();
		frm.setVisible(true);
	}
}
