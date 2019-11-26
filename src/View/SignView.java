package View;

import Functions.CheckConditions;
import Functions.SignInputType;

import java.util.Arrays;

import DB.AccountInfoDB;
import DB.AccountType;
import DB.SignDB;

public class SignView extends BasicView {

	public void loadSignIn() {
		boolean isSignInSuccess = false;
		// �α��� ���н� ����ؼ� �� �������� �ӹ��鼭 �α����� �õ�
		printPageStart();
		String id = "";
		String password = "";
		while (!isSignInSuccess) {
			System.out.println("�α��� �������Դϴ�.");
			System.out.println("���� �������� ���÷��� ID�� -1�� �Է����ֽʽÿ�.");
			System.out.print("���̵�: ");
			id = sc.nextLine();
			if (id.equals("-1")) {
				printPageEnd();
				return;
			}
			System.out.print("��й�ȣ: ");
			password = sc.nextLine();

			// SignDB.Login�� ID�� PW�� �Ѱ��ָ�
			if (SignDB.login(id, password)) {
				printPageEnd();
				isSignInSuccess = true;
			} else {
				printPageMiddle();
				System.out.println("�������� �ʴ� ID�̰ų� Ʋ�� ��й�ȣ�Դϴ�.");
				printToBeContinue();
				printPageMiddle();
			}
		}

		// account_type�� ���� �� ȣ��
		AccountType account_type = AccountInfoDB.getAccountType(id);
		if (isSignInSuccess) {
			switch (account_type) {
			case CUSTOMER:
				CustomerView cv = new CustomerView();
				cv.loadAccountPage(id);
				break;
			case DEALER:
				DealerView dv = new DealerView();
				dv.loadAccountPage(id);
				break;
			case ADMINISTRATOR:
				AdminView av = new AdminView();
				av.loadAccountPage(id);
				break;
			default:
				break;
			}
		}
	}

	private String FillSignUpInfo(int select) {
		switch (select) {
		case 1:
			return getInput(SignInputType.ID, "*���̵�: ");
		case 2:
			return getInput(SignInputType.PW, "*��й�ȣ: ");
		case 3:
			return getInput(SignInputType.LNAME, "*�̸�: ");
		case 4:
			return getInput(SignInputType.FNAME, "*��: ");
		case 5:
			return getInput(SignInputType.PHONE, "*�޴���ȭ ��ȣ(NNN-NNNN-NNNN): ");
		case 6:
			return getInput(SignInputType.BIRTHDATE, "�������(YYYY-MM-DD): ");
		case 7:
			return getInput(SignInputType.GENDER, "����(M/F): ");
		case 8:
			return getInput(SignInputType.EMAIL, "�̸���: ");
		case 9:
			return getInput(SignInputType.ADDRESS, "�ּ�: ");
		case 10:
			return getInput(SignInputType.OCCUPATION, "����: ");
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
		System.out.println("�����Ͻð��� �ϴ� ������ �Է����ֽʽÿ�.");
		String account_type = getInput(SignInputType.ACCOUNT_TYPE, "1.������   2.�Ǹ��� \n");
		String[] input = new String[10];
		Arrays.fill(input, "");
		while (true) {
			printPageMiddle();
			System.out.println("�Է��ϰ��� �ϴ� ������ �������ֽʽÿ� (*�� �ʼ� ����).");
			System.out.println("1.���̵�*  2.��й�ȣ*  3.�̸�(�� ����)*  4.��*  5.�޴���ȭ ��ȣ*  6.�������  7.����  8.�̸���  9.�ּ�  10.����");
			System.out.println("�ϷḦ ���Ͻø� 11��, ���Ḧ ���Ͻø� 12�� �Է����ּ���");
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
						System.out.println("ȸ�������� �Ϸ�Ǿ����ϴ�.");
						printToBeContinue();
						break;
					}
					// �ʼ������� ���� �Է����� ���� ���
					else {
						printPageMiddle();
						System.out.println("���� �ʼ��������� ���� �Է����� �ʾҽ��ϴ�. �ٽ� Ȯ�����ֽʽÿ�.");
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
					System.out.println("�̹� �����ϴ� ID�Դϴ�.");
					printToBeContinue();
					input[select - 1] = FillSignUpInfo(select);
					printPageMiddle();
				}
			}
		}
		printPageEnd();
	}
}
