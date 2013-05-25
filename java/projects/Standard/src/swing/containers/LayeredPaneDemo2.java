package swing.containers;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

@SuppressWarnings("serial")
public class LayeredPaneDemo2 extends JFrame {
	private JLayeredPane layeredPane;
	private int level;
	private Color[] colors = {
			Color.GREEN, Color.RED, Color.BLUE, Color.GRAY, Color.DARK_GRAY,
			Color.YELLOW, Color.CYAN, Color.MAGENTA, Color.WHITE
	};

	private class Shuffler implements Runnable {

		public void run() {
			while (true) {
				try {
					Thread.sleep(150);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
				int childrenCount = layeredPane.getComponentCount();
				if (childrenCount > 0) {
					int randomComponent = (int) (Math.random() * childrenCount);
					int randomLevel = (int) (Math.random() * 100);
					layeredPane.setLayer(
							layeredPane.getComponent(randomComponent),
							randomLevel);
				}
			}

		}

	}

	public LayeredPaneDemo2() {
		super("Layered pane demo");
		level = 0;
	}

	private void init() {
		final Container c = getContentPane();

		layeredPane = new JLayeredPane();
		layeredPane.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				JComponent comp = new JLabel("component #" + level);
				comp.setAlignmentX(Component.CENTER_ALIGNMENT);
				comp.setAlignmentY(Component.CENTER_ALIGNMENT);
				comp.setOpaque(true);
				comp.setBackground(colors[(int) (Math.random() * colors.length)]);
				comp.setBorder(BorderFactory.createLineBorder(Color.GREEN, 4));
				comp.setBounds(e.getX(), e.getY(),
						comp.getPreferredSize().width * 2,
						comp.getPreferredSize().height * 2);

				layeredPane.add(comp, new Integer(level));
				++level;
			}
		});

		c.add(layeredPane, BorderLayout.CENTER);
		setSize(500, 500);

		new Thread(new Shuffler()).start();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		LayeredPaneDemo2 app = new LayeredPaneDemo2();
		app.init();
		app.setVisible(true);
	}

}
