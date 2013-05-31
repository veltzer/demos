package test;

import java.util.List;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import sorter.Item;
import sorter.Sorted;
import sorter.Sorter;

public abstract class Main {

	static final String ERR_STRING1 = "Oops, sorter is bad";

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		BeanFactory bf = new FileSystemXmlApplicationContext("beans.xml");
		Sorter sorter = (Sorter) bf.getBean("sorter");
		Sorted sorted = (Sorted) sorter;
		System.out.println("Sorted: " + sorted.isSorted());
		sorter.sort();
		System.out.println("Sorted: " + sorted.isSorted());
		List<Item> sortedItems = sorter.getItems();
		double previous = -100000;
		double sum = 0;
		for (Item item : sortedItems) {
			double currentPrice = item.getPrice();
			if (previous > currentPrice) {
				((AbstractApplicationContext) bf).close();
				throw new RuntimeException(ERR_STRING1);
			}
			sum += item.getPrice();
		}
		System.out.println("sum of all prices is " + sum);
		((AbstractApplicationContext) bf).close();
	}

}
