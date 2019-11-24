package View;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DB.AccountInfoDB;
import DB.HistoryDB;
import DB.VehicleDB;

public class HistoryView extends BasicView {
	public void loadHistoriesPage(String id) {
		int account_type = AccountInfoDB.getAccountType(id);
		while (true) {
			printPageStart();
			System.out.println("���� �������� �����丮 ���� �������Դϴ�.");

			if (printBack())
				break;

			System.out.println("���� ������ �����丮 ������ �Ʒ��� �����ϴ�.");
			// TODO:
			// 1. DB���� �ش� ������ ���� �����丮�� �ҷ���(�Ϸ�)
			// 2. �����丮�� ������ ���ٰ� ����ϰ� ����(�Ϸ�)
			// 3. �����丮�� ������ �����丮���� ��� (showHistories() �̿�)
			ArrayList<String> poids = new ArrayList<>();
			ResultSet rs = HistoryDB.getHistories(id, account_type);
			try {
				if (!rs.next()) {
					System.out.println("�����ϴ� �����丮�� �����ϴ�.");
				} else {
					while (rs.next()) {
						poids.add(rs.getString(2));
						if (account_type == 1) {
							System.out.println("���ų�¥: " + rs.getString(1) + ", �Ź���ȣ: " + rs.getString(2) + ", �Ǹ���: "
									+ VehicleDB.getDealerIdByPoid(rs.getString(2)));
						} else if (account_type == 2) {
							System.out.println("���ų�¥: " + rs.getString(1) + ", �Ź���ȣ: " + rs.getString(2) + ", ������: "
									+ rs.getString(3));
						}
					}
					loadDetailedHistoryPage(poids);
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			printPageEnd();
		}
	}

	private void loadDetailedHistoryPage(ArrayList<String> poids) {
		// TODO:
		// 1. �Է¹��� �Ź���ȣ�� �ڽ��� ����, �Ǹ��̷��� �´��� Ȯ��
		// 2. ������ �ش� �Ź��� ���� �ڼ��� �����͸� ������
		// 3. Ʋ������ ���и޽����� ���;
		while (true) {
			printPageMiddle();
			System.out.println("�� �ڼ��ϰ� ���ð� ������ �ش� �����丮 ��ȣ�� �Է����ֽʽÿ�.");
			String posting_id = sc.nextLine();
			printPageMiddle();

			// �Է¹��� poid�� poids���� �����ϸ� ������ �Ѱ��� ������
			if (poids.contains(posting_id)) {

			}
			break;
		}
	}
}
