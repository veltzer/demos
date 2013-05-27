package exercise;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Mark Veltzer <mark@veltzer.net>
 */
@SuppressWarnings("serial")
public class CookieFormServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		out.println("<html>");
		out.println("<head>");
		out.println("<title>Cookie Form Servlet</title>");
		out.println("</head>");
		out.println("<body>");
		String uName = getUserNameCookie(request);
		if (uName != null) {
			out.println("<b>Welcome back, " + uName + ":</b>");
		} else {
			out.println("<b>Please login:</b>");
			uName = "";
		}
		out.println("<form action='' method=POST>");
		out.println("User Name: <input type=text name=uname value=" + uName
				+ "><br>");
		out.println("Password: <input type=password name=pass><br>");
		out.println("<input type=checkbox name=remember>Remember my user name<br>");
		out.println("<input type=submit value=Login>");
		out.println("</form>");
		out.println("</body>");
		out.println("</html>");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		String rem = request.getParameter("remember");
		if (rem != null && rem.equals("on")) {
			String uName = request.getParameter("uname");
			Cookie c = new Cookie("username", uName);
			c.setMaxAge(Integer.MAX_VALUE);
			response.addCookie(c);
		}

		out.println("<html>");
		out.println("<head>");
		out.println("<title>Form Servlet</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("Main application screen");
		out.println("</body>");
		out.println("</html>");

	}

	private String getUserNameCookie(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals("username")) {
					return cookies[i].getValue();
				}
			}
		}
		return null;
	}

}
