package programming.solutions.sol0701inheritance;

public class BusinessAccount extends Account {
	private double AMOUNT_PROTECTION=10000;

	public BusinessAccount(double balance){
		super(balance);
	}
	
	public double withdraw(double amount){
		if(getBalance()-amount>=-AMOUNT_PROTECTION){
			setBalance(getBalance()-amount);
			return amount;
		}else
			return 0.0;
	}
	
}
