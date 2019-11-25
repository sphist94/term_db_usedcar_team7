package View;

import DB.AccountInfoDB;

public class CustomerView extends AccountView {
	protected boolean loadAccountPage(String id) {
		boolean isExit = false;
		while (!isExit) {
			printPageStart();
			System.out.println("���� �Ǹ��� �������� �α����� �Ǿ����ϴ�.");
			System.out.println("���Ͻô� ��ɿ� �´� ���ڸ� �Է����ֽʽÿ�.");
			System.out.println("1.ȸ������  2.�Ź��˻�  3.�α׾ƿ�");
			int selection = sc.nextInt();
			printPageEnd();

			switch (selection) {
			case 1:
				isExit = loadAccountInformationPage(id);
				break;
			case 2:
				loadVehicleSearchPage(id);
				break;
			case 3:
				isExit = signOut();
				break;
			}
		}
		return true;
	}

	public static boolean doBuyTheVehicle(String poid, String id) {
		System.out.println("�ش� �Ź��� �����Ͻðڽ��ϱ�?");
		System.out.println("1.��  2.�ƴϿ�");
		String selection = sc.nextLine();
		if(selection.equals("1") && AccountInfoDB.buyTheVehicle(id, poid)) {
			System.out.println("�Ź���ȣ  " + poid + " ���Ÿ� �Ϸ��߽��ϴ�.");
			System.out.println("�̿����ּż� �����մϴ�.");
			printToBeContinue();
			return true;
			
		}else {
			System.out.println("�Ź���ȣ  " + poid + " ���ſ� �����߽��ϴ�.");
			System.out.println("��� �Ŀ� �ٽ� �̿����ּ���.");
			printToBeContinue();
		}
		return false;
	}
}
