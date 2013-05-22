

package exercise;

import java.sql.*;
import java.util.*;

/**
 *
 * @author  rank
 * @version
 */
public class ItemDAO implements java.io.Serializable {
    private static final String URL = "jdbc:odbc:CartServlet";
    static {
        try{
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        } catch (ClassNotFoundException e) {}
    }
    
    public Map findAllItems() throws SQLException {
        HashMap items = new HashMap();
        
        /**
         * Enter your code here
         */
        
        return items;
    }
    
    
    private Connection openConnection() throws SQLException {
        /**
         * Enter your code here
         */
    }
    
    private Item fillItem(ResultSet rs) throws SQLException {
        Item item = new Item();
        /**
         * Enter your code here
         */
        return item;
    }
    
}
