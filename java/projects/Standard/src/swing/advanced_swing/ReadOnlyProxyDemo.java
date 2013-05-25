package swing.advanced_swing;

import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

@SuppressWarnings("serial")
public class ReadOnlyProxyDemo extends JPanel {
	public ReadOnlyProxyDemo() {
		FileTableModel m = new FileTableModel();
		m.setDirectory(new File("."));
		add(new JScrollPane(new JTable(new ReadOnlyTableModel(m))));
	}

	public static void main(String[] argv) {
		ReadOnlyProxyDemo layout = new ReadOnlyProxyDemo();
		JFrame frm = new JFrame();
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.getContentPane().add("Center", layout);
		frm.pack();
		frm.setVisible(true);
	}
}
