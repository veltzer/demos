package core.threads;

import java.util.LinkedList;

/**
 * A simple blocking queue.
 * @author Mark Veltzer <mark@veltzer.net>
 */
public class BlockingQueue<E> {
	private LinkedList<E> linkedList;

	public BlockingQueue() {
		linkedList = new LinkedList<E>();
	}

	public E take() {
		synchronized (this) {
			while (linkedList.isEmpty()) {
				try {
					wait();
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
			return linkedList.removeFirst();
		}
	}

	public void put(E e) {
		synchronized (this) {
			linkedList.addLast(e);
			notify();
		}
	}
}
