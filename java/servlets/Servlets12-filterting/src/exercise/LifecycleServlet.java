
package exercise;

import java.io.IOException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 *
 * @author  rank
 * @version
 */
@SuppressWarnings("serial")
public class LifecycleServlet extends GenericServlet {
    
	public LifecycleServlet() {
		super();
		System.out.println("Servlet instance created.");
	}
    
	public void init() throws ServletException {
		System.out.println("Servlet initialized.");
	}
        
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		System.out.println("Servlet handling the request.");
		response.getOutputStream().println("Hello World");
	}
    
    
	public void destroy() {
		System.out.println("Servlet destroyed.");
	}
    
    
}
