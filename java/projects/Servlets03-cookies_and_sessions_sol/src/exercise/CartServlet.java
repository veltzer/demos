package exercise;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Mark Veltzer <mark@veltzer.net>
 */
@SuppressWarnings("serial")
public class CartServlet extends HttpServlet {

	public void init() throws ServletException {

		// create the items list;
		Map<String, Item> itemList = new TreeMap<String, Item>();
		itemList.put("1", new Item("1", "Monitor", 250));
		itemList.put("2", new Item("2", "Hard disk", 79.90));
		itemList.put("3", new Item("3", "Mouse", 19.90));
		itemList.put("4", new Item("4", "Keyboard", 29.90));
		itemList.put("5", new Item("5", "CPU", 145.50));
		itemList.put("6", new Item("6", "Fan", 8.89));

		ServletContext ctx = getServletConfig().getServletContext();
		ctx.setAttribute("items", itemList);
		System.out.println("Added items to list.");
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		addItemToCart(request);

		out.println("<html>");
		out.println("<head>");
		out.println("<title>Cart Servlet</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h2>Welcome to John's computer shop</h2>");

		printCart(request, response);
		printItems(request, response);

		out.println("</body>");
		out.println("</html>");
	}

	private void addItemToCart(HttpServletRequest request) {
		HttpSession session = request.getSession();

		@SuppressWarnings("unchecked")
		List<Item> cart = (List<Item>) session.getAttribute("cart");
		if (cart == null) {
			cart = new ArrayList<Item>();
			session.setAttribute("cart", cart);
		}

		String itemId = request.getParameter("itemid");
		if (itemId != null) {
			@SuppressWarnings("unchecked")
			Map<String, Item> items = (Map<String, Item>) getServletContext()
					.getAttribute("items");
			Item item = items.get(itemId);
			cart.add(item);
		}
	}

	private void printCart(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();

		@SuppressWarnings("unchecked")
		List<Item> cart = (List<Item>) session.getAttribute("cart");
		out.println("Items currently in your cart:<br>");
		double total = 0;
		if (cart.isEmpty()) {
			out.println("Cart is empty.");
		} else {
			Iterator<Item> it = cart.iterator();
			while (it.hasNext()) {
				Item item = it.next();
				out.println(item.getName()
						+ " - "
						+ NumberFormat.getCurrencyInstance(Locale.US).format(
								item.getPrice()) + "<br>");
				total += item.getPrice();
			}
		}
		out.println("<h5>Total: "
				+ NumberFormat.getCurrencyInstance(Locale.US).format(total)
				+ "</h5>");
		out.println("<br>");
	}

	private void printItems(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();

		out.println("<h4>Items for sale:</h4>");
		@SuppressWarnings("unchecked")
		Map<String, Item> items = (Map<String, Item>) getServletContext()
				.getAttribute("items");
		Iterator<Item> it = items.values().iterator();
		while (it.hasNext()) {
			Item item = it.next();
			out.println(item.getItemId()
					+ " "
					+ item.getName()
					+ " - "
					+ NumberFormat.getCurrencyInstance(Locale.US).format(
							item.getPrice()));
			out.println("<a href='"
					+ response.encodeURL(request.getRequestURI() + "?itemid="
							+ item.getItemId()) + "'>Buy now!</a><br>");
		}
	}
}
