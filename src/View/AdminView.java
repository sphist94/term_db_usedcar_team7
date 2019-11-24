package View;
public class AdminView extends AccountView {
	protected boolean loadAccountPage(String id) {
		boolean state = true;
		while (state) {
			printPageStart();
			System.out.println("현재 판매자 계정으로 로그인이 되었습니다.");
			System.out.println("원하시는 기능에 맞는 숫자를 입력해주십시오.");
			System.out.println("1.회원정보  2.매물검색  3.로그아웃");
			int selection = sc.nextInt();
			printPageEnd();

			switch (selection) {
			case 1:
				//회원탈퇴되면 false를 return 받아 loop가 꺼짐
				state = loadAccountInformationPage(id);
				break;
			case 2:
				loadVehicleSearchPage();
				break;
			case 3:
				state = signOut();
				break;
			}
		}
		return true;
	}

}
