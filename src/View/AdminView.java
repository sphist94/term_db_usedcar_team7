package View;

import DB.AccountInfoDB;

public class AdminView extends AccountView {
	protected boolean loadAccountPage(String id) {
		boolean isExit = false;
		while (!isExit) {
			printPageStart();
			System.out.println("현재 관리자 계정으로 로그인이 되었습니다.");
			System.out.println("원하시는 기능에 맞는 숫자를 입력해주십시오.");
			System.out.println("1.회원정보  2.매물검색  3.로그아웃");
			int selection = sc.nextInt();
			printPageEnd();

			switch (selection) {
			case 1:
				//회원탈퇴되면 false를 return 받아 loop가 꺼짐
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
	
	public static boolean doGetOffFakeVehicle(String poid, String id) {
		System.out.println("해당 매물을 허위매물로 내리겠습니까?");
		System.out.println("1.예  2.아니오");
		String selection = sc.nextLine();
		if(selection.equals("1") && AccountInfoDB.getOffTheVehicle(id, poid)) {
			System.out.println("매물번호  " + poid + " 허위매물로 처리하였습니다.");
			printToBeContinue();
			return true;
			
		}else {
			System.out.println("매물번호  " + poid + " 허위매물 처리에 실패했습니다.");
			//System.out.println("잠시 후에 다시 이용해주세요.");
			printToBeContinue();
		}
		return false;
	}
	
	public static boolean doGetOnFakeVehicle(String poid, String id) {
		System.out.println("해당 허위매물을 다시 정상매물로 바꾸시겠습니까?");
		System.out.println("1.예  2.아니오");
		String selection = sc.nextLine();
		if(selection.equals("1") && AccountInfoDB.getOnTheVehicle(id, poid)) {
			System.out.println("매물번호  " + poid + " 정상매물로 바꿨습니다.");
			printToBeContinue();
			return true;
			
		}else {
			System.out.println("매물번호  " + poid + " 정상매물 처리에 실패했습니다.");
			//System.out.println("잠시 후에 다시 이용해주세요.");
			printToBeContinue();
		}
		return false;
	}

}
