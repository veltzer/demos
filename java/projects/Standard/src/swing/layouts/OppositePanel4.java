package swing.layouts;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class OppositePanel4 extends Box {
	private boolean leftComponentAdded;

	public OppositePanel4() {
		super(BoxLayout.X_AXIS);
	}

	@Override
	public Component add(Component comp) {

		if (!leftComponentAdded) {
			leftComponentAdded = true;
			super.add(comp);
			super.add(createGlue());
			return comp;
		}
		return super.add(comp);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		OppositePanel4 panel = new OppositePanel4();

		panel.add(new JLabel("Hello, this is a message"));
		panel.add(new JButton("Hello button"));

		JFrame frame = new JFrame("Hello World");
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// frame.pack();
		frame.setSize(300, 200);
		frame.setVisible(true);
	}

}
