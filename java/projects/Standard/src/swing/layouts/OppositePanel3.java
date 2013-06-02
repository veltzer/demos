package swing.layouts;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class OppositePanel3 extends JComponent {
	private boolean leftComponentAdded;
	private boolean rightComponentAdded;

	public OppositePanel3() {
		super();

		setLayout(new BorderLayout());

	}

	static final String ERR_STRING1 = "Cannot hold more than two components in OppositePanel2";

	@Override
	public Component add(Component comp) {

		if (!leftComponentAdded) {
			leftComponentAdded = true;
			add(comp, BorderLayout.LINE_START);
			return comp;
		}
		if (!rightComponentAdded) {
			rightComponentAdded = true;
			add(comp, BorderLayout.LINE_END);
			return comp;
		}
		throw new RuntimeException(ERR_STRING1);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		OppositePanel3 panel = new OppositePanel3();

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
