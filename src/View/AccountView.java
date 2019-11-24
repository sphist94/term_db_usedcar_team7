package View;

import java.util.Arrays;

import DB.AccountInfoDB;
import DB.SignDB;
import Functions.CheckConditions;
import Functions.SignInputType;

abstract public class AccountView extends BasicView {

	abstract protected boolean loadAccountPage(String id);

	protected boolean signOut() {
		return false;
	}

	protected void loadAccountInformationPage(String id) {
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
				loadAccountInfoResetPage(id);
				break;
			case 2:
				loadPasswordResetPage(id);
				break;
			case 3:
				loadHistoryLookupPage();
				break;
			case 4:
				loadWithdrawalPage(id);
				break;
			}
		}
	}

	protected boolean loadCheckingByPassword(String id) {
		int cnt = 0;
		while (cnt > 3) {
			printPageStart();
			System.out.println("본인확인을 위해 비밀번호를 입력해주십시오.");
			String password = getInput(SignInputType.PW, "비밀번호: ");
			printPageEnd();

			if (AccountInfoDB.isEuqalPassword(id, password)) {
				System.out.println("본인확인이 완료되었습니다.");
				return true;
			}

			System.out.println("비밀번호가 틀렸습니다.");
			++cnt;
		}

		System.out.println("비밀번호를 3번 이상 틀렸습니다.");
		System.out.println("잠시 후에 다시 시도해주십시오.");
		return false;
	}

	private String FillAccountInfoRest(int select) {
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

	protected void loadAccountInfoResetPage(String id) {
		if (loadCheckingByPassword(id)) {
			return;
		}
		

		String[] input = new String[8];
		Arrays.fill(input, "");
		while (true) {
			printPageStart();
			System.out.println("현재 페이지는 회원정보 수정 페이지입니다.");
			System.out.println("입력하고자 하는 사항을 선택해주십시오.");
			System.out.println("1.이름(성 제외)*  2.성  3.휴대전화 번호  4.생년월일  5.성별  6.이메일  7.주소  8.직업");
			System.out.println("해당하는 사항에 알맞게 기입해주십시오.");
			System.out.println("완료를 원하시면 9를, 종료를 원하시면 10를 입력해주세요");
			System.out.println("그 외의 숫자나 문자는 무시됩니다.");
			printPageEnd();

			String selection = sc.nextLine();
			if (CheckConditions.isInteger(selection)) {
				int select = Integer.parseInt(selection);
				// select가 정수이지만 사항과 관련없는 정수일경우 다시 시작
				if (select < 1 || select > 10) {
					System.out.println("잘못된 값을 입력하셨습니다. 다시 입력해주십시오.");
					continue;
				}
				// 9 은 입력완료를 의미
				else if (select == 9) {
					AccountInfoDB.updateAccountInfo(id, input);
					System.out.println("회원정보 수정이 완료되었습니다.");
					break;
				}
				// 10는 종료를 의미, 따라서 회원정보 수정 페이지 종료
				else if (select == 10) {
					break;
				}
				input[select - 1] = FillAccountInfoRest(select);
			}
			else {
				System.out.println("잘못된 값을 입력하셨습니다. 다시 입력해주십시오.");
			}
		}
		
	}

	protected boolean loadPasswordResetPage(String id) {
		if (loadCheckingByPassword(id)) {
			return false;
		}

		while (true) {
			if (printBack()) {
				break;
			}
			printPageStart();
			System.out.print("변경 비밀번호: ");
			String password1 = getInput(SignInputType.PW, "변경 비밀번호: ");
			System.out.print("변경 비밀번호 확인: ");
			String password2 = getInput(SignInputType.PW, "변경 비밀번호 확인: ");
			printPageEnd();

			if (password1.equals(password2) && AccountInfoDB.updatePassword(id, password1)) {
				System.out.println("비밀번호 변경이 완료됐습니다.");
				return true;
			} else {
				System.out.println("확인 비밀번호가 일치하지 않습니다.");
				System.out.println("다시 입력해주십시오.");
			}
		}

		return false;
	}

	protected void loadWithdrawalPage(String id) {
		if (loadCheckingByPassword(id)) {
			return;
		}
		printPageStart();
		while (true) {
			printPageMiddle();
			System.out.println("현재 페이지는 회원탈퇴 페이지입니다.");
			System.out.println("원하시는 기능에 맞는 숫자를 입력해주십시오.");
			System.out.println("정말로 회원탈퇴를 하시겠습니까?");
			System.out.println("1.예   2.아니오");
			System.out.println("");
			String selection = sc.nextLine();
			printPageMiddle();

			if (CheckConditions.isInteger(selection)) {
				int select = Integer.parseInt(selection);
				if (select == 1) {
					AccountInfoDB.WidthDrawal(id);
					System.out.println("회원탈퇴가 완료됐습니다. 그 동안 이용해주셔서 감사합니다.");
					break;
				} 
				else if (select == 2) 
					break;
				else {
					System.out.println("잘못된 값을 입력하셨습니다. 다시 입력해주십시오.");
				}
			}
		}
		printPageEnd();
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
