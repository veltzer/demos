package programming.samples.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Bank extends Remote {
	String open(String user, double initialBalance) throws RemoteException;

	Account find(String accId) throws RemoteException;

	boolean deposit(String accId, double amount) throws RemoteException;
}
