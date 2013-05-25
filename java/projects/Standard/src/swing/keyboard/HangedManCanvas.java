package swing.keyboard;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class HangedManCanvas extends JPanel {
	private int errorLevel;

	public HangedManCanvas() {
		errorLevel = 0;
		setBackground(Color.WHITE);
	}

	public int getErrorLevel() {
		return errorLevel;
	}

	public void setErrorLevel(int ierrorLevel) {
		errorLevel = ierrorLevel;
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.BLUE);
		g2.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND,
				BasicStroke.JOIN_ROUND));
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		int unit = Math.min(getWidth(), getHeight()) / 10;
		if (errorLevel > 0) {
			g2.drawLine(unit, unit, unit, unit * 9);
		}
		if (errorLevel > 1) {
			g2.drawLine(unit, unit, unit * 5, unit);
		}
		if (errorLevel > 2) {
			g2.drawLine(unit * 5, unit, unit * 5, unit * 2);
		}
		if (errorLevel > 3) {
			g.drawOval(unit * 4, unit * 2, unit * 2, unit * 2);
		}
		if (errorLevel > 4) {
			g2.drawLine(unit * 5, unit * 4, unit * 4, unit * 5);
		}
		if (errorLevel > 5) {
			g2.drawLine(unit * 5, unit * 4, unit * 6, unit * 5);
		}
		if (errorLevel > 6) {
			g2.drawLine(unit * 5, unit * 4, unit * 5, unit * 6);
		}
		if (errorLevel > 7) {
			g2.drawLine(unit * 5, unit * 6, unit * 4, unit * 7);
		}
		if (errorLevel > 8) {
			g2.drawLine(unit * 5, unit * 6, unit * 6, unit * 7);
		}
	}
}
