package test;

import interbit.sorter.Item;
import interbit.sorter.Sorted;
import interbit.sorter.Sorter;

import java.util.List;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class OneWayMain {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		BeanFactory bf = new FileSystemXmlApplicationContext("beans.xml");
		Sorter sorter =(Sorter)bf.getBean("sorter");
		Sorted sorted = (Sorted)sorter;
		System.out.println("Sorted: " + sorted.isSorted());
		sorter.sort();

		Thread.sleep(1000);

		System.out.println("Sorted: " + sorted.isSorted());
		List<Item> sortedItems = sorter.getItems();
		int y = 0;
		for (Item item : sortedItems)
		{
			y+= item.getPrice();
			//System.out.println(item);
		}
		System.out.println(y);
		((AbstractApplicationContext) bf).close();
	}

}
