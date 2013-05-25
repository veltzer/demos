package swing.keyboard;

import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class InputVerifierDemo extends JPanel {
	private JTextField verified = new JTextField("abc");
	private JTextField other = new JTextField("Another field");

	public InputVerifierDemo() {
		add(verified);
		add(other);
		verified.setInputVerifier(new InputVerifier() {
			@Override
			public boolean verify(JComponent input) {
				JTextField t = (JTextField) input;
				return t.getText().matches("[0-9]*");
			}
		});
	}

	public static void main(String[] argv) {
		InputVerifierDemo demo = new InputVerifierDemo();
		JFrame frm = new JFrame();
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.getContentPane().add("Center", demo);
		frm.setSize(200, 80);
		frm.setVisible(true);
	}
}
