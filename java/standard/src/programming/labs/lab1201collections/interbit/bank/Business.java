package interbit.bank;

public interface Business {
	void addCustomer(Customer customer);
	Customer getCustomer(int index);
	int getNumOfCustomers();
}
