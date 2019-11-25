package View;

import java.util.ArrayList;
import java.util.Scanner;

import DB.VehicleDB;
import Functions.SignInputType;
import Functions.VehicleInputType;
import Functions.CheckConditions;

public class BasicView {
	static Scanner sc = new Scanner(System.in);

	public void printPageStart() {
		// System.out.println("PAGE START=================================");
		System.out.println("===========================================");
	}

	public void printPageEnd() {
		System.out.println("===========================================\n");
		// System.out.println("===================================PAGE END");
	}

	public void printPageMiddle() {
		System.out.println("-------------------------------------------");
	}

	public boolean printBack() {
		final int back_int = -1;
		printPageMiddle();
		System.out.println("���� �������� ���÷��� " + Integer.toString(back_int) + "�� �Է����ֽʽÿ�.");
		System.out.println("��� ������ ���Ͻø� �ƹ� Ű�� �Է��� �ֽʽÿ�.");
		String back = sc.nextLine();
		printPageMiddle();

		// �Է� ���� 999���̸� return true
		if (CheckConditions.isInteger(back) && Integer.parseInt(back) == back_int) {
			return true;
		}

		// �� �̿� ���̸� return false
		return false;
	}

	public String getInput(SignInputType type, String msg) {
		String str = "";
		while (true) {
			System.out.print(msg);

//			//���ۿ� \n�� �����ϸ� ���
//			while(sc.hasNextLine())
//				sc.nextLine();
			
			str = sc.nextLine();
			if (CheckConditions.checkInputType(str, type)) 
				break;
			System.out.println("�߸��� ���� �Է��߰ų�, �ݵ�� �Է��ؾ��ϴ� �׸��Դϴ�.");
			System.out.println("�ٽ� �Է����ֽʽÿ�.");
		}
		return str;
	}
	
	public static void printToBeContinue() {
		System.out.println("��� ������ ���Ͻø� �ƹ� Ű�� �Է��Ͻʽÿ�.");
		sc.nextLine();
	}
	
	protected String getMaker() {
		ArrayList<String> maker_list = VehicleDB.getMakers();
		System.out.println("���� �Է� ������ �������Դϴ�.");
		for (int i = 0; i < maker_list.size(); ++i) {
			if (i != maker_list.size() - 1)
				System.out.print(maker_list.get(i) + ", ");
			else
				System.out.println(maker_list.get(i));
		}
		System.out.println("�� �߿��� �Է����ֽʽÿ�");
		while (true) {
			String str = sc.nextLine();
			if (CheckConditions.isMaker(str))
				return str;
			System.out.println("�߸��� ���� �Է��߽��ϴ�. �ٽ� �Է����ֽʽÿ�.");
		}
	}

	protected String getModel(String maker) {
		ArrayList<String> model_list = VehicleDB.getModel(maker);
		System.out.println("���� �Է� ������ ���Դϴ�.");
		for (int i = 0; i < model_list.size(); ++i) {
			if (i != model_list.size() - 1)
				System.out.print(model_list.get(i) + ", ");
			else
				System.out.println(model_list.get(i));
		}
		System.out.println("�� �߿��� �Է����ֽʽÿ�");
		while (true) {
			String str = sc.nextLine();
			if (CheckConditions.isModel(maker, str))
				return str;
			System.out.println("�߸��� ���� �Է��߽��ϴ�. �ٽ� �Է����ֽʽÿ�.");
		}
	}

	protected String getDetailedModel(String model) {
		ArrayList<String> detailedmodel_list = VehicleDB.getDetailedModel(model);
		System.out.println("���� �Է� ������ ���θ��Դϴ�.");
		for (int i = 0; i < detailedmodel_list.size(); ++i) {
			if (i != detailedmodel_list.size() - 1)
				System.out.print(detailedmodel_list.get(i) + ", ");
			else
				System.out.println(detailedmodel_list.get(i));
		}
		System.out.println("�� �߿��� �Է����ֽʽÿ�");
		while (true) {
			String str = sc.nextLine();
			if (CheckConditions.isDetailedModel(model, str))
				return str;
			System.out.println("�߸��� ���� �Է��߽��ϴ�. �ٽ� �Է����ֽʽÿ�.");
		}
	}
	
	protected String FillVehicleRegistration(int select) {

		switch (select) {
		case 1:
			return getInputVehicleInfo(VehicleInputType.AGE, "����(YYYY-MM): ");
		case 2:
			return getInputVehicleInfo(VehicleInputType.VEHICLE_NUMBER, "������ȣ: ");
		case 3:
			return getInputVehicleInfo(VehicleInputType.MILEAGE, "����Ÿ�: ");
		case 4:
			return getInputVehicleInfo(VehicleInputType.PRICE, "����: ");

		default:
			return "";
		}
	}
	
	protected String getInputVehicleInfo(VehicleInputType type, String msg) {
		String str = "";
		while (true) {
			System.out.print(msg);
			str = sc.nextLine();
			if (CheckConditions.checkVehicleInputType(str, type))
				break;
			System.out.println("�߸��� ���� �Է��߽��ϴ�. �ٽ� �Է����ֽʽÿ�.");
		}
		return str;
	}
	
	protected void showVehicles(String poid, String mileage, String price, String full_maker) {
		System.out.println("�Ź���ȣ: " + poid + ", ����: " + price + ", ����Ÿ�: " + mileage + ", ������/��/���: " + full_maker);
	}
}
