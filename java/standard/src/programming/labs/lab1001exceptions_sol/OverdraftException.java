package programming.labs.lab1001exceptions_sol;


public class OverdraftException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	public OverdraftException(double amount, double limit, double balance,
			String accountId) {
		super();
		this.amount = amount;
		this.limit = limit;
		this.balance = balance;
		this.accountId = accountId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getLimit() {
		return limit;
	}
	public void setLimit(double limit) {
		this.limit = limit;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
}
