package swing.java2d_and_rendering;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

import javax.swing.JFrame;

/**
 * Description of the Class
 * @author Mark Veltzer <mark@veltzer.net>
 */
@SuppressWarnings("serial")
public class Transform extends Component {
	/**
	 * The main program for the Star class
	 * @param argv The command line arguments
	 */
	public static void main(String[] argv) {
		JFrame frm = new JFrame();
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.getContentPane().add("Center", new Transform());
		frm.pack();
		frm.setVisible(true);
	}

	/**
	 * Description of the Method
	 * @param g Description of the Parameter
	 */
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.BLUE);
		Shape rect = new Rectangle(20, 20, 50, 50);
		g2d.fill(rect);
		AffineTransform rotate = AffineTransform.getRotateInstance(
				Math.PI / 4.0, 20, 20);
		g2d.transform(rotate);
		g2d.setColor(new Color(0xff, 0x7f, 0x7f, 0x7f));
		g2d.fill(rect);
	}
}
