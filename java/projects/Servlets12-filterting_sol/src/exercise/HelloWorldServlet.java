
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
public class HelloWorldServlet extends GenericServlet {
    
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		response.getOutputStream().println("Hello World");
	}
}
