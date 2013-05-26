package core.threads;

/**
 * A simple implementation of a read-write-lock. A read-write-lock allows for
 * multiple read actions (read permits), up to a predefined limit. A write
 * operation is only allowed if no locks are held. Thus, a write operation
 * cannot exist with any other operations. This lock is "unfair", in that it may
 * block a long-waiting write request in favor of newly arrived read requests.
 * An improvement could be do a first come first serve approach or maybe even
 * add priorities. The problem with the number of permits limiting the number of
 * readers could be solved by setting the number of permits high enough. Another
 * problem with the semaphore part of the this example is that we rely on each
 * client to return the exact number of permits that it took. We could, instead,
 * remember the number of permits that each thread took using a map or thread
 * local data and therefore change the signature of the up method to have no
 * arguments.
 * @author Mark Veltzer <mark@veltzer.net>
 */
public class NaiiveReadWriteLock {
	private final int maxPermits;
	private int currentPermits;

	public NaiiveReadWriteLock(int permits) {
		maxPermits = permits;
		currentPermits = maxPermits;
	}

	private synchronized void down(int amount) {
		while (currentPermits - amount < 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
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
