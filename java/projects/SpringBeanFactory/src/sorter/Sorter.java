package sorter;

import java.util.Comparator;
import java.util.List;

public interface Sorter {
	void addItem(Item item);

	void setList(List<Item> unsorted);

	void setComparator(Comparator<Item> comparator);

	void sort();

	List<Item> getItems();
}
