import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		//programState : 프로그램이 실행중인지 종료중인지 표현
		boolean programState = true;
		View view = new View();
		while(programState) {
			view.printPageStart();
			System.out.println("   중고차 판매 사이트 \"차판다\"에 오신걸 환영합니다.");
			System.out.println("1. 로그인   2. 회원가입  3. 프로그램 종료");
			System.out.println("원하시는 기능에 맞는 숫자를 입력해주십시오.");
			int selection = sc.nextInt();
			view.printPageEnd();
			
			SignView sv = new SignView();
			switch(selection) {
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
