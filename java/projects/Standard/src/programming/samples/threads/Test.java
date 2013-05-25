package programming.samples.threads;

public class Test {

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
			e.printStackTrace();
		}
		whatisthis.run();

	}

}
