package core.threads;

/**
 * A simple demonstration of thread priorities. Playing with the various
 * parameters: the data array size, the threads' sleeping time, and the threads'
 * priorities, can become a simple benchmark to see how thread priorities work.
 * This is highly system and JRE dependent.
 * @author Mark Veltzer <mark@veltzer.net>
 */
public final class PriorityTest {
	/**
	 * The longer the array - the more difference we will see with the threads'
	 * timing (when dealing with different priorities).
	 */
	private int[] data = new int[100];

	private class ActingThread extends Thread {
		private int numOperations;

		public ActingThread(String name) {
			super(name);
		}

		@Override
		public void run() {
			while (true) {
				data[0] = 0;
				for (int i = 1; i < data.length; ++i) {
					data[i] = 1 + data[i - 1];
				}
				++numOperations;

				/*
				 * try { Thread.sleep(1); } catch (InterruptedException e) {
				 * throw new RuntimeException(e); }
				 */
			}
		}

		public void printNumOperations() {
			System.out.println(getName() + ": " + numOperations);
		}
	}

	private void act() {
		ActingThread t1 = new ActingThread("t1");
		ActingThread t2 = new ActingThread("t2");

		// t1.setDaemon(true);
		// t2.setDaemon(true);

		t1.setPriority(Thread.MAX_PRIORITY);
		t2.setPriority(Thread.MIN_PRIORITY);

		t1.start();
		t2.start();
		while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			t1.printNumOperations();
			t2.printNumOperations();
		}
	}

	private PriorityTest() {
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			PriorityTest test = new PriorityTest();
			test.act();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		System.out.println("when?");
	}
}
