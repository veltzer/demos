package swing.performance;

import javax.swing.DebugGraphics;
import javax.swing.JFrame;
import javax.swing.JLabel;

public abstract class DebugGraphicsDemo {
	public static void main(String[] argv) {
		JFrame frm = new JFrame("Hello World");
		JLabel hi = new JLabel("Hi World");
		hi.setDebugGraphicsOptions(DebugGraphics.LOG_OPTION);
		frm.getContentPane().add("Center", hi);
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.pack();
		frm.setVisible(true);
	}
}
