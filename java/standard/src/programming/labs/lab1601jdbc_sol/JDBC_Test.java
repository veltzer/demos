package programming.labs.lab1601jdbc_sol;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class JDBC_Test {
	
	private Connection connection;
	private Statement statement;
	private ResultSet result;
	
	public JDBC_Test(){
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("Cannot find the Driver!");
			e.printStackTrace();
		}		
	}
	
	public void getConnection(){
		try {
			connection=DriverManager.getConnection("jdbc:odbc:ExampleDataSource");
			statement=connection.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void sqlStatements(){
		try {
			statement.executeUpdate("CREATE TABLE chat(name STRING , id NUMBER)");
			statement.executeUpdate("INSERT INTO chat VALUES ('yossi',1111)");
			statement.executeUpdate("INSERT INTO chat VALUES ('pasi',2222)");
			statement.executeUpdate("INSERT INTO chat VALUES ('moshe',3333)");
			result=statement.executeQuery("SELECT * FROM chat WHERE ID>2000");
		} catch (Exception e) {
			System.out.println("Cannot make an sql statement");
			e.printStackTrace();
		}
	}
	
	public void print(){
		try{	
			while(result.next()){
				String name=result.getString("name");
				int id=result.getInt("id");
				System.out.println(name+" "+id);
			}
		}catch(SQLException e){
			System.out.println("no rows found");
		}catch(Exception e){
			System.out.println("select failed");
		}
	}
	
	public void close(){
		try {
			result.close();
			statement.close();
			connection.close();
		} catch (Exception e) {
			System.out.println("Cannot close the resources");
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		JDBC_Test test=new JDBC_Test();
		test.getConnection();
		test.sqlStatements();
		test.print();
		test.close();
	}
}
