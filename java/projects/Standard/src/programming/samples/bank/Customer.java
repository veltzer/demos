package programming.samples.bank;

public class Customer {
	private String name;
	private String id;
	private int age;
	private Account account;

	public Customer() {
		super();
	}

	/**
	 * @param name
	 * @param id
	 * @param age
	 */
	public Customer(String iname, String iid, int iage, Account iaccount) {
		super();
		name = iname;
		id = iid;
		age = iage;
		account = iaccount;
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

	public void setName(String iname) {
		name = iname;
	}

	public String getId() {
		return id;
	}

	public void setId(String iid) {
		id = iid;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int iage) {
		age = iage;
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

	public void setAccount(Account iaccount) {
		account = iaccount;
	}

}
