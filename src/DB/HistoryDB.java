package DB;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class HistoryDB {
	public static ArrayList<String[]> getHistories(String id, AccountType account_type) {
		String sql = "";

		switch (account_type) {
		case CUSTOMER:
			sql = "select * from orders where Cuid='" + id + "'";
			break;
		case DEALER:
			sql = "select * from orders, vehicle where vehicle.Deid = '" + id + "' and vehicle.Poid = orders.Poid";
			break;
		case ADMINISTRATOR:
			sql = "select * from control, vehicle where control.Adid = '" + id + "' and control.Poid = vehicle.Poid";
			break;
		default:
			break;
		}

		if (!sql.isEmpty()) {
			try {
				ArrayList<String[]> ret = new ArrayList<>();
				ResultSet rs = DBConnection.stmt.executeQuery(sql);
				ResultSetMetaData rsmd = rs.getMetaData();
				int col_size = rsmd.getColumnCount();
				if (col_size > 0) {
					while (rs.next()) {
						String[] temp = new String[col_size];
						for (int i = 1; i <= col_size; ++i) {
							temp[i - 1] = rs.getString(i);
						}
						ret.add(temp);
					}
					return ret;
				}
			} catch (SQLException e) {
				System.err.println(e.getMessage());
			}
		}
		return null;
	}
}
