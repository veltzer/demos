package programming.labs.lab0701inheritance_sol;

public abstract class Test {

	public static void main(String[] args) {
		Bank bank = Bank.getBank();
		bank.addCustomer(new Customer("Sharon", "1234", 21, new Account(5000)));
		bank.addCustomer(new Customer("Anat", "1235", 21, new BusinessAccount(
				5000)));

		bank.getCustomer(0).getAccount().withdraw(2000);
		bank.getCustomer(1).getAccount().withdraw(2000);
		System.out.println("Customer1 balnce:"
				+ bank.getCustomer(0).getAccount().getBalance());
		System.out.println("Customer2 balnce:"
				+ bank.getCustomer(1).getAccount().getBalance() + "\n");

		bank.getCustomer(0).getAccount().withdraw(5000);
		bank.getCustomer(1).getAccount().withdraw(5000);
		System.out.println("Customer1 balnce:"
				+ bank.getCustomer(0).getAccount().getBalance());
		System.out.println("Customer2 balnce:"
				+ bank.getCustomer(1).getAccount().getBalance());
	}
}
