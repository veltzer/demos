package core.threads;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * A demonstration of how to use atomic variables.
 * @author Mark Veltzer <mark@veltzer.net>
 */

public abstract class Atomics {

	private static class MyRunnable implements Runnable {
		private int myVal;
		private int iter;
		private AtomicInteger at;

		public MyRunnable(int iinitval, AtomicInteger iat, int iiter) {
			myVal = iinitval;
			at = iat;
			iter = iiter;
		}

		public void run() {
			int i = 0;
			while (i < iter) {
				if (at.compareAndSet(myVal, myVal + 1)) {
					System.out.println("thread "
							+ Thread.currentThread().getId() + ": myVal is "
							+ myVal);
					myVal += 2;
					i++;
				}
			}
		}
	}

	public static void main(String[] args) {
		AtomicInteger at = new AtomicInteger(0);
		Runnable run1 = new MyRunnable(0, at, 50000);
		Runnable run2 = new MyRunnable(1, at, 50000);
		new Thread(run1).start();
		new Thread(run2).start();
	}
}
