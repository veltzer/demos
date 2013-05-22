
package exercise;

import javax.servlet.*;
import java.io.*;

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
