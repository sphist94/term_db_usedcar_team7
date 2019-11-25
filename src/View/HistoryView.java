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

			if (printBack())
				break;

			System.out.println("���� ������ �����丮 ������ �Ʒ��� �����ϴ�.");

			ArrayList<String> poids = new ArrayList<>();
			ArrayList<String[]> rs = HistoryDB.getHistories(id, account_type);
			if (rs == null) {
				System.out.println("���� ������ �����丮 ������ �������� �ʽ��ϴ�.\n");
				printToBeContinue();
			} else {
				for (int i = 0; i < rs.size(); ++i) {
					String[] temp = rs.get(i);
					poids.add(temp[1]);
					showHistories(temp[0], temp[1], temp[2], account_type);
				}
				if(account_type == 3) {
					
				}
			}
			if (poids.size() != 0) {
				loadDetailedHistoryPage(poids);
			}
			printPageEnd();
		}
	}

	private void loadDetailedHistoryPage(ArrayList<String> poids) {
		while (true) {
			printPageMiddle();
			System.out.println("�� �ڼ��ϰ� ���ð� ������ �ش� �����丮 ��ȣ�� �Է����ֽʽÿ�.");
			System.out.println("�ڷ� ���÷��� -1�� �Է��Ͻʽÿ�.");
			String poid = sc.nextLine();
			printPageMiddle();

			if (poid.equals("-1"))
				break;

			// �Է¹��� poid�� poids���� �����ϸ� ������ �Ѱ��� ������
			// �����丮�� ��� id�� �Ѱܼ� ������, �������� ��츦 �����Ͽ�
			// �Ź��� �����ϴ� ���, �����ڰ� �����Ź��� ������ ��츦 ������ �ʿ䰡 ����.
			if (poids.contains(poid)) {
				VehicleView.printDetailedVehicleInfo(poid, "");
			}
		}
	}
}
