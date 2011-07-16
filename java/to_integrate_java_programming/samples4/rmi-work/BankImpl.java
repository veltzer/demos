import java.rmi.*;
import java.rmi.server.*;
import java.util.*;

public class BankImpl extends UnicastRemoteObject implements Bank {
    private Map accounts = new HashMap();

    public BankImpl() throws RemoteException{
    }
    public synchronized String open(String user, double initBalance){
        String accId= newAccountId();
        Account acc=new Account(accId, user, initBalance);
        accounts.put(accId, acc);
        return accId;
    }
    public synchronized Account find(String accId){
        return (Account) accounts.get(accId);
    }



    public synchronized boolean deposit(String accId, double amount){
        Account acc=(Account)accounts.get(accId);
        if (acc==null)
		   return false;   // no such account
        acc.deposit(amount);
        return true;
    }

    private String newAccountId(){
		return "Acc"+ Math.random();
	}

	public static void main(String[] args) {
 		try{
		    BankImpl bank = new BankImpl();
		    Naming.bind("myBank", bank);
         }catch(Exception ex){
		    ex.printStackTrace();
         }
    }
}
