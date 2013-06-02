package programming.samples.bank;

/**
 * @author Mark Veltzer <mark@veltzer.net>
 */
public class Account {
	public static final int CHECKING_ACCOUNT = 1;
	public static final int BUSINESS_ACCOUNT = 2;
	/**
	 * This is the account id
	 */
	private String id;
	/**
	 * This is the current balance
	 */
	private double balance;
	private int typeOfAccount;
	private static int counter = 0;

	/**
	 * @param balance
	 * @param typeOfAccount
	 */
	public Account(int itypeOfAccount, double ibalance) {
		super();
		balance = ibalance;
		typeOfAccount = itypeOfAccount;
		Integer i = new Integer(counter);
		id = i.toString();
		counter++;
	}

	/**
	 * @return
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(String iid) {
		id = iid;
	}

	/**
	 * @return
	 */
	public double getBalance() {
		return balance;
	}

	/**
	 * @param balance
	 */
	public void setBalance(double ibalance) {
		balance = ibalance;
	}

	/**
	 * @return
	 */
	public int getTypeOfAccount() {
		return typeOfAccount;
	}

	/**
	 * @param typeOfAccount
	 */
	public void setTypeOfAccount(int itypeOfAccount) {
		if (typeOfAccount != BUSINESS_ACCOUNT
				&& typeOfAccount != CHECKING_ACCOUNT) {
			System.err.println("Wrong account type");
			return;
		}
		typeOfAccount = itypeOfAccount;
	}
}
