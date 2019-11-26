package DB;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import Functions.CheckConditions;

public class VehicleDB {
	public static String getCustmoerByPoid(String poid) {
		try {
			String sql = "select Cuid from orders where Poid =" + poid;
			ResultSet rs = DBConnection.stmt.executeQuery(sql);
			if (rs.next())
				return rs.getString(1);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return "";
	}

	public static boolean isSoldVehicle(String poid) {
		try {
			String sql = "select orders.Poid from orders where orders.Poid =" + poid;
			ResultSet rs = DBConnection.stmt.executeQuery(sql);
			if (rs.next())
				return true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return false;
	}

	public static String getDealerIdByPoid(String poid) {
		try {
			String sql = "select Deid from vehicle where Poid = " + poid;
			ResultSet rs = DBConnection.stmt.executeQuery(sql);
			if (rs.next()) {
				return rs.getString(1);
			}
			return "";
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return "";
		}
	}

	public static String[] getVehicleInfoByPoid(String poid) {
		try {
			String sql = "select * from vehicle where Poid = " + poid;
			ResultSet rs = DBConnection.stmt.executeQuery(sql);
			if (rs.next()) {
				ResultSetMetaData rsmd = rs.getMetaData();
				int col_size = rsmd.getColumnCount();
				if (col_size > 0) {
					String[] ret = new String[col_size];
					for (int i = 1; i <= col_size; ++i) {
						ret[i - 1] = rs.getString(i);
					}
					return ret;
				}
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return null;
	}

	public static ArrayList<String> getColorsByPoid(String poid) {
		try {
			String sql = "select color_type.Coname from colored, color_type where colored.Poid = " + poid
					+ "and colored.Coid = color_type.Coid";
			ResultSet rs = DBConnection.stmt.executeQuery(sql);
			if (rs.next()) {
				ArrayList<String> ret = new ArrayList<>();
				do {
					ret.add(rs.getString(1));
				} while (rs.next());
				return ret;
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return null;
	}

	public static ArrayList<String> getFuelTypesByPoid(String poid) {
		try {
			String sql = "select fuel_type.Funame from fueled, fuel_type  where fueled.Poid = " + poid
					+ "and fueled.Fuid = fuel_type.Fuid";
			ResultSet rs = DBConnection.stmt.executeQuery(sql);
			if (rs.next()) {
				ArrayList<String> ret = new ArrayList<>();
				do {
					ret.add(rs.getString(1));
				} while (rs.next());
				return ret;
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return null;
	}

	public static int getTotalNumVehicle() {
		try {
			int ret = 0;
			String sql = "select count(*) from vehicle";
			ResultSet rs = DBConnection.stmt.executeQuery(sql);
			if (rs.next() && CheckConditions.isInteger(rs.getString(1)))
				ret = Integer.parseInt(rs.getString(1));
			return ret;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			// error reture
			return -1;
		}
	}

	public static ArrayList<String> getMakers() {
		try {
			String sql = "select Maname from maker";
			ResultSet rs = DBConnection.stmt.executeQuery(sql);
			ArrayList<String> ret = new ArrayList<>();
			if (rs.next()) {
				do {
					ret.add(rs.getString(1));
				} while (rs.next());
				return ret;
			}

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return null;
	}

	public static ArrayList<String> getModel(String maker) {
		try {
			String sql = "select Moname from model where Maname = '" + maker + "'";
			ResultSet rs = DBConnection.stmt.executeQuery(sql);
			ArrayList<String> ret = new ArrayList<>();
			if (rs.next()) {
				do {
					ret.add(rs.getString(1));
				} while (rs.next());
				return ret;
			}

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return null;
	}

	public static ArrayList<String> getDetailedModel(String model) {
		try {
			String sql = "select Dename from detailed_model where Moname = '" + model + "'";
			ResultSet rs = DBConnection.stmt.executeQuery(sql);
			ArrayList<String> ret = new ArrayList<>();
			if (rs.next()) {
				do {
					ret.add(rs.getString(1));
				} while (rs.next());
				return ret;
			}

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return null;
	}

	public static ArrayList<String> getColorType() {
		try {
			String sql = "select Coname from color_type";
			ResultSet rs = DBConnection.stmt.executeQuery(sql);
			ArrayList<String> ret = new ArrayList<>();
			if (rs.next()) {
				do {
					ret.add(rs.getString(1));
				} while (rs.next());
				return ret;
			}

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return null;
	}

	public static ArrayList<String> getFuelType() {
		try {
			String sql = "select Funame from fuel_type";
			ResultSet rs = DBConnection.stmt.executeQuery(sql);
			ArrayList<String> ret = new ArrayList<>();
			if (rs.next()) {
				do {
					ret.add(rs.getString(1));
				} while (rs.next());
				return ret;
			}

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return null;
	}

	public static ArrayList<String> getTransmissionName() {
		try {
			String sql = "select Trname from transmission";
			ResultSet rs = DBConnection.stmt.executeQuery(sql);
			ArrayList<String> ret = new ArrayList<>();
			if (rs.next()) {
				do {
					ret.add(rs.getString(1));
				} while (rs.next());
				return ret;
			}

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return null;
	}

	public static ArrayList<String> getCategoryName() {
		try {
			String sql = "select Caname from category";
			ResultSet rs = DBConnection.stmt.executeQuery(sql);
			ArrayList<String> ret = new ArrayList<>();
			if (rs.next()) {
				do {
					ret.add(rs.getString(1));
				} while (rs.next());
				return ret;
			}

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return null;
	}

	public static ArrayList<String> getEngineDisplacement() {
		try {
			String sql = "select Enname from engine_displacement";
			ResultSet rs = DBConnection.stmt.executeQuery(sql);
			ArrayList<String> ret = new ArrayList<>();
			if (rs.next()) {
				do {
					ret.add(rs.getString(1));
				} while (rs.next());
				return ret;
			}

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return null;
	}

	public static String getColorId(String coname) {
		try {
			String sql = "select Coid from color_type where Coname = '" + coname + "'";
			ResultSet rs = DBConnection.stmt.executeQuery(sql);
			if (rs.next())
				return rs.getString(1);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return "";
	}

	public static String getFuelId(String funame) {
		try {
			String sql = "select Fuid from fuel_type where Funame = '" + funame + "'";
			ResultSet rs = DBConnection.stmt.executeQuery(sql);
			if (rs.next())
				return rs.getString(1);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return "";
	}

	public static boolean insertColoredWithPoid(String poid, String coname) {
		try {
			String coid = getColorId(coname);
			String sql = "insert into colored(Poid, Coid) values (" + poid + ", " + coid + ")";
			DBConnection.stmt.executeUpdate(sql);
			return true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return false;
	}

	public static boolean insertFueledWithPoid(String poid, String funame) {
		try {
			String fuid = getFuelId(funame);
			String sql = "insert into fueled(Poid, Fuid) values (" + poid + ", " + fuid + ")";
			DBConnection.stmt.executeUpdate(sql);
			return true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return false;
	}

	public static boolean insertVehicle(String poid, String deid, String[] extra_info, ArrayList<String> colors,
			ArrayList<String> fuels) {
		try {
			String sql = "insert into VEHICLE (Poid, Deid, Age, Veid, Mileage, Price, Maname, Moname, Dename, Enname, Trname, Caname) values (";
			sql += poid + ", '" + deid + "', ";
			for (int i = 0; i < 10; ++i) {
				if (i == 2 || i == 3 || i == 7) {
					sql += extra_info[i] + ", ";
				} else if (i != 9) {
					sql += "'" + extra_info[i] + "', ";
				} else {
					sql += "'" + extra_info[i] + "')";
				}
			}
			DBConnection.stmt.executeUpdate(sql);

			for (int i = 0; i < colors.size(); ++i) {
				insertColoredWithPoid(poid, colors.get(i));
			}

			for (int i = 0; i < fuels.size(); ++i) {
				insertFueledWithPoid(poid, fuels.get(i));
			}

			return true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return false;
	}

	public static boolean removeColoredByPoid(String poid) {
		try {
			String sql = "delete from colored where Poid = " + poid;
			DBConnection.stmt.executeUpdate(sql);
			return true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return false;
	}

	public static boolean removeFueledByPoid(String poid) {
		try {
			String sql = "delete from fueled where Poid = " + poid;
			DBConnection.stmt.executeUpdate(sql);
			return true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return false;
	}

	public static boolean updateVehicle(String poid, String[] extra_info, ArrayList<String> colors,
			ArrayList<String> fuels) {
		try {
			String[] meta_info = { "Age", "Vehicle_no", "Mileage", "Price", "Maname", "Moname", "Dename", "Enname",
					"Trname", "Caname" };
			String sql = "update vehicle set ";
			int cnt = 0;
			for (int i = 0; i < 10; ++i) {
				if (!extra_info[i].isEmpty()) {
					++cnt;
				}
			}

			int idx = 0;
			for (int i = 0; i < 10; ++i) {
				if (!extra_info[i].isEmpty()) {
					++idx;
					if (i == 2 || i == 3 || i == 7)
						sql += meta_info[i] + " = " + extra_info[i] + " ";
					else
						sql += meta_info[i] + " = '" + extra_info[i] + "' ";
					if (idx != cnt)
						sql += ", ";
				}
			}

			if (colors.size() > 0) {
				removeColoredByPoid(poid);
				for (int i = 0; i < colors.size(); ++i) {
					insertColoredWithPoid(poid, colors.get(i));
				}
			}
			if (fuels.size() > 0) {
				removeFueledByPoid(poid);
				for (int i = 0; i < fuels.size(); ++i) {
					insertFueledWithPoid(poid, fuels.get(i));
				}
			}

			if (cnt > 0) {
				sql += " where vehicle.Poid = " + poid;
				DBConnection.stmt.executeUpdate(sql);
			}
			return true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return false;
	}

	public static ArrayList<String[]> getVehicleList() {
		try {
			int col_size = 6;
			String sql = "select vehicle.Poid, Mileage, Price, Maname, Moname, Dename "
					+ "from vehicle where not exists(" + "select * from orders where orders.Poid = vehicle.Poid) "
					+ "and not exists(" + "select * from control where control.Poid = vehicle.Poid)";
			ResultSet rs = DBConnection.stmt.executeQuery(sql);
			ArrayList<String[]> ret = new ArrayList<>();
			while (rs.next()) {
				String[] temp = new String[6];
				for (int i = 1; i <= col_size; ++i) {
					temp[i - 1] = rs.getString(i);
				}
				ret.add(temp);
			}
			return ret;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return null;
	}

	public static ArrayList<String[]> getConditionedVehicleList(String[] extra_info, ArrayList<String> colors,
			ArrayList<String> fuels) {
		try {
			String coid = "";
			String fuid = "";
			if (colors.size() > 0)
				coid = getColorId(colors.get(0));
			if (fuels.size() > 0)
				fuid = getFuelId(fuels.get(0));

			int col_size = 6;
			String[] meta_info = { "Age", "Veid", "Mileage", "Price", "Maname", "Moname", "Dename", "Enname", "Trname",
					"Caname" };

			String sql = "select vehicle.Poid, Mileage, Price, Maname, Moname, Dename from vehicle";

			boolean isFirst = true;
			for (int i = 0; i < 10; ++i) {
				if (!extra_info[i].isEmpty()) {
					if (isFirst) {
						sql += " where ";
						isFirst = false;
					} else {
						sql += " and ";
					}
					if (i == 0) {
						sql += meta_info[i] + ">= '" + extra_info[i] + "'";
					} else if (i == 2 || i == 3) {
						sql += meta_info[i] + "<= " + extra_info[i];
					} else if (i == 7) {
						sql += meta_info[i] + "= " + extra_info[i] + " ";
					} else if (i != 9) {
						sql += meta_info[i] + "= " + "'" + extra_info[i] + "' ";
					} else {
						sql += meta_info[i] + "= " + "'" + extra_info[i] + "' ";
					}
				}
			}

			if (!coid.isEmpty() || !fuid.isEmpty()) {
				if (isFirst) {
					sql += " where ";
					isFirst = false;
				} else {
					sql += " and ";
				}
				if (!coid.isEmpty() && !fuid.isEmpty()) {
					sql += " exists(select colored.Poid from colored where colored.Coid = " + coid
							+ " and colored.Poid = vehicle.Poid) ";
					sql += " and exists(select fueled.Poid from fueled where fueled.Coid = colored.Poid and fueled.Fuid = "
							+ fuid + ") ";
				} else if (!coid.isEmpty()) {
					sql += " exists(select colored.Poid from colored where colored.Coid = " + coid
							+ " and colored.Poid = vehicle.Poid) ";
				} else if (!fuid.isEmpty()) {
					sql += " exists(select fueled.Poid from fueled where fueled.Coid = vehicle.Poid and fueled.Fuid = "
							+ fuid + ") ";
				}
			}

			if (isFirst) {
				sql += " where ";
				isFirst = false;
			} else {
				sql += " and ";
			}
			// 허위매물 또는 판매된 매물은 제외하는 sql
			sql += " not exists(select * from orders where orders.Poid = vehicle.Poid) "
					+ "and not exists(select * from control where control.Poid = vehicle.Poid)";

			ResultSet rs = DBConnection.stmt.executeQuery(sql);
			ArrayList<String[]> ret = new ArrayList<>();
			while (rs.next()) {
				String[] temp = new String[6];
				for (int i = 1; i <= col_size; ++i) {
					temp[i - 1] = rs.getString(i);
				}
				ret.add(temp);
			}
			return ret;
		} catch (

		SQLException e) {
			System.err.println(e.getMessage());
		}
		return null;
	}

	public static ArrayList<String[]> getOwnRegisteredVehicle(String id) {
		try {
			String sql = "select vehicle.Poid, Mileage, Price, Maname, Moname, Dename from vehicle where Deid = '" + id
					+ "' " + " and not exists(select * from orders where orders.Poid = vehicle.Poid) "
					+ " and not exists(select * from control where control.Poid = vehicle.Poid) ";
			ResultSet rs = DBConnection.stmt.executeQuery(sql);
			ArrayList<String[]> ret = new ArrayList<>();
			while (rs.next()) {
				String[] temp = new String[6];
				for (int i = 1; i <= 6; ++i) {
					temp[i - 1] = rs.getString(i);
				}
				ret.add(temp);
			}
			if (ret.size() > 0)
				return ret;

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
}
