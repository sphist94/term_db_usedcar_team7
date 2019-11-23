package DB;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.io.BufferedReader;

public class InsertionPhase2Sqls {
	public static boolean isComment(String line) {
		line.replaceAll(" ", "");
		if(line.substring(0, 2).equals("--"))
			return true;
		return false;
	}
	public static boolean checkIsEndOfSql(String line) {
		if (line.charAt(line.length() - 1) == ';') {
			return true;
		}
		return false;
	}

	public static void readSqlFiles(String fileName) {
		try {
			final String path = "C:\\Users\\sphis\\git\\term_db_usedcar_team7\\sql\\";
			//System.out.println(path + fileName);
			File file = new File(path + fileName);
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String line;

			try {
				DBConnection.stmt = DBConnection.conn.createStatement();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			StringBuffer sb = new StringBuffer();


			while ((line = br.readLine()) != null) {
				if (line.isEmpty() || isComment(line)) continue;
				if (checkIsEndOfSql(line)) {
					try {
						sb.append(line.substring(0, line.length()-1));
						String sql = sb.toString();
						sb = new StringBuffer();
						System.out.println(sql);
						DBConnection.stmt.executeUpdate(sql);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				} else {
					sb.append(line);
				}
			}
			br.close();
			fr.close();
		} catch (FileNotFoundException e) {
			e.getStackTrace();
		} catch (IOException e) {
			e.getStackTrace();
		}

	}
}
