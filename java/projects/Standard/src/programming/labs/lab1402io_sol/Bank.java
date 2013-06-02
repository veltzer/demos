package programming.labs.lab1402io_sol;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@SuppressWarnings("serial")
public final class Bank implements Business, Serializable {
	private static Bank bank = null;
	private List<Customer> customers;

	private Bank() {
		customers = new LinkedList<Customer>();
	}

	public static synchronized Business getBank() {
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

	public void removeCustomer(int index) {
		customers.remove(index);
	}
}
