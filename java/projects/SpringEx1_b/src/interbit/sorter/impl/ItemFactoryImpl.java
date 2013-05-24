package interbit.sorter.impl;

import org.springframework.beans.factory.FactoryBean;

import interbit.sorter.Item;
import interbit.sorter.ItemFactory;

public class ItemFactoryImpl implements ItemFactory, FactoryBean {

	private int counter = 0;

	public Item createItem() {
		return new Item("Item" + (counter++), 100 * Math.random());
	}

	@Override
	public Object getObject() throws Exception {
		return createItem();
	}

	@Override
	public Class getObjectType() {
		return Item.class;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

	
}
