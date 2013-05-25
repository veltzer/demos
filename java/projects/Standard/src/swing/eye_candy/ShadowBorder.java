package swing.eye_candy;

import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;

import javax.swing.border.Border;

public class ShadowBorder implements Border {
	private Color shadow = Color.GRAY;
	private Color lightShadow = new Color(shadow.getRed(), shadow.getGreen(),
			shadow.getBlue(), 170);
	private Color lighterShadow = new Color(shadow.getRed(), shadow.getGreen(),
			shadow.getBlue(), 70);

	public Insets getBorderInsets(Component c) {
		if (c.getComponentOrientation() == ComponentOrientation.RIGHT_TO_LEFT) {
			return new Insets(3, 1, 3, 1);
		} else {
			return new Insets(1, 1, 3, 3);
		}
	}

	public void paintBorder(Component c, Graphics graphics, int x, int y,
			int width, int height) {

		Graphics2D g = (Graphics2D) graphics
				.create(1, 1, width - 1, height - 1);

		if (c.getComponentOrientation() == ComponentOrientation.RIGHT_TO_LEFT) {
			g.scale(-1, 1);
			g.translate(-width + 1, 0);
		}

		g.setColor(shadow);
		g.fillRect(x, y, width - 3, 1);
		g.fillRect(0, 0, 1, height - 3);
		g.fillRect(width - 3, 1, 1, height - 3);
		g.fillRect(1, height - 3, width - 3, 1);

		g.setColor(lightShadow);
		g.fillRect(width - 3, 0, 1, 1);
		g.fillRect(0, height - 3, 1, 1);
		g.fillRect(width - 2, 1, 1, height - 3);
		g.fillRect(1, height - 2, width - 3, 1);

		g.setColor(lighterShadow);
		g.fillRect(width - 2, 0, 1, 1);
		g.fillRect(0, height - 2, 1, 1);
		g.fillRect(width - 2, height - 2, 1, 1);
		g.fillRect(width - 1, 1, 1, height - 2);
		g.fillRect(1, height - 1, width - 2, 1);
	}

	public boolean isBorderOpaque() {
		return false;
	}
}
