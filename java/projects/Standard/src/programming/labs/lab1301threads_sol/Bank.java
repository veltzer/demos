package programming.labs.lab1301threads_sol;

import java.util.LinkedList;
import java.util.List;

public final class Bank implements Business {
	private static Bank bank = null;
	private List<Customer> customers;

	private Bank() {
		customers = new LinkedList<Customer>();
	}

	// can you think of what synchronized does on
	// a static method ?!?
	public static synchronized Business getBank() {
		if (bank == null) {
			bank = new Bank();
		}
		return bank;
	}

	public void addCustomer(Customer customer) {
		customers.add(customer);
	}

	public void removeCustomer(int index) {
		// No need to check about validity of index.
		// see note below.
		customers.remove(index);
	}

	public Customer getCustomer(int index) {
		// No need for error checking here
		// better to get OutOfBounds exception than
		// pretend all is well by returning null and get
		// hit by the exception later
		return customers.get(index);
	}

	public int getNumOfCustomers() {
		return customers.size();
	}
}
