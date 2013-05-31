package core.gc;

import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.util.LinkedList;

/**
 * Demonstration of the SoftReference wrapper. This class implements a pool of
 * objets, which are softly linked. Not all objects in the pool may actually
 * exist. Notice that this class does not handle synchronization. Maybe it
 * should.
 * @author Mark Veltzer <mark@veltzer.net>
 */
public class SoftPool {
	private LinkedList<SoftReference<PooledObject>> pooledObjects;

	public SoftPool(int initialSize) {
		pooledObjects = new LinkedList<SoftReference<PooledObject>>();
		for (int i = 0; i < initialSize; ++i) {
			addPooledObject(new PooledObject());
		}
	}

	public int getSize() {
		return pooledObjects.size();
	}

	/**
	 * Add an object to the pool. The method will add a soft reference for that
	 * object.
	 * @param pooledObject
	 */
	public void addPooledObject(PooledObject pooledObject) {
		pooledObjects.addLast(new SoftReference<PooledObject>(pooledObject));
	}

	public PooledObject getPooledObject() {
		while (!pooledObjects.isEmpty()) {
			SoftReference<PooledObject> reference = pooledObjects.removeLast();
			PooledObject referee = reference.get();
			if (referee != null) {
				return referee;
			}
		}
		// This means that we have no more objects in the pool.
		// What do we do here?
		// Perhaps block
		// Perhaps retun null
		// Perhaps throw an exception
		// Here is an idea: lets return a new object
		return new PooledObject();
	}

	/**
	 * How many objects are there, really?
	 * @return number of real objects in the pool
	 */
	public int getAvailableSize() {
		int result = 0;
		for (Reference<PooledObject> reference : pooledObjects) {
			if (reference.get() != null) {
				result++;
			}
		}
		return result;
	}

	public static void main(String[] args) {
		System.out.println("starting");
		try {
			SoftPool pool = new SoftPool(90000);
			System.out.println("There are " + pool.getAvailableSize()
					+ " out of " + pool.getSize() + " objects available");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
