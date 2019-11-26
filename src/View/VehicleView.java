package View;

import java.util.ArrayList;
import java.util.Arrays;

import DB.AccountInfoDB;
import DB.AccountType;
import DB.VehicleDB;
import Functions.CheckConditions;
import Functions.Utilities;

public class VehicleView extends BasicView {
	public void loadVehicleSearchPage(String id) {
		while (true) {
			printPageStart();
			System.out.println("현재 페이지는 매물검색 관련 페이지입니다.");
			System.out.println("원하시는 기능에 맞는 숫자를 입력해주십시오.");
			System.out.println("1.전체 검색  2.조건 검색");
			System.out.println("이전 페이지로 가시려면 -1를 입력해주십시오.");
			String selection = sc.nextLine();
			printPageEnd();

			if (!CheckConditions.isInteger(selection))
				continue;

			int select = Integer.parseInt(selection);
			switch (select) {
			case -1:
				return;
			case 1:
				loadAllVehicleSearchPage(id);
				break;
			case 2:
				loadConditionedVehicleSearchPage(id);
				break;
			}
		}
	}

	private void loadAllVehicleSearchPage(String id) {
		final int num_list = 10;
		int cnt = 0;
		
		printPageStart();
		System.out.println("현재 페이지는 전체 매물검색 페이지입니다.");
		System.out.println(Integer.toString(num_list) + "개의 매물 목록이 검색됩니다.");
		ArrayList<String[]> list = VehicleDB.getVehicleList();

		// 10개의 리스트를 출력한다.
		int start = 0;
		while (true) {
			for (int i = start; i < list.size() && i < start + num_list; ++i) {
				String[] temp = list.get(i);
				showVehicles(temp[0], temp[1], temp[2], temp[3] + " " + temp[4] + " " + temp[5]);
			}

			if (cnt >= list.size()) {
				System.out.println("모든 매물이 검색 완료되었습니다.");
				break;
			}

			printPageMiddle();
			System.out.println("추가 검색하시겠습니까?");
			System.out.println("1.예  2.아니오  3.차량매물 세부사항을 보시겠습니까?");
			String selection = sc.nextLine();
			if (selection.equals("1")) {
				start += num_list;
			} else if (selection.equals("2")) {
				break;
			} else if (selection.equals("3")) {
				loadDetailedVehiclePage(id);
			}
			printPageMiddle();
		}
		printPageEnd();
	}

	private boolean loadDetailedVehiclePage(String id) {
		while (true) {
			System.out.println("더 자세하게 보시고 싶으면 해당 매물번호를 입력해주십시오.");
			System.out.println("이전 페이지로 가시려면 -1를 입력해주십시오.");
			String poid = sc.nextLine();

			if (!CheckConditions.isInteger(poid) || poid.equals("-1"))
				break;

			if (!VehicleDB.isSoldVehicle(poid)) {
				printDetailedVehicleInfo(poid, id);
				return true;
			} else {
				System.out.println("이미 구매 완료된 매물입니다.");
			}
		}
		return false;
	}

	private void loadConditionedVehicleSearchPage(String id) {
		String[] meta_info = { "연식", "차량번호", "주행거리", "가격", "제조사", "모델", "세부모델", "배기량", "변속기", "차종", "색상", "연료" };
		ArrayList<String> colors = new ArrayList<>();
		ArrayList<String> fuels = new ArrayList<>();
		ArrayList<String[]> conditioned_list = new ArrayList<>();
		String[] input = new String[12];
		Arrays.fill(input, "");
		while (true) {
			printPageStart();
			System.out.println("매물 조건검색 페이지입니다.");
			System.out.println("해당하는 사항에 알맞게 기입해주십시오.");
			System.out.println("1.연식  2.차량번호  3.주행거리  4.가격  5.제조사  6.모델  7.세부모델  8.배기량  9.변속기  10.차종  11.색상  12.연료");
			System.out.println("완료를 원하시면 13, 종료를 원하시면 14를 입력해주세요");
			String selection = sc.nextLine();
			if (CheckConditions.isInteger(selection)) {
				int select = Integer.parseInt(selection);
				if (select < 1 || select > 14) {
					continue;
				} else if (select == 13) {
					conditioned_list = VehicleDB.getConditionedVehicleList(input, colors, fuels);
					break;
				} else if (select == 14) {
					break;
					// 제조사 입력
				} else if (select == 5) {
					input[select - 1] = getMaker();
				} else if (select == 6) {
					if (input[4].isEmpty()) {
						System.out.println("먼저 제조사를 입력해야 차량 모델을 입력할 수 있습니다.");
						printToBeContinue();
						continue;
					}
					input[select - 1] = getModel(input[4]);
				} else if (select == 7) {
					if ((input[4].isEmpty() || input[5].isEmpty())) {
						System.out.println("먼저 제조사 또는 차량 모델을 입력해야 차량 세부모델을 입력할 수 있습니다.");
						printToBeContinue();
						continue;
					}
					input[select - 1] = getDetailedModel(input[5]);
				} else if (select >= 8 && select <= 12) {

					System.out.println("현재 입력 가능한 " + meta_info[select - 1] + "입니다.");
					ArrayList<String> list = null;
					switch (select) {
					case 8:
						list = VehicleDB.getEngineDisplacement();
						break;
					case 9:
						list = VehicleDB.getTransmissionName();
						break;
					case 10:
						list = VehicleDB.getCategoryName();
						break;
					case 11:
						list = VehicleDB.getColorType();
						break;
					case 12:
						list = VehicleDB.getFuelType();
						break;
					}
					for (int i = 0; i < list.size(); ++i) {
						if (i != list.size() - 1) {
							System.out.print(list.get(i) + ", ");
						} else {
							System.out.println(list.get(i));
						}
					}

					while (true) {
						input[select - 1] = sc.nextLine();
						boolean ret = false;
						switch (select) {
						case 8:
							ret = CheckConditions.isEngineDisplacement(input[select - 1]);
							break;
						case 9:
							ret = CheckConditions.isTransmission(input[select - 1]);
							break;
						case 10:
							ret = CheckConditions.isCategory(input[select - 1]);
							break;
						case 11:
							colors = Utilities.parseMultiValues(input[select - 1]);
							ret = CheckConditions.isColorType(input[select - 1]);
							break;
						case 12:
							fuels = Utilities.parseMultiValues(input[select - 1]);
							ret = CheckConditions.isFuelType(input[select - 1]);
							break;
						}
						if (ret)
							break;
						System.out.println("잘못된 값을 입력했습니다. 다시 입력해주십시오.");
					}

				} else {
					input[select - 1] = FillVehicleRegistration(select);
				}
			}
		}
		printConditionedSearch(conditioned_list, id);
		printPageEnd();
	}

	private void printConditionedSearch(ArrayList<String[]> list, String id) {
		final int num_list = 10;
		int cnt = 0;

		// 10개의 리스트를 출력한다.
		int start = 0;
		while (true) {
			for (int i = start; i < list.size() && i < start + num_list; ++i) {
				String[] temp = list.get(i);
				showVehicles(temp[0], temp[1], temp[2], temp[3] + " " + temp[4] + " " + temp[5]);
			}

			if (cnt >= list.size()) {
				System.out.println("모든 매물이 검색 완료되었습니다.");
				break;
			}

			printPageMiddle();
			System.out.println("추가 검색하시겠습니까?");
			System.out.println("1.예  2.아니오  3.차량매물 세부사항을 보시겠습니까?");
			String selection = sc.nextLine();
			if (selection.equals("1")) {
				start += num_list;
			} else if (selection.equals("2")) {
				break;
			} else if (selection.equals("3")) {
				loadDetailedVehiclePage(id);
			}
			printPageMiddle();
		}
		// printPageEnd();
	}

	// 차량매물 세부사항으로 들어갔을때
	static public void printDetailedVehicleInfo(String poid, String id) {
		String[] vehicle_info = VehicleDB.getVehicleInfoByPoid(poid);
		ArrayList<String> fuel_info = VehicleDB.getFuelTypesByPoid(poid);
		ArrayList<String> color_info = VehicleDB.getColorsByPoid(poid);

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

		AccountType account_type = AccountInfoDB.getAccountType(id);
		switch (account_type) {
		case CUSTOMER:
			CustomerView.doBuyTheVehicle(poid, id);
			break;
		case ADMINISTRATOR:
			AdminView.doGetOffFakeVehicle(poid, id);
			break;
		default:
		}
		printToBeContinue();
	}
}


