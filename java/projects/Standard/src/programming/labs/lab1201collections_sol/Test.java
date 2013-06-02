package programming.labs.lab1201collections_sol;

public abstract class Test {

	public static void main(String[] args) {
		Business bank = Bank.getBank();
		bank.addCustomer(new Customer());
		for (int i = 0; i < 100; i++) {
			bank.getCustomer(0).addAccount(new CheckingAccount(i));
		}

		for (int i = 0; i < bank.getCustomer(0).getNumOfAccounts(); i++) {
			System.out.println("Balance " + i + ": "
					+ bank.getCustomer(0).getAccount(i).getBalance());
		}
	}
}
