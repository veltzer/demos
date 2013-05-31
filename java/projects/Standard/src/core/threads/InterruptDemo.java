package core.threads;

/**
 * This demos that thread.interrupt is only effective when the thread being
 * interrupted is actually in sleep, wait or in a blocking IO call (read/write).
 * It is a signal delivered by the OS.
 * @author Mark Veltzer <mark@veltzer.net>
 */

public abstract class InterruptDemo {
	// This thread runs forever and always consumes CPU
	private static class T1 extends Thread {

		private volatile boolean over = false;

		@Override
		public void run() {
			try {
				float result = 0;
				while (!over) {
					float f = 1;
					result += Math.sin(f);
					f++;
					// This is to trick the smartass
					// compiler and his nagging warnings
					if (over) {
						sleep(5);
					}
				}
				System.out.println("result is " + result);
			} catch (InterruptedException e) {
				System.out.println("T1 ending");
				// throw new RuntimeException(e);
			}
		}

	}

	// This thread does sleep (most of the time)
	private static class T2 extends Thread {

		@Override
		public void run() {
			try {
				while (true) {
					sleep(1);
				}
			} catch (InterruptedException e) {
				System.out.println("T2 ending");
				// throw new RuntimeException(e);
			}
		}
	}

	public static void main(String[] args) {
		T1 t1 = new T1();
		T2 t2 = new T2();
		t1.start();
		t2.start();
		t1.interrupt();
		t2.interrupt();
	}
}
