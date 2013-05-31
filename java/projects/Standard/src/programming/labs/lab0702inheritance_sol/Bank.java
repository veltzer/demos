package programming.labs.lab0702inheritance_sol;

public final class Bank {
	// this is the single instance that will store the bank
	private static Bank bank = null;
	// this is a constant for private use to limit the number
	// of customers for this bank
	private static final int MAX_CUSTOMERS = 100;
	// this is the array which will be used to store the
	// customers
	private Customer[] customers;
	// this is the index which will be used to indicate
	// how far is the array filled up
	private int index;

	// private constructor - cannot be called
	// from the outside
	private Bank() {
		customers = new Customer[MAX_CUSTOMERS];
		// if there was no next line index would still be
		// 0 (java guarantees this) but it looks much
		// better this way
		index = 0;
	}

	// this is the static method used to get the bank
	// single instance - this is not thread safe
	public static Bank getBank() {
		// this if statement will only be executed once
		if (bank == null) {
			bank = new Bank();
		}
		return bank;
	}

	public void addCustomer(Customer customer) {
		// notice that we do not hard code the length
		// of the array here
		// if we add too many customers we will get
		// an out of bounds exception and this is fine
		// so there is no need for bounds checking here
		customers[index++] = customer;
	}

	public Customer getCustomer(int iindex) {
		// no need for manual error handling here since
		// we will get out of bounds exception
		return customers[iindex];
	}

	public int getNumOfCustomers() {
		return index;
	}

	public String toString() {
		String s = "Bank\n";
		for (int i = 0; i < index; i++) {
			s += customers[i];
		}
		return s;
	}
}
