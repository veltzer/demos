package meta.sorter;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Item implements Serializable {
	private String name;
	private double price;

	public Item(String iname, double iprice) {
		name = iname;
		price = iprice;
	}

	public double getPrice() {
		return price;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Item: " + name + " price: " + price;
	}

}
