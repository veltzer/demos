package programming.labs.lab1001exceptions_sol;

@SuppressWarnings("serial")
public class OverdraftException extends RuntimeException {
	private double amount;
	private double limit;
	private double balance;
	private String accountId;

	/**
	 * @param amount
	 * @param limit
	 * @param balance
	 * @param accountId
	 */
	public OverdraftException(double iamount, double ilimit, double ibalance,
			String iaccountId) {
		super();
		amount = iamount;
		limit = ilimit;
		balance = ibalance;
		accountId = iaccountId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double iamount) {
		amount = iamount;
	}

	public double getLimit() {
		return limit;
	}

	public void setLimit(double ilimit) {
		limit = ilimit;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double ibalance) {
		balance = ibalance;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String iaccountId) {
		accountId = iaccountId;
	}
}
