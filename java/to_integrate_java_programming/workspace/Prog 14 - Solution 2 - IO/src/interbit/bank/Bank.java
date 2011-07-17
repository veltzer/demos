package interbit.bank;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Bank implements Business, Serializable {
	// this is for serialization purposes (don't ask)
	private static final long serialVersionUID = 1L;
	private static Bank bank = null;
	private List<Customer> customers;

	private Bank() {
		customers = new LinkedList<Customer>();
	}

	public synchronized static Business getBank() {
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