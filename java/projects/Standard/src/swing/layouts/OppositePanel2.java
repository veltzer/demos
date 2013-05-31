package swing.layouts;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class OppositePanel2 extends JComponent {
	private JPanel leftPanel;
	private JPanel rightPanel;
	private boolean leftComponentAdded;
	private boolean rightComponentAdded;
	private boolean panelsAdded;

	public OppositePanel2() {
		super();
		panelsAdded = false;

		setLayout(new GridLayout(1, 2));
		leftPanel = new JPanel();
		leftPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		rightPanel = new JPanel();
		rightPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		add(leftPanel);
		add(rightPanel);

		panelsAdded = true;
	}

	static final String ERR_STRING1 = "Cannot hold more than two components in OppositePanel2";

	@Override
	public Component add(Component comp) {

		if (!panelsAdded) {
			return super.add(comp);
		}
		if (!leftComponentAdded) {
			leftComponentAdded = true;
			return leftPanel.add(comp);
		}
		if (!rightComponentAdded) {
			rightComponentAdded = true;
			return rightPanel.add(comp);
		}
		throw new RuntimeException(ERR_STRING1);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		OppositePanel2 panel = new OppositePanel2();

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
