package interbit.bookstore;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.PlatformTransactionManager;


public class BookStoreDAOHibernateImpl 
	extends HibernateDaoSupport implements BookStoreAdminDAO{
	

	public void addNewBook(Book book) {
		getHibernateTemplate().persist(book);
	}

	public List<Book> showBooks() {
		return getHibernateTemplate().loadAll(Book.class);
	}

	public List<Book> showBooksBellow(double price) {
		return getHibernateTemplate().find("from Book as b where b.price < "+ price);
	}

	public void addCustomer(Customer customer) {
		getHibernateTemplate().persist(customer);
		
	}

	public List<Customer> showCustomers() {
		//return getHibernateTemplate().find("from Customer");
		return getHibernateTemplate().executeFind(new HibernateCallback(){

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				
				List<Book> l = (List<Book>)session.createQuery("from Book").list();
				for (Book object : l) {
					
				}
				
				
				return null;
			}
			
		});
	}

	public List<Customer> showCustomersByName(String name) {
		return getHibernateTemplate().find("from Customer as c where c.name = '"+ name + "'");	}

	public void updateBook(Book book) {
		System.out.println("Updating Book");
		getHibernateTemplate().update(book);
		
	}



}
