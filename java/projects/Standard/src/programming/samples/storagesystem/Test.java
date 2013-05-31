package programming.samples.storagesystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test {
	private StorageSystem s;

	public Test() {
		s = new ArrayStorage();
	}

	public void printBanner() {
		System.out.println("Welcome to the storage system");
	}

	public void printMenu() {
		System.out.println("1) add item");
		System.out.println("2) delete item");
		System.out.println("3) print the inventory");
		System.out.println("4) exit");
	}

	public int getNumber(String message) {
		System.out.print(message);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			String input = br.readLine();
			return Integer.parseInt(input);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void doAddItem() {
		Book b = new Book();
		b.fromConsole();
		s.saveItem(b);
	}

	public void doDeleteItem() {
		int id = getNumber("What is to delete ? ");
		s.deleteItem(id);
	}

	public void doPrintInventory() {
		s.itrInit();
		while (!s.itrIsOver()) {
			Item i = s.itrGetCurrent();
			i.toConsole();
			s.itrNext();
		}
	}

	private static final String ERR_STRING1 = "bad selection";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Test t = new Test();
		t.printBanner();
		t.printMenu();
		int res = t.getNumber("What is your command? ");
		while (res != 4) {
			switch (res) {
			case 1:
				t.doAddItem();
				break;
			case 2:
				t.doDeleteItem();
				break;
			case 3:
				t.doPrintInventory();
				break;
			default:
				throw new RuntimeException(ERR_STRING1);
			}
			res = t.getNumber("What is your command? ");
		}
	}

}
