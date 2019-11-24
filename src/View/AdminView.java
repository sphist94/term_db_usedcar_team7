package View;
public class AdminView extends AccountView {
	protected boolean loadAccountPage(String id) {
		boolean state = true;
		while (state) {
			printPageStart();
			System.out.println("���� �Ǹ��� �������� �α����� �Ǿ����ϴ�.");
			System.out.println("���Ͻô� ��ɿ� �´� ���ڸ� �Է����ֽʽÿ�.");
			System.out.println("1.ȸ������  2.�Ź��˻�  3.�α׾ƿ�");
			int selection = sc.nextInt();
			printPageEnd();

			switch (selection) {
			case 1:
				//ȸ��Ż��Ǹ� false�� return �޾� loop�� ����
				state = loadAccountInformationPage(id);
				break;
			case 2:
				loadVehicleSearchPage();
				break;
			case 3:
				state = signOut();
				break;
			}
		}
		return true;
	}

}
