package sorter.impl;

import org.springframework.beans.factory.FactoryBean;

import sorter.Item;
import sorter.ItemFactory;


public class ItemFactoryImpl implements FactoryBean, ItemFactory{

	private int counter = 0;

	public Object getObject() throws Exception {
		return createItem();
	}

	public Class getObjectType() {
		return Item.class;
	}

	public boolean isSingleton() {
		return false;
	}

	public Item createItem() {
		
		return  new Item("Item" + (counter++) , 100*Math.random());
	}
}
