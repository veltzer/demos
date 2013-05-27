package core.threads;

/**
 * An N semaphore. This class controls access to N resources. The number of
 * resources is set at construction time. Each request can be made to any number
 * of resources and the requester is blocked until he gets those resources. The
 * semaphore relies on everyone being "nice" in that they call the semaphoe
 * correctly and state the same amount in the up up and down methods. This class
 * is not fair in the sense that many threads wanting little resources will
 * probably get them and thus starve a single thread which wants lots of
 * resources. An improvement could be a first come first serve fix.
 * @author Mark Veltzer <mark@veltzer.net>
 */
public class Semaphore {
	private int currentPermits;

	public Semaphore(int maxPermits) {
		currentPermits = maxPermits;
	}

	public synchronized void down(int amount) {
		while (amount > currentPermits) {
			try {
				wait();
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
		currentPermits = currentPermits - amount;
	}

	public synchronized void up(int amount) {
		currentPermits = currentPermits + amount;
		notifyAll();
	}
}
