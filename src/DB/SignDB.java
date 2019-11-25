package DB;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SignDB {
	public static boolean DidExistId(String id) {
		try {
			DBConnection.stmt = DBConnection.conn.createStatement();
			String sql = "select Id from Account" + " where Id=" + "'" + id + "'";
			ResultSet rs = DBConnection.stmt.executeQuery(sql);
			// 회수해온 아이디 비밀번호가 전부 동일한 경우
			if (rs.next() && id.equals(rs.getString(1)))
				return true;
			return false;
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
			return false;
		}
	}

	public static int login(String id, String password) {
		try {
			DBConnection.stmt = DBConnection.conn.createStatement();
			String sql = "select Id, Password, Account_type from Account" + " where Id=" + "'" + id + "'";
			ResultSet rs = DBConnection.stmt.executeQuery(sql);
			// 회수해온 아이디 비밀번호가 전부 동일한 경우
			if (rs.next() && id.equals(rs.getString(1)) && password.equals(rs.getString(2)))
				return Integer.parseInt(rs.getString(3));
			return 0;
		} catch (SQLException ex2) {
			System.err.println(ex2.getMessage());
			return 0;
		}
	}

	public static boolean signUp(String[] input, String account_type) {
		try {
			DBConnection.stmt = DBConnection.conn.createStatement();
			StringBuffer sb = new StringBuffer();
			sb.append("insert into ACCOUNT (Id, Password, Lname, Fname, Phone_no, Birth_date, Gender, Email, Address, Occupation, Account_type) values (");
			for (int i = 0; i < input.length; ++i) {
				if (input[i].isEmpty())
					sb.append("NULL, ");
				else
					sb.append("'" + input[i] + "', ");
			}
			sb.append(account_type + ")");
			String sql = sb.toString();
			DBConnection.stmt.executeUpdate(sql);
			
			//account_type에 따른 구매자, 판매자, 관리자 계정 업데이트
			switch(Integer.parseInt(account_type)) {
			case 1:
				sb = new StringBuffer();
				sb.append("insert into customer(Cuid) values ('"+input[0]+"')");
				DBConnection.stmt.executeUpdate(sb.toString());
				break;
			case 2:
				sb = new StringBuffer();
				sb.append("insert into dealer(Deid) values ('"+input[0]+"')");
				DBConnection.stmt.executeUpdate(sb.toString());
				break;
			case 3:
				sb = new StringBuffer();
				sb.append("insert into administrator(Adid) values ('"+input[0]+"')");
				DBConnection.stmt.executeUpdate(sb.toString());
				break;
			}
			return true;
			
		} catch (SQLException ex2) {
			System.err.println(ex2.getMessage());
			return false;
		}
	}
}
