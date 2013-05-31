package core.threads;

/**
 * Is there a way to optimize this solution further ?!? Instead of putting
 * everyone to sleep on one big lock we can several queues: one for readers and
 * one for writers and thus create a "reader preferred RWLock" or
 * "Writer preferred RWLock". We could even add priorities put threads to sleep
 * on a special lock per priority.
 * @author Mark Veltzer <mark@veltzer.net>
 */

public class ReaderWriterLock {

	private static class ReadWriteThread extends Thread {
		private boolean reader;
		private ReaderWriterLock lock;

		public void run() {
			if (reader) {
				lock.read();
				try {
					sleep((long) (Math.random() * 10000));
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
				lock.readLeave();
			} else {
				lock.write();
				try {
					sleep((long) (Math.random() * 10000));
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
				lock.writeLeave();
			}
		}

		public ReadWriteThread(boolean ireader, ReaderWriterLock ilock) {
			super();
			reader = ireader;
			lock = ilock;
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ReaderWriterLock lock = new ReaderWriterLock();
		for (int i = 0; i < 100; i++) {
			ReadWriteThread t = new ReadWriteThread(i % 10 != 0, lock);
			t.start();
		}

	}

	private int readers = 0;
	private int writers = 0;
	private int readersWaiting = 0;
	private int writersWaiting = 0;

	public synchronized void read() {
		readersWaiting++;
		while (writers > 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
		readersWaiting--;
		readers++;
		debug();
	}

	public synchronized void readLeave() {
		readers--;
		// this is the naive version
		/*
		 * notifyAll();
		 */
		// this is the more performance oriented version
		if (readers == 0) {
			notify();
		}
		debug();
	}

	public synchronized void write() {
		writersWaiting++;
		while (readers > 0 || writers > 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
		writersWaiting--;
		writers++;
		debug();
	}

	public synchronized void writeLeave() {
		writers--;
		notifyAll();
		debug();
	}

	public int getReaders() {
		return readers;
	}

	public int getReadersWaiting() {
		return readersWaiting;
	}

	public int getWriters() {
		return writers;
	}

	public int getWritersWaiting() {
		return writersWaiting;
	}

	// the synchronized is in order to print all the data in
	// a 'consistent' state...
	public synchronized void debug() {
		System.out.println("readers " + readers);
		System.out.println("readersWaiting " + readersWaiting);
		System.out.println("writers " + writers);
		System.out.println("writersWaiting " + writersWaiting);

	}
}
