

package exercise;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

/**
 *
 * @author  rank
 * @version
 */
public class SnoopRequestServlet extends HttpServlet {
    
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        
        out.println("<h2>HTTP request is:</h2>");
        out.println(request.getMethod()+" "+request.getRequestURI()+" "+request.getProtocol()+"<br>");
        Enumeration enum = request.getHeaderNames();
        while(enum.hasMoreElements()) {
            String hName=(String) enum.nextElement();
            out.println(hName+": "+request.getHeader(hName)+"<br>");
        }
    }
}
