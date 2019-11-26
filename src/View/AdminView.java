package View;

import DB.AccountInfoDB;

public class AdminView extends AccountView {
	protected boolean loadAccountPage(String id) {
		boolean isExit = false;
		while (!isExit) {
			printPageStart();
			System.out.println("���� ������ �������� �α����� �Ǿ����ϴ�.");
			System.out.println("���Ͻô� ��ɿ� �´� ���ڸ� �Է����ֽʽÿ�.");
			System.out.println("1.ȸ������  2.�Ź��˻�  3.�α׾ƿ�");
			int selection = sc.nextInt();
			printPageEnd();

			switch (selection) {
			case 1:
				//ȸ��Ż��Ǹ� false�� return �޾� loop�� ����
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
	
	public static boolean doGetOffFakeVehicle(String poid, String id) {
		System.out.println("�ش� �Ź��� �����Ź��� �����ڽ��ϱ�?");
		System.out.println("1.��  2.�ƴϿ�");
		String selection = sc.nextLine();
		if(selection.equals("1") && AccountInfoDB.getOffTheVehicle(id, poid)) {
			System.out.println("�Ź���ȣ  " + poid + " �����Ź��� ó���Ͽ����ϴ�.");
			printToBeContinue();
			return true;
			
		}else {
			System.out.println("�Ź���ȣ  " + poid + " �����Ź� ó���� �����߽��ϴ�.");
			//System.out.println("��� �Ŀ� �ٽ� �̿����ּ���.");
			printToBeContinue();
		}
		return false;
	}
	
	public static boolean doGetOnFakeVehicle(String poid, String id) {
		System.out.println("�ش� �����Ź��� �ٽ� ����Ź��� �ٲٽðڽ��ϱ�?");
		System.out.println("1.��  2.�ƴϿ�");
		String selection = sc.nextLine();
		if(selection.equals("1") && AccountInfoDB.getOnTheVehicle(id, poid)) {
			System.out.println("�Ź���ȣ  " + poid + " ����Ź��� �ٲ���ϴ�.");
			printToBeContinue();
			return true;
			
		}else {
			System.out.println("�Ź���ȣ  " + poid + " ����Ź� ó���� �����߽��ϴ�.");
			//System.out.println("��� �Ŀ� �ٽ� �̿����ּ���.");
			printToBeContinue();
		}
		return false;
	}

}
