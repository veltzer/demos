package exercise;

/**
 * @author Mark Veltzer <mark@veltzer.net>
 */
@SuppressWarnings("serial")
public class Item implements java.io.Serializable {

	/** Holds value of property name. */
	private String name;

	/** Holds value of property price. */
	private double price;

	/** Holds value of property itemId. */
	private String itemId;

	/** Creates new Item */
	public Item() {
	}

	public Item(String id, String iname, double iprice) {
		itemId = id;
		name = iname;
		price = iprice;
	}

	/**
	 * Getter for property name.
	 * @return Value of property name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter for property name.
	 * @param name New value of property name.
	 */
	public void setName(String iname) {
		name = iname;
	}

	/**
	 * Getter for property price.
	 * @return Value of property price.
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Setter for property price.
	 * @param price New value of property price.
	 */
	public void setPrice(double iprice) {
		price = iprice;
	}

	/**
	 * Getter for property itemId.
	 * @return Value of property itemId.
	 */
	public String getItemId() {
		return itemId;
	}

	/**
	 * Setter for property itemId.
	 * @param itemId New value of property itemId.
	 */
	public void setItemId(String iitemId) {
		itemId = iitemId;
	}

}
