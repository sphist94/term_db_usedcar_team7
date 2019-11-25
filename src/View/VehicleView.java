package View;

import java.util.ArrayList;

import DB.VehicleDB;
import Functions.CheckConditions;

public class VehicleView extends BasicView {
	public void loadVehicleSearchPage() {
		while (true) {
			printPageStart();
			System.out.println("현재 페이지는 매물검색 관련 페이지입니다.");

			if (printBack())
				break;

			System.out.println("원하시는 기능에 맞는 숫자를 입력해주십시오.");
			System.out.println("1.전체 검색  2.조건 검색");
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

	// full_maker: 제조사, 모델, 세부모델을 합친것
	private void showVehicles(String poid, String mileage, String price, String full_maker) {
		System.out.println("매물번호: " + poid + ", 가격: " + price + ", 주행거리: " + mileage + "제조사/모델/등급: " + full_maker);
	}

	private void loadAllVehicleSearchPage() {
		final int num_list = 10;
		int cnt = 0;
		printPageStart();
		System.out.println("현재 페이지는 전체 매물검색 페이지입니다.");
		System.out.println(Integer.toString(num_list) + "개의 매물 목록이 검색됩니다.");
		ArrayList<String[]> list = VehicleDB.getVehicleList();

		// 20개의 리스트를 출력한다.
		int i = 0;
		while (true) {
			for (i = cnt; i < list.size() && i < cnt + num_list; ++i) {
				String[] temp = list.get(i);
				showVehicles(temp[0], temp[1], temp[2], temp[3] + " " + temp[4] + " " + temp[5]);
			}

			if (cnt >= list.size()) {
				System.out.println("모든 매물이 검색 완료되었습니다.");
				break;
			}

			printPageMiddle();
			System.out.println("추가 검색하시겠습니까?");
			System.out.println("1.예 2.아니오");
			System.out.println("3.차량매물 세부사항을 보시겠습니까?");
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
			System.out.println("더 자세하게 보시고 싶으면 해당 매물번호를 입력해주십시오.");
			System.out.println("숫자 외의 문자는 무시됩니다.");

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
				System.out.println("이미 구매 완료된 매물입니다.");
			}
		}
		return false;
	}

	private void loadConditionedVehicleSearchPage() {
		while (true) {
			printPageStart();
			System.out.println("매물 조건검색 페이지입니다.");
			System.out.println("해당하는 사항에 알맞게 기입해주십시오.");

			if (printBack())
				break;

			System.out.println("0 입력시 조건 없음");
			System.out.print("차량번호: ");
			String vehicle_number = sc.next();
			System.out.print("주행거리: ");
			int mileage = sc.nextInt();
			System.out.print("연식: ");
			String age = sc.next();
			System.out.print("가격: ");
			String price = sc.next();
			System.out.print("제조사: ");
			String maker = sc.next();
			System.out.print("모델: ");
			String model = sc.next();
			System.out.print("세부모델: ");
			String detailed_model = sc.next();
			System.out.print("차종: ");
			String category = sc.next();
			System.out.print("배기량: ");
			String engine = sc.next();
			System.out.println("변속기(여러 변속기를 가지고 있으면 콤마(,)로 구분하여 입력해주십시오.): ");
			String transmission = sc.next();
			System.out.println("색상(여러가지 색상을 가지고 있으면 콤마(,)로 구분하여 입력해주십시오.): ");
			String color = sc.next();
			System.out.println("연료(하이브리드 연료이면 콤마(,)로 구분하여 입력해주십시오.): ");
			String fuel = sc.next();

			printPageMiddle();
			System.out.println("차량정보 기입을 완료하시겠습니까?");
			System.out.println("1.완료  2.뒤로가기");
			int selection = sc.nextInt();
			printPageMiddle();
			printPageEnd();

			// TODO
			// DB에서 해당하는 조건의 매물을 불러오는 기능 구현이 필요

		}
	}

	static public void printDetailedVehicleInfo(String poid) {
		String[] vehicle_info = VehicleDB.getVehicleInfoByPoid(poid);
		ArrayList<String> fuel_info = VehicleDB.getFuelTypesByPoid(poid);
		ArrayList<String> color_info = VehicleDB.getColorsByPoid(poid);

		String[] meta_info = { "매물번호", "판매자", "연식", "차량번호", "주행거리", "제조사", "모델", "세부모델", "배기량", "변속기", "차종", "색상",
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

		// 11

		while (true) {
			System.out.println("이전 페이지로 가시려면 -1를 입력해주십시오.");
			String selection = sc.nextLine();
			if (selection.equals("-1"))
				break;
		}
	}
}
