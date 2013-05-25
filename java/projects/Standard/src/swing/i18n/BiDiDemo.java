package swing.i18n;

import java.awt.ComponentOrientation;

import javax.swing.AbstractAction;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;

@SuppressWarnings("serial")
public class BiDiDemo extends JPanel {
	private static JToolBar toolbar;

	public BiDiDemo() {
		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		add(new JLabel("Label"));
		add(Box.createHorizontalStrut(4));
		add(new JTextField("Text Field"));
		toolbar.add(new FlipOrientation());
	}

	public static void main(String[] argv) {
		toolbar = new JToolBar();
		BiDiDemo layout = new BiDiDemo();
		JFrame frm = new JFrame();
		frm.getContentPane().add("North", toolbar);
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.getContentPane().add("Center", layout);
		frm.pack();
		frm.setVisible(true);
	}

	class FlipOrientation extends AbstractAction {
		public FlipOrientation() {
			putValue(NAME, "Flip");
		}

		public void actionPerformed(java.awt.event.ActionEvent actionEvent) {
			JFrame frm = (JFrame) JFrame.getFrames()[0];
			ComponentOrientation o = frm.getComponentOrientation();
			if (o == ComponentOrientation.LEFT_TO_RIGHT) {
				frm.applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
			} else {
				frm.applyComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			}
			frm.validate();
			frm.repaint();
		}

	}
}
