package test;

import interbit.bookstore.Book;

import interbit.bookstore.BookStoreAdminDAO;


import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataIntegrityViolationException;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Resource res = new FileSystemResource("beans.xml");
		BeanFactory bf = new XmlBeanFactory(res);
		BookStoreAdminDAO bsa = (BookStoreAdminDAO)bf.getBean("BookstoreAdmin");
		Book aBook = new Book("title5", "Shimi", 5);
		try
		{
			bsa.addNewBook(aBook);
		} catch (DataIntegrityViolationException ex)
		{
			System.out.println(ex.getMessage());
		}
		System.out.println("Current books in BookStore:");
		for (Book b : bsa.showBooks())
		{
			System.out.println(b);
		}
		System.out.println("Current books in BookStore with price bellow 50:");
		for (Book b : bsa.showBooksBellow(50))
		{
			System.out.println(b);
		}

	}

}
