package View;

import Functions.CheckConditions;
import Functions.VehicleInputType;

import java.util.ArrayList;
import java.util.Arrays;

import DB.VehicleDB;
import Functions.Utilities;

public class DealerView extends AccountView {
	protected boolean loadAccountPage(String id) {
		boolean isExit = false;
		while (!isExit) {
			printPageStart();
			System.out.println("현재 판매자 계정으로 로그인이 되었습니다.");
			System.out.println("원하시는 기능에 맞는 숫자를 입력해주십시오.");
			System.out.println("1.회원정보  2.매물검색  3.매물등록  4.로그아웃");
			int selection = sc.nextInt();
			printPageEnd();

			switch (selection) {
			case 1:
				isExit = loadAccountInformationPage(id);
				break;
			case 2:
				loadVehicleSearchPage();
				break;
			case 3:
				loadVehicleRegistrationPage(id);
				break;
			case 4:
				isExit = signOut();
				break;
			}
		}
		return true;
	}

	private String getInputVehicleInfo(VehicleInputType type, String msg) {
		String str = "";
		while (true) {
			System.out.print(msg);
			str = sc.nextLine();
			if (CheckConditions.checkVehicleInputType(str, type))
				break;
			System.out.println("잘못된 값을 입력했습니다. 다시 입력해주십시오.");
		}
		return str;
	}

	private String FillVehicleRegistration(int select) {

		switch (select) {
		case 1:
			return getInputVehicleInfo(VehicleInputType.AGE, "연식(YYYY-MM): ");
		case 2:
			return getInputVehicleInfo(VehicleInputType.VEHICLE_NUMBER, "차량번호: ");
		case 3:
			return getInputVehicleInfo(VehicleInputType.MILEAGE, "주행거리: ");
		case 4:
			return getInputVehicleInfo(VehicleInputType.PRICE, "가격: ");

		default:
			return "";
		}
	}

	private String getMaker() {
		ArrayList<String> maker_list = VehicleDB.getMakers();
		System.out.println("현재 입력 가능한 제조사입니다.");
		for (int i = 0; i < maker_list.size(); ++i) {
			if (i != maker_list.size() - 1)
				System.out.print(maker_list.get(i) + ", ");
			else
				System.out.println(maker_list.get(i));
		}
		System.out.println("이 중에서 입력해주십시오");
		while (true) {
			String str = sc.nextLine();
			if (CheckConditions.isMaker(str))
				return str;
			System.out.println("잘못된 값을 입력했습니다. 다시 입력해주십시오.");
		}
	}

	private String getModel(String maker) {
		ArrayList<String> model_list = VehicleDB.getModel(maker);
		System.out.println("현재 입력 가능한 모델입니다.");
		for (int i = 0; i < model_list.size(); ++i) {
			if (i != model_list.size() - 1)
				System.out.print(model_list.get(i) + ", ");
			else
				System.out.println(model_list.get(i));
		}
		System.out.println("이 중에서 입력해주십시오");
		while (true) {
			String str = sc.nextLine();
			if (CheckConditions.isModel(maker, str))
				return str;
			System.out.println("잘못된 값을 입력했습니다. 다시 입력해주십시오.");
		}
	}

	private String getDetailedModel(String model) {
		ArrayList<String> detailedmodel_list = VehicleDB.getDetailedModel(model);
		System.out.println("현재 입력 가능한 세부모델입니다.");
		for (int i = 0; i < detailedmodel_list.size(); ++i) {
			if (i != detailedmodel_list.size() - 1)
				System.out.print(detailedmodel_list.get(i) + ", ");
			else
				System.out.println(detailedmodel_list.get(i));
		}
		System.out.println("이 중에서 입력해주십시오");
		while (true) {
			String str = sc.nextLine();
			if (CheckConditions.isDetailedModel(model, str))
				return str;
			System.out.println("잘못된 값을 입력했습니다. 다시 입력해주십시오.");
		}
	}

	private void loadVehicleRegistrationPage(String id) {
		String[] meta_info = { "연식", "차량번호", "주행거리", "가격", "제조사", "모델", "세부모델", "배기량", "변속기", "차종", "색상", "연료" };
		printPageStart();
		int Poid = VehicleDB.getTotalNumVehicle() + 1;
		String[] input = new String[12];
		ArrayList<String> colors = new ArrayList<>();
		ArrayList<String> fuels = new ArrayList<>();
		Arrays.fill(input, "");
		while (true) {
			printPageMiddle();
			System.out.println("현재 페이지는 차량등록 페이지입니다.");
			System.out.println("입력하고자 하는 사항을 선택해주십시오. 전부 입력해야합니다.");
			System.out.println("1.연식  2.차량번호  3.주행거리  4.가격  5.제조사  6.모델  7.세부모델  8.배기량  9.변속기  10.차종  11.색상  12.연료");
			System.out.println("색상이 여러 색상을 가지고 있거나, 연료가 하이브리드일 시 쉼표(,)로 구분해주세요.");
			System.out.println("완료를 원하시면 13, 종료를 원하시면 14를 입력해주세요");
			String selection = sc.nextLine();
			if (CheckConditions.isInteger(selection)) {
				int select = Integer.parseInt(selection);
				if (select < 1 || select > 14) {
					continue;
				} else if (select == 13) {
					boolean isDone = true;
					for (int i = 0; i < 12; ++i) {
						if (input[i].isEmpty()) {
							System.out.println("아직 " + meta_info[i] + "를 입력하지 않았습니다.");
							printToBeContinue();
							isDone = false;
							break;
						}
					}
					if (isDone)
						VehicleDB.updateVehicle(Integer.toString(Poid), id, input, colors, fuels);
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
			printPageMiddle();
		}
		printPageEnd();
	}

}
