package View;
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
	
	private void loadVehicleRegistrationPage() {
		printPageStart();
		System.out.println("������� �������Դϴ�.");
		System.out.println("�ش��ϴ� ���׿� �˸°� �������ֽʽÿ�.");
		System.out.print("������ȣ: ");
		String vehicle_number = sc.next();
		System.out.print("����Ÿ�: ");
		int mileage = sc.nextInt();
		System.out.print("����: ");
		String age = sc.next();
		System.out.print("����: ");
		String price = sc.next();
		System.out.print("������: ");
		String maker = sc.next();
		System.out.print("��: ");
		String model = sc.next();
		System.out.print("���θ�: ");
		String detailed_model = sc.next();
		System.out.print("����: ");
		String category = sc.next();
		System.out.print("��ⷮ: ");
		String engine = sc.next();
		System.out.println("���ӱ�(���� ���ӱ⸦ ������ ������ �޸�(,)�� �����Ͽ� �Է����ֽʽÿ�.): ");
		String transmission = sc.next();
		System.out.println("����(�������� ������ ������ ������ �޸�(,)�� �����Ͽ� �Է����ֽʽÿ�.): ");
		String color = sc.next();
		System.out.println("����(���̺긮�� �����̸� �޸�(,)�� �����Ͽ� �Է����ֽʽÿ�.): ");
		String fuel = sc.next();
		
		printPageMiddle();
		System.out.println("�������� ������ �Ϸ��Ͻðڽ��ϱ�?");
		System.out.println("1.�Ϸ�  2.�ڷΰ���");
		int selection = sc.nextInt();
		printPageMiddle();
		printPageEnd();
		
		switch(selection) {
		case 1:
			// TODO: 
			// 1. �ش� ������ ������ ���������� �����ϰ� �Է��ߴ��� ��
			// 2. ���� ���ӱ�, ����, ������ �� ������ DB�� ���� ��
			break;
		case 2:
			break;
		}
	}
	
	
}
