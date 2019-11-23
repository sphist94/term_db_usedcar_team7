package View;

import Functions.SignInputType;

public class SignView extends BasicView {
	public void loadSignIn() {
		boolean isSignInSuccess = false;

		// �α��� ���н� ����ؼ� �� �������� �ӹ��鼭 �α����� �õ�
		while (!isSignInSuccess) {
			printPageStart();
			System.out.println("�α��� �������Դϴ�.");
			System.out.println("�ش��ϴ� ���׿� �˸°� �������ֽʽÿ�.");

			if (printBack())
				break;

			String id = getInput(SignInputType.ID, "ID: ");
			String password = getInput(SignInputType.PW, "PW: ");
			printPageEnd();

			// TODO:
			// 1. �Է� ID�� DB�� ����Ʃ�õ�� ���� �����ϴ��� ��
			// 2. �����Ѵٸ� ��й�ȣ ��ġ�ϴ� ���ϰ�, ��ġ�ϸ� ���� ����(������, �Ǹ���, ������)�� ���� ������ �ε�
			// 3. �Է� ID�� �������� �ʰų� ��й�ȣ�� ��ġ���� �ʴ´ٸ� ���� �޽��� ����ϰ� �ش� ������ ���ε�
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
			System.out.println("ȸ������ �������Դϴ�.");
			
			if (printBack())
				break;
			
			System.out.println("�����Ͻð��� �ϴ� ������ �Է����ֽʽÿ�.");
			String account_type = getInput(SignInputType.ACCOUNT_TYPE, "1. ������   2. �Ǹ��� ");
			System.out.println("�ش��ϴ� ���׿� �˸°� �������ֽʽÿ�. (*�� �ʼ� ����)");
			System.out.println("�Է����� ���� �� - Ű�� �Է����ֽʽÿ�.");
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
			// 1. ID �Է½� DB�� �����Ϳ� ���� �̹� �����ϴ� ���̵����� Ȯ���� �ʿ�
			// 2. Phone, Gender, Birthdate�� �Է� ���� ()�� ��ġ���� ������ �ٽ� ����
			// 3. ȸ�������� ���������� ���� �޽����� ���
		}
	}
}
