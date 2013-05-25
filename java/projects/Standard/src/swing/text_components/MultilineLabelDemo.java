package swing.text_components;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class MultilineLabelDemo extends JPanel {
	public MultilineLabelDemo() {
		JTextArea label = new JTextArea(
				"This is a really really long String!\nIt even has a newline character in it");
		label.setLineWrap(true);
		JLabel l = new JLabel();
		label.setFont(l.getFont());
		label.setOpaque(false);
		label.setEditable(false);
		add(label);
	}

	public static void main(String[] argv) {
		MultilineLabelDemo layout = new MultilineLabelDemo();
		JFrame frm = new JFrame();
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.getContentPane().add("Center", layout);
		frm.pack();
		frm.setVisible(true);
	}
}
