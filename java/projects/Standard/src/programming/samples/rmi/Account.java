package programming.samples.rmi;

@SuppressWarnings("serial")
public class Account implements java.io.Serializable {
	private String accId;
	private String user;
	private double balance;

	public Account(String iaccId, String iuser, double ibalance) {
		accId = iaccId;
		user = iuser;
		balance = ibalance;
	}

	public double getBalance() {
		return balance;
	}

	public void deposit(double amt) {
		balance += amt;
	}

	public String toString() {
		return "Account # " + accId + " belonging to:" + user + " Balance:"
				+ balance;
	}
}
