package swing.java2d_and_rendering;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.JFrame;

/**
 * Description of the Class
 * @author Mark Veltzer <mark@veltzer.net>
 */
@SuppressWarnings("serial")
public class HelloJava2D extends Component {
	/**
	 * The main program for the HelloJava2D class
	 * @param argv The command line arguments
	 */
	public static void main(String[] argv) {
		JFrame frm = new JFrame("Hello World");
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.getContentPane().add("Center", new HelloJava2D());
		frm.pack();
		frm.setVisible(true);
	}

	/**
	 * Description of the Method
	 * @param g Description of the Parameter
	 */
	public void paint(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawString("Hi World", 0, g.getFont().getSize());
	}
}
