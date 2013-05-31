package programming.labs.lab0801abstract_sol;

public abstract class Account {
	private String id;
	private double balance;
	private static int nextId = 1001;

	public Account(double ibalance) {
		id = "" + nextId++;
		setBalance(ibalance);
	}

	protected void setBalance(double ibalance) {
		balance = ibalance;
	}

	public double getBalance() {
		return balance;
	}

	public String getId() {
		return id;
	}

	public void deposit(double amount) {
		balance += amount;
	}

	public abstract double withdraw(double ibalance);
}
