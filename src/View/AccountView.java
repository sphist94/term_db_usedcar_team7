package View;

abstract public class AccountView extends BasicView {

	abstract protected boolean loadAccountPage();

	protected boolean signOut() {
		return false;
	}

	protected void loadAccountInformationPage() {
		while (true) {
			printPageStart();
			System.out.println("현재 페이지는 회원정보 관련 페이지입니다.");
			if (printBack())
				break;
			
			System.out.println("원하시는 기능에 맞는 숫자를 입력해주십시오.");
			System.out.println("1.회원정보 수정  2.비밀번호 수정  3.히스토리 조회  4.회원탈퇴");
			int selection = sc.nextInt();
			printPageEnd();

			switch (selection) {
			case 1:
				loadAccountInfoResetPage();
				break;
			case 2:
				loadPasswordResetPage();
				break;
			case 3:
				loadHistoryLookupPage();
				break;
			case 4:
				loadWithdrawalPage();
				break;
			}
		}
	}

	protected void loadCheckingByPassword() {
		printPageStart();
		System.out.println("본인확인을 위해 비밀번호를 입력해주십시오.");
		System.out.print("현재 비밀번호: ");
		String password = sc.next();
		printPageEnd();

		// TODO:
		// 1. 현재 ID에 대한 비밀번호와 입력 현재 비밀번호가 같은지 확인
	}

	protected void loadAccountInfoResetPage() {
		loadCheckingByPassword();
		printPageStart();
		System.out.println("현재 페이지는 회원정보 수정 페이지입니다.");
		System.out.println("수정을 원하지 않을 시 \'-\'를 입력해주십시오.");

		System.out.print("Lname: ");
		String last_name = sc.next();
		System.out.print("Fname: ");
		String first_name = sc.next();
		System.out.print("Phone(NNN-NNNN-NNNN): ");
		String phone = sc.next();
		System.out.print("Gender: (M/F) ");
		String gender = sc.next();
		System.out.print("Birthdate(YYYY-MM-DD): ");
		String birthdate = sc.next();
		System.out.print("Email: ");
		String email = sc.next();
		sc.nextLine();
		System.out.print("Address: ");
		String address = sc.nextLine();
		System.out.print("Occupation: ");
		String occupation = sc.nextLine();

		System.out.println("\n회원정보 수정을 완료하시겠습니까?");
		System.out.println("원하시는 기능에 맞는 숫자를 입력해주십시오.");
		System.out.println("1.예   2.아니오");
		int selection = sc.nextInt();
		printPageEnd();

		if (selection == 1) {
			// TODO:
			// last_name 부터 occupation 까지 '-'이 아닌 데이터를 업데이트
		}
	}

	protected void loadPasswordResetPage() {
		loadCheckingByPassword();

		printPageStart();
		System.out.print("변경 비밀번호: ");
		String password1 = sc.next();
		System.out.print("변경 비밀번호 확인: ");
		String password2 = sc.next();
		printPageEnd();

		if (password1.equals(password2)) {
			// TODO:
			// 1. 변경 비밀번호와 확인이 같은지 확인
			// 2. 이상없으면 DB의 비밀번호 데이터를 변경 비밀번호로 변경
			// 3. 이상있으면 실패 메시지를 출력
		}
	}

	protected void loadWithdrawalPage() {
		loadCheckingByPassword();

		printPageStart();
		System.out.println("현재 페이지는 회원탈퇴 페이지입니다.");
		System.out.println("원하시는 기능에 맞는 숫자를 입력해주십시오.");
		System.out.println("정말로 회원탈퇴를 하시겠습니까?");
		System.out.println("1.예   2.아니오");
		int selection = sc.nextInt();
		printPageEnd();

		if (selection == 1) {
			// TODO:
			// 1. DB와 연동하여 회원탈퇴 하는 기능
		}
	}

	protected boolean loadVehicleSearchPage() {
		VehicleView vv = new VehicleView();
		vv.loadVehicleSearchPage();
		return true;
	}

	protected void loadHistoryLookupPage() {
		HistoryView hv = new HistoryView();
		hv.loadHistoriesPage();

	}
}
