package programming.labs.lab0702inheritance.interbit.bank;

public final class Bank {
	private static Bank bank;
	private Customer[] customers;
	private int index;

	private Bank() {
		customers = new Customer[1000];
	}

	public static Bank getBank() {
		if (bank == null) {
			bank = new Bank();
		}
		return bank;
	}

	public void addCustomer(Customer customer) {
		if (index < customers.length) {
			customers[index++] = customer;
		} else {
			throw new RuntimeException("no more customers");
		}
	}

	public Customer getCustomer(int iindex) {
		if (iindex >= 0 && iindex < index) {
			return customers[iindex];
		}
		return null;
	}

	public int getNumOfCustomers() {
		return index;
	}
}
