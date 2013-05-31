package programming.labs.lab1001exceptions_sol;

public class BusinessAccount extends Account {
	public static final double AMOUNT_PROTECTION = 10000;

	public BusinessAccount(double ibalance) {
		super(ibalance);
	}

	public double withdraw(double amount) {
		if (getBalance() - amount >= -AMOUNT_PROTECTION) {
			setBalance(getBalance() - amount);
			return amount;
		} else {
			throw new OverdraftException(amount, AMOUNT_PROTECTION,
					getBalance(), getId());
		}
	}

}
