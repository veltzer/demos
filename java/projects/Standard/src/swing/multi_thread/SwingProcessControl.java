package swing.multi_thread;

import java.util.concurrent.Semaphore;

import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

public class SwingProcessControl implements IProcessControl {
	private JProgressBar pr;
	private boolean stopped;
	private Semaphore sem;

	public SwingProcessControl(JProgressBar ipr) {
		pr = ipr;
		stopped = false;
		sem = new Semaphore(1);
	}

	@Override
	public boolean isStopped() {
		return stopped;
	}

	@Override
	public void pause() {
		try {
			sem.acquire();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void cont() {
		sem.release();
	}

	@Override
	public void doPause() {
		try {
			sem.acquire();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		sem.release();

	}

	@Override
	public void initProgress(int val) {
		final int ival = val;
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				pr.setValue(0);
				pr.setMaximum(ival);
			}
		});
	}

	@Override
	public void reportProgress(int val) {
		final int ival = val;
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				pr.setValue(ival);
			}
		});

	}

	@Override
	public void finishProgress() {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				pr.setValue(pr.getMaximum());
			}
		});

	}

	@Override
	public void stop() {
		stopped = true;
	}

	@Override
	public void start() {
		stopped = false;

	}

}
