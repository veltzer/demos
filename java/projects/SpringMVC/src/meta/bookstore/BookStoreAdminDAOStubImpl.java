package meta.bookstore;

import java.util.ArrayList;
import java.util.List;

public class BookStoreAdminDAOStubImpl implements BookStoreAdminDAO {

	@Override
	public void addCustomer(Customer customer) {
		System.out.println("adding customer...");
	}

	@Override
	public void addNewBook(Book book) {
		System.out.println("adding book...");
	}

	@Override
	public List<Book> showBooks() {
		Book b = new Book("Spring", "Doe", 111);
		List<Book> l = new ArrayList<Book>();
		l.add(b);
		return l;
	}

	@Override
	public List<Book> showBooksBellow(double price) {
		return showBooks();
	}

	@Override
	public List<Customer> showCustomers() {
		Customer c = new Customer("1", "Ido", "ido@tzang", "Israel");
		List<Customer> l = new ArrayList<Customer>();
		l.add(c);
		return l;
	}

	@Override
	public List<Customer> showCustomersByName(String name) {
		return showCustomers();
	}

	@Override
	public void updateBook(Book book) {
		System.out.println("updating book...");
	}

}
