import View.MainView;
import DB.DBConnection;
import DB.InsertionPhase2Sqls;

public class ProgramStart {
	public static void main(String[] args) {
		DBConnection.getConnection();
		
		//디버그나 테스트할때는 주석처리 해둘것
//		System.out.println("데이터 초기화 중입니다. 잠시만 기다려 주십시오.");
//		InsertionPhase2Sqls.readSqlFiles("Team8_phase1.sql");
//		System.out.println("데이터 입력중입니다. 잠시만 기다려 주십시오.");
//		InsertionPhase2Sqls.readSqlFiles("Team8_phase2.sql");
//		System.out.println("기다려주셔서 감사합니다.");
		//주석처리 여기까지
		
		MainView mv = new MainView();
		mv.startPage();
	}
}
