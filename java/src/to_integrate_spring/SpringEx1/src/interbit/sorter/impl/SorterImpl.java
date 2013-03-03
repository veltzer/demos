package interbit.sorter.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Configurable;

import interbit.sorter.Item;
import interbit.sorter.ItemFactory;
import interbit.sorter.Sorter;

public abstract class SorterImpl implements Sorter, SorterImplMBean{

	private int itemCount;
	private List<Item> items;
	private Comparator<Item> comparator;
	
	public void setList(List<Item> unsorted) {
		this.items = unsorted; 
	}

	public void setComparator(Comparator<Item> comparator) {
		this.comparator = comparator;
	}

	public void sort() {
		Collections.sort(items, comparator);
	}

	public List<Item> getItems() {
		return Collections.unmodifiableList(items);
	}

/*	public void addItem(Item item) {
		items.add(item);
	}
	*/
	public void setItemCount(int itemCount)
	{
		this.itemCount = itemCount;
	}
	
	public abstract ItemFactory getItemFactory();

	
	public void init()
	{
		for (int i = 0; i < itemCount; i++)
		{
			items.add(getItemFactory().createItem());
		}
	}
}
