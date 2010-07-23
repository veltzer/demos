package extreme.threads;

/**
 * A simple implementaion of a read-write-lock. A read-write-lock allows for
 * multiple read actions (read permits), up to a pre-defined limit. A write
 * operation is only allowed if no locks are held. Thus, a write operation
 * cannot exist with any other operations. This lock is "unfair", in that it may
 * block a long-waiting write request in favour of newly arrived read requests.
 * An improvement could be do a first come first serve approach or maybe even
 * add priorities.
 * 
 * @author Mark Veltzer
 * 
 */
public class NaiiveReadWriteLock {
	private final int maxPermits;

	private int currentPermits;

	public NaiiveReadWriteLock(int maxPermits) {
		this.maxPermits = maxPermits;
		currentPermits = maxPermits;
	}

	private synchronized void down(int amount) {
		while (currentPermits - amount < 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		currentPermits = currentPermits - amount;
	}

	private synchronized void up(int amount) {
		currentPermits = currentPermits + amount;
		notifyAll();
	}

	public void aquireReadLock() {
		down(1);
	}

	public void releaseReadLock() {
		up(1);
	}

	public void aquireWriteLock() {
		down(maxPermits);
	}

	public void releaseWriteLock() {
		up(maxPermits);
	}
}
