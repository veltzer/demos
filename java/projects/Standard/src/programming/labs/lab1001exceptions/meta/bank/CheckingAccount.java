package programming.labs.lab1001exceptions.meta.bank;

public class CheckingAccount extends Account {

	private static final double COMMITION = 0.05;

	public CheckingAccount(double balance) {
		super(balance);
	}

	public double withdraw(double amount) {
		if (amount * (1 + COMMITION) <= getBalance()) {
			setBalance(getBalance() - (amount * (1 + COMMITION)));
			return amount * (1 + COMMITION);
		} else {
			return 0.0;
		}
	}

}
