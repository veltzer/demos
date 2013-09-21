package meta.bookstore;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class BookStoreDAOHibernateImpl extends HibernateDaoSupport implements
		BookStoreAdminDAO {

	public void addNewBook(Book book) {
		getHibernateTemplate().persist(book);
	}

	public List<Book> showBooks() {
		return getHibernateTemplate().loadAll(Book.class);
	}

	@SuppressWarnings("unchecked")
	public List<Book> showBooksBellow(double price) {
		return getHibernateTemplate().find(
				"from Book as b where b.price < " + price);
	}

	public void addCustomer(Customer customer) {
		getHibernateTemplate().persist(customer);

	}

	@SuppressWarnings({
			"unchecked", "rawtypes"
	})
	public List<Customer> showCustomers() {
		// return getHibernateTemplate().find("from Customer");
		return getHibernateTemplate().executeFind(new HibernateCallback() {

			@Override
			public Object doInHibernate(Session session) throws SQLException {
				/*
				 * List<Book> l =
				 * (List<Book>)session.createQuery("from Book").list(); for
				 * (Book object : l) { }
				 */

				return null;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<Customer> showCustomersByName(String name) {
		return getHibernateTemplate().find(
				"from Customer as c where c.name = '" + name + "'");
	}

	public void updateBook(Book book) {
		System.out.println("Updating Book");
		getHibernateTemplate().update(book);

	}

}
