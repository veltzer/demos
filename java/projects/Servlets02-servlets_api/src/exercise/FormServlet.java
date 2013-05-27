package exercise;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Mark Veltzer <mark@veltzer.net>
 */
@SuppressWarnings("serial")
public class FormServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		out.println("<html>");
		out.println("<head>");
		out.println("<title>Form Servlet</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<b>New user form:</b>");
		out.println("<form action='' method=POST>");
		out.println("First Name: <input type=text name=fname><br>");
		out.println("Last Name: <input type=text name=lname><br>");
		out.println("ID: <input type=text name=id><br>");
		out.println("Email: <input type=text name=email><br>");
		out.println("<input type=submit value=Send>");
		out.println("</form>");
		out.println("</body>");
		out.println("</html>");
	}

	/**
	 * Enter your code here
	 */

}
