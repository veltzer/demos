package swing.layouts;

import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public abstract class OppositeLayoutDemo {
	/**
	 * @param args
	 */
	public static void main(String[] argv) {
		JFrame frame = new JFrame("Hello World");
		Container c = frame.getContentPane();
		// c.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		c.setLayout(new OppositeLayout());
		c.add(new JLabel("Hello, this is a message"));
		c.add(new JButton("Hello button"));
		c.add(new JButton("another"));

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// frame.pack();
		frame.setSize(300, 200);
		frame.setVisible(true);
	}
}
