package programming.labs.lab1201collections_sol;

public interface Business {
	void addCustomer(Customer customer);

	Customer getCustomer(int index);

	int getNumOfCustomers();
}
