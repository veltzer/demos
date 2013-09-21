package programming.labs.lab1301threads.meta.bank;

public interface Business {
	void addCustomer(Customer customer);

	Customer getCustomer(int index);

	int getNumOfCustomers();

	void removeCustomer(int index);
}
