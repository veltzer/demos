package interbit.mvc.controllers;

import java.net.BindException;

import interbit.bookstore.Book;
import interbit.bookstore.BookStoreAdminDAO;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

public class AddBookController extends SimpleFormController {
	private BookStoreAdminDAO dao;

	public BookStoreAdminDAO getDao() {
		return dao;
	}

	public void setDao(BookStoreAdminDAO dao) {
		this.dao = dao;
	}

	public AddBookController() {
		setCommandClass(Book.class);
	}

	@Override
	protected ModelAndView onSubmit(Object cmd)
			throws Exception {
		Book book = (Book) cmd;
		dao.addNewBook(book);

		return new ModelAndView(getSuccessView(), "books", dao.showBooks());
	}

}
