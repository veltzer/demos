package swing.layout_managers;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class GridDemo extends JPanel {
	private GridBagLayout layout;
	private GridBagConstraints constraints = new GridBagConstraints();

	public GridDemo() {
		super(new GridBagLayout());
		layout = (GridBagLayout) getLayout();
		createPair(new JLabel("Name"), new JTextField("Someone"));
		createPair(new JLabel("Password"), new JPasswordField());

		JButton login = new JButton("Login");
		JButton cancel = new JButton("Cancel");
		constraints.gridx = 1;
		layout.setConstraints(login, constraints);
		add(login);
		constraints.gridx = 2;
		layout.setConstraints(cancel, constraints);
		add(cancel);
	}

	private void createPair(JComponent first, JComponent second) {
		constraints.ipadx = 2;
		constraints.ipady = 2;
		constraints.anchor = GridBagConstraints.WEST;
		layout.setConstraints(first, constraints);
		add(first);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		constraints.weightx = 1;
		layout.setConstraints(second, constraints);
		add(second);

		// return to the default values
		constraints.weightx = 0;
		constraints.gridwidth = 1;
		constraints.fill = GridBagConstraints.NONE;
	}

	public static void main(String[] argv) {
		GridDemo layout = new GridDemo();
		JFrame frm = new JFrame();
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.getContentPane().add("Center", layout);
		frm.pack();
		frm.setVisible(true);
	}
}
