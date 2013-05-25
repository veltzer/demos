package swing.text_components;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class TextFieldSizeDemo extends JPanel {
	public TextFieldSizeDemo() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JTextField field = new JTextField("Default");
		add(field);
		field = new JTextField("No stretch");
		field.setMaximumSize(field.getPreferredSize());
		add(field);
		field = new JTextField("Stretch Width");
		Dimension d = field.getMaximumSize();
		d.height = field.getPreferredSize().height;
		field.setMaximumSize(d);
		add(field);
	}

	public static void main(String[] argv) {
		TextFieldSizeDemo layout = new TextFieldSizeDemo();
		JFrame frm = new JFrame();
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.getContentPane().add("Center", layout);
		frm.pack();
		frm.setVisible(true);
	}
}
