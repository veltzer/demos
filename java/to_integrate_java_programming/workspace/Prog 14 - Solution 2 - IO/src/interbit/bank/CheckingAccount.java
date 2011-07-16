package interbit.bank;

public class CheckingAccount extends Account {

	private static final long serialVersionUID = 1L;
	private double COMMITION = 0.05;

	public CheckingAccount(double balance) {
		super(balance);
	}

	@Override
	public double withdraw(double amount) throws OverdraftException {
		if (amount * (1 + COMMITION) <= getBalance()) {
			setBalance(getBalance() - (amount * (1 + COMMITION)));
			return amount * (1 + COMMITION);
		} else
			throw new OverdraftException(
					"The amount bigger than the balance!!!");
	}

}
