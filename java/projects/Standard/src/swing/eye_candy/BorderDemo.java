package swing.eye_candy;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

@SuppressWarnings("serial")
public class BorderDemo extends JPanel {

	public BorderDemo() {
		setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), "Border Title"));
		JButton button = new JButton("Button");
		button.setBorder(null);
		add(button);
		JLabel label = new JLabel("Label");
		label.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		add(label);
	}

	public static void main(String[] args) {
		BorderDemo demo = new BorderDemo();
		JFrame frm = new JFrame();
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.getContentPane().add("Center", demo);
		frm.pack();
		frm.setVisible(true);
	}
}
