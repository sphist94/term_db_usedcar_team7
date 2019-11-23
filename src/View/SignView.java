package View;

import java.io.Console;
import java.io.IOException;

import Functions.CheckConditions;
import Functions.Utilities;

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

			System.out.print("ID: ");
			String id = sc.nextLine();
			System.out.print("PW: ");
			String password = sc.nextLine();
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
			System.out.println("�����Ͻð��� �ϴ� ������ �Է����ֽʽÿ�.");

			if (printBack())
				break;

			System.out.println("1. ������   2. �Ǹ��� ");
			String account_type;
			while (true) {
				account_type = sc.nextLine();
				if(CheckConditions.isAccountType(account_type))
					break;
				else {
					System.out.println("�������� ���� ���Դϴ�. �ٽ� �Է����ֽʽÿ�.");
				}
			}
			
			System.out.println("�ش��ϴ� ���׿� �˸°� �������ֽʽÿ�. (*�� �ʼ� ����)");
			System.out.println("�Է����� ���� �� \'-\'�� �Է����ֽʽÿ�.");
			System.out.print("*ID: ");
			String id = sc.nextLine();
			System.out.print("*PW: ");
			String pw = sc.nextLine();
			System.out.print("*Lname: ");
			String last_name = sc.nextLine();
			System.out.print("*Fname: ");
			String first_name = sc.nextLine();
			
			
			System.out.print("*Phone(NNN-NNNN-NNNN): ");
			String phone;
			while (true) {
				phone = sc.nextLine();
				if(CheckConditions.isPhoneNumber(account_type))
					break;
				else {
					System.out.println("�������� ���� ���Դϴ�. �ٽ� �Է����ֽʽÿ�.");
				}
			}

			System.out.print("Birthdate(YYYY-MM-DD): ");
			String birthdate = sc.nextLine();
			System.out.print("Gender: (M/F) ");
			String gender;
			while (true) {
				gender = sc.nextLine();
				if(CheckConditions.isGender(account_type))
					break;
				else {
					System.out.println("�������� ���� ���Դϴ�. �ٽ� �Է����ֽʽÿ�.");
				}
			}
			System.out.print("Email: ");
			String email = sc.nextLine();
			System.out.print("Address: ");
			String address = sc.nextLine();
			System.out.print("Occupation: ");
			String occupation = sc.nextLine();
			printPageEnd();

			// TODO:
			// 1. ID �Է½� DB�� �����Ϳ� ���� �̹� �����ϴ� ���̵����� Ȯ���� �ʿ�
			// 2. Phone, Gender, Birthdate�� �Է� ���� ()�� ��ġ���� ������ �ٽ� ����
			// 3. ȸ�������� ���������� ���� �޽����� ���
		}
	}
}
