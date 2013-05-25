package interbit.sorter.comparators;

import interbit.sorter.Item;

import java.util.Comparator;

public class ItemComparatorByName implements Comparator<Item> {

	public int compare(Item o1, Item o2) {
		return o1.getName().compareTo(o2.getName());
	}

}
