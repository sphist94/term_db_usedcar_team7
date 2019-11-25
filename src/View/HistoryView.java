package View;


import java.util.ArrayList;
import DB.AccountInfoDB;
import DB.HistoryDB;
import DB.VehicleDB;

public class HistoryView extends BasicView {
	public void showHistories(String date, String poid, String id, int account_type) {
		if (account_type == 1) {
			System.out.println("구매날짜: " + date + ", 매물번호: " + poid + ", 판매자: " + VehicleDB.getDealerIdByPoid(poid));
		} else if (account_type == 2) {
			System.out.println("구매날짜: " + date + ", 매물번호: " + poid + ", 구매자: " + id);
		}
	}

	public void loadHistoriesPage(String id) {
		int account_type = AccountInfoDB.getAccountType(id);
		while (true) {
			printPageStart();
			System.out.println("현재 페이지는 히스토리 관련 페이지입니다.");

			if (printBack())
				break;

			System.out.println("현재 계정의 히스토리 내역은 아래와 같습니다.");
			
			ArrayList<String> poids = new ArrayList<>();
			ArrayList<String[]> rs = HistoryDB.getHistories(id, account_type);
			for (int i = 0; i < rs.size(); ++i) {
				String[] temp = rs.get(i);
				poids.add(temp[1]);
				showHistories(temp[0], temp[1], temp[2], account_type);
			}
			if(poids.size() != 0) {
				loadDetailedHistoryPage(poids);
			}
			printPageEnd();
		}
	}

	private void loadDetailedHistoryPage(ArrayList<String> poids) {
		while (true) {
			printPageMiddle();
			System.out.println("더 자세하게 보시고 싶으면 해당 히스토리 번호를 입력해주십시오.");
			System.out.println("뒤로 가시려면 -1를 입력하십시오.");
			String poid = sc.nextLine();
			printPageMiddle();

			if (poid.equals("-1"))
				break;

			// 입력받은 poid가 poids에서 존재하면 디테일 한것을 보여줌
			if (poids.contains(poid)) {
				VehicleView.printDetailedVehicleInfo(poid);
			}
		}
	}
}
