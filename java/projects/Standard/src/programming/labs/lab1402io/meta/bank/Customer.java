package programming.labs.lab1402io.meta.bank;

import java.util.ArrayList;
import java.util.List;

public class Customer {
	private String name;
	private String id;
	private int age;
	private List<Account> accounts;

	public Customer() {
		this("John", "is-111", 30);
	}

	public Customer(String iname, String iid, int iage) {
		setName(iname);
		setId(iid);
		setAge(iage);
		accounts = new ArrayList<Account>();
	}

	public Customer(String iname, String iid, int iage, CheckingAccount account) {
		setName(iname);
		setId(iid);
		setAge(iage);
		accounts = new ArrayList<Account>();
		addAccount(account);
	}

	public int getAge() {
		return age;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Account getAccount(int index) {
		if (index < accounts.size()) {
			return (Account) accounts.get(index);
		} else {
			return null;
		}
	}

	public void setAge(int iage) {
		if (iage > 0 && iage < 120) {
			age = iage;
		}
	}

	public void setId(String iid) {
		id = iid;
	}

	public void setName(String iname) {
		name = iname;
	}

	public void addAccount(Account account) {
		accounts.add(account);
	}

	public int getNumOfAccounts() {
		return accounts.size();
	}

}
