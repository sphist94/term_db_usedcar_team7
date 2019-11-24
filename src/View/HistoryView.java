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
			System.out.println("현재 페이지는 히스토리 관련 페이지입니다.");

			if (printBack())
				break;

			System.out.println("현재 계정의 히스토리 내역은 아래와 같습니다.");
			// TODO:
			// 1. DB에서 해당 계정에 대한 히스토리를 불러옴(완료)
			// 2. 히스토리가 없으면 없다고 출력하고 종료(완료)
			// 3. 히스토리가 있으면 히스토리들을 출력 (showHistories() 이용)
			ArrayList<String> poids = new ArrayList<>();
			ResultSet rs = HistoryDB.getHistories(id, account_type);
			try {
				if (!rs.next()) {
					System.out.println("존재하는 히스토리가 없습니다.");
				} else {
					while (rs.next()) {
						poids.add(rs.getString(2));
						if (account_type == 1) {
							System.out.println("구매날짜: " + rs.getString(1) + ", 매물번호: " + rs.getString(2) + ", 판매자: "
									+ VehicleDB.getDealerIdByPoid(rs.getString(2)));
						} else if (account_type == 2) {
							System.out.println("구매날짜: " + rs.getString(1) + ", 매물번호: " + rs.getString(2) + ", 구매자: "
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
		// 1. 입력받은 매물번호가 자신의 구매, 판매이력이 맞는지 확인
		// 2. 맞으면 해당 매물에 대한 자세히 데이터를 보여줌
		// 3. 틀렸으면 실패메시지를 출력;
		while (true) {
			printPageMiddle();
			System.out.println("더 자세하게 보시고 싶으면 해당 히스토리 번호를 입력해주십시오.");
			String posting_id = sc.nextLine();
			printPageMiddle();

			// 입력받은 poid가 poids에서 존재하면 디테일 한것을 보여줌
			if (poids.contains(posting_id)) {

			}
			break;
		}
	}
}
