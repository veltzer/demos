package interbit.mvc.controllers;

import interbit.bookstore.BookStoreAdminDAO;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class ShowBooksController implements Controller {
	
	private BookStoreAdminDAO dao;
	
	public BookStoreAdminDAO getDao() {
		return dao;
	}

	public void setDao(BookStoreAdminDAO dao) {
		this.dao = dao;
	}






	public ModelAndView handleRequest(HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws Exception {
		System.out.println("In Controller");
//		ServletContext sc = httpservletrequest.getSession().getServletContext();
//		ApplicationContext ac = 
//		    WebApplicationContextUtils.getWebApplicationContext(sc);
//		
//		BookStoreAdminDAO dao = (BookStoreAdminDAO)ac.getBean("BookstoreAdminTest2");
		System.out.println("In Controller propagation");
		return new ModelAndView("showbooks", "books", dao.showBooks());
	}
}
