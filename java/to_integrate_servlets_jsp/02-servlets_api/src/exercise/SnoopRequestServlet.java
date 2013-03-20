

package exercise;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

/**
 *
 * @author  rank
 * @version
 */
public class SnoopRequestServlet extends HttpServlet {
    
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        
        out.println("<h2>HTTP request is:</h2>");
        /**
         * Enter your code here
         */
    }
}
