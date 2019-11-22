package View;
public class DealerView extends AccountView {
	protected boolean loadAccountPage() {
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
				loadAccountInformationPage();
				break;
			case 2:
				loadVehicleSearchPage();
				break;
			case 3:
				loadVehicleRegistrationPage();
				break;
			case 4:
				state = signOut();
				break;
			}
		}
		return true;
	}
	
	private void loadVehicleRegistrationPage() {
		printPageStart();
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
		
		switch(selection) {
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
