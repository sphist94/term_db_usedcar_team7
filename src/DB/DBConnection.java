package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
	public static final String URL="jdbc:oracle:thin:@localhost:56566:xe";
	public static final String USER="system";
	public static final String PASSWD="oracle";
	
	public static Connection conn=null;
	public static Statement stmt=null;
	public static String sql="";
	
	public static void getConnection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
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
