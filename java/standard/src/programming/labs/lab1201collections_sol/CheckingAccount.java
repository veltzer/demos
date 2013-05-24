package programming.labs.lab1201collections_sol;
public class CheckingAccount extends Account{
	
	private double COMMITION=0.05;
	
	
	public CheckingAccount(double balance){
		super(balance);
	}
	
	
	public double withdraw(double amount) throws OverdraftException{
		if(amount*(1+COMMITION)<=getBalance()){
			setBalance(getBalance() - (amount*(1+COMMITION)));
			return amount*(1+COMMITION);
		}else
			throw new OverdraftException("The amount bigger than the balance!!!");
	}

			
	
}
