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
			System.out.println("���� �������� ȸ������ ���� �������Դϴ�.");
			if (printBack())
				break;

			System.out.println("���Ͻô� ��ɿ� �´� ���ڸ� �Է����ֽʽÿ�.");
			System.out.println("1.ȸ������ ����  2.��й�ȣ ����  3.�����丮 ��ȸ  4.ȸ��Ż��");
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
			System.out.println("����Ȯ���� ���� ��й�ȣ�� �Է����ֽʽÿ�.");
			String password = getInput(SignInputType.PW, "��й�ȣ: ");
			printPageEnd();

			if (AccountInfoDB.isEuqalPassword(id, password)) {
				System.out.println("����Ȯ���� �Ϸ�Ǿ����ϴ�.");
				return true;
			}

			System.out.println("��й�ȣ�� Ʋ�Ƚ��ϴ�.");
			++cnt;
		}

		System.out.println("��й�ȣ�� 3�� �̻� Ʋ�Ƚ��ϴ�.");
		System.out.println("��� �Ŀ� �ٽ� �õ����ֽʽÿ�.");
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
		if (loadCheckingByPassword(id)) {
			return;
		}
		

		String[] input = new String[8];
		Arrays.fill(input, "");
		while (true) {
			printPageStart();
			System.out.println("���� �������� ȸ������ ���� �������Դϴ�.");
			System.out.println("�Է��ϰ��� �ϴ� ������ �������ֽʽÿ�.");
			System.out.println("1.�̸�(�� ����)*  2.��  3.�޴���ȭ ��ȣ  4.�������  5.����  6.�̸���  7.�ּ�  8.����");
			System.out.println("�ش��ϴ� ���׿� �˸°� �������ֽʽÿ�.");
			System.out.println("�ϷḦ ���Ͻø� 9��, ���Ḧ ���Ͻø� 10�� �Է����ּ���");
			System.out.println("�� ���� ���ڳ� ���ڴ� ���õ˴ϴ�.");
			printPageEnd();

			String selection = sc.nextLine();
			if (CheckConditions.isInteger(selection)) {
				int select = Integer.parseInt(selection);
				// select�� ���������� ���װ� ���þ��� �����ϰ�� �ٽ� ����
				if (select < 1 || select > 10) {
					System.out.println("�߸��� ���� �Է��ϼ̽��ϴ�. �ٽ� �Է����ֽʽÿ�.");
					continue;
				}
				// 9 �� �Է¿ϷḦ �ǹ�
				else if (select == 9) {
					AccountInfoDB.updateAccountInfo(id, input);
					System.out.println("ȸ������ ������ �Ϸ�Ǿ����ϴ�.");
					break;
				}
				// 10�� ���Ḧ �ǹ�, ���� ȸ������ ���� ������ ����
				else if (select == 10) {
					break;
				}
				input[select - 1] = FillAccountInfoRest(select);
			}
			else {
				System.out.println("�߸��� ���� �Է��ϼ̽��ϴ�. �ٽ� �Է����ֽʽÿ�.");
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
			System.out.print("���� ��й�ȣ: ");
			String password1 = getInput(SignInputType.PW, "���� ��й�ȣ: ");
			System.out.print("���� ��й�ȣ Ȯ��: ");
			String password2 = getInput(SignInputType.PW, "���� ��й�ȣ Ȯ��: ");
			printPageEnd();

			if (password1.equals(password2) && AccountInfoDB.updatePassword(id, password1)) {
				System.out.println("��й�ȣ ������ �Ϸ�ƽ��ϴ�.");
				return true;
			} else {
				System.out.println("Ȯ�� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
				System.out.println("�ٽ� �Է����ֽʽÿ�.");
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
					break;
				} 
				else if (select == 2) 
					break;
				else {
					System.out.println("�߸��� ���� �Է��ϼ̽��ϴ�. �ٽ� �Է����ֽʽÿ�.");
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
