

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
@SuppressWarnings("serial")
public class SnoopRequestServlet extends HttpServlet {
    
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        
        out.println("<h2>HTTP request is:</h2>");
        out.println(request.getMethod()+" "+request.getRequestURI()+" "+request.getProtocol()+"<br>");
        @SuppressWarnings("rawtypes")
		Enumeration e = request.getHeaderNames();
        while(e.hasMoreElements()) {
            String hName=(String)e.nextElement();
            out.println(hName+": "+request.getHeader(hName)+"<br>");
        }
    }
}
