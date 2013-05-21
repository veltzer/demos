package programming.solutions.sol1001exceptions;

public interface Business {
	void addCustomer(Customer customer);
	Customer getCustomer(int index);
	int getNumOfCustomers();
}
