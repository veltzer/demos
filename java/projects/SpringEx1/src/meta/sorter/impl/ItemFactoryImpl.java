package meta.sorter.impl;


import meta.sorter.Item;
import meta.sorter.ItemFactory;

import org.springframework.beans.factory.FactoryBean;

public class ItemFactoryImpl implements FactoryBean<Item>, ItemFactory {

	private int counter = 0;

	public Item getObject() throws Exception {
		return createItem();
	}

	public Class<Item> getObjectType() {
		return Item.class;
	}

	public boolean isSingleton() {
		return false;
	}

	public Item createItem() {

		return new Item("Item" + (counter++), 100 * Math.random());
	}

}
