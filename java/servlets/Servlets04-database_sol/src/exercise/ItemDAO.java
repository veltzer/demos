

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
        
        Connection con = openConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("Select * from Items");
        while (rs.next()) {
            Item item = fillItem(rs);
            items.put(item.getItemId(),item);
        }
        con.close();

        return items;
    }


    private Connection openConnection() throws SQLException {
        return DriverManager.getConnection(URL,"sa",null); 
    }

    private Item fillItem(ResultSet rs) throws SQLException {
        Item item = new Item();
        item.setItemId(rs.getString("id"));
        item.setName(rs.getString("name"));
        item.setPrice(rs.getDouble("price"));
        return item;
    }
    
}
