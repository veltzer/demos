package com.interbit.bank;

/**
 * @author Administrator
 *
 */
public class Account {
	/**
	 * This is the account id 
	 */
	private String id;
	/**
	 * This is the current balance
	 */
	private double balance;
	/**
	 * 
	 */
	int typeOfAccount;
	final public static int CHECKING_ACCOUNT = 1;
	final public static int BUSINESS_ACCOUNT = 2;
	private static int counter = 0;

	/**
	 * @param balance
	 * @param typeOfAccount
	 */
	public Account(int typeOfAccount, double balance) {
		super();
		this.balance = balance;
		this.typeOfAccount = typeOfAccount;
		Integer i = new Integer(counter);
		this.id = i.toString();
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
	public void setId(String id) {
		this.id = id;
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
	public void setBalance(double balance) {
		this.balance = balance;
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
	public void setTypeOfAccount(int typeOfAccount) {
		if (typeOfAccount != BUSINESS_ACCOUNT
				&& typeOfAccount != CHECKING_ACCOUNT) {
			System.err.println("Wrong account type");
			return;
		}
		this.typeOfAccount = typeOfAccount;
	}
}