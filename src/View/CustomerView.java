package View;

import DB.AccountInfoDB;
import Functions.CheckConditions;

public class CustomerView extends AccountView {
	protected boolean loadAccountPage(String id) {
		boolean isExit = false;
		while (!isExit) {
			printPageStart();
			System.out.println("현재 구매자 계정으로 로그인이 되었습니다.");
			System.out.println("원하시는 기능에 맞는 숫자를 입력해주십시오.");
			System.out.println("1.회원정보  2.매물검색  3.로그아웃");
			String selection = sc.nextLine();
			printPageEnd();
			
			if(CheckConditions.isInteger(selection))
				continue;
			
			int select = Integer.parseInt(selection);
			switch (select) {
			case 1:
				isExit = loadAccountInformationPage(id);
				break;
			case 2:
				loadVehicleSearchPage(id);
				break;
			case 3:
				isExit = signOut();
				break;
			}
		}
		return true;
	}

	public static boolean doBuyTheVehicle(String poid, String id) {
		System.out.println("해당 매물을 구매하시겠습니까?");
		System.out.println("1.예  2.아니오");
		String selection = sc.nextLine();
		if(selection.equals("1") && AccountInfoDB.buyTheVehicle(id, poid)) {
			System.out.println("매물번호  " + poid + " 구매를 완료했습니다.");
			System.out.println("이용해주셔서 감사합니다.");
			printToBeContinue();
			return true;
			
		}else {
			System.out.println("잠시 후에 다시 이용해주세요.");
			printToBeContinue();
		}
		return false;
	}
}
