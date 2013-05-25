package swing.keyboard2;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MnemonicDemo extends JPanel {
	public MnemonicDemo() {
		Action a = new AbstractAction() {
			public void actionPerformed(ActionEvent ev) {
				JOptionPane
						.showMessageDialog(MnemonicDemo.this, "Button Pressed",
								"Mnemonic", JOptionPane.PLAIN_MESSAGE);
			}
		};
		a.putValue(Action.NAME, "Mnemonic");
		a.putValue(Action.MNEMONIC_KEY, new Integer(KeyEvent.VK_N));
		JButton button = new JButton(a);
		add(button);
	}

	public static void main(String[] argv) {
		MnemonicDemo demo = new MnemonicDemo();
		JFrame frm = new JFrame();
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.getContentPane().add("Center", demo);
		frm.pack();
		frm.setVisible(true);
	}
}
