package com.interbit.storagesystem;

public interface StorageSystem {
	void saveItem(Item i);
	Item getItemById(int id);
	void deleteItem(int id);
	void deleteItem(Item i);
	// This is for iteration purposes
	void itrInit();
	Item itrGetCurrent();
	void itrNext();
	boolean itrIsOver();
}
