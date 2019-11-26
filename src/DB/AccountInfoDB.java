package DB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import Functions.CheckConditions;

public class AccountInfoDB {
	public static int getAccountType(String id) {
		try {
			DBConnection.stmt = DBConnection.conn.createStatement();
			String sql = "select Account_type from Account where Id = '" + id + "'";
			ResultSet rs = DBConnection.stmt.executeQuery(sql);
			if (rs.next() && CheckConditions.isInteger(rs.getString(1))) {
				return Integer.parseInt(rs.getString(1));
			}
			return 0;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return 0;
		}
	}

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

	// TODO:
	// DB에서 삭제시 무결성 제약조건을 어김
	// 그에대한 해결이 필요
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

	public static String getPhoneNumber(String id) {
		try {
			String sql = " select Phone_no from account where Id = '" + id + "'";
			ResultSet rs = DBConnection.stmt.executeQuery(sql);
			if (rs.next())
				return rs.getString(1);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return "";
	}

	public static boolean buyTheVehicle(String id, String poid) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c1 = Calendar.getInstance();
			String strToday = sdf.format(c1.getTime());

			String sql = "insert into orders(Sold_date, Poid, Cuid) values('" + strToday + "', " + poid + ", '" + id
					+ "')";
			int res = DBConnection.stmt.executeUpdate(sql);
			if (res > 0)
				return true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return false;
	}

	public static boolean getOffTheVehicle(String id, String poid) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c1 = Calendar.getInstance();
			String strToday = sdf.format(c1.getTime());

			String sql = "insert into control(Getoff_date, Poid, Adid) values('" + strToday + "', " + poid + ", '" + id
					+ "')";
			int res = DBConnection.stmt.executeUpdate(sql);
			if (res > 0)
				return true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return false;
	}

	public static boolean getOnTheVehicle(String id, String poid) {
		try {
			String sql = "delete from control where Poid = " + poid;
			int res = DBConnection.stmt.executeUpdate(sql);
			if (res > 0)
				return true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return false;
	}

	public static ArrayList<String[]> getSalesForEachMonth(String id) {
		try {
			String sql = "select sum(Price) as sales, substr(Sold_date, 1, 7) as month  "
					+ " from orders , (select * from vehicle where vehicle.Deid = '" + id + "') t "
					+ " where orders.Poid = t.Poid group by(substr(Sold_date, 1, 7))";
			ResultSet rs = DBConnection.stmt.executeQuery(sql);
			ArrayList<String[]> ret = new ArrayList<>();
			while (rs.next()) {
				String[] temp = new String[2];
				temp[0] = rs.getString(1);
				temp[1] = rs.getString(2);
				ret.add(temp);
			}
			return ret;
		} catch (SQLException e) {
			System.err.println(e.getMessage());

		}
		return null;
	}

	public static ArrayList<String[]> getSalesForEachYear(String id) {
		try {
			String sql = "select sum(Price) as sales, substr(Sold_date, 1, 4) as month  "
					+ " from orders , (select * from vehicle where vehicle.Deid = '" + id + "') t "
					+ " where orders.Poid = t.Poid group by(substr(Sold_date, 1, 4))";
			ResultSet rs = DBConnection.stmt.executeQuery(sql);
			ArrayList<String[]> ret = new ArrayList<>();
			while (rs.next()) {
				String[] temp = new String[2];
				temp[0] = rs.getString(1);
				temp[1] = rs.getString(2);
				ret.add(temp);
			}
			return ret;
		} catch (SQLException e) {
			System.err.println(e.getMessage());

		}
		return null;
	}

	public static ArrayList<String[]> getSalesForEachMaker(String id) {
		try {
			String sql = "select sum(Price) as sales, Maname as maker  "
					+ " from orders , (select * from vehicle where vehicle.Deid = '" + id + "') t "
					+ " where orders.Poid = t.Poid group by(t.Maname)";
			ResultSet rs = DBConnection.stmt.executeQuery(sql);
			ArrayList<String[]> ret = new ArrayList<>();
			while (rs.next()) {
				String[] temp = new String[2];
				temp[0] = rs.getString(1);
				temp[1] = rs.getString(2);
				ret.add(temp);
			}
			return ret;
		} catch (SQLException e) {
			System.err.println(e.getMessage());

		}
		return null;
	}
}
