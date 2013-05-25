package programming.labs.lab0501java_class_definition;

public class Account {
	private String id;
	private double balance;

	public Account(String iid, double ibalance) {
		id = iid;
		if (ibalance > 0) {
			balance = ibalance;
		}
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

	public double withdraw(double amount) {
		if (amount <= balance) {
			return amount;
		} else {
			return 0.0;
		}
	}

	public String toString() {
		return "Account id:" + id + " balance:" + balance;
	}

}
