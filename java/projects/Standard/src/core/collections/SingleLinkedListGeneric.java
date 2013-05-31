package core.collections;

/**
 * This is a simple single linked list implemented in java without the aid of
 * the java collection package. The drawback of this implementation is that
 * order of iteration is different than order of insertion.
 * @author Mark Veltzer <mark@veltzer.net>
 */

public class SingleLinkedListGeneric<T> {
	private static final class SingleLinkedListElem<T> {
		private T data;
		private SingleLinkedListElem<T> next;

		private SingleLinkedListElem(T idata, SingleLinkedListElem<T> inext) {
			data = idata;
			next = inext;
		}
	}

	public static final class Iterator<T> {
		private SingleLinkedListElem<T> curr;

		private Iterator(SingleLinkedListElem<T> icurr) {
			curr = icurr;
		}

		boolean hasNext() {
			return curr != null;
		}

		T next() {
			T ret = curr.data;
			curr = curr.next;
			return ret;
		}
	}

	private int size;
	private SingleLinkedListElem<T> first;

	public SingleLinkedListGeneric() {
		first = null;
		size = 0;
	}

	public void add(T data) {
		first = new SingleLinkedListElem<T>(data, first);
		size++;
	}

	public int size() {
		return size;
	}

	public Iterator<T> getIterator() {
		return new Iterator<T>(first);
	}

	public static void main(String[] args) {
		SingleLinkedListGeneric<Integer> li = new SingleLinkedListGeneric<Integer>();
		li.add(45);
		li.add(23);
		li.add(75);
		System.out.println("size of the list is " + li.size());
		SingleLinkedListGeneric.Iterator<Integer> it = li.getIterator();
		while (it.hasNext()) {
			Integer i = (Integer) it.next();
			System.out.println("i is " + i);
		}
	}
}
