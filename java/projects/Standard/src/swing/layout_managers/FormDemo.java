package swing.layout_managers;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class FormDemo extends JPanel {
	public FormDemo() {
		super(new FormLayout("2dlu, right:pref:grow, 2dlu, fill:default, 2dlu",
				"top:2dlu, pref, 2dlu, pref, 2dlu, pref, 2dlu"));
		JLabel name = new JLabel("Name");
		JLabel password = new JLabel("Password");
		JComponent nameText = new JTextField("Someone");
		JComponent passwordText = new JPasswordField(10);
		JButton login = new JButton("Login");
		JButton cancel = new JButton("Cancel");
		CellConstraints cc = new CellConstraints();
		add(name, cc.xy(2, 2, "left, top"));
		add(password, cc.xy(2, 4, "left, top"));
		add(nameText, cc.xy(4, 2, "left, top"));
		add(passwordText, cc.xy(4, 4, "left, top"));
		add(login, cc.xy(2, 6, "left, top"));
		add(cancel, cc.xy(4, 6, "left, top"));
	}

	public static void main(String[] argv) {
		FormDemo layout = new FormDemo();
		JFrame frm = new JFrame();
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.getContentPane().add("Center", layout);
		frm.pack();
		frm.setVisible(true);
	}
}
