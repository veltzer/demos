package swing.performance;

import javax.swing.JFrame;
import javax.swing.JLabel;

public abstract class HelloWorld {
	public static void main(String[] argv) {
		JFrame frm = new JFrame("Hello World");
		frm.getContentPane().add("Center", new JLabel("Hi World"));
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.pack();
		frm.setVisible(true);
	}
}
