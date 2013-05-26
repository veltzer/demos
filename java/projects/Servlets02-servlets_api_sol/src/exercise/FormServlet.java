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

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		String fName = request.getParameter("fname");
		String lName = request.getParameter("lname");
		String id = request.getParameter("id");
		String email = request.getParameter("email");

		out.println("<html>");
		out.println("<head>");
		out.println("<title>Form Servlet</title>");
		out.println("</head>");
		out.println("<body>");
		if (fName.equals("") || lName.equals("")) {
			out.println("<font color='red'>First name and Last name must be provided!</font>");
		} else {
			out.println("<b>Welcome " + fName + " " + lName + "!</b>");
			out.println("<table border=1>");
			out.println("<tr><td bgcolor=yellow>First Name</td>");
			out.println("<td>" + fName + "</td><tr>");
			out.println("<tr><td bgcolor=yellow>Last Name</td>");
			out.println("<td>" + lName + "</td><tr>");
			out.println("<tr><td bgcolor=yellow>ID</td>");
			out.println("<td>" + id + "</td><tr>");
			out.println("<tr><td bgcolor=yellow>Email</td>");
			out.println("<td>" + email + "</td><tr>");
			out.println("</table>");
		}
		out.println("<br><a href='exercise.FormServlet'>back</a>");
		out.println("</body>");
		out.println("</html>");

	}

}
