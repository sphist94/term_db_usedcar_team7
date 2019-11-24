package View;

import Functions.SignInputType;
import DB.VehicleDB;

public class DealerView extends AccountView {
	protected boolean loadAccountPage(String id) {
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
				loadAccountInformationPage(id);
				break;
			case 2:
				loadVehicleSearchPage();
				break;
			case 3:
				loadVehicleRegistrationPage(id);
				break;
			case 4:
				state = signOut();
				break;
			}
		}
		return true;
	}
	
	private String FillVehicleRegistration(int select) {
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
	

	private void loadVehicleRegistrationPage(String id) {
		//insert into VEHICLE (Poid, Deid, Age, Veid, Mileage, Price, Maname, Moname, Dename, Enname, Trname, Caname)
		//Poid, Mileage, Price, Enname: ����, �������� ��Ʈ��
		
		printPageStart();
		int Poid = VehicleDB.getTotalNumVehicle() + 1;
		String[] input = new String[10];
		while (true) {
			printPageMiddle();
			System.out.println("���� �������� ������� �������Դϴ�.");
			System.out.println("�Է��ϰ��� �ϴ� ������ �������ֽʽÿ�. ���� �Է��ؾ��մϴ�.");
			System.out.println("�ش��ϴ� ���׿� �˸°� �������ֽʽÿ�.");
			System.out.println("1.����  2.������ȣ  3.����Ÿ�  4.����  5.������  6.��  7.���θ�  8.��ⷮ  9.���ӱ�  10.����  11.����  12.����");
			System.out.println("�ϷḦ ���Ͻø� 9, ���Ḧ ���Ͻø� 10�� �Է����ּ���");
			System.out.println("�� ���� ���ڳ� ���ڴ� ���õ˴ϴ�.");
			printPageMiddle();
			break;
		}
		printPageEnd();
		
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

		switch (selection) {
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
