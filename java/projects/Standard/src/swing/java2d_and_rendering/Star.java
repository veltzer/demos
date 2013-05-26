package swing.java2d_and_rendering;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

import javax.swing.JFrame;

/**
 * Description of the Class
 * @author Mark Veltzer <mark@veltzer.net>
 */
@SuppressWarnings("serial")
public class Star extends Component {
	/**
	 * Description of the Field
	 */
	private static final int[] X_POINTS = {
			55, 67, 109, 73, 83, 55, 27, 37, 1, 43
	};
	/**
	 * Description of the Field
	 */
	private static final int[] Y_POINTS = {
			0, 36, 36, 54, 96, 72, 96, 54, 36, 36
	};

	/**
	 * The main program for the Star class
	 * @param argv The command line arguments
	 */
	public static void main(String[] argv) {
		JFrame frm = new JFrame("Hello World");
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.getContentPane().add("Center", new Star());
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
		Shape poly = new Polygon(X_POINTS, Y_POINTS, X_POINTS.length);
		Shape elipse = new Ellipse2D.Float(-5, -5, 115, 115);
		Area intersection = new Area(elipse);
		intersection.exclusiveOr(new Area(poly));
		g2d.fill(intersection);
	}
}
