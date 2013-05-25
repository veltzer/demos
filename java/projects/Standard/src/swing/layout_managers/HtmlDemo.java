package swing.layout_managers;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import swing.layout_managers.htmllayout.HtmlLayout;

@SuppressWarnings("serial")
public class HtmlDemo extends JPanel {
	public HtmlDemo() {
		super(new HtmlLayout("<table rows=3 cols=2 hgap=3 vgap=3>" + "<tr>"
				+ "<td> Name <td component=name>" + "<tr>"
				+ "<td> Password <td component=password>" + "<tr>"
				+ "<td component=login><td component=cancel>"));
		JComponent nameText = new JTextField("Someone");
		JComponent passwordText = new JPasswordField(10);
		JButton login = new JButton("Login");
		JButton cancel = new JButton("Cancel");
		add(nameText, "name");
		add(passwordText, "password");
		add(login, "login");
		add(cancel, "cancel");
	}

	public static void main(String[] argv) {
		HtmlDemo layout = new HtmlDemo();
		JFrame frm = new JFrame();
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.getContentPane().add("Center", layout);
		frm.pack();
		frm.setVisible(true);
	}
}
