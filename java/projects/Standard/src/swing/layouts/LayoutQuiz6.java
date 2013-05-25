package swing.layouts;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import swing.graphics2d.RollingPolygon;

@SuppressWarnings("serial")
public class LayoutQuiz6 extends JFrame {
	public LayoutQuiz6() {
		super("LayoutQuiz6");
	}

	private void init() {
		Container c = getContentPane();

		RollingPolygon rollingPolygon = new RollingPolygon();
		rollingPolygon.init();
		rollingPolygon.setPreferredSize(new Dimension(300, 300));
		rollingPolygon.setRunning(true);

		Box topBox = new Box(BoxLayout.LINE_AXIS);
		topBox.add(rollingPolygon);

		Box rightBox = new Box(BoxLayout.PAGE_AXIS);
		rightBox.add(Box.createVerticalGlue());
		rightBox.add(createButton("try"));
		rightBox.add(createButton("revert"));
		rightBox.add(createButton("apply"));
		topBox.add(rightBox);

		c.add(topBox, BorderLayout.CENTER);

		setSize(400, 400);
	}

	private JComponent createButton(String name) {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 1));
		panel.add(new JButton(name));
		return panel;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LayoutQuiz6 app = new LayoutQuiz6();
		app.init();
		app.setVisible(true);
	}

}
