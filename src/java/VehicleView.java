
public class VehicleView extends View {
	public void loadVehicleSearchPage() {
		printPageStart();
		System.out.println("현재 페이지는 매물검색 관련 페이지입니다.");
		System.out.println("원하시는 기능에 맞는 숫자를 입력해주십시오.");
		System.out.println("1.전체 검색  2.조건 검색");
		System.out.println("뒤로 가시려면 999를 입력해주십시오.");
		int selection = sc.nextInt();
		printPageEnd();

		switch (selection) {
		case 1:
			loadAllVehicleSearchPage();
			break;
		case 2:
			loadConditionedVehicleSearchPage();
			break;
		}
	}
	
	//full_maker: 제조사, 모델, 세부모델을 합친것
	private void showVehicles(int poid, int mileage, int price, String full_maker) {
		System.out.println("매물번호: " + poid + ", 가격: " + price + ", 주행거리: "+mileage+"차명: "+full_maker);
	}
	
	private void loadAdditionVehicleSearchPage() {
		printPageStart();
		System.out.println("추가 검색하시겠습니까?");
		System.out.println("사항에 알맞게 기입해주십시오.");
		System.out.print("검색 개수(최소0개, 최대 50개, 그 밖의 범위는 검색하지 않음을 의미): ");
		int num_vehicle = sc.nextInt();
		printPageEnd();
	}

	private void loadAllVehicleSearchPage() {
		printPageStart();
		System.out.println("현재 페이지는 전체 매물검색 페이지입니다.");
		System.out.println("사항에 알맞게 기입해주십시오.");
		System.out.print("검색 개수(최소0개, 최대 50개, 그 밖의 범위는 검색하지 않음을 의미): ");
		int num_vehicle = sc.nextInt();
		printPageEnd();
		
		if(num_vehicle <= 50 && num_vehicle > 0 ) {
			//TODO: 해당하는 개수만큼 DB에서 불러옴
		}
		
		//TODO: 계속해서 실행하게끔 만들고, 더이상 매물이 없을 시 그만출력
		loadAdditionVehicleSearchPage();
		
		
		loadDetailedVehiclePage();
	}


	private boolean loadDetailedVehiclePage() {
		System.out.println("더 자세하게 보시고 싶으면 해당 매물번호를 입력해주십시오.");
		System.out.println("뒤로가시고 싶으면 999을 입력해주십시오.");
		int posting_id = sc.nextInt();
		printPageEnd();
		
		if(posting_id == 999) {
			return false;
		}
		else {
		// TODO:
		// 1. 입력받은 매물번호가 자신의 구매, 판매이력이 맞는지 확인
		// 2. 맞으면 해당 매물에 대한 자세히 데이터를 보여줌
		// 3. 틀렸으면 실패메시지를 출력
		}
		
		return true;
		
	}
	
	private void loadConditionedVehicleSearchPage() {
		printPageStart();
		System.out.println("매물 조건검색 페이지입니다.");
		System.out.println("해당하는 사항에 알맞게 기입해주십시오.");
		System.out.println("0 입력시 조건 없음");
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
		
		//TODO
		//DB에서 해당하는 조건의 매물을 불러오는 기능 구현이 필요
	}
}
