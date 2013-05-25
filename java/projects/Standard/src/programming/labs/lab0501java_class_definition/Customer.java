package programming.labs.lab0501java_class_definition;

public class Customer {
	private String name;
	private String id;
	private int age;
	private Account account;

	public Customer() {
		this("John", "is-111", 30);
	}

	public Customer(String name, String id, int age) {
		setName(name);
		setId(id);
		setAge(age);
		account = new Account("2323/41", 5000);
	}

	public Customer(String name, String id, int age, Account account) {
		setName(name);
		setId(id);
		setAge(age);
		setAccount(account);
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

	public Account getAccount() {
		return account;
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

	public void setAccount(Account iaccount) {
		account = iaccount;
	}

}
