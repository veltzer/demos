package swing.look_and_feel;

import java.awt.Component;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.border.EtchedBorder;

@SuppressWarnings("serial")
public class TriangleEtchedBorder extends EtchedBorder {
	@SuppressWarnings("deprecation")
	public Insets getBorderInsets(Component c) {
		// right now only button is supported since this border needs intimate
		// knowledge of
		// the component structure this is also a very simplified preffered size
		// calculation
		JButton b = (JButton) c;
		Icon icon = (Icon) b.getIcon();
		String text = b.getText();
		Font font = b.getFont();
		FontMetrics fm = b.getToolkit().getFontMetrics(font);

		int iconWidth = 0;
		int iconHeight = 0;
		if (icon != null) {
			iconWidth = icon.getIconWidth();
			iconHeight = icon.getIconHeight();
		}

		int fontWidth = 0;
		if (text != null) {
			fontWidth = fm.stringWidth(text);
		}

		int width = Math.max(fontWidth, iconWidth);
		int height = iconHeight + fm.getHeight();

		return (new Insets(width / 2 + height, height, 0, height));
	}

	public boolean isBorderOpaque() {
		return (true);
	}

	public void paintBorder(Component c, Graphics g, int x, int y, int width,
			int height) {
		g.translate(x, y);
		width = c.getWidth();
		height = c.getHeight();
		if (etchType == LOWERED) {
			g.setColor(getShadowColor(c));
		} else {
			g.setColor(getHighlightColor(c));
		}
		g.drawPolygon(new int[] {
				0, width - 1, width / 2 - 1
		}, new int[] {
				height - 2, height - 2, 0
		}, 3);

		if (etchType == LOWERED) {
			g.setColor(getHighlightColor(c));
		} else {
			g.setColor(getShadowColor(c));
		}
		g.drawLine(2, height - 3, width / 2, 1);
		g.drawLine(1, height - 1, width, height - 1);
		g.drawLine(width, height - 1, width / 2 + 1, 1);

		g.translate(-x, -y);
	}

}
