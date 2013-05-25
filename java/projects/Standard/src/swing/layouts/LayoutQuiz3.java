package swing.layouts;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class LayoutQuiz3 extends JFrame {
	public LayoutQuiz3() {
		super("LayoutQuiz3");
	}

	private void init() {
		Container c = getContentPane();
		Box box1 = new Box(BoxLayout.X_AXIS);
		box1.add(new JButton("top-left"));
		box1.add(Box.createGlue());
		box1.add(new JButton("top-right"));
		box1.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
		c.add(box1, BorderLayout.NORTH);
		Box box2 = new Box(BoxLayout.X_AXIS);
		box2.add(new JButton("bottom-left"));
		box2.add(Box.createGlue());
		box2.add(new JButton("bottom-right"));
		box2.setBorder(BorderFactory.createTitledBorder("bottom"));
		c.add(box2, BorderLayout.SOUTH);
		setSize(400, 400);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LayoutQuiz3 app = new LayoutQuiz3();
		app.init();
		app.setVisible(true);
	}

}
