package swing.advanced_swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class SwingWorkerDemo extends JPanel {
	private JButton save = new JButton("Save");

	public SwingWorkerDemo() {
		// the main application thread
		add(save);
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				// all events occur on the swing thread
				new UpdateButton().start();
			}
		});
	}

	public static void main(String[] argv) {
		// the main application thread
		SwingWorkerDemo demo = new SwingWorkerDemo();
		JFrame frm = new JFrame();
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.getContentPane().add("Center", demo);
		frm.pack();
		frm.setVisible(true);
	}

	class UpdateButton extends SwingWorker {
		public Object construct() {
			// our own special thread
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			return ("Saved");
		}

		public void finished() {
			// this is the Swing thread
			save.setText((String) getValue());
		}
	}
}
