package domain;

import java.util.List;

public class ListHolderImpl implements ListHolder {

	private List<Integer> list;
	private int numOfElements;

	public void iterate() {
		/*
		 * int sum=0; for (Integer i : list) { sum += i; }
		 */

	}

	public void setNumOfElements(int inumOfElements) {
		numOfElements = inumOfElements;
	}

	public void setList(List<Integer> ilist) {
		list = ilist;
	}

	public void init() {
		for (int i = 0; i < numOfElements; i++) {
			list.add(new Integer(1));
		}
	}

}
