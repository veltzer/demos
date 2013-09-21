package programming.labs.lab1001exceptions.meta.bank;

public final class Bank implements Business {
	private static Bank bank;
	private Customer[] customers;
	private int index;
	private static boolean isExist;

	private Bank() {
		customers = new Customer[1000];
	}

	public static Business getBank() {
		if (!isExist) {
			bank = new Bank();
			isExist = true;
		}
		return bank;
	}

	public void addCustomer(Customer customer) {
		if (index < customers.length) {
			customers[index++] = customer;
		}
	}

	public Customer getCustomer(int iindex) {
		if (iindex < index) {
			return customers[iindex];
		} else {
			return null;
		}
	}

	public int getNumOfCustomers() {
		return index;
	}
}
