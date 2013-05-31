package programming.labs.lab0502java_class_definition;

public class Account {
	private String id;
	private double balance;
	private int typeOfAccount;
	private static int nextId = 1001;
	private static final int CHECKING_ACCOUNT = 1;
	private static final int BUSINESS_ACCOUNT = 2;

	public Account(double ibalance) {
		id = "" + nextId++;
		typeOfAccount = CHECKING_ACCOUNT;
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

	public int getTypeOfAccount() {
		return typeOfAccount;
	}

	public void setTypeOfAccount(int itypeOfAccount) {
		if (itypeOfAccount == CHECKING_ACCOUNT
				|| itypeOfAccount == BUSINESS_ACCOUNT) {
			typeOfAccount = itypeOfAccount;
		} else {
			typeOfAccount = CHECKING_ACCOUNT;
		}
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
