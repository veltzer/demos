

package exercise;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.text.*;

/**
 *
 * @author  rank
 * @version
 */
public class CartServlet extends HttpServlet {

    public void init() throws ServletException {

        // create the items list;
        Map itemList = new TreeMap();
        itemList.put("1",new Item("1","Monitor",250));
        itemList.put("2",new Item("2","Hard disk",79.90));
        itemList.put("3",new Item("3","Mouse",19.90));
        itemList.put("4",new Item("4","Keyboard",29.90));
        itemList.put("5",new Item("5","CPU",145.50));
        itemList.put("6",new Item("6","Fan",8.89));

        ServletContext ctx = getServletConfig().getServletContext();
        ctx.setAttribute("items",itemList);
        System.out.println("Added items to list.");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws 									ServletException, IOException {
    
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

    private void addItemToCart(HttpServletRequest request) {
        /**
         * Enter your code here
         */
    }

    private void printCart(HttpServletRequest request, HttpServletResponse response) throws IOException{
        PrintWriter out = response.getWriter();
        double total=0;
        out.println("Items currently in your cart:<br>");


        /**
         * Enter your code here
         */
        out.println("<h5>Total: "+NumberFormat.getCurrencyInstance().format(total)+"</h5>");
        out.println("<br>");
    }

    private void printItems(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();

        out.println("<h4>Items for sale:</h4>");
        Map items = (Map) getServletContext().getAttribute("items");
        Iterator it = items.values().iterator();
        while(it.hasNext()) {
            Item item = (Item) it.next();
            out.println(item.getItemId()+" "+item.getName()+" - "+NumberFormat.getCurrencyInstance().format(item.getPrice()));
            out.println("<a href='"+response.encodeURL(request.getRequestURI()+"?itemid="+item.getItemId())+"'>Buy now!</a><br>");
        }
    }
}