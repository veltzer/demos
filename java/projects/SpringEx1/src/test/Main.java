package test;


import java.util.List;

import meta.sorter.Item;
import meta.sorter.Sorted;
import meta.sorter.Sorter;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public abstract class Main {

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		BeanFactory bf = new FileSystemXmlApplicationContext("beans.xml");
		Sorter sorter = (Sorter) bf.getBean("sorter");
		Sorted sorted = (Sorted) sorter;
		System.out.println("Sorted: " + sorted.isSorted());
		sorter.sort();
		System.out.println("Sorted: " + sorted.isSorted());
		List<Item> sortedItems = sorter.getItems();
		int y = 0;
		for (Item item : sortedItems) {
			y += item.getPrice();
			// System.out.println(item);
		}
		System.out.println(y);
		Thread.sleep(210000);
		((AbstractApplicationContext) bf).close();
	}

}
