package test;

import java.util.List;

import interbit.sorter.Item;
import interbit.sorter.Sorted;
import interbit.sorter.Sorter;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		BeanFactory bf = new FileSystemXmlApplicationContext("beans.xml");
		Sorter sorter =(Sorter)bf.getBean("sorter");
		Sorted sorted = (Sorted)sorter;
		System.out.println("Sorted: " + sorted.isSorted());
		sorter.sort();
		System.out.println("Sorted: " + sorted.isSorted());
		List<Item> sortedItems = sorter.getItems();
		int y = 0;
		for (Item item : sortedItems)
		{
			y+= item.getPrice();
			//System.out.println(item);
		}
		System.out.println(y);
		
	}

}
