package programming.samples.rmi;

@SuppressWarnings("serial")
public class Account implements java.io.Serializable {
    private String accId;
    private String user;
    private double balance;

	public Account(String accId, String user, double balance){
		this.accId = accId;
		this.user=user;
		this.balance = balance;
	}
    public double getBalance(){
		return balance;
	}
	public void deposit(double amt){
		balance += amt;
	}
	public String toString(){
		return "Account # "+ accId + " belonging to:"+ user + " Balance:"+ balance;
	}
}
