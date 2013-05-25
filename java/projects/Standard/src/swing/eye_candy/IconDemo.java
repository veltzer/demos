package swing.eye_candy;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class IconDemo extends JPanel {
	public IconDemo() {
		Icon duke = new ImageIcon(getClass().getResource("/duke.gif"));
		Icon no = new NoEntryIcon(duke);
		add(new JLabel(duke));
		add(new JLabel(no));
	}

	static class NoEntryIcon implements Icon {
		private Icon icon;

		public NoEntryIcon(Icon iicon) {
			icon = iicon;
		}

		public int getIconHeight() {
			return icon.getIconHeight();
		}

		public int getIconWidth() {
			return icon.getIconWidth();
		}

		public void paintIcon(Component c, Graphics g, int x, int y) {
			Graphics2D g2d = (Graphics2D) g;
			icon.paintIcon(c, g, x, y);
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
					RenderingHints.VALUE_RENDER_QUALITY);
			g2d.setColor(Color.RED);
			g2d.setStroke(new BasicStroke(5));
			int size = Math.min(getIconWidth(), getIconHeight()) - 10;
			Shape circle = new Ellipse2D.Float(5, 5, size, size);
			g2d.draw(circle);
			Shape line = new Line2D.Float(5, 5, size, size);
			g2d.clip(circle);
			g2d.draw(line);
		}
	}

	public static void main(String[] argv) {
		IconDemo demo = new IconDemo();
		JFrame frm = new JFrame();
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.getContentPane().add("Center", demo);
		frm.pack();
		frm.setVisible(true);
	}
}
