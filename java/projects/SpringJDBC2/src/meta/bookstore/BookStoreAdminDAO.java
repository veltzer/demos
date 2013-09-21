package meta.bookstore;

import java.util.List;

public interface BookStoreAdminDAO {
	void addNewBook(Book book);

	List<Book> showBooks();

	List<Book> showBooksBellow(double price);

	void updateBook(Book book);

	void addCustomer(Customer customer);

	List<Customer> showCustomers();

	List<Customer> showCustomersByName(String name);

}
