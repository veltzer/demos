package interbit.sorter.comparators;

import interbit.sorter.Item;

import java.util.Comparator;

public class ItemComparatorByPrice implements Comparator<Item>{

	public int compare(Item o1, Item o2) {
		return Double.compare(o1.getPrice(), o2.getPrice());
	}

}
