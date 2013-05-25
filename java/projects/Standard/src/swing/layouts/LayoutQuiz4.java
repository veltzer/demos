package swing.layouts;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.border.BevelBorder;

@SuppressWarnings("serial")
public class LayoutQuiz4 extends JFrame {
	public LayoutQuiz4() {
		super("LayoutQuiz4");
	}

	private void init() {
		Container c = getContentPane();

		Box box = new Box(BoxLayout.PAGE_AXIS);
		for (int i = 0; i < 8; ++i) {
			JCheckBox checkBox = new JCheckBox("value " + i);
			box.add(checkBox);
			box.add(Box.createVerticalStrut(15));
		}
		box.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		c.add(box, BorderLayout.CENTER);
		setSize(400, 400);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LayoutQuiz4 app = new LayoutQuiz4();
		app.init();
		app.setVisible(true);
	}

}
