package View;

import java.util.ArrayList;
import DB.AccountInfoDB;
import DB.HistoryDB;
import DB.VehicleDB;

public class HistoryView extends BasicView {
	public void showHistories(String date, String poid, String id, int account_type) {
		if (account_type == 1) {
			System.out.println("���ų�¥: " + date + ", �Ź���ȣ: " + poid + ", �Ǹ���: " + VehicleDB.getDealerIdByPoid(poid));
		} else if (account_type == 2) {
			System.out.println("�Ǹų�¥: " + date + ", �Ź���ȣ: " + poid + ", ������: " + id);
		} else if (account_type == 3) {
			System.out
					.println("�����Ź� ó�� ��¥: " + date + ", �Ź���ȣ: " + poid + ", �Ǹ���: " + VehicleDB.getDealerIdByPoid(poid));
		}
	}

	

	public void loadHistoriesPage(String id) {
		int account_type = AccountInfoDB.getAccountType(id);
		while (true) {
			printPageStart();
			System.out.println("���� �������� �����丮 ���� �������Դϴ�.");
			System.out.println("���� ������ �����丮 ������ �Ʒ��� �����ϴ�.");

			ArrayList<String> poids = new ArrayList<>();
			ArrayList<String[]> rs = HistoryDB.getHistories(id, account_type);

			if (rs.size() == 0) {
				System.out.println("���� ������ �����丮 ������ �������� �ʽ��ϴ�.");
				System.out.println("���� �������� ���÷��� -1�� �Է����ֽʽÿ�.");
				if (sc.nextLine().equals("-1"))
					break;
			} else {
				for (int i = 0; i < rs.size(); ++i) {
					String[] temp = rs.get(i);
					poids.add(temp[1]);
					showHistories(temp[0], temp[1], temp[2], account_type);
				}
			}
			if (poids.size() != 0)
				loadDetailedHistoryPage(id, poids, account_type);
			printPageEnd();
		}
	}

	private boolean loadDetailedHistoryPage(String id, ArrayList<String> poids, int account_type) {
		printPageMiddle();
		System.out.println("�� �ڼ��ϰ� ���ð� ������ �ش� �����丮 ��ȣ�� �Է����ֽʽÿ�.");
		System.out.println("���� �������� ���÷��� -1�� �Է��Ͻʽÿ�.");
		String poid = sc.nextLine();
		printPageMiddle();

		if (poid.equals("-1"))
			return false;

		// �Է¹��� poid�� poids���� �����ϸ� ������ �Ѱ��� ������
		// �����丮�� ��� id�� �Ѱܼ� ������, �������� ��츦 �����Ͽ�
		// �Ź��� �����ϴ� ���, �����ڰ� �����Ź��� ������ ��츦 ������ �ʿ䰡 ����.
		if (poids.contains(poid)) {
			printDetailedHistoryInfo(id, poid, account_type);
		}
		return true;
	}

	private void printDetailedHistoryInfo(String id, String poid, int account_type) {
		String[] vehicle_info = VehicleDB.getVehicleInfoByPoid(poid);
		ArrayList<String> fuel_info = VehicleDB.getFuelTypesByPoid(poid);
		ArrayList<String> color_info = VehicleDB.getColorsByPoid(poid);

		if (account_type == 2)
			System.out.println("������: " + VehicleDB.getCustmoerByPoid(poid));

		String[] meta_info = { "�Ź���ȣ", "�Ǹ��� ���̵�", "����", "������ȣ", "����Ÿ�", "������", "��", "���θ�", "��ⷮ", "���ӱ�", "����", "����",
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

		System.out.println("�Ǹ��� �޴���ȭ��ȣ: " + AccountInfoDB.getPhoneNumber(vehicle_info[1]));

		if (account_type == 3)
			AdminView.doGetOnFakeVehicle(poid, id);
		printToBeContinue();
	}
}
