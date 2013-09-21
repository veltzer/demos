package meta.bookstore;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class BookstoreAdminImpl implements BookStoreAdminDAO, BeanFactoryAware {

	private BeanFactory bf;

	public void addNewBook(Book book) {
		JdbcTemplate jt = (JdbcTemplate) bf.getBean("jt");
		Object[] params = new Object[3];
		params[0] = book.getTitle();
		params[1] = book.getAuthor();
		params[2] = book.getPrice();
		jt.update("insert into BOOKS values(?,?,?)", params);
	}

	@SuppressWarnings({
			"unchecked", "rawtypes"
	})
	public List<Book> showBooks() {
		JdbcTemplate jt = (JdbcTemplate) bf.getBean("jt");
		return jt.query("SELECT * from BOOKS", new RowMapper() {
			public Object mapRow(ResultSet rs, int num) throws SQLException {
				String title = rs.getString("title");
				String author = rs.getString("author");
				double price = rs.getDouble("price");
				return new Book(title, author, price);
			}
		});

	}

	public void setBeanFactory(BeanFactory ibf) {
		bf = ibf;
	}

	@SuppressWarnings({
			"unchecked", "rawtypes"
	})
	public List<Book> showBooksBellow(double price) {
		JdbcTemplate jt = (JdbcTemplate) bf.getBean("jt");
		Object[] params = new Object[1];
		params[0] = price;
		return jt.query("SELECT * from BOOKS where price < ?", params,
				new RowMapper() {
					public Object mapRow(ResultSet rs, int num)
							throws SQLException {
						String title = rs.getString("title");
						String author = rs.getString("author");
						double price = rs.getDouble("price");
						return new Book(title, author, price);
					}
				});
	}

}
