package meta.sorter.comparators;


import java.util.Comparator;

import meta.sorter.Item;

public class ItemComparatorByPrice implements Comparator<Item> {

	public int compare(Item o1, Item o2) {
		return Double.compare(o1.getPrice(), o2.getPrice());
	}

}
