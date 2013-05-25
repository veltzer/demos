package swing.multi_thread;

public class TestWorkerThread implements IRunnableWithControl {

	@Override
	public void run(IProcessControl control) {
		int times = 10;
		control.initProgress(times);
		int i = 0;
		while (i < times && !control.isStopped()) {
			control.doPause();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			i++;
			control.reportProgress(i);
		}
		control.finishProgress();
	}

}
