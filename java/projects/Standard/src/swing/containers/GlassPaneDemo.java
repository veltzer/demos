package swing.containers;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GlassPaneDemo extends JFrame {
	private JButton[] buttons = {
			new JButton("First"), new JButton("Second"), new JButton("Third")
	};

	private Point point;

	private boolean showStar;

	private MyGlassPane myGlassPane;

	private class MyGlassPane extends JPanel {
		@Override
		protected void paintComponent(Graphics g) {
			if (!showStar) {
				return;
			}
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(Color.YELLOW);
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setStroke(new BasicStroke(7, BasicStroke.CAP_ROUND,
					BasicStroke.JOIN_ROUND));

			g2.translate(point.x, point.y);
			g2.drawOval(-8, -8, 16, 16);
			for (int i = 0; i < 8; ++i) {
				g2.drawLine(0, -20, 0, -16);
				g2.rotate(Math.PI / 4);
			}
			g2.translate(-point.x, -point.y);
		}

	}

	public GlassPaneDemo() {
		super("Glass pane demo");
		showStar = false;
		point = new Point(0, 0);
	}

	private void init() {
		final Container c = getContentPane();
		c.setLayout(new FlowLayout());
		for (int i = 0; i < buttons.length; ++i) {
			final JButton button = buttons[i];
			button.addMouseListener(new MouseAdapter() {

				@Override
				public void mouseEntered(MouseEvent e) {
					showStar = true;
					point = button.getLocation();
					point.x += c.getX();
					point.y += c.getY();
					myGlassPane.repaint();
				}

				@Override
				public void mouseExited(MouseEvent e) {
					showStar = false;
					myGlassPane.repaint();
				}
			});
			add(button);
		}

		myGlassPane = new MyGlassPane();

		setGlassPane(myGlassPane);
		myGlassPane.setOpaque(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		myGlassPane.setVisible(true);
		pack();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		GlassPaneDemo app = new GlassPaneDemo();
		app.init();
		app.setVisible(true);
	}

}
