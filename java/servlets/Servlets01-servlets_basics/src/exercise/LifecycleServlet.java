
package exercise;

import javax.servlet.*;
import java.io.*;

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
    
    /**
     * Enter your code here
     */
    
    
    
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        System.out.println("Servlet handling the request.");
        response.getOutputStream().println("Hello World");
    }
    
    
    /**
     * Enter your code here
     */
    
}
