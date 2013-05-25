package swing.layout_managers;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class BoxDemo extends Box {
	public BoxDemo() {
		super(BoxLayout.Y_AXIS);
		add(Box.createVerticalStrut(3));
		add(createPair(new JLabel("Name"),
				limitHeight(new JTextField("Someone")), 3));
		add(Box.createVerticalStrut(3));
		add(createPair(new JLabel("Password"),
				limitHeight(new JPasswordField()), 3));
		add(Box.createVerticalStrut(3));
		add(createPair(new JButton("Login"), new JButton("Cancel"), 8));
		add(Box.createVerticalStrut(3));
	}

	private JComponent limitHeight(JComponent cmp) {
		Dimension d = cmp.getMaximumSize();
		d.height = cmp.getPreferredSize().height;
		cmp.setMaximumSize(d);
		cmp.setAlignmentY(0.75f);
		return (cmp);
	}

	private Box createPair(JComponent first, JComponent second, int spacing) {
		Box line = new Box(BoxLayout.LINE_AXIS);
		line.add(first);
		line.add(Box.createHorizontalStrut(spacing));
		line.add(second);
		return (line);
	}

	public static void main(String[] argv) {
		BoxDemo layout = new BoxDemo();
		JFrame frm = new JFrame();
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.getContentPane().add("Center", layout);
		frm.pack();
		frm.setVisible(true);
	}
}
