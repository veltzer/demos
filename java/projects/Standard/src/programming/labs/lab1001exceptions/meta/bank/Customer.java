package programming.labs.lab1001exceptions.meta.bank;

public class Customer {
	private String name;
	private String id;
	private int age;
	private Account[] accounts;
	private int index;

	public Customer() {
		this("John", "is-111", 30);
	}

	public Customer(String iname, String iid, int iage) {
		setName(iname);
		setId(iid);
		setAge(iage);
		accounts = new Account[100];
	}

	public Customer(String iname, String iid, int iage, CheckingAccount account) {
		setName(iname);
		setId(iid);
		setAge(iage);
		accounts = new Account[100];
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

	public Account getAccount(int iindex) {
		if (iindex < index) {
			return accounts[iindex];
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
		if (index < accounts.length) {
			accounts[index++] = account;
		}
	}

	public int getNumOfAccounts() {
		return index;
	}

}
