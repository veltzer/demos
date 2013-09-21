package programming.labs.lab0801abstract.meta.bank;

public class Account {
	private String id;
	private double balance;
	private static int nextId = 1001;
	private static final double COMMITION = 0.05;

	public Account(double ibalance) {
		id = "" + nextId++;
		setBalance(ibalance);
	}

	public double getBalance() {
		return balance;
	}

	public String getId() {
		return id;
	}

	protected void setBalance(double ibalance) {
		balance = ibalance;
	}

	public void deposit(double amount) {
		balance += amount;
	}

	public double withdraw(double amount) {
		if (amount * (1 + COMMITION) <= balance) {
			balance -= amount * (1 + COMMITION);
			return amount;
		} else {
			return 0.0;
		}
	}

	public String toString() {
		return "Account id:" + id + " balance:" + balance;
	}

}
