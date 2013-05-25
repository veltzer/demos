package swing.layouts;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.HeadlessException;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DebugGraphics;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class LayoutQuiz2 extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LayoutQuiz2() throws HeadlessException {
		super("LayoutQuiz2");
	}

	private void init() {
		Container c = getContentPane();

		Box box = new Box(BoxLayout.X_AXIS);
		JLabel welcomeLabel = new JLabel("Welcome");
		JLabel dateLabel = new JLabel("" + new Date());
		box.add(welcomeLabel);
		box.add(Box.createGlue());
		box.add(dateLabel);
		box.setBorder(BorderFactory.createLoweredBevelBorder());

		c.add(box, BorderLayout.NORTH);

		box.setDebugGraphicsOptions(DebugGraphics.LOG_OPTION);
		setSize(400, 400);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LayoutQuiz2 app = new LayoutQuiz2();
		app.init();
		app.setVisible(true);
	}

}
