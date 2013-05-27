package exercise;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * @author Mark Veltzer <mark@veltzer.net>
 */
@SuppressWarnings("serial")
public class ItemDAO implements Serializable {
	protected static final String URL = "jdbc:odbc:CartServlet";
	static {
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	public Map<String, Item> findAllItems() throws SQLException {
		/**
		 * Enter your code here
		 */
		return null;
	}

	protected Connection openConnection() throws SQLException {
		/**
		 * Enter your code here
		 */
		return null;
	}

	protected Item fillItem(ResultSet rs) throws SQLException {
		/**
		 * Enter your code here
		 */
		return null;
	}

}
