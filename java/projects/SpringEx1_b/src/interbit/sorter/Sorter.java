package interbit.sorter;


import java.util.Comparator;
import java.util.List;

import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;

public interface Sorter {
	void addItem(Item item);
	
	void setList(List<Item> unsorted);

	void setComparator(Comparator<Item> comparator);
	
	void sort();
	
	List<Item> getItems();
}
