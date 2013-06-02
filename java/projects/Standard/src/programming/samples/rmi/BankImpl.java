package programming.samples.rmi;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("serial")
public class BankImpl extends UnicastRemoteObject implements Bank {
	private Map<String, Account> accounts = new HashMap<String, Account>();

	public BankImpl() throws RemoteException {
	}

	public synchronized String open(String user, double initBalance) {
		String accId = newAccountId();
		Account acc = new Account(accId, user, initBalance);
		accounts.put(accId, acc);
		return accId;
	}

	public synchronized Account find(String accId) {
		return accounts.get(accId);
	}

	public synchronized boolean deposit(String accId, double amount) {
		Account acc = accounts.get(accId);
		if (acc == null) {
			return false; // no such account
		}
		acc.deposit(amount);
		return true;
	}

	private String newAccountId() {
		return "Acc" + Math.random();
	}

	public static void main(String[] args) {
		try {
			BankImpl bank = new BankImpl();
			Naming.bind("myBank", bank);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
