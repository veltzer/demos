package programming.samples.threads;

public abstract class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(Thread.currentThread());
		Thread whatisthis = new Thread("A new thread") {
			public void run() {
				System.out.println(Thread.currentThread());
			}
		};
		whatisthis.start();
		try {
			whatisthis.join();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		whatisthis.run();

	}

}
