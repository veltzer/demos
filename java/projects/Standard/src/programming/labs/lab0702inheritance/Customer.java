package programming.labs.lab0702inheritance;

public class Customer {
	private String name;
	private String id;
	private int age;
	private static final int MAX_ACCOUNTS = 5;
	// this array will hold the customers accounts
	private Account[] accounts;
	// this running index will hold how far down the
	// array are we
	private int index;

	public Customer() {
		// notice that we call the Object constructor here
		// just to be nice (this is not necessary)
		super();
		// we do NOT initialize fields here (it is better
		// to get nulls and crash than to get default values
		// and pretend everything is OK when it is not)
		accounts = new Account[MAX_ACCOUNTS];
		index = 0;
	}

	public Customer(String iname, String iid, int iage) {
		// calling the no parameters constructor - to initialize
		// the accounts field and other stuff
		this();
		// no need to go through setters
		name = iname;
		id = iid;
		age = iage;

	}

	public Customer(String iname, String iid, int iage, Account account) {
		// call the simpler constructor
		this(iname, iid, iage);
		// add the account manually
		accounts[index++] = account;
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
		// no need for error checking - better to get
		// out of bounds exception
		return accounts[iindex];
	}

	public void setAge(int iage) {
		// some sanity - should throw an error in the else
		// clause
		if (iage > 0 && iage < 120) {
			age = iage;
		} else {
			System.err.println("error in age");
		}
	}

	public void setId(String iid) {
		id = iid;
	}

	public void setName(String iname) {
		name = iname;
	}

	public Account addAccount() {
		// no need for manual bound checking - better
		// to get exception and fix the bug
		Account a = new Account();
		accounts[index++] = a;
		return a;
	}

	public int getNumOfAccounts() {
		return index;
	}

	public String toString() {
		String s = "Customer - name:" + name + " age:" + age + " id:" + id
				+ "\n";
		for (int i = 0; i < index; i++) {
			s += accounts[i];
		}
		return s;
	}

}
