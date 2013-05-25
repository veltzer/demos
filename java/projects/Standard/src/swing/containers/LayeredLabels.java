package swing.containers;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

@SuppressWarnings("serial")
public class LayeredLabels extends JLayeredPane {
	private int maxLevel;

	private Color[] colors = {
			Color.GREEN, Color.RED, Color.BLUE, Color.GRAY, Color.DARK_GRAY,
			Color.YELLOW, Color.CYAN, Color.MAGENTA, Color.WHITE
	};

	private JLabel[] labels;

	public LayeredLabels() {
		maxLevel = 0;
		init();
	}

	private void init() {
		labels = new JLabel[colors.length];
		for (int i = 0; i < labels.length; ++i) {
			labels[i] = new JLabel();
			final JLabel label = labels[i];
			label.setBounds(10 + (30 * i), 10 + (30 * i), 120, 120);
			label.setBackground(colors[i]);
			label.setOpaque(true);

			add(label);
			setLayer(label, maxLevel++);

			label.addMouseListener(new MouseAdapter() {

				@Override
				public void mouseClicked(MouseEvent e) {
					setLayer(label, maxLevel++);
				}
			});

		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		LayeredLabels app = new LayeredLabels();
		app.init();
		app.setVisible(true);
	}

}
