package com.interbit.storagesystem;

public abstract class Item {
	private int id;

	abstract void fromConsole();

	public void toConsole() {
		System.out.println("id: " + id);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
