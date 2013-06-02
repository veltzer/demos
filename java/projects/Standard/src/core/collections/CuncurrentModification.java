package core.collections;

import java.util.ArrayList;
import java.util.List;

public abstract class CuncurrentModification {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<Integer> li = new ArrayList<Integer>();
		li.add(4);
		li.add(2);
		li.add(78);
		for (int value : li) {
			System.out.println("value is " + value);
			li.remove(0);
		}
	}
}
