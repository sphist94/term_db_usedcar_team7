package DB;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HistoryDB {
	public static ResultSet getHistories(String id, int account_type) {
		try {	
			String sql = "";

			switch (account_type) {
			case 1:
				sql = "select * from orders where Cuid='" + id + "'";
				break;
			case 2:
				sql = "select * from orders, vehicle where vehicle.Deid = '" + id + "' and vehicle.Poid = orders.Poid";
				break;
			case 3:
				// TODO: 관리자에 이력에 대한 구현이 필요
				sql = "";
				break;
			}
			if (!sql.isEmpty())
				return DBConnection.stmt.executeQuery(sql);
			return null;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}
}
