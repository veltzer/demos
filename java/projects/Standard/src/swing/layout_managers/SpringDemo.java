package swing.layout_managers;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.Spring;
import javax.swing.SpringLayout;

@SuppressWarnings("serial")
public class SpringDemo extends JPanel {
	private SpringLayout layout;

	public SpringDemo() {
		super(new SpringLayout());
		layout = (SpringLayout) getLayout();
		JLabel name = new JLabel("Name");
		JLabel password = new JLabel("Password");
		JComponent nameText = new JTextField("Someone");
		JComponent passwordText = new JPasswordField(10);
		createPair(name, nameText);
		createPair(password, passwordText);
		layout.putConstraint(SpringLayout.NORTH, password, 5,
				SpringLayout.SOUTH, name);
		layout.putConstraint(SpringLayout.WEST, nameText, 0, SpringLayout.WEST,
				passwordText);
		SpringLayout.Constraints nameCons = layout.getConstraints(nameText);
		SpringLayout.Constraints passwordCons = layout
				.getConstraints(passwordText);
		Spring spring = Spring
				.max(nameCons.getWidth(), passwordCons.getWidth());
		nameCons.setWidth(spring);
		passwordCons.setWidth(spring);
		JButton login = new JButton("Login");
		JButton cancel = new JButton("Cancel");
		createPair(login, cancel);
		layout.putConstraint(SpringLayout.NORTH, login, 5, SpringLayout.SOUTH,
				password);
	}

	private void createPair(JComponent first, JComponent second) {
		add(first);
		add(second);
		layout.putConstraint(SpringLayout.WEST, second, 5, SpringLayout.EAST,
				first);
		layout.putConstraint(SpringLayout.NORTH, second, 0, SpringLayout.NORTH,
				first);
	}

	public static void main(String[] argv) {
		SpringDemo layout = new SpringDemo();
		JFrame frm = new JFrame();
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.getContentPane().add("Center", layout);
		frm.pack();
		frm.setVisible(true);
	}
}
