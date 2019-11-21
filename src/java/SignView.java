
public class SignView extends View{
//	public enum AccountType{
//		CUSTOMER, DEALER, ADMINISTRATOR
//	};

	public boolean loadSignIn() {
		printPageStart();
		System.out.println("로그인 페이지입니다.");
		System.out.print("ID: ");
		String id = sc.next();
		System.out.print("PW: ");
		String pw = sc.next();
		printPageEnd();

		// TODO:
		// 1. 입력 ID를 DB의 계정튜플들과 비교해 존재하는지 비교
		// 2. 존재한다면 비밀번호 일치하는 비교하고, 일치하면 계정 유형(구매자, 판매자, 관리자)에 따라 페이지 로딩
		// 3. 입력 ID가 존재하지 않거나 비밀번호가 일치하지 않는다면 에러 메시지 출력하고 해당 페이지 리로딩

		// int account_type = getAccountType(id);
		int account_type = 1;
		switch (account_type) {
		case 1:
			CustomerView cv = new CustomerView();
			cv.loadAccountPage();
			break;
		case 2:
			DealerView dv = new DealerView();
			dv.loadAccountPage();
			break;
		case 3:
			AdminView av = new AdminView();
			av.loadAccountPage();
			break;
		}

		return true;
	}

	public boolean loadSignUp() {
		printPageStart();
		System.out.println("회원가입 페이지입니다. 뒤로 가시려면 999번을 입력해주십시오.");
		System.out.println("가입하시고자 하는 유형을 입력해주십시오.");
		System.out.println("1. 구매자   2. 판매자 ");
		int account_type = sc.nextInt();
		if(account_type == 999)
			return false;
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
		if(selection != 1) {
			printPageEnd();
			return true;
		}
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

		// TODO:
		// 1. ID 입력시 DB의 데이터와 비교해 이미 존재하는 아이디인지 확인이 필요
		// 2. Phone, Gender, Birthdate의 입력 값이 ()와 일치하지 않으면 다시 수행
		// 3. 회원가입이 성공했으면 성공 메시지를 출력
		return true;
	}
}
