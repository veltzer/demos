package swing.tables;

import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class FrozenDemo extends JPanel {
	public FrozenDemo() {
		FileTableModel m = new FileTableModel();
		File directory = new File(".");
		m.setDirectory(directory);
		add(new FTable(m, new int[] {
			0
		}));
	}

	public static void main(String[] argv) {
		FrozenDemo layout = new FrozenDemo();
		JFrame frm = new JFrame();
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.getContentPane().add("Center", layout);
		frm.pack();
		frm.setVisible(true);
	}
}
