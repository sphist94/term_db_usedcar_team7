package View;

import Functions.SignInputType;
import DB.VehicleDB;

public class DealerView extends AccountView {
	protected boolean loadAccountPage(String id) {
		boolean state = true;
		while (state) {
			printPageStart();
			System.out.println("현재 판매자 계정으로 로그인이 되었습니다.");
			System.out.println("원하시는 기능에 맞는 숫자를 입력해주십시오.");
			System.out.println("1.회원정보  2.매물검색  3.매물등록  4.로그아웃");
			int selection = sc.nextInt();
			printPageEnd();

			switch (selection) {
			case 1:
				loadAccountInformationPage(id);
				break;
			case 2:
				loadVehicleSearchPage();
				break;
			case 3:
				loadVehicleRegistrationPage(id);
				break;
			case 4:
				state = signOut();
				break;
			}
		}
		return true;
	}
	
	private String FillVehicleRegistration(int select) {
		switch (select) {
		case 1:
			return getInput(SignInputType.LNAME, "이름: ");
		case 2:
			return getInput(SignInputType.FNAME, "성: ");
		case 3:
			return getInput(SignInputType.PHONE, "휴대전화 번호(NNN-NNNN-NNNN): ");
		case 4:
			return getInput(SignInputType.BIRTHDATE, "생일(YYYY-MM-DD): ");
		case 5:
			return getInput(SignInputType.GENDER, "성별(M/F): ");
		case 6:
			return getInput(SignInputType.EMAIL, "이메일: ");
		case 7:
			return getInput(SignInputType.ADDRESS, "주소: ");
		case 8:
			return getInput(SignInputType.OCCUPATION, "직업: ");
		default:
			return "";
		}
	}
	

	private void loadVehicleRegistrationPage(String id) {
		//insert into VEHICLE (Poid, Deid, Age, Veid, Mileage, Price, Maname, Moname, Dename, Enname, Trname, Caname)
		//Poid, Mileage, Price, Enname: 정수, 나머지는 스트링
		
		printPageStart();
		int Poid = VehicleDB.getTotalNumVehicle() + 1;
		String[] input = new String[10];
		while (true) {
			printPageMiddle();
			System.out.println("현재 페이지는 차량등록 페이지입니다.");
			System.out.println("입력하고자 하는 사항을 선택해주십시오. 전부 입력해야합니다.");
			System.out.println("해당하는 사항에 알맞게 기입해주십시오.");
			System.out.println("1.연식  2.차량번호  3.주행거리  4.가격  5.제조사  6.모델  7.세부모델  8.배기량  9.변속기  10.색상  11.차종  12.연료");
			System.out.println("완료를 원하시면 9, 종료를 원하시면 10를 입력해주세요");
			System.out.println("그 외의 숫자나 문자는 무시됩니다.");
			printPageMiddle();
			break;
		}
		printPageEnd();
		
		System.out.println("차량등록 페이지입니다.");
		System.out.println("해당하는 사항에 알맞게 기입해주십시오.");
		System.out.print("차량번호: ");
		String vehicle_number = sc.next();
		System.out.print("주행거리: ");
		int mileage = sc.nextInt();
		System.out.print("연식: ");
		String age = sc.next();
		System.out.print("가격: ");
		String price = sc.next();
		System.out.print("제조사: ");
		String maker = sc.next();
		System.out.print("모델: ");
		String model = sc.next();
		System.out.print("세부모델: ");
		String detailed_model = sc.next();
		System.out.print("차종: ");
		String category = sc.next();
		System.out.print("배기량: ");
		String engine = sc.next();
		System.out.println("변속기(여러 변속기를 가지고 있으면 콤마(,)로 구분하여 입력해주십시오.): ");
		String transmission = sc.next();
		System.out.println("색상(여러가지 색상을 가지고 있으면 콤마(,)로 구분하여 입력해주십시오.): ");
		String color = sc.next();
		System.out.println("연료(하이브리드 연료이면 콤마(,)로 구분하여 입력해주십시오.): ");
		String fuel = sc.next();

		printPageMiddle();
		System.out.println("차량정보 기입을 완료하시겠습니까?");
		System.out.println("1.완료  2.뒤로가기");
		int selection = sc.nextInt();
		printPageMiddle();
		printPageEnd();

		switch (selection) {
		case 1:
			// TODO:
			// 1. 해당 정보를 가지고 차량정보를 적절하게 입력했는지 비교
			// 2. 다중 변속기, 색상, 연료일 시 적절히 DB에 넣을 것
			break;
		case 2:
			break;
		}
	}

}
