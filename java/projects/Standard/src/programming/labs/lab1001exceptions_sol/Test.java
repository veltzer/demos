package programming.labs.lab1001exceptions_sol;

public abstract class Test {

	public static void main(String[] args) {
		Business business = Bank.getBank();

		business.addCustomer(new Customer("Ben", "1234", 40));
		business.getCustomer(0).addAccount(new CheckingAccount(2000));
		business.getCustomer(0).addAccount(new BusinessAccount(2000));

		try {
			business.getCustomer(0).getAccount(0).withdraw(1000);
		} catch (OverdraftException e) {
			System.err.println("could not withdraw " + e.getAmount());
		}

		try {
			business.getCustomer(0).getAccount(1).withdraw(13000);
		} catch (OverdraftException e) {
			System.err.println("could not withdraw " + e.getAmount());
		}
	}
}
