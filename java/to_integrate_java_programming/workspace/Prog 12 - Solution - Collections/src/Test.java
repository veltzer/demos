import interbit.bank.Bank;
import interbit.bank.Business;
import interbit.bank.CheckingAccount;
import interbit.bank.Customer;
public class Test {

	public static void main(String[] args) {
		Business bank=Bank.getBank();
		bank.addCustomer(new Customer());
		for (int i = 0; i <100; i++) {
			bank.getCustomer(0).addAccount(new CheckingAccount(i));			
		}
		
		for (int i = 0; i <bank.getCustomer(0).getNumOfAccounts(); i++) {
			System.out.println("Balance "+i+": "+bank.getCustomer(0).getAccount(i).getBalance());
		}
	}
}
