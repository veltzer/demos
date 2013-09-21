package meta.bookstore;

import java.util.List;

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

	@SuppressWarnings("unchecked")
	public List<Customer> showCustomers() {
		return getHibernateTemplate().find("from Customer");
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
