package swing.tables;

import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class SortDemo extends JPanel {
	public SortDemo() {
		FileTableModel m = new FileTableModel();
		File directory = new File(".");
		m.setDirectory(directory);
		add(new JScrollPane(new VTable(m)));
	}

	public static void main(String[] argv) {
		SortDemo layout = new SortDemo();
		JFrame frm = new JFrame();
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.getContentPane().add("Center", layout);
		frm.pack();
		frm.setVisible(true);
	}
}
