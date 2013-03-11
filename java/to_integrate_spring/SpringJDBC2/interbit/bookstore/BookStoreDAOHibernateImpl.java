package interbit.bookstore;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.PlatformTransactionManager;


public class BookStoreDAOHibernateImpl 
	extends HibernateDaoSupport implements BookStoreAdminDAO{
	

	public void addNewBook(Book book) {
		getHibernateTemplate().persist(book);
	}

	@SuppressWarnings("unchecked")
	public List<Book> showBooks() {
		return getHibernateTemplate().loadAll(Book.class);
	}

	@SuppressWarnings("unchecked")
	public List<Book> showBooksBellow(double price) {
//		return getHibernateTemplate().find("from Book as b where b.price < ?", price);
		DetachedCriteria criteria = 
			DetachedCriteria.forClass(Book.class).add(Restrictions.lt("price", price));
		
		return getHibernateTemplate().findByCriteria(criteria);
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
		return getHibernateTemplate().find("from Customer as c where c.name = '"+ name + "'");	}

	public void updateBook(Book book) {
		System.out.println("Updating Book");
		getHibernateTemplate().update(book);
		
	}




	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
