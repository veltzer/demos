package programming.solutions.sol0502_java_class_definition;

public class Account {
	private String id;
	private double balance;
	private int typeOfAccount;
	private static int nextId=1001;
	private final int CHECKING_ACCOUNT=1;
	private final int BUSINESS_ACCOUNT=2;
	
	public Account(double balance){
		id=""+nextId++;
		typeOfAccount=CHECKING_ACCOUNT;
		if(balance>0)
			this.balance=balance;
	}
	
	public double getBalance() {
		return balance;
	}

	public String getId() {
		return id;
	}
	
	public int getTypeOfAccount(){
		return typeOfAccount;
	}
	
	public void setTypeOfAccount(int typeOfAccount){
		if(typeOfAccount==CHECKING_ACCOUNT || typeOfAccount==BUSINESS_ACCOUNT)
			this.typeOfAccount=typeOfAccount;
		else
			this.typeOfAccount=CHECKING_ACCOUNT;
	}

	public void deposit(double amount){
		balance+=amount;
	}
	
	public double withdraw(double amount){
		if(amount<=balance)
			return amount;
		else
			return 0.0;
	}

	public String toString(){
		return "Account id:"+id+" balance:"+balance;
	}
		
	
}
