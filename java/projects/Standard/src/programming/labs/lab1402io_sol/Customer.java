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

	public Customer(String iname, String iid, int iage) {
		this();
		setName(iname);
		setId(iid);
		setAge(iage);
	}

	public Customer(String iname, String iid, int iage, CheckingAccount account) {
		this(iname, iid, iage);
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
		if (iage > 0 && iage < 120) {
			age = iage;
		} else {
			throw new RuntimeException(ERR_STRING1);
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
