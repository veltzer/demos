package swing.layouts;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class OppositePanel extends JPanel {
	static final String ERR_STRING1 = "Cannot hold more than two components in OppositePanel2";

	public OppositePanel() {
		super(new OppositeLayout());
	}

	@Override
	public Component add(Component comp) {
		if (getComponentCount() >= 2) {
			throw new RuntimeException(ERR_STRING1);
		}
		return super.add(comp);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		JFrame frame = new JFrame("Hello World");

		OppositePanel panel = new OppositePanel();

		panel.add(new JLabel("Hello, this is a message"));
		panel.add(new JButton("Hello button"));
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// frame.pack();
		frame.setSize(300, 200);
		frame.setVisible(true);
	}
}
