package View;

import java.util.ArrayList;

import DB.VehicleDB;
import Functions.CheckConditions;

public class VehicleView extends BasicView {
	public void loadVehicleSearchPage() {
		while (true) {
			printPageStart();
			System.out.println("���� �������� �Ź��˻� ���� �������Դϴ�.");

			if (printBack())
				break;

			System.out.println("���Ͻô� ��ɿ� �´� ���ڸ� �Է����ֽʽÿ�.");
			System.out.println("1.��ü �˻�  2.���� �˻�");
			String selection = sc.nextLine();
			printPageEnd();

			if (!CheckConditions.isInteger(selection))
				continue;

			int select = Integer.parseInt(selection);
			switch (select) {
			case 1:
				loadAllVehicleSearchPage();
				break;
			case 2:
				loadConditionedVehicleSearchPage();
				break;
			}
		}
	}

	// full_maker: ������, ��, ���θ��� ��ģ��
	private void showVehicles(String poid, String mileage, String price, String full_maker) {
		System.out.println("�Ź���ȣ: " + poid + ", ����: " + price + ", ����Ÿ�: " + mileage + "������/��/���: " + full_maker);
	}

	private void loadAllVehicleSearchPage() {
		final int num_list = 10;
		int cnt = 0;
		printPageStart();
		System.out.println("���� �������� ��ü �Ź��˻� �������Դϴ�.");
		System.out.println(Integer.toString(num_list) + "���� �Ź� ����� �˻��˴ϴ�.");
		ArrayList<String[]> list = VehicleDB.getVehicleList();

		// 20���� ����Ʈ�� ����Ѵ�.
		int i = 0;
		while (true) {
			for (i = cnt; i < list.size() && i < cnt + num_list; ++i) {
				String[] temp = list.get(i);
				showVehicles(temp[0], temp[1], temp[2], temp[3] + " " + temp[4] + " " + temp[5]);
			}

			if (cnt >= list.size()) {
				System.out.println("��� �Ź��� �˻� �Ϸ�Ǿ����ϴ�.");
				break;
			}

			printPageMiddle();
			System.out.println("�߰� �˻��Ͻðڽ��ϱ�?");
			System.out.println("1.�� 2.�ƴϿ�");
			System.out.println("3.�����Ź� ���λ����� ���ðڽ��ϱ�?");
			String selection = sc.nextLine();
			if (selection == "1") {
				cnt += num_list;
			} else if (selection == "2") {
				break;
			} else if (selection == "3") {
				loadDetailedVehiclePage();
			}
			printPageMiddle();
		}
		//printPageEnd();

		while (loadDetailedVehiclePage())
			;

	}

	private boolean loadDetailedVehiclePage() {
		while (true) {
			printPageMiddle();
			System.out.println("�� �ڼ��ϰ� ���ð� ������ �ش� �Ź���ȣ�� �Է����ֽʽÿ�.");
			System.out.println("���� ���� ���ڴ� ���õ˴ϴ�.");

			if (printBack())
				break;

			String poid = sc.nextLine();
			printPageMiddle();

			if (!CheckConditions.isInteger(poid))
				continue;

			if (!VehicleDB.isSoldVehicle(poid)) {
				printDetailedVehicleInfo(poid);
				return true;
			} else {
				System.out.println("�̹� ���� �Ϸ�� �Ź��Դϴ�.");
			}
		}
		return false;
	}

	private void loadConditionedVehicleSearchPage() {
		while (true) {
			printPageStart();
			System.out.println("�Ź� ���ǰ˻� �������Դϴ�.");
			System.out.println("�ش��ϴ� ���׿� �˸°� �������ֽʽÿ�.");

			if (printBack())
				break;

			System.out.println("0 �Է½� ���� ����");
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

			// TODO
			// DB���� �ش��ϴ� ������ �Ź��� �ҷ����� ��� ������ �ʿ�

		}
	}

	static public void printDetailedVehicleInfo(String poid) {
		String[] vehicle_info = VehicleDB.getVehicleInfoByPoid(poid);
		ArrayList<String> fuel_info = VehicleDB.getFuelTypesByPoid(poid);
		ArrayList<String> color_info = VehicleDB.getColorsByPoid(poid);

		String[] meta_info = { "�Ź���ȣ", "�Ǹ���", "����", "������ȣ", "����Ÿ�", "������", "��", "���θ�", "��ⷮ", "���ӱ�", "����", "����",
				"����" };

		for (int i = 0; i < meta_info.length; ++i) {
			System.out.print(meta_info[i] + ": ");
			if (i == 11) {
				for (int j = 0; j < color_info.size(); ++j) {
					if (j == color_info.size() - 1) {
						System.out.println(color_info.get(j));
					} else {
						System.out.print(color_info.get(j) + ", ");
					}
				}
			} else if (i == 12) {
				for (int j = 0; j < fuel_info.size(); ++j) {
					if (j == fuel_info.size() - 1) {
						System.out.println(fuel_info.get(j));
					} else {
						System.out.print(fuel_info.get(j) + ", ");
					}
				}
			} else {
				System.out.println(vehicle_info[i]);
			}
		}

		// 11

		while (true) {
			System.out.println("���� �������� ���÷��� -1�� �Է����ֽʽÿ�.");
			String selection = sc.nextLine();
			if (selection.equals("-1"))
				break;
		}
	}
}
