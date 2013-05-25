package swing.keyboard2;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class KeyEventDemo extends JPanel implements KeyListener {
	private JButton button = new JButton("Press Any Key");

	public KeyEventDemo() {
		add(button);
		button.addKeyListener(this);
	}

	private String keyToString(KeyEvent e) {
		return (KeyEvent.getKeyText(e.getKeyCode()) + " " + KeyEvent
				.getKeyModifiersText(e.getModifiers()));
	}

	public void keyPressed(KeyEvent e) {
		button.setText("Pressed: " + keyToString(e));
	}

	public void keyReleased(KeyEvent e) {
		button.setText("Released: " + keyToString(e));
	}

	public void keyTyped(KeyEvent e) {
		button.setText("Typed: " + keyToString(e));
	}

	public static void main(String[] argv) {
		KeyEventDemo demo = new KeyEventDemo();
		JFrame frm = new JFrame();
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.getContentPane().add("Center", demo);
		frm.pack();
		frm.setVisible(true);
	}
}
