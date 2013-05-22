
package exercise;

import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

/**
 *
 * @author  rank
 * @version
 */
public class ControllerServlet extends HttpServlet {

    private static final String CATALOG_PAGE = "/list.jsp";
    private static final String ITEM_PAGE = "/item.jsp";

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
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {

        String mode = request.getParameter("mode");

        RequestDispatcher rd;
        if (mode.equalsIgnoreCase("cat")) {
            rd =  request.getRequestDispatcher(CATALOG_PAGE);
        } else if (mode.equalsIgnoreCase("item")) {
            rd =  request.getRequestDispatcher(ITEM_PAGE);
        } else {
            throw new ServletException("Illegal mode");
        }
        rd.forward(request,response);
    }

}
