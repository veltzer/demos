package swing.multi_thread;

public interface IProcessControl {
	// APIs to be used by the controlling entity...
	void start();

	void stop();

	void pause();

	void cont();

	// APIs to be used by the controlled entity...
	boolean isStopped();

	void doPause();

	// APIs to report progress
	void initProgress(int val);

	void reportProgress(int val);

	void finishProgress();
}
