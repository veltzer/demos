package programming.labs.lab0702inheritance_sol;

public class BusinessAccount extends Account {
	private static final double AMOUNT_PROTECTION = 10000;

	// override the parents constructor
	// notice that if we do not add a constructor
	// then we will not get a free one from java
	// since our parents does NOT have a default constructor
	public BusinessAccount(double balance) {
		super(balance);
	}

	// this is an example of an overridden method which
	// does NOT call the parent implementation
	// there is nothing wrong with this pattern
	// we also forgot to charge commition
	public double withdraw(double amount) {
		if (getBalance() - amount >= -AMOUNT_PROTECTION) {
			setBalance(getBalance() - amount);
			return amount;
		} else {
			System.err.println("Cannot withdraw");
			return 0.0;
		}
	}

	public String toString() {
		return "Business account - id:" + getId() + " balance:" + getBalance()
				+ " protection:" + AMOUNT_PROTECTION + "\n";
	}

}
