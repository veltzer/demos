package programming.solutions.sol0801abstract;

public interface Business {
	void addCustomer(Customer customer);
	Customer getCustomer(int index);
	int getNumOfCustomers();
}
