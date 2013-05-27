package swing.java2d_and_rendering;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JFrame;

/**
 * Description of the Class
 * @author Mark Veltzer <mark@veltzer.net>
 */
@SuppressWarnings("serial")
public class HelloAliasing extends Component {
	/**
	 * The main program for the HelloJava2D class
	 * @param argv The command line arguments
	 */
	public static void main(String[] argv) {
		JFrame frm = new JFrame();
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.getContentPane().add("Center", new HelloAliasing());
		frm.pack();
		frm.setVisible(true);
	}

	/**
	 * Description of the Method
	 * @param g Description of the Parameter
	 */
	public void paint(Graphics g) {
		Font f = new Font("SansSerif", Font.PLAIN, 50);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setFont(f);
		g2d.setColor(Color.BLACK);
		g2d.drawString("Difference", 0, f.getSize());
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2d.drawString("Difference", 0, f.getSize() * 2);
	}
}
