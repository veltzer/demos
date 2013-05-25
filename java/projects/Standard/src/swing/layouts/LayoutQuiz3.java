package swing.layouts;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.HeadlessException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

public class LayoutQuiz3 extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LayoutQuiz3() throws HeadlessException {
		super("LayoutQuiz3");
	}

	private void init() {
		Container c = getContentPane();

		{
			Box box = new Box(BoxLayout.X_AXIS);
			box.add(new JButton("top-left"));
			box.add(Box.createGlue());
			box.add(new JButton("top-right"));
			box.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));

			c.add(box, BorderLayout.NORTH);
		}
		{
			Box box = new Box(BoxLayout.X_AXIS);
			box.add(new JButton("bottom-left"));
			box.add(Box.createGlue());
			box.add(new JButton("bottom-right"));
			box.setBorder(BorderFactory.createTitledBorder("bottom"));

			c.add(box, BorderLayout.SOUTH);
		}
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
