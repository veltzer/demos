package interbit.views;

import interbit.bookstore.Book;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;

import org.springframework.web.servlet.view.xslt.AbstractXsltView;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

@SuppressWarnings("deprecation")
public class ShowBooksXML extends AbstractXsltView {

	@Override
	protected Source createXsltSource(@SuppressWarnings("rawtypes") Map model, String rootName,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		Document document = DocumentBuilderFactory.newInstance()
				.newDocumentBuilder().newDocument();
		Element root = document.createElement(rootName);
		
		@SuppressWarnings("unchecked")
		List<Book> books = (List<Book>) model.get("books");

		for (Book b: books) {
			Element bookNode = document.createElement("book");

			Text textNode = null;
			
			Element titleNode = document.createElement("title");
			textNode = document.createTextNode(b.getTitle());
			titleNode.appendChild(textNode);
			bookNode.appendChild(titleNode);
			
			Element authorNode = document.createElement("author");
			textNode = document.createTextNode(b.getAuthor());
			authorNode.appendChild(textNode);
			bookNode.appendChild(authorNode);

			Element priceNode = document.createElement("price");
			textNode = document.createTextNode("" + b.getPrice());
			priceNode.appendChild(textNode);
			bookNode.appendChild(priceNode);

			root.appendChild(bookNode);
		}
		return new DOMSource(root);
	}

}
