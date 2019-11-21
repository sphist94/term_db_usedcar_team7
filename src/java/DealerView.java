public class DealerView extends AccountView {
	protected boolean loadAccountPage() {
		boolean state = true;
		while (state) {
			printPageStart();
			System.out.println("���� �Ǹ��� �������� �α����� �Ǿ����ϴ�.");
			System.out.println("���Ͻô� ��ɿ� �´� ���ڸ� �Է����ֽʽÿ�.");
			System.out.println("1.ȸ������  2.�Ź��˻�  3.�Ź����  4.�α׾ƿ�");
			int selection = sc.nextInt();
			printPageEnd();
			
			switch (selection) {
			case 1:
				loadAccountInformationPage();
				break;
			case 2:
				loadVehicleSearchPage();
				break;
			case 3:
				loadVehicleRegistrationPage();
				break;
			case 4:
				state = signOut();
				break;
			}
		}
		return true;
	}
	
	protected void loadHistoryLookupPage() {
		printPageStart();
		System.out.println("���� �������� �Ǹ��̷� ��ȸ �������Դϴ�.");
		System.out.println("");
		System.out.println("���Ͻô� ��ɿ� �´� ���ڸ� �Է����ֽʽÿ�.");
		System.out.println("������ ȸ��Ż�� �Ͻðڽ��ϱ�?");
		System.out.println("1.��   2.�ƴϿ�");
		int selection = sc.nextInt();
	}
	
	private void loadVehicleRegistrationPage() {
		printPageStart();
		System.out.println("ȸ������ �������Դϴ�. �ڷ� ���÷��� 999���� �Է����ֽʽÿ�.");
		System.out.println("�����Ͻð��� �ϴ� ������ �Է����ֽʽÿ�.");
		System.out.println("1. ������   2. �Ǹ��� ");
		int account_type = sc.nextInt();
//		if(account_type == 999)
//			return false;
		System.out.println("�ش��ϴ� ���׿� �˸°� �������ֽʽÿ�. (*�� �ʼ� ����)");
		System.out.print("*ID: ");
		String id = sc.next();
		System.out.print("*PW: ");
		String pw = sc.next();
		System.out.print("*Lname: ");
		String last_name = sc.next();
		System.out.print("*Fname: ");
		String first_name = sc.next();
		System.out.print("*Phone(NNN-NNNN-NNNN): ");
		String phone = sc.next();
		
		printPageMiddle();
		System.out.println("�ʼ� ������ ���� �Է��ϼ̽��ϴ�. �߰� ������ �Է��Ͻðڽ��ϱ�?");
		System.out.println("1.��  2. �ƴϿ�");
		int selection = sc.nextInt();
//		if(selection != 1) {
//			printPageEnd();
//			return true;
//		}
		printPageMiddle();
		
		System.out.print("Gender: (M/F) ");
		String gender = sc.next();
		System.out.print("Birthdate(YYYY-MM-DD): ");
		String birthdate = sc.next();
		System.out.print("Email: ");
		String email = sc.next();
		
		//���ۺ���
		sc.nextLine();
		
		System.out.print("Address: ");
		String address = sc.nextLine();
		System.out.print("Occupation: ");
		String occupation = sc.nextLine();
		printPageEnd();
	}
	
	
}
