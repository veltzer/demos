package programming.labs.lab1301threads.interbit.bank;

import java.util.ArrayList;
import java.util.List;

public class Customer {
	private String name;
	private String id;
	private int age;
	private List<Account> accounts;
	
	
	public Customer(){
		this("John","is-111",30);
	}
	
	public Customer(String name,String id,int age){
		setName(name);
		setId(id);
		setAge(age);
		accounts=new ArrayList<Account>();
	}
	
	public Customer(String name,String id,int age,CheckingAccount account){
		setName(name);
		setId(id);
		setAge(age);
		accounts=new ArrayList<Account>();
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
		if(index<accounts.size())
			return (Account)accounts.get(index);
		return null;
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
	
	public void addAccount(Account account){
		accounts.add(account);
	}
	
	public int getNumOfAccounts(){
		return accounts.size();
	}

}
