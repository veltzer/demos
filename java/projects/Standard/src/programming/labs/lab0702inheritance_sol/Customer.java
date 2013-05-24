package programming.labs.lab0702inheritance_sol;
public class Customer {
	private String name;
	private String id;
	private int age;
	private final int MAX_ACCOUNTS=5;
	// this array will hold the customers accounts
	private Account[] accounts;
	// this running index will hold how far down the
	// array are we
	private int index;
	
	public Customer(){
		// notice that we call the Object constructor here
		// just to be nice (this is not necessary)
		super();
		// we do NOT initialize fields here (it is better
		// to get nulls and crash than to get default values
		// and pretend everything is OK when it is not)
		accounts=new Account[MAX_ACCOUNTS];
		index=0;
	}
	
	public Customer(String name,String id,int age){
		// calling the no parameters constructor - to initialize
		// the accounts field and other stuff
		this();
		// no need to go through setters
		this.name=name;
		this.id=id;
		this.age=age;
		
	}
	
	public Customer(String name,String id,int age,Account account){
		// call the simpler constructor
		this(name,id,age);
		// add the account manually
		accounts[index++]=account;
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
	
	public Account getAccount(int index){
		// no need for error checking - better to get
		// out of bounds exception
		return accounts[index];
	}

	public void setAge(int age) {
		// some sanity - should throw an error in the else
		// clause
		if(age>0 && age<120)
			this.age=age;
		else {
			System.err.println("error in age");
		}
	}

	public void setId(String id) {
		this.id=id;
	}

	public void setName(String name) {
		this.name=name;
	}
	
	public Account addAccount(){
		// no need for manual bound checking - better
		// to get exception and fix the bug
		Account a=new Account();
		accounts[index++]=a;
		return a;
		
	}
	
	public int getNumOfAccounts(){
		return index;
	}
	
	public String toString(){
		String s="Customer - name:"+name+" age:"+age+" id:"+id+"\n";
		for(int i=0;i<index;i++) {
			s+=accounts[i];
		}
		return s;
	}

}
