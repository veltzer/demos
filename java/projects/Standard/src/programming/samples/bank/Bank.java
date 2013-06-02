package programming.samples.bank;

public final class Bank {
	private static Bank bank = null;

	public static Bank getBank() {
		if (bank == null) {
			bank = new Bank();
		}
		return bank;
	}

	private Customer[] customers;
	private int index;
	private static final int SIZE = 100;

	private Bank() {
		customers = new Customer[SIZE];
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

	public static void main(String[] args) {
		Account myaccount = new Account(Account.CHECKING_ACCOUNT, 3000);
		Customer c = new Customer("mark", "014995815", 35, myaccount);
		Bank b = Bank.getBank();
		b.addCustomer(c);
		System.out.println("bank has " + b.getNumOfCustomers() + " customers");
	}
}
