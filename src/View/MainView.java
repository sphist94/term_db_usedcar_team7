package View;

import java.util.Scanner;
import Functions.CheckConditions;

public class MainView {
	Scanner sc = new Scanner(System.in);
	public void startPage() {
		boolean programState = true;
		BasicView view = new BasicView();
		
		while (programState) {
			view.printPageStart();
			System.out.println("   중고차 판매 사이트 \"차판다\"에 오신걸 환영합니다.");
			System.out.println("1.로그인  2.회원가입  3.프로그램 종료  (나머지 값들은 무시됩니다.) ");
			System.out.println("원하시는 기능에 맞는 숫자를 입력해주십시오.");
			String selection = sc.nextLine();

			// 입력값이 정수값이 아닐 경우 프로그램 다시 시작
			if (!CheckConditions.isInteger(selection))
				continue;

			view.printPageEnd();

			SignView sv = new SignView();
			int select = Integer.parseInt(selection);
			switch (select) {
			case 1:
				sv.loadSignIn();
				break;
			case 2:
				sv.loadSignUp();
				break;
			case 3:
				programState = false;
			}
		}
	}
}
