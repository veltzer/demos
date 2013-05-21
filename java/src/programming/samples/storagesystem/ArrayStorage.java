package programming.samples.storagesystem;

public class ArrayStorage implements StorageSystem {
	private Item[] array;
	private int index;

	public ArrayStorage() {
		array = new Item[0];
	}

	@Override
	public Item getItemById(int id) {
		return array[id];
	}

	@Override
	public void saveItem(Item i) {
		// put the item in the last place
		int length = array.length;
		i.setId(length);
		Item[] newarray = new Item[length + 1];
		System.arraycopy(array, 0, newarray, 0, length);
		newarray[length] = i;
		array = newarray;

	}

	@Override
	public void deleteItem(int id) {
		int length = array.length;
		array[id] = array[length - 1];
		array[id].setId(id);
		array[length - 1] = null;
		Item[] newarray = new Item[length - 1];
		System.arraycopy(array, 0, newarray, 0, length - 1);
		array = newarray;
	}

	@Override
	public void deleteItem(Item i) {
		deleteItem(i.getId());

	}

	@Override
	public void itrInit() {
		index = 0;
	}

	@Override
	public boolean itrIsOver() {
		return (index >= array.length);
	}

	@Override
	public void itrNext() {
		index++;
	}

	@Override
	public Item itrGetCurrent() {
		return array[index];
	}
}
