package swing.advanced_swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class InvokeLaterDemo extends JPanel {
	private JButton save = new JButton("Save");

	public InvokeLaterDemo() {
		// the main application thread
		add(save);
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				// all events occur on the swing thread
				Thread t = new Thread() {
					public void run() {
						// our own special thread
						try {
							sleep(3000);
						} catch (InterruptedException e) {
							throw new RuntimeException(e);
						}
						SwingUtilities.invokeLater(new UpdateButton());
					}
				};
				t.start();
			}
		});
	}

	public static void main(String[] argv) {
		// the main application thread
		InvokeLaterDemo demo = new InvokeLaterDemo();
		JFrame frm = new JFrame();
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.getContentPane().add("Center", demo);
		frm.pack();
		frm.setVisible(true);
	}

	class UpdateButton implements Runnable {
		public void run() {
			// this is the Swing thread
			save.setText("Saved");
		}
	}
}
