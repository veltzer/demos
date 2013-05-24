package programming.solutions.sol1201collections;

public interface Business {
	void addCustomer(Customer customer);
	Customer getCustomer(int index);
	int getNumOfCustomers();
}
