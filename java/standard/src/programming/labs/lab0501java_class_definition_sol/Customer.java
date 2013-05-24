package programming.labs.lab0501java_class_definition_sol;

public class Customer {
	private String name;
	private String id;
	private int age;
	private Account account;
	
	public Customer(){
		this("John","is-111",30);
	}
	
	public Customer(String name,String id,int age){
		setName(name);
		setId(id);
		setAge(age);
		account=new Account(5000);
	}
	
	public Customer(String name,String id,int age,Account account){
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
	
	public Account getAccount(){
		return account;
	}

	public void setAge(int age) {
		if(age>0 && age<120)
			this.age=age;
	}

	public void setId(String id) {
		this.id=id;
	}

	public void setName(String name) {
		this.name=name;
	}
	
	public void setAccount(Account account){
		this.account=account;
	}

}
