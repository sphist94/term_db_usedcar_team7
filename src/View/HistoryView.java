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
			System.out.println("판매날짜: " + date + ", 매물번호: " + poid + ", 구매자: " + id);
		} else if (account_type == 3) {
			System.out
					.println("허위매물 처리 날짜: " + date + ", 매물번호: " + poid + ", 판매자: " + VehicleDB.getDealerIdByPoid(poid));
		}
	}

	

	public void loadHistoriesPage(String id) {
		int account_type = AccountInfoDB.getAccountType(id);
		while (true) {
			printPageStart();
			System.out.println("현재 페이지는 히스토리 관련 페이지입니다.");
			System.out.println("현재 계정의 히스토리 내역은 아래와 같습니다.");

			ArrayList<String> poids = new ArrayList<>();
			ArrayList<String[]> rs = HistoryDB.getHistories(id, account_type);

			if (rs.size() == 0) {
				System.out.println("현재 계정의 히스토리 내역은 존재하지 않습니다.");
				System.out.println("이전 페이지로 가시려면 -1를 입력해주십시오.");
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
		System.out.println("더 자세하게 보시고 싶으면 해당 히스토리 번호를 입력해주십시오.");
		System.out.println("이전 페이지로 가시려면 -1를 입력하십시오.");
		String poid = sc.nextLine();
		printPageMiddle();

		if (poid.equals("-1"))
			return false;

		// 입력받은 poid가 poids에서 존재하면 디테일 한것을 보여줌
		// 히스토리의 경우 id를 넘겨서 구매자, 관리자인 경우를 구분하여
		// 매물을 구매하는 경우, 관리자가 허위매물로 내리는 경우를 구분할 필요가 없다.
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
			System.out.println("구매자: " + VehicleDB.getCustmoerByPoid(poid));

		String[] meta_info = { "매물번호", "판매자 아이디", "연식", "차량번호", "주행거리", "제조사", "모델", "세부모델", "배기량", "변속기", "차종", "색상",
				"연료" };

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

		System.out.println("판매자 휴대전화번호: " + AccountInfoDB.getPhoneNumber(vehicle_info[1]));

		if (account_type == 3)
			AdminView.doGetOnFakeVehicle(poid, id);
		printToBeContinue();
	}
}
