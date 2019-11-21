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
	
	protected void loadHistoryLookupPage() {
		printPageStart();
		System.out.println("현재 페이지는 판매이력 조회 페이지입니다.");
		System.out.println("");
		System.out.println("원하시는 기능에 맞는 숫자를 입력해주십시오.");
		System.out.println("정말로 회원탈퇴를 하시겠습니까?");
		System.out.println("1.예   2.아니오");
		int selection = sc.nextInt();
	}
	
	private void loadVehicleRegistrationPage() {
		printPageStart();
		System.out.println("회원가입 페이지입니다. 뒤로 가시려면 999번을 입력해주십시오.");
		System.out.println("가입하시고자 하는 유형을 입력해주십시오.");
		System.out.println("1. 구매자   2. 판매자 ");
		int account_type = sc.nextInt();
//		if(account_type == 999)
//			return false;
		System.out.println("해당하는 사항에 알맞게 기입해주십시오. (*는 필수 정보)");
		System.out.print("*ID: ");
		String id = sc.next();
		System.out.print("*PW: ");
		String pw = sc.next();
		System.out.print("*Lname: ");
		String last_name = sc.next();
		System.out.print("*Fname: ");
		String first_name = sc.next();
		System.out.print("*Phone(NNN-NNNN-NNNN): ");
		String phone = sc.next();
		
		printPageMiddle();
		System.out.println("필수 정보는 전부 입력하셨습니다. 추가 정보를 입력하시겠습니까?");
		System.out.println("1.예  2. 아니오");
		int selection = sc.nextInt();
//		if(selection != 1) {
//			printPageEnd();
//			return true;
//		}
		printPageMiddle();
		
		System.out.print("Gender: (M/F) ");
		String gender = sc.next();
		System.out.print("Birthdate(YYYY-MM-DD): ");
		String birthdate = sc.next();
		System.out.print("Email: ");
		String email = sc.next();
		
		//버퍼비우기
		sc.nextLine();
		
		System.out.print("Address: ");
		String address = sc.nextLine();
		System.out.print("Occupation: ");
		String occupation = sc.nextLine();
		printPageEnd();
	}
	
	
}
