package interbit.sorter.impl.old;

import org.springframework.beans.factory.FactoryBean;

import interbit.sorter.Item;
import interbit.sorter.ItemFactory;

public class ItemFactoryImpl implements  ItemFactory{

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
