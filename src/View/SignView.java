package View;

import Functions.SignInputType;

public class SignView extends BasicView {
	public void loadSignIn() {
		boolean isSignInSuccess = false;

		// 로그인 실패시 계속해서 이 페이지에 머물면서 로그인을 시도
		while (!isSignInSuccess) {
			printPageStart();
			System.out.println("로그인 페이지입니다.");
			System.out.println("해당하는 사항에 알맞게 기입해주십시오.");

			if (printBack())
				break;

			String id = getInput(SignInputType.ID, "ID: ");
			String password = getInput(SignInputType.PW, "PW: ");
			printPageEnd();

			// TODO:
			// 1. 입력 ID를 DB의 계정튜플들과 비교해 존재하는지 비교
			// 2. 존재한다면 비밀번호 일치하는 비교하고, 일치하면 계정 유형(구매자, 판매자, 관리자)에 따라 페이지 로딩
			// 3. 입력 ID가 존재하지 않거나 비밀번호가 일치하지 않는다면 에러 메시지 출력하고 해당 페이지 리로딩
			// if(){
			{
				isSignInSuccess = true;
			}
		}

		if (isSignInSuccess) {
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
		}
	}

	public void loadSignUp() {
		while (true) {
			printPageStart();
			System.out.println("회원가입 페이지입니다.");
			
			if (printBack())
				break;
			
			System.out.println("가입하시고자 하는 유형을 입력해주십시오.");
			String account_type = getInput(SignInputType.ACCOUNT_TYPE, "1. 구매자   2. 판매자 ");
			System.out.println("해당하는 사항에 알맞게 기입해주십시오. (*는 필수 정보)");
			System.out.println("입력하지 않을 시 - 키를 입력해주십시오.");
			String id = getInput(SignInputType.ID, "*ID: ");
			String pw = getInput(SignInputType.PW, "*PW: ");
			String last_name = getInput(SignInputType.LNAME, "*Lname: ");
			String first_name = getInput(SignInputType.FNAME, "*Fname: ");
			String phone = getInput(SignInputType.PHONE, "*Phone(NNN-NNNN-NNNN): ");
			String birthdate = getInput(SignInputType.BIRTHDATE, "Birthdate(YYYY-MM-DD): ");
			String gender = getInput(SignInputType.GENDER, "Gender: (M/F) ");
			String email = getInput(SignInputType.EMAIL, "Email: ");
			String address = getInput(SignInputType.ADDRESS, "Address: ");
			String occupation = getInput(SignInputType.OCCUPATION, "Occupation: ");
			printPageEnd();

			// TODO:
			// 1. ID 입력시 DB의 데이터와 비교해 이미 존재하는 아이디인지 확인이 필요
			// 2. Phone, Gender, Birthdate의 입력 값이 ()와 일치하지 않으면 다시 수행
			// 3. 회원가입이 성공했으면 성공 메시지를 출력
		}
	}
}
