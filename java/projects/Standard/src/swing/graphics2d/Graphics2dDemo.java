package swing.graphics2d;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.Shape;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Graphics2dDemo extends JPanel {
	public Graphics2dDemo() {
		setBackground(Color.ORANGE);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;
		paintLines(g2);
		paintText(g2);
		paintShapes(g2);
	}

	private void paintLines(Graphics2D g2) {
		// Draw first line,
		g2.setColor(Color.RED);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setStroke(new BasicStroke());
		g2.drawLine(30, 30, getWidth() - 90, getHeight() - 30);

		// Draw second line
		g2.setColor(Color.BLUE);
		g2.setStroke(new BasicStroke(7, BasicStroke.CAP_ROUND,
				BasicStroke.JOIN_ROUND));

		g2.drawLine(30, 30, getWidth() - 30, getHeight() - 90);
	}

	private void paintText(Graphics2D g2) {
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2.translate(100, 200);
		g2.rotate(-Math.PI / 10);
		g2.setPaint(new GradientPaint(10, 10, Color.RED, 150, 150,
				Color.DARK_GRAY));
		g2.setFont(g2.getFont().deriveFont(60.0f).deriveFont(Font.BOLD));
		g2.drawString("Antialiased", 50, 50);
	}

	private void paintShapes(Graphics2D g2) {
		int[] xPoints = {
				55, 67, 109, 73, 83, 55, 27, 37, 1, 43
		};
		int[] yPoints = {
				0, 36, 36, 54, 96, 72, 96, 54, 36, 36
		};

		Shape polygon = new Polygon(xPoints, yPoints, xPoints.length);
		for (int i = 0; i < 20; ++i) {

			g2.setPaint(new Color((int) (Math.random() * 256), (int) (Math
					.random() * 256), (int) (Math.random() * 256), 255));
			g2.rotate(Math.PI / 10);

			g2.fill(polygon);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// JFrame.setDefaultLookAndFeelDecorated(true);
		Graphics2dDemo demo = new Graphics2dDemo();

		JFrame frame = new JFrame("Graphics 2d demo");
		frame.getContentPane().add(demo, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setSize(500, 500);
		frame.setVisible(true);
		// ..
		// ..
	}

}
