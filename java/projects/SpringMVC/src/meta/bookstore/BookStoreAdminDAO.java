package meta.bookstore;

import java.util.List;
import org.springframework.security.access.annotation.Secured;

public interface BookStoreAdminDAO {
	@Secured("ROLE_ADMIN")
	void addNewBook(Book book);

	@Secured("ROLE_USER")
	List<Book> showBooks();

	List<Book> showBooksBellow(double price);

	void updateBook(Book book);

	void addCustomer(Customer customer);

	List<Customer> showCustomers();

	List<Customer> showCustomersByName(String name);

}
