package programming.samples.bank;

public class Customer {
	String name;
	String id;
	int age;
	Account account;

	public Customer() {
		super();
	}

	/**
	 * @param name
	 * @param id
	 * @param age
	 */
	public Customer(String name, String id, int age, Account account) {
		super();
		this.name = name;
		this.id = id;
		this.age = age;
		this.account = account;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Account myaccount = new Account(Account.CHECKING_ACCOUNT, 3000);
		Customer c = new Customer("mark", "014995815", 35, myaccount);
		System.out.println(c);

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void printMe() {
		System.out.println("name is " + name);
		System.out.println("id is " + id);
		System.out.println("age is " + age);
	}

	@Override
	public String toString() {
		return name + "," + id + "," + age + "," + account.getTypeOfAccount()
				+ "," + account.getBalance();
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}
