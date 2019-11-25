package View;

import Functions.CheckConditions;
import Functions.SignInputType;

import java.util.Arrays;

import DB.SignDB;

public class SignView extends BasicView {
	String id = "";
	String password = "";
	public void loadSignIn() {
		boolean isSignInSuccess = false;
		int account_type = 0;
		// 로그인 실패시 계속해서 이 페이지에 머물면서 로그인을 시도
		printPageStart();
		while (!isSignInSuccess) {
			System.out.println("로그인 페이지입니다.");
			System.out.println("해당하는 사항에 알맞게 기입해주십시오.");

			if (printBack())
				break;

			id = getInput(SignInputType.ID, "ID: ");
			password = getInput(SignInputType.PW, "PW: ");
			

			// SignDB.Login에 ID와 PW를 넘겨주면
			// DB와 비교를 해서 있는 정보면 account_type을 반환해준다.
			// 만약 account_type이 0이면 없는 계정, 1: 구매자, 2: 판매자, 3: 관리자
			if ((account_type = SignDB.login(id, password)) != 0) {
				printPageEnd();
				isSignInSuccess = true;
			} else {
				printPageMiddle();
				System.out.println("존재하지 않는 ID이거나 틀린 비밀번호입니다.");
				System.out.println("다시 입력해주십시오.");
				printToBeContinue();
				printPageMiddle();
			}
		}

		// account_type에 따른 뷰 호출
		if (isSignInSuccess) {
			switch (account_type) {
			case 1:
				CustomerView cv = new CustomerView();
				cv.loadAccountPage(id);
				break;
			case 2:
				DealerView dv = new DealerView();
				dv.loadAccountPage(id);
				break;
			case 3:
				AdminView av = new AdminView();
				av.loadAccountPage(id);
				break;
			}
		}
	}

	private String FillSignUpInfo(int select) {
		switch (select) {
		case 1:
			return getInput(SignInputType.ID, "*아이디: ");
		case 2:
			return getInput(SignInputType.PW, "*비밀번호: ");
		case 3:
			return getInput(SignInputType.LNAME, "*이름: ");
		case 4:
			return getInput(SignInputType.FNAME, "*성: ");
		case 5:
			return getInput(SignInputType.PHONE, "*휴대전화 번호(NNN-NNNN-NNNN): ");
		case 6:
			return getInput(SignInputType.BIRTHDATE, "생년월일(YYYY-MM-DD): ");
		case 7:
			return getInput(SignInputType.GENDER, "성별(M/F): ");
		case 8:
			return getInput(SignInputType.EMAIL, "이메일: ");
		case 9:
			return getInput(SignInputType.ADDRESS, "주소: ");
		case 10:
			return getInput(SignInputType.OCCUPATION, "직업: ");
		default:
			return "";
		}
	}

	private boolean didFillRequiredInfo(String[] input) {
		for (int i = 0; i < 5; ++i) {
			if (input[i].isEmpty())
				return false;
		}
		return true;
	}

	public void loadSignUp() {
		printPageStart();
		System.out.println("회원가입 페이지입니다.");

		if (printBack())
			return;

		System.out.println("가입하시고자 하는 유형을 입력해주십시오.");
		String account_type = getInput(SignInputType.ACCOUNT_TYPE, "1.구매자   2.판매자 \n");
		String[] input = new String[10];
		Arrays.fill(input, "");
		while (true) {
			printPageMiddle();
			System.out.println("입력하고자 하는 사항을 선택해주십시오.");
			System.out.println("1.아이디*  2.비밀번호*  3.이름(성 제외)*  4.성*  5.휴대전화 번호*  6.생년월일  7.성별  8.이메일  9.주소  10.직업");
			System.out.println("해당하는 사항에 알맞게 기입해주십시오. (*는 필수 정보)");
			System.out.println("완료를 원하시면 11를, 종료를 원하시면 12를 입력해주세요");
			System.out.println("그 외의 숫자나 문자는 무시됩니다.");
			printPageMiddle();

			String selection = sc.nextLine();
			if (CheckConditions.isInteger(selection)) {
				int select = Integer.parseInt(selection);
				// select가 정수이지만 사항과 관련없는 정수일경우 다시 시작
				if (select < 1 || select > 12)
					continue;
				// 11은 입력완료를 의미. 따라서 필수정보를 다 기입했는지 확인이 필요함
				else if (select == 11) {
					// 필수정보를 전부 입력한 경우
					if (didFillRequiredInfo(input)) {
						// DB를 업데이트 해준다
						SignDB.signUp(input, account_type);
						System.out.println("회원가입이 완료되었습니다.");
						break;
					}
					// 필수정보를 전부 입력하지 않은 경우
					else {
						printPageMiddle();
						System.out.println("아직 필수정보들을 전부 입력하지 않았습니다. 다시 확인해주세요.");
						printPageMiddle();
						continue;
					}
				}
				// 12는 종료를 의미, 따라서 회원정보 페이지 종료
				else if (select == 12) {
					break;
				}

				input[select - 1] = FillSignUpInfo(select);

				// ID는 이미 존재하는건지 확인이 필요
				while (select == 1 && SignDB.DidExistId(input[select - 1])) {
					printPageMiddle();
					System.out.println("이미 존재하는 ID입니다. 다시 입력해주십시오.");
					input[select - 1] = FillSignUpInfo(select);
					printPageMiddle();
				}
			}
		}
		printPageEnd();
	}
}
