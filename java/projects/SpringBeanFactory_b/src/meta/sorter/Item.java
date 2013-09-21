package meta.sorter;

public class Item {
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
