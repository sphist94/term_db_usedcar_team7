package View;
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
				loadVehicleSearchPage();
				break;
			case 3:
				isExit = signOut();
				break;
			}
		}
		return true;
	}

}
