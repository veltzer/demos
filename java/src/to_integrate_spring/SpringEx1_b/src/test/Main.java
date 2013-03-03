package test;

import interbit.sorter.Item;
import interbit.sorter.Sorted;
import interbit.sorter.Sorter;

import java.util.List;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;

public class Main {

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

		try {
			Thread.sleep(1000000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
