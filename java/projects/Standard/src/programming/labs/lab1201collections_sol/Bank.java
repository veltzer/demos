package programming.labs.lab1201collections_sol;

import java.util.LinkedList;
import java.util.List;

public final class Bank implements Business {
	private static Bank bank = null;
	private List<Customer> customers;

	private Bank() {
		customers = new LinkedList<Customer>();
	}

	public static Business getBank() {
		if (bank == null) {
			bank = new Bank();
		}
		return bank;
	}

	public void addCustomer(Customer customer) {
		customers.add(customer);
	}

	public Customer getCustomer(int index) {
		return customers.get(index);
	}

	public int getNumOfCustomers() {
		return customers.size();
	}
}
