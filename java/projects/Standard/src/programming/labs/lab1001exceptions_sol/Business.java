package programming.labs.lab1001exceptions_sol;

public interface Business {
	void addCustomer(Customer customer);

	Customer getCustomer(int index);

	int getNumOfCustomers();
}
