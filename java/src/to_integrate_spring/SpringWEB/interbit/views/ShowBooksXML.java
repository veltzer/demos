package interbit.views;

import interbit.bookstore.Book;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.View;

public class ShowBooksXML implements View {

	public String getContentType() {
		return "text/xml";
	}

	public void render(Map model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<Book> books = (List)model.get("books");
		PrintWriter writer = response.getWriter();
		writer.print("<dsdsfdsfsdfdsds>");
		

	}

}
