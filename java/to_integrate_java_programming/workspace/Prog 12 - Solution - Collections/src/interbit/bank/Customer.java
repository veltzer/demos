package interbit.bank;





import java.util.ArrayList;
import java.util.List;

public class Customer {
	private String name;
	private String id;
	private int age;
	private List<Account> accounts;
	
	
	public Customer(){
		accounts=new ArrayList<Account>();
	}
	
	public Customer(String name,String id,int age){
		this();
		setName(name);
		setId(id);
		setAge(age);
		
	}
	
	public Customer(String name,String id,int age,CheckingAccount account){
		this(name,id,age);
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
	
	public Account getAccount(int index){
		// No need for error handling or checking of index
		// it is better to crash with a bad index at this
		// point than delay the problem by returning null
		return accounts.get(index);
	}

	public void setAge(int age) {
		if(age>0 && age<120)
			this.age=age;
		else throw new RuntimeException("Age not in range");
	}

	public void setId(String id) {
		this.id=id;
	}

	public void setName(String name) {
		this.name=name;
	}
	
	public void addAccount(Account account){
		accounts.add(account);
	}
	
	public int getNumOfAccounts(){
		return accounts.size();
	}

}
