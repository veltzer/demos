package programming.labs.lab1402io_sol;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class Customer implements Serializable {
	private String name;
	private String id;
	private int age;
	private List<Account> accounts;

	public Customer() {
		accounts = new ArrayList<Account>();
	}

	public Customer(String name, String id, int age) {
		this();
		setName(name);
		setId(id);
		setAge(age);
	}

	public Customer(String name, String id, int age, CheckingAccount account) {
		this(name, id, age);
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
		return accounts.get(index);
	}

	private static final String ERR_STRING1 = "bad age";

	public void setAge(int iage) {
		if (iage > 0 && iage < 120)
			age = iage;
		else {
			throw new RuntimeException(ERR_STRING1);
		}
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addAccount(Account account) {
		accounts.add(account);
	}

	public int getNumOfAccounts() {
		return accounts.size();
	}

}
