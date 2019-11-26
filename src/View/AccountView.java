package View;

import java.util.Arrays;

import DB.AccountInfoDB;
import Functions.CheckConditions;
import Functions.SignInputType;

abstract public class AccountView extends BasicView {

	abstract protected boolean loadAccountPage(String id);

	protected boolean signOut() {
		return false;
	}

	protected boolean loadAccountInformationPage(String id) {
		boolean isExit = false;
		while (!isExit) {
			printPageStart();
			System.out.println("���� �������� ȸ������ ���� �������Դϴ�.");
			System.out.println("���Ͻô� ��ɿ� �´� ���ڸ� �Է����ֽʽÿ�.");
			System.out.println("1.ȸ������ ����  2.��й�ȣ ����  3.�����丮 ��ȸ  4.ȸ��Ż��");
			System.out.println("���� �������� ���÷��� -1�� �Է����ֽʽÿ�.");
			String selection = sc.nextLine();
			printPageEnd();

			if (!CheckConditions.isInteger(selection))
				continue;

			int select = Integer.parseInt(selection);
			switch (select) {
			case -1:
				return false;
			case 1:
				loadAccountInfoResetPage(id);
				break;
			case 2:
				isExit = loadPasswordResetPage(id);
				break;
			case 3:
				loadHistoryLookupPage(id);
				break;
			case 4:
				isExit = loadWithdrawalPage(id);
				break;
			}
		}
		return true;
	}

	protected boolean loadCheckingByPassword(String id) {
		printPageStart();
		int cnt = 0;
		while (cnt < 3) {
			printPageMiddle();
			System.out.println("����Ȯ���� ���� ��й�ȣ�� �Է����ֽʽÿ�.");
			// ���� ����
			sc.nextLine();
			String password = getInput(SignInputType.PW, "��й�ȣ: ");

			if (AccountInfoDB.isEuqalPassword(id, password)) {
				System.out.println("����Ȯ���� �Ϸ�Ǿ����ϴ�.");
				printPageMiddle();
				printPageEnd();
				return true;
			}

			System.out.println("��й�ȣ�� Ʋ�Ƚ��ϴ�.");
			++cnt;
		}

		System.out.println("��й�ȣ�� 3�� �̻� Ʋ�Ƚ��ϴ�.");
		System.out.println("��� �Ŀ� �ٽ� �õ����ֽʽÿ�.");
		printPageEnd();
		return false;
	}

	private String FillAccountInfoRest(int select) {
		switch (select) {
		case 1:
			return getInput(SignInputType.LNAME, "�̸�: ");
		case 2:
			return getInput(SignInputType.FNAME, "��: ");
		case 3:
			return getInput(SignInputType.PHONE, "�޴���ȭ ��ȣ(NNN-NNNN-NNNN): ");
		case 4:
			return getInput(SignInputType.BIRTHDATE, "����(YYYY-MM-DD): ");
		case 5:
			return getInput(SignInputType.GENDER, "����(M/F): ");
		case 6:
			return getInput(SignInputType.EMAIL, "�̸���: ");
		case 7:
			return getInput(SignInputType.ADDRESS, "�ּ�: ");
		case 8:
			return getInput(SignInputType.OCCUPATION, "����: ");
		default:
			return "";
		}
	}

	protected void loadAccountInfoResetPage(String id) {
		if (!loadCheckingByPassword(id)) {
			return;
		}

		String[] input = new String[8];
		Arrays.fill(input, "");
		while (true) {
			printPageStart();
			System.out.println("���� �������� ȸ������ ���� �������Դϴ�.");
			System.out.println("�Է��ϰ��� �ϴ� ������ �������ֽʽÿ�.");
			System.out.println("�ش��ϴ� ���׿� �˸°� �������ֽʽÿ�.");
			System.out.println("1.�̸�(�� ����)*  2.��  3.�޴���ȭ ��ȣ  4.�������  5.����  6.�̸���  7.�ּ�  8.����");
			System.out.println("�ϷḦ ���Ͻø� 9, ���Ḧ ���Ͻø� 10�� �Է����ּ���");
			System.out.println("�� ���� ���ڳ� ���ڴ� ���õ˴ϴ�.");
			printPageEnd();

			String selection = sc.nextLine();
			if (CheckConditions.isInteger(selection)) {
				int select = Integer.parseInt(selection);
				// select�� ���������� ���װ� ���þ��� �����ϰ�� �ٽ� ����
				if (select < 1 || select > 10) {
					System.out.println("�߸��� ���� �Է��ϼ̽��ϴ�. �ٽ� �Է����ֽʽÿ�.");
					printToBeContinue();
					continue;
				}
				// 9 �� �Է¿ϷḦ �ǹ�
				else if (select == 9) {
					AccountInfoDB.updateAccountInfo(id, input);
					System.out.println("ȸ������ ������ �Ϸ�Ǿ����ϴ�.");
					printToBeContinue();
					break;
				}
				// 10�� ���Ḧ �ǹ�, ���� ȸ������ ���� ������ ����
				else if (select == 10) {
					break;
				}
				input[select - 1] = FillAccountInfoRest(select);
			} else {
				System.out.println("�߸��� ���� �Է��ϼ̽��ϴ�. �ٽ� �Է����ֽʽÿ�.");
				printToBeContinue();
			}
		}
	}

	protected boolean loadPasswordResetPage(String id) {
		if (!loadCheckingByPassword(id)) {
			return false;
		}
		printPageStart();
		int cnt = 0;
		while (cnt < 3) {
			String password1 = getInput(SignInputType.PW, "���� ��й�ȣ: ");
			String password2 = getInput(SignInputType.PW, "���� ��й�ȣ Ȯ��: ");

			if (password1.equals(password2) && AccountInfoDB.updatePassword(id, password1)) {
				System.out.println("��й�ȣ ������ �Ϸ�ƽ��ϴ�.");
				System.out.println("�ٽ� �α��� ���ֽʽÿ�.");
				printToBeContinue();
				printPageEnd();
				return true;
			} else {
				System.out.println("Ȯ�� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
				System.out.println("�ٽ� �Է����ֽʽÿ�.");
			}
			++cnt;
		}
		return false;
	}

	protected boolean loadWithdrawalPage(String id) {
		if (!loadCheckingByPassword(id)) {
			return false;
		}

		printPageStart();
		while (true) {
			printPageMiddle();
			System.out.println("���� �������� ȸ��Ż�� �������Դϴ�.");
			System.out.println("���Ͻô� ��ɿ� �´� ���ڸ� �Է����ֽʽÿ�.");
			System.out.println("������ ȸ��Ż�� �Ͻðڽ��ϱ�?");
			System.out.println("1.��   2.�ƴϿ�");
			System.out.println("");
			String selection = sc.nextLine();
			printPageMiddle();

			if (CheckConditions.isInteger(selection)) {
				int select = Integer.parseInt(selection);
				if (select == 1) {
					AccountInfoDB.WidthDrawal(id);
					System.out.println("ȸ��Ż�� �Ϸ�ƽ��ϴ�. �� ���� �̿����ּż� �����մϴ�.");
					printToBeContinue();
					return true;
				} else if (select == 2)
					break;
				else {
					System.out.println("�߸��� ���� �Է��ϼ̽��ϴ�. �ٽ� �Է����ֽʽÿ�.");
				}
			}
		}

		printPageEnd();
		return false;
	}

	protected boolean loadVehicleSearchPage(String id) {
		VehicleView vv = new VehicleView();
		vv.loadVehicleSearchPage(id);
		return true;
	}

	protected void loadHistoryLookupPage(String id) {
		HistoryView hv = new HistoryView();
		hv.loadHistoriesPage(id);

	}
}
