package programming.solutions.sol0402oo_concepts;

public class Account {
	private String id;
	private double balance;
	
	public Account(String id,double balance){
		this.id=id;
		if(balance>0)
			this.balance=balance;
	}
	
	public double getBalance() {
		return balance;
	}

	public String getId() {
		return id;
	}

	public void deposit(double amount){
		balance+=amount;
	}
	
	public double withdraw(double amount){
		if(amount<=balance){
			balance-=amount;
			return amount;
		}else
			return 0.0;
	}

	public String toString(){
		return "Account id:"+id+" balance:"+balance;
	}

}
