package programming.labs.lab1301threads.meta.bank;

import java.util.LinkedList;
import java.util.List;

public final class Bank implements Business {
	private static Bank bank;
	private List<Customer> customers;
	private static boolean isExist;

	private Bank() {
		customers = new LinkedList<Customer>();
	}

	public static Business getBank() {
		if (!isExist) {
			bank = new Bank();
			isExist = true;
		}
		return bank;
	}

	public void addCustomer(Customer customer) {
		customers.add(customer);
	}

	public Customer getCustomer(int index) {
		if (index < customers.size()) {
			return (Customer) customers.get(index);
		} else {
			return null;
		}
	}

	public int getNumOfCustomers() {
		return customers.size();
	}

	public void removeCustomer(int index) {
		customers.remove(index);
	}
}
