package DB;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class Utilities {
	public static String[] convertResultSetToStringArray(ResultSet rs) {
		try {
			ResultSetMetaData rsmd = rs.getMetaData();
			int col_size = rsmd.getColumnCount();
			if (col_size > 0) {
				String[] ret = new String[col_size];
				for (int i = 1; i <= col_size; ++i) {
					ret[i - 1] = rs.getString(i);
				}
				return ret;
			}
			return null;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
}
