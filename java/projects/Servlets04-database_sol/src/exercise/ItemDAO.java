package exercise;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Mark Veltzer <mark@veltzer.net>
 */
@SuppressWarnings("serial")
public class ItemDAO implements Serializable {
	private static final String URL = "jdbc:odbc:CartServlet";
	static {
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	public Map<String, Item> findAllItems() throws SQLException {
		Map<String, Item> items = new HashMap<String, Item>();

		Connection con = openConnection();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("Select * from Items");
		while (rs.next()) {
			Item item = fillItem(rs);
			items.put(item.getItemId(), item);
		}
		con.close();

		return items;
	}

	private Connection openConnection() throws SQLException {
		return DriverManager.getConnection(URL, "sa", null);
	}

	private Item fillItem(ResultSet rs) throws SQLException {
		Item item = new Item();
		item.setItemId(rs.getString("id"));
		item.setName(rs.getString("name"));
		item.setPrice(rs.getDouble("price"));
		return item;
	}

}
