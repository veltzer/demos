package test;


import java.io.PrintStream;

import meta.bookstore.Book;
import meta.bookstore.BookStoreAdminDAO;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataIntegrityViolationException;

public abstract class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext c = new ClassPathXmlApplicationContext("beans.xml");
		BookStoreAdminDAO bsa = (BookStoreAdminDAO) c.getBean("BookstoreAdmin");
		Book aBook = new Book("title5", "Shimi", 5);
		try {
			bsa.addNewBook(aBook);
		} catch (DataIntegrityViolationException ex) {
			System.out.println(ex.getMessage());
		}
		System.out.println("Current books in BookStore:");
		for (Book b : bsa.showBooks()) {
			System.out.println(b);
		}
		System.out.println("Current books in BookStore with price bellow 50:");
		for (Book b : bsa.showBooksBellow(50)) {
			System.out.println(b);
		}
		((PrintStream) c).close();
	}

}
