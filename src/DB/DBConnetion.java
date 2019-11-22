package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class dbConnect {
	public static final String URL="jdbc:oracle:thin:@localhost:1521:orcl";
	public static final String USER="hr";
	public static final String PASSWD="hr";
	
	public static Connection conn=null;
	public static Statement stmt=null;
	public static String sql="";
	
	public void getConnection() {
		// TODO Auto-generated method stub
		
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Success!");
		}catch(ClassNotFoundException e) {
			System.err.println("error = "+e.getMessage());
			System.exit(1);
		}
		
		try {
			conn=DriverManager.getConnection(URL, USER, PASSWD);
		}catch(SQLException ex) {
			System.err.println("Cannot get a connection: "+ex.getMessage());
			System.exit(1);
		}
	}

}
