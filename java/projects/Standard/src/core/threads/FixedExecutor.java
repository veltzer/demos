package core.threads;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * A very simple implementation of a fixed executor: an executor which has a
 * fixed number of threads, which all attempt at getting their hands on the next
 * runnable job to execute.
 * @author Mark Veltzer <mark@veltzer.net>
 */
public class FixedExecutor {
	/**
	 * A blocking queue of runnable jobs.
	 */
	private BlockingQueue<Runnable> runnables;

	/**
	 * A list of threads. In this implementation the list is unused. However, it
	 * may be a good idea to keep it, if, in the future, we may wish to have
	 * some control on the threads.
	 */
	private List<BlockingThread> threads;

	private class BlockingThread extends Thread {
		public BlockingThread() {
			// setDaemon(true);
		}

		@Override
		public void run() {
			while (true) {
				// Notice that we do not catch errors from the runnables.
				// It the user gives us bad code then it is his fault and we
				// should not try to cover the error up
				try {
					Runnable runnable = runnables.take();
					runnable.run();
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}

	public FixedExecutor(int numThreads) {
		runnables = new LinkedBlockingQueue<Runnable>();
		threads = new LinkedList<BlockingThread>();
		for (int i = 0; i < numThreads; ++i) {
			BlockingThread t = new BlockingThread();
			threads.add(t);
			t.start();
		}
	}

	public void execute(Runnable command) {
		try {
			runnables.put(command);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) {
		// This is the built in java ExecutorService
		// ExecutorService executor = Executors.newFixedThreadPool(4);

		FixedExecutor executor = new FixedExecutor(4);

		Runnable r = new Runnable() {

			public void run() {
				System.out.println("Doing something from thread "
						+ Thread.currentThread());
				// sleep for up to a second
				try {
					Thread.sleep((long) (Math.random() * 1000));
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
		};
		for (int i = 0; i < 100; ++i) {
			executor.execute(r);
		}
	}

}
