package core.collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This is a simple demo of java array list API
 * @author Mark Veltzer <mark@veltzer.net>
 */

public abstract class SimpleArrayList {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<Integer> li = new ArrayList<Integer>();
		li.add(4);
		li.add(2);
		li.add(78);
		System.out.println("starting iteration with java 5 for loop");
		for (int value : li) {
			System.out.println("value is " + value);
		}
		System.out.println("starting iteration with explicit iterator");
		Iterator<Integer> iter = li.iterator();
		while (iter.hasNext()) {
			Integer value = iter.next();
			System.out.println("value is " + value);
		}
		System.out.println(li.size());
	}

}
