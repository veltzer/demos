package meta.bookstore;

import java.util.List;

public interface BookStoreAdminDAO {
	void addNewBook(Book book);

	List<Book> showBooks();

	List<Book> showBooksBellow(double price);

	// void addCustomer(Customer customer);
	// List<Customer> showCustomers();
	// List<Customer> showCustomersByName(String name);

}
