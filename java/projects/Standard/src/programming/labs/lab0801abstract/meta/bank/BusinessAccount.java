package programming.labs.lab0801abstract.meta.bank;

public class BusinessAccount extends Account {
	public static final double AMOUNT_PROTECTION = 10000;

	public BusinessAccount(double balance) {
		super(balance);
	}

	public double withdraw(double amount) {
		if (getBalance() - amount >= -AMOUNT_PROTECTION) {
			setBalance(getBalance() - amount);
			return amount;
		} else {
			return 0.0;
		}
	}

}
