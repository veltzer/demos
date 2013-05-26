package swing.getting_started_with_swing;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Description of the Class
 * @author Mark Veltzer <mark@veltzer.net>
 */
public abstract class HelloWorld {
	/**
	 * The main program for the HelloWorld class
	 * @param argv The command line arguments
	 */
	public static void main(String[] argv) {
		JFrame frm = new JFrame("Hello World");
		frm.getContentPane().add("Center", new JLabel("Hi World"));
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.pack();
		frm.setVisible(true);
	}
}
