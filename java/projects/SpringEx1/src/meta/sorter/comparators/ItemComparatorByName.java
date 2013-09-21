package meta.sorter.comparators;


import java.util.Comparator;

import meta.sorter.Item;

public class ItemComparatorByName implements Comparator<Item> {

	public int compare(Item o1, Item o2) {
		return o1.getName().compareTo(o2.getName());
	}

}
