package meta.bookstore;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class BookStoreDAO extends HibernateDaoSupport implements
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
}
