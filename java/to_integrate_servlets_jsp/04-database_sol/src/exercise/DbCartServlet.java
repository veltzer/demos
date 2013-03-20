

package exercise;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.text.*;
import java.sql.*;

/**
 *
 * @author  rank
 * @version
 */
public class DbCartServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        addItemToCart(request);

        out.println("<html>");
        out.println("<head>");
        out.println("<title>Cart Servlet</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h2>Welcome to John's computer shop</h2>");

        printCart(request,response);
        printItems(request,response);

        out.println("</body>");
        out.println("</html>");
    }

    private Map getItemsList() throws ServletException {
        try {
            ItemDAO itemDao = new ItemDAO();
            return itemDao.findAllItems();
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void addItemToCart(HttpServletRequest request) throws ServletException {
        HttpSession session = request.getSession();
        List cart = (List) session.getAttribute("cart");
        if (cart==null) {
            cart = new ArrayList();
            session.setAttribute("cart",cart);
        }
        String itemId = request.getParameter("itemid");
        if (itemId!=null) {
            Map items = getItemsList();
            Item item = (Item) items.get(itemId);
            if (item!=null) {
                cart.add(item);
            }
        }
    }

    private void printCart(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();

        List cart = (List) session.getAttribute("cart");
        out.println("Items currently in your cart:<br>");
        double total=0;
        if (cart.isEmpty()) {
            out.println("Cart is empty.");
        } else {
            Iterator it = cart.iterator();

            while(it.hasNext()) {
                Item item = (Item) it.next();
                out.println(item.getName()+" - "+NumberFormat.getCurrencyInstance(Locale.US).format(item.getPrice())+"<br>");
                total+=item.getPrice();
            }
        }
        out.println("<h5>Total: "+NumberFormat.getCurrencyInstance(Locale.US).format(total)+"</h5>");
        out.println("<br>");
    }

    private void printItems(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        PrintWriter out = response.getWriter();

        out.println("<h4>Items for sale:</h4>");
        Map items = getItemsList();
        Iterator it = items.values().iterator();
        while(it.hasNext()) {
            Item item = (Item) it.next();
            out.println(item.getItemId()+" "+item.getName()+" - "+NumberFormat.getCurrencyInstance(Locale.US).format(item.getPrice()));
            out.println("<a href='"+response.encodeURL(request.getRequestURI()+"?itemid="+item.getItemId())+"'>Buy now!</a><br>");
        }
    }
}