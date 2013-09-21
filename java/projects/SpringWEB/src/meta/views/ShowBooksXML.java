package meta.views;


import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import meta.bookstore.Book;

import org.springframework.web.servlet.View;

public class ShowBooksXML implements View {

	public String getContentType() {
		return "text/xml";
	}

	@Override
	public void render(Map<String, ?> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		@SuppressWarnings("unchecked")
		List<Book> books = (List<Book>) model.get("books");
		PrintWriter writer = response.getWriter();
		for (Book b : books) {
			writer.print(b);
		}
	}
}
