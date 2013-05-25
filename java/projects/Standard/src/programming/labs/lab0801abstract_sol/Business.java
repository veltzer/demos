package programming.labs.lab0801abstract_sol;

public interface Business {
	void addCustomer(Customer customer);

	Customer getCustomer(int index);

	int getNumOfCustomers();
}
