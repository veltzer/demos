package meta.sorter.impl;

import meta.sorter.Item;
import meta.sorter.ItemFactory;

import org.springframework.beans.factory.FactoryBean;


public class ItemFactoryImpl implements ItemFactory, FactoryBean<Object> {

	private int counter = 0;

	public Item createItem() {
		return new Item("Item" + (counter++), 100 * Math.random());
	}

	@Override
	public Object getObject() throws Exception {
		return createItem();
	}

	@Override
	public Class<Item> getObjectType() {
		return Item.class;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

}
