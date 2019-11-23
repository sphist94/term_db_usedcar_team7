import View.MainView;
import DB.DBConnection;
import DB.InsertionPhase2Sqls;

public class ProgramStart {
	public static void main(String[] args) {
		DBConnection.getConnection();
		InsertionPhase2Sqls.readSqlFiles("Team8_phase1.sql");
		InsertionPhase2Sqls.readSqlFiles("Team8_phase2.sql");
		MainView mv = new MainView();
		mv.startPage();
	}
}
