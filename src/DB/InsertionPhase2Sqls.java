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
			//자신의 파일 넣고 싶으면 파일 경로 넣어야함
			File file = new File(".");
			final String path = file.getAbsolutePath().replace(".", "") + "sql\\";
			System.out.println(path + fileName);
			file = new File(path + fileName);
			
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
						//System.out.println(sql);
						DBConnection.stmt.executeUpdate(sql);
					} catch (SQLException e) {
						//e.printStackTrace();
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
