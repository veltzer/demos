package programming.labs.lab1001exceptions.meta.bank;

public interface Business {
	void addCustomer(Customer customer);

	Customer getCustomer(int index);

	int getNumOfCustomers();
}
