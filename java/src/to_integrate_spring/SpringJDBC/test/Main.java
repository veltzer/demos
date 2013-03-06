package test;

import java.util.List;

import interbit.bookstore.Book;
import interbit.bookstore.Customer;

import interbit.bookstore.BookStoreAdminDAO;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataIntegrityViolationException;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Resource res = new FileSystemResource("beans.xml");
		BeanFactory bf = new FileSystemXmlApplicationContext("beans.xml");
		BookStoreAdminDAO bsa = (BookStoreAdminDAO) bf
				.getBean("BookstoreAdminTest");
		Book aBook = new Book("title5", "Shimi", 5);

		try
		{
			bsa.addNewBook(aBook);
		} catch (DataIntegrityViolationException ex)
		{
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

		Customer c1 = new Customer("1" + System.currentTimeMillis(), "Shimi",
				"s@s", "tel aviv");
		Customer c2 = new Customer("2" + System.currentTimeMillis(), "Shimi",
				"s@s", "tel aviv");
		Customer c3 = new Customer("3" + System.currentTimeMillis(), "John",
				"s@s", "tel aviv");

		bsa.addCustomer(c1);
		bsa.addCustomer(c2);
		bsa.addCustomer(c3);

		for (Customer c : bsa.showCustomersByName("Shimi")) {
			System.out.println(c);
		}
		
		aBook.setPrice(1900);
		bsa.updateBook(aBook);

	}

}
