package interbit.sorter.impl;

import interbit.sorter.Item;
import interbit.sorter.ItemFactory;

import org.springframework.beans.factory.FactoryBean;

public class ItemFactoryImpl implements FactoryBean<Item>, ItemFactory{

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
		
		return  new Item("Item" + (counter++) , 100*Math.random());
	}

}
