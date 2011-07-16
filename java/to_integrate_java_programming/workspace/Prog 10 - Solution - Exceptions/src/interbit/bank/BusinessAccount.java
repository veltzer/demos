package interbit.bank;

public class BusinessAccount extends Account {
	public double AMOUNT_PROTECTION = 10000;

	public BusinessAccount(double balance){
		super(balance);
	}
	
	public double withdraw(double amount) throws OverdraftException{
		if(getBalance()-amount>=-AMOUNT_PROTECTION){
			setBalance(getBalance()-amount);
			return amount;
		} else {
			throw new OverdraftException(amount,AMOUNT_PROTECTION,getBalance(),getId());
		}
	}
	
}
