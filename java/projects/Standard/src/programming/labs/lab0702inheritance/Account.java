package programming.labs.lab0702inheritance;

public class Account {
	private String id;
	private double balance;
	private static int nextId = 1001;
	static final double COMMITION = 0.05;

	public Account() {
		this(0);
	}

	public Account(double ibalance) {
		super();
		id = "" + nextId++;
		setBalance(ibalance);
	}

	public final double getBalance() {
		return balance;
	}

	public final void setBalance(double ibalance) {
		balance = ibalance;
	}

	public String getId() {
		return id;
	}

	public void deposit(double amount) {
		balance += amount;
	}

	public double withdraw(double amount) {
		if (amount * (1 + COMMITION) <= balance) {
			balance -= amount * (1 + COMMITION);
			return amount;
		} else {
			System.err.println("Cannot withdraw");
			return 0.0;
		}
	}

	@Override
	public final String toString() {
		return "Account - id:" + id + " balance:" + balance + "\n";
	}
}
