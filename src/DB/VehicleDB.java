package DB;

import java.sql.ResultSet;
import java.sql.SQLException;
import Functions.CheckConditions;

public class VehicleDB {
	public static String getDealerIdByPoid(String poid) {
		try {
			String sql = "select Deid from vehicle Poid = " + poid;
			ResultSet rs = DBConnection.stmt.executeQuery(sql);
			if (rs.next())
				return rs.getString(1);
			return "";
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return "";
		}
	}

	public static String[] getVehicleInfoByPoid(String poid) {
		try {
			String sql = "select * from vehicle Poid = " + poid;
			ResultSet rs = DBConnection.stmt.executeQuery(sql);
			String[] ret = Utilities.convertResultSetToStringArray(rs);
			return ret;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}
	
	//TODO
	public static String[] getColorsByPoid(String poid) {
		try {
			String sql = "select * from vehicle Poid = " + poid;
			ResultSet rs = DBConnection.stmt.executeQuery(sql);
			String[] ret = Utilities.convertResultSetToStringArray(rs);
			return ret;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}
	
	//TODO
	public static String[] getFuelTypesByPoid(String poid) {
		try {
			String sql = "select * from vehicle Poid = " + poid;
			ResultSet rs = DBConnection.stmt.executeQuery(sql);
			String[] ret = Utilities.convertResultSetToStringArray(rs);
			return ret;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

	public static int getTotalNumVehicle() {
		try {
			int ret = 0;
			String sql = "select count(*) from vehicle";
			ResultSet rs = DBConnection.stmt.executeQuery(sql);
			if (CheckConditions.isInteger(rs.getString(1)))
				ret = Integer.parseInt(rs.getString(1));
			return ret;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			// error reture
			return -1;
		}
	}
}
