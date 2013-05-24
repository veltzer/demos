package interbit.webservices;

import interbit.bookstore.Book;
import interbit.bookstore.BookStoreAdminDAO;

import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

@SuppressWarnings("deprecation")
public class XfireWsTest extends AbstractDependencyInjectionSpringContextTests {
//	private Echo echo;
	private BookStoreAdminDAO bookStoreAdminDAO;
	
	public void setBookStoreAdminDAO(BookStoreAdminDAO bookStoreAdminDAO) {
		this.bookStoreAdminDAO = bookStoreAdminDAO;
	}

/*
	public void setEcho(Echo echo) {
		this.echo = echo;
	}
*/
	@Override
	protected String getConfigPath() {
		return getClass().getSimpleName() + "-beans.xml";
	}
/*
	public void testEcho() {
		assertEquals("test", echo.echo("test"));
	}
*/
	public void testBookStoreInsertBook() {
		bookStoreAdminDAO.addNewBook(new Book(
				"Title " + System.currentTimeMillis() % 10000, 
				"Author1", 
				102)
			);
		
		for (Book b: bookStoreAdminDAO.showBooks()) {
			System.out.println(b.getTitle());
		}
	}
}
