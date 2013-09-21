package meta.mvc.controllers;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import meta.bookstore.BookStoreAdminDAO;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class ShowBooksController implements Controller {

	private BookStoreAdminDAO dao;
	private String viewName;

	public void setViewName(String iviewName) {
		viewName = iviewName;
	}

	public BookStoreAdminDAO getDao() {
		return dao;
	}

	public void setDao(BookStoreAdminDAO idao) {
		dao = idao;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		System.out.println("In Controller");
		return new ModelAndView(viewName, "books", dao.showBooks());
	}
}
