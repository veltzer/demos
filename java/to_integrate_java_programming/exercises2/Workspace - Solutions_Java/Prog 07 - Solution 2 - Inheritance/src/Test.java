import interbit.bank.*;
public class Test {

	public static void main(String[] args) {
		Bank bank=Bank.getBank();
		bank.addCustomer(new Customer("Yossi","1234",40));
		bank.getCustomer(0).addAccount(new Account(5000));
		bank.getCustomer(0).addAccount(new BusinessAccount(5000));
		
		for (int i = 0; i <bank.getCustomer(0).getNumOfAccounts(); i++) {
			bank.getCustomer(0).getAccount(i).withdraw(6000);
			System.out.println("Customer "+i+": "+bank.getCustomer(0).getAccount(i).getBalance());
			
			if(bank.getCustomer(0).getAccount(i) instanceof BusinessAccount){
				System.out.println("AMOUNT_PROTECTION: "+
					((BusinessAccount)bank.getCustomer(0).getAccount(i)).AMOUNT_PROTECTION);
			}
		}
	}
}
