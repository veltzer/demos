package sorter.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import sorter.Item;
import sorter.ItemFactory;
import sorter.Sorter;

public abstract class SorterImpl implements Sorter {

	private int itemCount;
	private List<Item> items;
	private Comparator<Item> comparator;

	public void setList(List<Item> unsorted) {
		items = unsorted;
	}

	public void setComparator(Comparator<Item> icomparator) {
		comparator = icomparator;
	}

	public void sort() {
		Collections.sort(items, comparator);
	}

	public List<Item> getItems() {
		return Collections.unmodifiableList(items);
	}

	/*
	 * public void addItem(Item item) { items.add(item); }
	 */
	public void setItemCount(int iitemCount) {
		itemCount = iitemCount;
	}

	public abstract ItemFactory getItemFactory();

	public void init() {
		for (int i = 0; i < itemCount; i++) {
			items.add(getItemFactory().createItem());
		}
	}
}
