package core.collections;

/**
 * This is a simple single linked list implemented in java without the aid of
 * the java collection package. The drawback of this implementation is that
 * order of iteration is different than order of insertion.
 * @author Mark Veltzer <mark@veltzer.net>
 */

public class SingleLinkedListCorrectIterOrder {
	private static final class SingleLinkedListElem {
		private Object data;
		private SingleLinkedListElem next;

		private SingleLinkedListElem(Object idata, SingleLinkedListElem inext) {
			data = idata;
			next = inext;
		}
	}

	public static final class Iterator {
		private SingleLinkedListElem curr;

		private Iterator(SingleLinkedListElem icurr) {
			curr = icurr;
		}

		boolean hasNext() {
			return curr != null;
		}

		Object next() {
			Object ret = curr.data;
			curr = curr.next;
			return ret;
		}
	}

	private int size;
	private SingleLinkedListElem first, last;

	public SingleLinkedListCorrectIterOrder() {
		first = last = null;
		size = 0;
	}

	public void add(Object data) {
		if (last != null) {
			last = last.next = new SingleLinkedListElem(data, null);
		} else {
			last = first = new SingleLinkedListElem(data, null);
		}
		size++;
	}

	public int size() {
		return size;
	}

	public Iterator getIterator() {
		return new Iterator(first);
	}

	public static void main(String[] args) {
		SingleLinkedListCorrectIterOrder li = new SingleLinkedListCorrectIterOrder();
		li.add(45);
		li.add(23);
		li.add(75);
		System.out.println("size of the list is " + li.size());
		SingleLinkedListCorrectIterOrder.Iterator it = li.getIterator();
		while (it.hasNext()) {
			Integer i = (Integer) it.next();
			System.out.println("i is " + i);
		}
	}
}
