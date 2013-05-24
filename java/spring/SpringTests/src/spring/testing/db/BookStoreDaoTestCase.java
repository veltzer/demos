package spring.testing.db;

import java.util.List;

import interbit.bookstore.Book;
import interbit.bookstore.BookStoreAdminDAO;

import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

@SuppressWarnings("deprecation")
public class BookStoreDaoTestCase extends AbstractTransactionalDataSourceSpringContextTests {
	private BookStoreAdminDAO bookStoreAdminDAO;
	
	public void setBookStoreAdminDAO(BookStoreAdminDAO bookStoreAdminDAO) {
		this.bookStoreAdminDAO = bookStoreAdminDAO;
	}
	
	public BookStoreDaoTestCase() {
	}
	
	@Override
	protected String getConfigPath() {
		return getClass().getSimpleName() + "-beans.xml";
	}
	
	

	@Override
	protected void onSetUpInTransaction() throws Exception {
		super.onSetUpInTransaction();
		deleteFromTables(new String[] {"BOOKS"});
		
	}

	public void testInsertBook() {
		bookStoreAdminDAO.addNewBook(new Book("LOTR1", "JRRT", 10));
		List<Book> l = bookStoreAdminDAO.showBooksBellow(11);
		assertEquals("More than one book...", 1, l.size());
		assertEquals("Wrong book name", "LOTR1", l.get(0).getTitle());
		
		List<Book> l2 = bookStoreAdminDAO.showBooksBellow(9);
		assertEquals("No books should have been selected...", 0, l2.size());
		
	}

	
	
	
	
	
	
	
	
	
	

}
