package programming.samples.bank;

public class Bank {
	static private Bank bank = null;

	static public Bank getBank() {
		if (bank == null) {
			bank = new Bank();
		}
		return bank;
	}

	private Customer[] customers;
	private int index;
	private final static int size = 100;

	private Bank() {
		customers = new Customer[size];
		index = 0;
	}

	public void addCustomer(Customer c) {
		customers[index++] = c;
	}

	public Customer getCustomer(int ind) {
		return customers[ind];
	}

	public int getNumOfCustomers() {
		return index;
	}

	static public void main(String[] args) {
		Account myaccount = new Account(Account.CHECKING_ACCOUNT, 3000);
		Customer c = new Customer("mark", "014995815", 35, myaccount);
		Bank b = Bank.getBank();
		b.addCustomer(c);
		System.out.println("bank has " + b.getNumOfCustomers() + " customers");
	}
}
