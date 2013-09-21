package meta.sorter.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import meta.sorter.Item;
import meta.sorter.ItemFactory;
import meta.sorter.Sorter;

import org.springframework.beans.factory.annotation.Configurable;


@Configurable
public abstract class SorterImpl2 implements Sorter, SorterImplMBean {

	private int itemCount;
	private List<Item> items;
	private Comparator<Item> comparator;

	public void setList(List<Item> unsorted) {
		this.items = unsorted;
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
