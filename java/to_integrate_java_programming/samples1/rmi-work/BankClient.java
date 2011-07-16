import java.rmi.*;
public class BankClient {
    public static void main(String[] args){
	try{
	    Bank bank=(Bank)Naming.lookup("rmi://localhost/myBank");
	    String accId=bank.open("john", 0);
	    bank.deposit(accId, 900);
	    Account acc= bank.find(accId);
	    System.out.println(acc);
	    acc.deposit(800);
	}catch(Exception ex){
	    ex.printStackTrace();
	}
  }
}
