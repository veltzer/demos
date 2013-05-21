package programming.solutions.sol0701inheritance;
public class Account {
	private String id;
	private double balance;
	private static int nextId=1001;
	private double COMMITION=0.05;
	
	
	public Account(double balance){
		id=""+nextId++;
		setBalance(balance);
	}
	
	public double getBalance() {
		return balance;
	}

	public String getId() {
		return id;
	}
	
	protected void setBalance(double balance){
		this.balance=balance;
	}
	
	public void deposit(double amount){
		balance+=amount;
	}
	
	public double withdraw(double amount){
		if(amount*(1+COMMITION)<=balance){
			balance-=amount*(1+COMMITION);
			return amount;
		}else
			return 0.0;
	}

	public String toString(){
		return "Account id:"+id+" balance:"+balance;
	}
		
	
}
