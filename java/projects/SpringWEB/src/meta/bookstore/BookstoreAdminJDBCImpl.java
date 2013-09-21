package meta.bookstore;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class BookstoreAdminJDBCImpl extends JdbcDaoSupport implements
		BookStoreAdminDAO {

	public void addNewBook(Book book) {
		Object[] params = new Object[3];
		params[0] = book.getTitle();
		params[1] = book.getAuthor();
		params[2] = book.getPrice();
		getJdbcTemplate().update("insert into BOOKS values(?,?,?)", params);
	}

	@SuppressWarnings({
			"unchecked", "rawtypes"
	})
	public List<Book> showBooks() {
		return getJdbcTemplate().query("SELECT * from BOOKS", new RowMapper() {
			public Object mapRow(ResultSet rs, int num) throws SQLException {
				String title = rs.getString("title");
				String author = rs.getString("author");
				double price = rs.getDouble("price");
				return new Book(title, author, price);
			}
		});

	}

	@SuppressWarnings({
			"unchecked", "rawtypes"
	})
	public List<Book> showBooksBellow(double price) {
		Object[] params = new Object[1];
		params[0] = price;
		return getJdbcTemplate().query("SELECT * from BOOKS where price < ?",
				params, new RowMapper() {
					public Object mapRow(ResultSet rs, int num)
							throws SQLException {
						String title = rs.getString("title");
						String author = rs.getString("author");
						double price = rs.getDouble("price");
						return new Book(title, author, price);
					}
				});
	}

	public void addCustomer(Customer customer) {
		Object[] params = new Object[4];
		params[0] = customer.getId();
		params[1] = customer.getName();
		params[2] = customer.getEmail();
		params[3] = customer.getAddress();
		getJdbcTemplate().update("insert into CUSTOMERS values(?,?,?,?)",
				params);

	}

	@SuppressWarnings({
			"unchecked", "rawtypes"
	})
	public List<Customer> showCustomers() {
		return getJdbcTemplate().query("SELECT * from CUSTOMERS",
				new RowMapper() {
					public Object mapRow(ResultSet rs, int num)
							throws SQLException {
						String id = rs.getString("ID");
						String name = rs.getString("NAME");
						String email = rs.getString("EMAIL");
						String address = rs.getString("ADDRESS");
						return new Customer(id, name, email, address);
					}
				});
	}

	@SuppressWarnings({
			"unchecked", "rawtypes"
	})
	public List<Customer> showCustomersByName(String name) {
		return getJdbcTemplate().query(
				"SELECT * from CUSTOMERS where name = ?", new Object[] {
					name
				}, new RowMapper() {
					public Object mapRow(ResultSet rs, int num)
							throws SQLException {
						String id = rs.getString("ID");
						String name = rs.getString("NAME");
						String email = rs.getString("EMAIL");
						String address = rs.getString("ADDRESS");
						return new Customer(id, name, email, address);
					}
				});
	}

	public void updateBook(Book book) {
		System.out.println("Updating Book");
		getJdbcTemplate()
				.update("update Books set title = ?, author = ?, price = ? where title = ?",
						new Object[] {
								book.getTitle(), book.getAuthor(),
								book.getPrice(), book.getTitle()
						});

	}

}
