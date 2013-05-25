package swing.multi_thread;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public final class Test {
	private JButton bStart, bPause, bContinue, bStop;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Test();
	}

	private Test() {
		JProgressBar pr = new JProgressBar();
		JFrame frm = new JFrame();
		frm.setLayout(new BorderLayout());
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.getContentPane().add(BorderLayout.SOUTH, pr);
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		frm.getContentPane().add(BorderLayout.CENTER, panel);
		frm.pack();
		bStart = new JButton("start");
		bPause = new JButton("pause");
		bPause.setEnabled(false);
		bContinue = new JButton("continue");
		bContinue.setEnabled(false);
		bStop = new JButton("stop");
		bStop.setEnabled(false);

		final IProcessControl control = new SwingProcessControl(pr);

		bStart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				bStart.setEnabled(false);
				bPause.setEnabled(true);
				bContinue.setEnabled(false);
				bStop.setEnabled(true);
				control.start();
				Thread t = new Thread(new Runnable() {

					@Override
					public void run() {
						IRunnableWithControl irw = new TestWorkerThread();
						irw.run(control);
					}
				});
				t.start();
			}
		});

		bPause.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				bStart.setEnabled(false);
				bPause.setEnabled(false);
				bContinue.setEnabled(true);
				bStop.setEnabled(false);
				control.pause();
			}
		});

		bContinue.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				bStart.setEnabled(false);
				bPause.setEnabled(true);
				bContinue.setEnabled(false);
				bStop.setEnabled(true);
				control.cont();
			}
		});

		bStop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				bStart.setEnabled(true);
				bPause.setEnabled(false);
				bContinue.setEnabled(false);
				bStop.setEnabled(false);
				control.stop();
			}
		});

		panel.add(bStart);
		panel.add(bPause);
		panel.add(bContinue);
		panel.add(bStop);

		frm.setVisible(true);
	}

}
