package swing.graphics2d;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.Shape;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class RollingPolygon extends JPanel {
	private Shape polygon;
	private int xPos;
	private int yPos;
	private int xDelta;
	private int yDelta;
	private double angle;
	private int gradientPercent;
	private int gradientDelta;
	private Color gradientStartColor;
	private Color gradientEndColor;
	private boolean isRunning;

	private class Roller implements Runnable {
		public void run() {
			while (true) {
				if (isRunning) {
					xPos += xDelta;
					yPos += yDelta;
					if (xPos <= 0) {
						xPos = 0;
						xDelta = -xDelta;
					}
					if (xPos >= getWidth()) {
						xPos = getWidth();
						xDelta = -xDelta;
					}
					if (yPos <= 0) {
						yPos = 0;
						yDelta = -yDelta;
					}
					if (yPos >= getHeight()) {
						yPos = getHeight();
						yDelta = -yDelta;
					}
					angle += Math.PI / 50;

					gradientPercent += gradientDelta;
					if ((gradientPercent < 0) || (gradientPercent > 100)) {
						gradientDelta = -gradientDelta;
						gradientPercent += gradientDelta;
					}

					repaint();

				}
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}

	public RollingPolygon() {
	}

	public void init() {
		int[] xPoints = {
				-50, 50, 50, -50
		};
		int[] yPoints = {
				-50, -50, 50, 50
		};

		polygon = new Polygon(xPoints, yPoints, xPoints.length);

		xPos = 0;
		yPos = 0;
		xDelta = 8;
		yDelta = 8;
		angle = 0.0;
		gradientPercent = 0;
		gradientDelta = 1;
		gradientStartColor = new Color(0xff, 0x00, 0xff);
		gradientEndColor = new Color(0x00, 0xff, 0x00);

		new Thread(new Roller()).start();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		int redColor = gradientStartColor.getRed()
				+ (int) ((gradientEndColor.getRed() - gradientStartColor
						.getRed()) * gradientPercent / 100.0);
		int greenColor = gradientStartColor.getGreen()
				+ (int) ((gradientEndColor.getGreen() - gradientStartColor
						.getGreen()) * gradientPercent / 100.0);
		int blueColor = gradientStartColor.getBlue()
				+ (int) ((gradientEndColor.getBlue() - gradientStartColor
						.getBlue()) * gradientPercent / 100.0);
		Color color = new Color(redColor, greenColor, blueColor);

		g2.setColor(color);
		g2.translate(xPos, yPos);
		g2.rotate(angle);

		g2.fill(polygon);
		g2.setFont(g2.getFont().deriveFont(20.0f).deriveFont(Font.BOLD));
		g2.drawString("rollaround", -50, 75);
		g2.rotate(-angle);
		g2.translate(-xPos, -yPos);

	}

	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean iisRunning) {
		isRunning = iisRunning;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		RollingPolygon rollingPolygon = new RollingPolygon();
		rollingPolygon.init();
		rollingPolygon.setRunning(true);

		JFrame frame = new JFrame("Rolling polygon");
		frame.getContentPane().add(rollingPolygon, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setSize(500, 500);
		frame.setVisible(true);
	}

}
