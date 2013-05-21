package programming.solutions.sol1301threads;

public interface Business {
	void addCustomer(Customer customer);

	Customer getCustomer(int index);

	int getNumOfCustomers();

	void removeCustomer(int index);
}
