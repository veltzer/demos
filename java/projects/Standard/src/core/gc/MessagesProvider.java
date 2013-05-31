package core.gc;

import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.List;

/**
 * Demonstrate the WeakReference. A provided allows for listeners registration,
 * but only holds weak references for those listeners.
 * @author Mark Veltzer <mark@veltzer.net>
 */
public class MessagesProvider {
	private List<WeakReference<MessagesListener>> listeners;

	/**
	 * A helping class - a daemon thread which loops forever and notifies all
	 * available listener
	 */
	private class Notifier extends Thread {
		public Notifier() {
			setDaemon(true);
		}

		@Override
		public void run() {
			int counter = 0;
			while (true) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
				// Let everyone know!
				notifyListeners("Message #" + counter);
				counter++;
			}
		}
	}

	/**
	 * Create the messages provider, start the notifier thread.
	 */
	public MessagesProvider() {
		listeners = new LinkedList<WeakReference<MessagesListener>>();
		new Notifier().start();
	}

	/**
	 * Add a new listener. The listener itself is not registered, but rather a
	 * WeakReference holding the listener.
	 * @param listener
	 */
	public void addListener(MessagesListener listener) {
		listeners.add(new WeakReference<MessagesListener>(listener));
	}

	/**
	 * Iterate the listeners, and notify each one of them. A listener may not
	 * neccessarily exist anymore: the WeakReference is consulted first.
	 * @param message
	 */
	private void notifyListeners(String message) {
		for (WeakReference<MessagesListener> ref : listeners) {
			MessagesListener listener = ref.get();
			if (listener != null) {
				listener.messageArrived(message);
			} else {
				// Wow! The object has been destroyed!
				System.out.println("Found null listener");
				// Would want to remove this listener. For purpose
				// of this demonstration we keep it.
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MessagesProvider provider = new MessagesProvider();
		MessagesListener l1 = new MessagesListener() {

			public void messageArrived(String message) {
				System.out.println("...l1 notified: " + message);

			}
		};
		provider.addListener(l1);

		MessagesListener l2 = new MessagesListener() {

			public void messageArrived(String message) {
				System.out.println("...l2 notified: " + message);

			}
		};
		provider.addListener(l2);

		l2 = null;
		// Object should soon be reclaimed, since it is
		// now only weakly referenced.

		for (int i = 0; i < 100000; ++i) {
			if (i % 10 == 0) {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
			// Please can you run the GC?
			if (i > 10000) {
				System.gc();
			}
		}

	}

}
