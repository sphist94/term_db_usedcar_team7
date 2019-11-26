package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
	static String address = "@localhost";
	static String port = "1600";
	static String sid = "xe";
	public static String URL = "jdbc:oracle:thin:";
	public static String USER = "system";
	public static String PASSWD = "oracle";

	public static Connection conn = null;
	public static Statement stmt = null;
	public static String sql = "";

	public static void getConnection(String _address, String _port, String _sid, String _id, String _password) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.err.println("error = " + e.getMessage());
			System.exit(1);
		}

		try {
			if (!_address.isEmpty())
				address = _address;
			if (!_port.isEmpty())
				port = _port;
			if (!_sid.isEmpty())
				sid = _sid;
			if(!_id.isEmpty())
				USER = _id;
			if(!_password.isEmpty())
				PASSWD = _password;
			URL += address + ":" + port + ":" + sid;
			conn = DriverManager.getConnection(URL, USER, PASSWD);
			stmt = conn.createStatement();
		} catch (SQLException ex) {
			System.err.println("Cannot get a connection: " + ex.getMessage());
			System.exit(1);
		}
	}
}
