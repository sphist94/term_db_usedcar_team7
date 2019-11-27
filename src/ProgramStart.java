import View.MainView;

import java.util.Scanner;

import DB.DBConnection;
import DB.InsertionPhase2Sqls;

public class ProgramStart {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("주소, 포트번호, SID, 사용자ID, 비밀번호를 입력해주세요.");
		System.out.println("입력값이 없으면 URL은 'jdbc:oracle:thin:@localhost:1600:xe' 이와 같이 진행됩니다.");
		System.out.println("위와 같이 진행하시려면 각 값에 <Enter> 키만 입력해주세요.");
		System.out.print("주소(default: @localhost): ");
		String address = sc.nextLine();
		System.out.print("포트번호(default: 1600): ");
		String port = sc.nextLine();
		System.out.print("SID(default: xe): ");
		String sid = sc.nextLine();
		System.out.print("사용자 ID(default: system): ");
		String user_id = sc.nextLine();
		System.out.print("비밀번호(default: oracle): ");
		String password = sc.nextLine();
		DBConnection.getConnection(address, port, sid, user_id, password);
		
		
		//디버그나 테스트할때는 주석처리 해둘것
		System.out.println("\n데이터 초기화 중입니다. 잠시만 기다려 주십시오.");
		InsertionPhase2Sqls.readSqlFiles("Team8_phase1.sql");
		System.out.println("데이터 입력중입니다. 잠시만 기다려 주십시오.");
		InsertionPhase2Sqls.readSqlFiles("Team8_phase2.sql");
		System.out.println("기다려주셔서 감사합니다.");
		//주석처리 여기까지
		
		MainView mv = new MainView();
		mv.startPage();
		sc.close();
		
	}
}
