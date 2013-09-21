package test;


import meta.bookstore.Book;
import meta.bookstore.BookStoreAdminDAO;
import meta.bookstore.Customer;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.dao.DataIntegrityViolationException;

public abstract class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BeanFactory bf = new FileSystemXmlApplicationContext("beans.xml");

		BookStoreAdminDAO bsa = (BookStoreAdminDAO) bf
				.getBean("BookstoreAdminTest");
		Book aBook = new Book("title" + System.currentTimeMillis() % 10000,
				"Ido", 5);

		try {
			bsa.addNewBook(aBook);
		} catch (DataIntegrityViolationException ex) {
			System.out.println("Got Exception: " + ex.getMessage());
		}

		System.out.println("Current books in BookStore:");
		for (Book b : bsa.showBooks()) {
			System.out.println(b);
		}

		System.out.println("Current books in BookStore with price bellow 50:");
		for (Book b : bsa.showBooksBellow(50)) {
			System.out.println(b);
		}

		Customer c1 = new Customer("1" + System.currentTimeMillis() % 10000,
				"Ido", "s@s", "tel aviv");
		Customer c2 = new Customer("2" + System.currentTimeMillis() % 10000,
				"Yael", "s@s", "tel aviv");
		Customer c3 = new Customer("3" + System.currentTimeMillis() % 10000,
				"John", "s@s", "tel aviv");

		bsa.addCustomer(c1);
		bsa.addCustomer(c2);
		bsa.addCustomer(c3);

		for (Customer c : bsa.showCustomersByName("Ido")) {
			System.out.println(c);
		}

		aBook.setPrice(1900);
		bsa.updateBook(aBook);
		((AbstractApplicationContext) bf).close();
	}

}
