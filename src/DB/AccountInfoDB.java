package DB;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountInfoDB {
	public static boolean isEuqalPassword(String id, String password) {
		try {
			DBConnection.stmt = DBConnection.conn.createStatement();
			String sql = "select Password from Account where Id = '" + id + "'";
			ResultSet rs = DBConnection.stmt.executeQuery(sql);

			if (rs.next() && password.equals(rs.getString(1)))
				return true;
			return false;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return false;
		}
	}

	public static boolean updatePassword(String id, String password) {
		try {
			DBConnection.stmt = DBConnection.conn.createStatement();
			String sql = "update Account set Password=" + password + " where Id=" + "'" + id + "'";
			DBConnection.stmt.executeQuery(sql);
			return true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return false;
		}
	}

	public static boolean WidthDrawal(String id) {
		try {
			DBConnection.stmt = DBConnection.conn.createStatement();
			String sql = "delete from Account where Id= '" + id + "'";
			DBConnection.stmt.executeQuery(sql);
			return true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return false;
		}
	}

	public static boolean updateAccountInfo(String id, String input[]) {
		try {
			DBConnection.stmt = DBConnection.conn.createStatement();

			final String[] attributes = { "Lname", "Fname", "Phone_no", "Birth_date", "Gender", "Email", "Address",
					"Occupation" };

			for (int i = 0; i < input.length; ++i) {
				if (!input[i].isEmpty()) {
					DBConnection.stmt.executeUpdate(
							"update account set " + attributes[i] + " = '" + input[i] + "' where Id = '" + id + "'");
				}
			}
			return true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return false;
		}
	}

}
