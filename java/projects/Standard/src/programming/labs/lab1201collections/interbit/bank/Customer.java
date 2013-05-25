package programming.labs.lab1201collections.interbit.bank;

public class Customer {
	private String name;
	private String id;
	private int age;
	private Account[] accounts;
	private int index;

	public Customer() {
		this("John", "is-111", 30);
	}

	public Customer(String name, String id, int age) {
		setName(name);
		setId(id);
		setAge(age);
		accounts = new Account[100];
	}

	public Customer(String name, String id, int age, CheckingAccount account) {
		setName(name);
		setId(id);
		setAge(age);
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

	public Account getAccount(int index) {
		if (index < this.index)
			return accounts[index];
		return null;
	}

	public void setAge(int age) {
		if (age > 0 && age < 120)
			this.age = age;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addAccount(Account account) {
		if (index < accounts.length)
			accounts[index++] = account;
	}

	public int getNumOfAccounts() {
		return index;
	}

}
