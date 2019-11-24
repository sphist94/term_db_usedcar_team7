package View;

import Functions.CheckConditions;
import Functions.SignInputType;

import java.util.Arrays;

import DB.SignDB;

public class SignView extends BasicView {
	public void loadSignIn() {
		boolean isSignInSuccess = false;
		int account_type = 0;
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

			// SignDB.Login�� ID�� PW�� �Ѱ��ָ�
			// DB�� �񱳸� �ؼ� �ִ� ������ account_type�� ��ȯ���ش�.
			// ���� account_type�� 0�̸� ���� ����, 1: ������, 2: �Ǹ���, 3: ������
			if ((account_type = SignDB.login(id, password)) != 0) {
				isSignInSuccess = true;
			} else {
				printPageMiddle();
				System.out.println("�������� �ʴ� ID�̰ų� Ʋ�� ��й�ȣ�Դϴ�.");
				System.out.println("�ٽ� �Է����ֽʽÿ�.");
				printPageMiddle();
			}
		}

		// account_type�� ���� �� ȣ��
		if (isSignInSuccess) {
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

	private String FillSignUpInfo(int select) {
		switch (select) {
		case 1:
			return getInput(SignInputType.ID, "*ID: ");
		case 2:
			return getInput(SignInputType.PW, "*PW: ");
		case 3:
			return getInput(SignInputType.LNAME, "*Lname: ");
		case 4:
			return getInput(SignInputType.FNAME, "*Fname: ");
		case 5:
			return getInput(SignInputType.PHONE, "*Phone(NNN-NNNN-NNNN): ");
		case 6:
			return getInput(SignInputType.BIRTHDATE, "Birthdate(YYYY-MM-DD): ");
		case 7:
			return getInput(SignInputType.GENDER, "Gender: (M/F) ");
		case 8:
			return getInput(SignInputType.EMAIL, "Email: ");
		case 9:
			return getInput(SignInputType.ADDRESS, "Address: ");
		case 10:
			return getInput(SignInputType.OCCUPATION, "Occupation: ");
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
		System.out.println("ȸ������ �������Դϴ�.");

		if (printBack())
			return;

		System.out.println("�����Ͻð��� �ϴ� ������ �Է����ֽʽÿ�.");
		String account_type = getInput(SignInputType.ACCOUNT_TYPE, "1.������   2.�Ǹ��� \n");
		String[] input = new String[10];
		Arrays.fill(input, "");
		while (true) {
			printPageMiddle();
			System.out.println("�Է��ϰ��� �ϴ� ������ �������ֽʽÿ�.");
			System.out.println("1.���̵�*  2.��й�ȣ*  3.��*  4.�̸�*  5.�޴���ȭ ��ȣ*  6.�������  7.����  8.�̸���  9.�ּ�  10.����");
			System.out.println("�ش��ϴ� ���׿� �˸°� �������ֽʽÿ�. (*�� �ʼ� ����)");
			System.out.println("�ϷḦ ���Ͻø� 11��, ���Ḧ ���Ͻø� 12�� �Է����ּ���");
			System.out.println("�� ���� ���ڳ� ���ڴ� ���õ˴ϴ�.");
			printPageMiddle();

			String selection = sc.nextLine();
			if (CheckConditions.isInteger(selection)) {
				int select = Integer.parseInt(selection);
				// select�� ���������� ���װ� ���þ��� �����ϰ�� �ٽ� ����
				if (select < 1 || select > 12)
					continue;
				// 11�� �Է¿ϷḦ �ǹ�. ���� �ʼ������� �� �����ߴ��� Ȯ���� �ʿ���
				else if (select == 11) {
					// �ʼ������� ���� �Է��� ���
					if (didFillRequiredInfo(input)) {
						// DB�� ������Ʈ ���ش�
						SignDB.signUp(input, account_type);
						break;
					}
					// �ʼ������� ���� �Է����� ���� ���
					else {
						printPageMiddle();
						System.out.println("���� �ʼ��������� ���� �Է����� �ʾҽ��ϴ�. �ٽ� Ȯ�����ּ���.");
						System.out.println("ȸ�������� �Ϸ�Ǿ����ϴ�.");
						printPageMiddle();
						continue;
					}
				}
				// 12�� ���Ḧ �ǹ�, ���� ȸ������ ������ ����
				else if (select == 12) {
					break;
				}

				input[select - 1] = FillSignUpInfo(select);

				// ID�� �̹� �����ϴ°��� Ȯ���� �ʿ�
				while (select == 1 && SignDB.DidExistId(input[select - 1])) {
					printPageMiddle();
					System.out.println("�̹� �����ϴ� ID�Դϴ�. �ٽ� �Է����ֽʽÿ�.");
					input[select - 1] = FillSignUpInfo(select);
					printPageMiddle();
				}
			}
		}
		printPageEnd();
	}
}
