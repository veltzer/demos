package extreme.threads;

/**
 * Is there a way to optimize this solution further ?!?
 * 
 * Instead of putting everyone to sleep on one big lock we
 * can several queues: one for readers and one for writers
 * and thus create a "reader preferred RWLock" or "Writer preferred
 * RWLock". We could even add priorities put threads to sleep
 * on a special lock per priority.
 * 
 * @author Mark Veltzer 
 *
 */

public class ReaderWriterLock {
	
	private static class ReadWriteThread extends Thread {
		private boolean reader;
		private ReaderWriterLock lock;
		public void run() {
			if(reader) {
				lock.read();
				try {
					sleep((long) (Math.random()*10000));
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
				lock.readLeave();
			} else {
				lock.write();
				try {
					sleep((long) (Math.random()*10000));
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
				lock.writeLeave();				
			}
		}
		public ReadWriteThread(boolean reader, ReaderWriterLock lock) {
			super();
			this.reader = reader;
			this.lock = lock;
		}
	}
	private int readers=0;
	private int writers=0;
	private int readersWaiting=0;
	private int writersWaiting=0;
	
	public synchronized void read() {
		readersWaiting++;
		while(writers>0) {
			try {
				wait();
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
		readersWaiting--;
		readers++;
		print();
	}
	
	public synchronized void readLeave() {
		readers--;
		// this is the naive version
		/*
		notifyAll();
		*/
		// this is the more performance oriented version
		if(readers==0) {
			notify();
		}
		print();
	}
	
	public synchronized void write() {
		writersWaiting++;
		while(readers>0 || writers>0) {
			try {
				wait();
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
		writersWaiting--;
		writers++;
		print();
	}
	public synchronized void writeLeave() {
		writers--;
		notifyAll();
		print();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ReaderWriterLock lock=new ReaderWriterLock();
		for(int i=0;i<100;i++) {
			ReadWriteThread t=new ReadWriteThread(i%10!=0,lock);
			t.start();
		}

	}

	public int getReaders() {
		return readers;
	}

	public void setReaders(int readers) {
		this.readers = readers;
	}

	public int getReadersWaiting() {
		return readersWaiting;
	}

	public void setReadersWaiting(int readersWaiting) {
		this.readersWaiting = readersWaiting;
	}

	public int getWriters() {
		return writers;
	}

	public void setWriters(int writers) {
		this.writers = writers;
	}

	public int getWritersWaiting() {
		return writersWaiting;
	}

	public void setWritersWaiting(int writersWaiting) {
		this.writersWaiting = writersWaiting;
	}
	
	public synchronized void print() {
		System.out.println("readers "+readers);
		System.out.println("readersWaiting "+readersWaiting);
		System.out.println("writers "+writers);
		System.out.println("writersWaiting "+writersWaiting);

	}

}
