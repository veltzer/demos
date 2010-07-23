package extreme.collections;

import java.util.ArrayList;
import java.util.List;

public class CuncurrentModification {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<Integer> li=new ArrayList<Integer>();
		li.add(4);
		li.add(2);
		li.add(78);
		for(int i:li) {
			System.out.println("i is "+i);
			li.remove(0);
		}
		System.out.println(li.size());
	}

}
