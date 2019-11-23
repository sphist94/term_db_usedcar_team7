import View.MainView;
import DB.DBConnection;

public class ProgramStart {
	public static void main(String[] args) {
		MainView mv = new MainView();
		DBConnection.getConnection();
		mv.startPage();
	}
}
