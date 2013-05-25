package swing.layout_managers;

import java.awt.ComponentOrientation;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class LayoutDemo extends JPanel {

	public LayoutDemo() {
		setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		setLayout(new NaturalGridLayout(2, 3));
		add(new JButton("VERY VERY LONG BUTTON........"));
		add(new JButton("short"));
		add(new JButton("Another Button"));
		add(new JLabel("A label"));
		add(new JScrollPane(new JTextArea("Some stuff\n1\n\n2\n3")));
	}

	public static void main(String[] argv) {
		LayoutDemo layout = new LayoutDemo();
		JFrame frm = new JFrame();
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.getContentPane().add("Center", layout);
		frm.pack();
		frm.setVisible(true);
	}
}
