package swing.eye_candy;

import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Paint;
import java.awt.SystemColor;

import javax.swing.border.Border;

public class GradientBorder implements Border {
	private String title;
	private Color sourceColor;
	private Color destinationColor;
	private Color textColor;

	public GradientBorder(String ititle) {
		this(ititle, SystemColor.activeCaption, SystemColor.window,
				SystemColor.activeCaptionText);
	}

	public GradientBorder(String ititle, Color isourceColor,
			Color idestinationColor, Color itextColor) {
		title = ititle;
		sourceColor = isourceColor;
		destinationColor = idestinationColor;
		textColor = itextColor;
	}

	public Insets getBorderInsets(Component c) {
		int top = c.getFont().getSize() + 6;
		return (new Insets(top, 2, 2, 2));
	}

	public boolean isBorderOpaque() {
		// better performance
		return true;
	}

	public void paintBorder(final Component c, Graphics g, int x, int y,
			int width, int height) {
		Graphics2D g2d = (Graphics2D) g;
		GradientPaint paint;
		Paint oldPaint = g2d.getPaint();
		int textX;
		Font f = c.getFont();
		g2d.setFont(f);
		x += 2;
		width -= 1;
		if (c.getComponentOrientation() == ComponentOrientation.RIGHT_TO_LEFT) {
			textX = x + width - 4 - g2d.getFontMetrics().stringWidth(title);
			paint = new GradientPaint(x, y, destinationColor, width, height,
					sourceColor);
		} else {
			y += 2;
			textX = x + 4;
			paint = new GradientPaint(x, y, sourceColor, width, height,
					destinationColor);
		}

		height = f.getSize() + 6;
		g2d.setPaint(paint);
		g2d.fillRect(x, y, width, height);
		g2d.setPaint(oldPaint);

		g2d.setColor(textColor);
		g2d.drawString(title, textX, y + height - 5);
	}
}
