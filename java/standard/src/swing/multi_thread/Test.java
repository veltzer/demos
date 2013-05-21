package swing.multi_thread;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class Test {
	private JButton b_start,b_pause,b_continue,b_stop;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Test();
	}
	
	public Test() {
		JProgressBar pr=new JProgressBar();
		JFrame frm = new JFrame();
		frm.setLayout(new BorderLayout());
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.getContentPane().add(BorderLayout.SOUTH, pr);
		JPanel panel=new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		frm.getContentPane().add(BorderLayout.CENTER,panel);
		frm.pack();
		b_start=new JButton("start");
		b_pause=new JButton("pause");
		b_pause.setEnabled(false);
		b_continue=new JButton("continue");
		b_continue.setEnabled(false);
		b_stop=new JButton("stop");
		b_stop.setEnabled(false);
		
		final IProcessControl control=new SwingProcessControl(pr);
		
		b_start.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				b_start.setEnabled(false);
				b_pause.setEnabled(true);
				b_continue.setEnabled(false);
				b_stop.setEnabled(true);
				control.start();
				Thread t=new Thread(new Runnable() {
					
					@Override
					public void run() {
						IRunnableWithControl irw=new TestWorkerThread();
						irw.run(control);
					}
				});
				t.start();
			}
		});
		
		b_pause.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				b_start.setEnabled(false);
				b_pause.setEnabled(false);
				b_continue.setEnabled(true);
				b_stop.setEnabled(false);
				control.pause();
			}
		});
		
		b_continue.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				b_start.setEnabled(false);
				b_pause.setEnabled(true);
				b_continue.setEnabled(false);
				b_stop.setEnabled(true);
				control.cont();				
			}
		});
		
		b_stop.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				b_start.setEnabled(true);
				b_pause.setEnabled(false);
				b_continue.setEnabled(false);
				b_stop.setEnabled(false);
				control.stop();								
			}
		});
		
		panel.add(b_start);
		panel.add(b_pause);
		panel.add(b_continue);
		panel.add(b_stop);
		
		frm.setVisible(true);
	}

}
