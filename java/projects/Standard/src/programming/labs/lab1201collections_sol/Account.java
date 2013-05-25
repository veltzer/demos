package programming.labs.lab1201collections_sol;

public abstract class Account {
	private String id;
	private double balance;
	private static int nextId = 1001;

	public Account(double balance) {
		id = "" + nextId++;
		setBalance(balance);
	}

	protected void setBalance(double balance) {
		this.balance = balance;
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

	public abstract double withdraw(double balance) throws OverdraftException;
}
