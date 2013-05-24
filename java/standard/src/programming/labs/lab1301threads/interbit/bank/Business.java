package programming.labs.lab1301threads.interbit.bank;

public interface Business {
	void addCustomer(Customer customer);
	Customer getCustomer(int index);
	int getNumOfCustomers();
	void removeCustomer(int index);
}
