package swing.advanced_swing;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class HelloWorld implements Runnable {
	public void run() {
		JFrame frm = new JFrame("Hello World");
		frm.getContentPane().add("Center", new JLabel("Hi World"));
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.pack();
		frm.setVisible(true);
	}

	public static void main(String[] argv) {
		SwingUtilities.invokeLater(new HelloWorld());
	}
}
