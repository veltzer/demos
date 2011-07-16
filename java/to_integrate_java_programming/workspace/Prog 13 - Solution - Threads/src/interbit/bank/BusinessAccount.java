package interbit.bank;

public class BusinessAccount extends Account {
	public double AMOUNT_PROTECTION = 10000;

	public BusinessAccount(double balance) {
		super(balance);
	}

	@Override
	public double withdraw(double amount) throws OverdraftException {
		if (getBalance() - amount >= -AMOUNT_PROTECTION) {
			setBalance(getBalance() - amount);
			return amount;
		} else
			throw new OverdraftException(
					"Your amount protection is 10000 only!!!");
	}

}
