package swing.layouts;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class LayoutQuiz5 extends JFrame {
	public LayoutQuiz5() {
		super("LayoutQuiz5");
	}

	private void init() {
		Container c = getContentPane();

		Box box = new Box(BoxLayout.PAGE_AXIS);
		for (int i = 0; i < 8; ++i) {
			JComponent comp = createPair("value " + i);
			comp.setBorder(BorderFactory.createLoweredBevelBorder());
			box.add(comp);
		}
		// box.add(box.createVerticalGlue());
		c.add(box, BorderLayout.CENTER);

		setSize(400, 400);
	}

	private JComponent createPair(String labelText) {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 2));

		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
		leftPanel.add(new JLabel(labelText));
		panel.add(leftPanel);

		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
		JTextField textField = new JTextField(10);
		textField.setText("" + (int) (Math.random() * 1000));
		rightPanel.add(textField);
		panel.add(rightPanel);

		return panel;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LayoutQuiz5 app = new LayoutQuiz5();
		app.init();
		app.setVisible(true);
	}

}
