package View;

import Functions.CheckConditions;

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
			System.out.println("1.회원정보  2.매물검색  3.매물등록  4.등록매물 조회 5.로그아웃");
			int selection = sc.nextInt();
			printPageEnd();

			switch (selection) {
			case 1:
				isExit = loadAccountInformationPage(id);
				break;
			case 2:
				loadVehicleSearchPage(id);
				break;
			case 3:
				loadVehicleRegistrationPage(id);
				break;
			case 4:
				loadLookupRegisteredVehicle(id);
				break;
			case 5:
				isExit = signOut();
				break;
			}
		}
		return true;
	}

	//판매자가 자신이 등록한 매물을 보는 함수
	private void loadLookupRegisteredVehicle(String id) {
		ArrayList<String[]> list = VehicleDB.getOwnRegisteredVehicle(id);
		ArrayList<String> poids = new ArrayList<> ();
		String[] meta_info = { "매물번호", "주행거리", "가격", "제조사", "모델", "세부모델" };
		for (int i = 0; i < list.size(); ++i) {
			poids.add(list.get(i)[0]);
			for (int j = 0; j < 6; ++j) {
				System.out.println(meta_info[i] + ": " + list.get(i)[j]);
			}
		}
		
		System.out.println("매물정보를 수정하시겠습니까?");
		System.out.println("해당하는 매물번호를 입력해주십시오.");
		String poid = sc.nextLine();
		if(poids.contains(poid)) {
			//TODO
		}else {
			System.out.println("사용자가 등록하지 않은 매물번호입니다.");
			System.out.println("다시 시도해주십시오.");
		}
		printToBeContinue();
	}
	
//	private void ResetVehicleInfo(String id, String poid) {
//		String[] input = VehicleDB.getVehicleInfoByPoid(poid);
//		ArrayList<String> colors = VehicleDB.getColorsByPoid(poid);
//		ArrayList<String> fuels = VehicleDB.getFuelTypesByPoid(poid);
//		String[] meta_info = { "연식", "차량번호", "주행거리", "가격", "제조사", "모델", "세부모델", "배기량", "변속기", "차종", "색상", "연료" };
//		printPageStart();
//		System.out.println("현재 페이지는 등록차량 정보 수정 페이지입니다.");
//		int Poid = VehicleDB.getTotalNumVehicle() + 1;
//		Arrays.fill(input, "");
//		while (true) {
//			printPageMiddle();
//			System.out.println("입력하고자 하는 사항을 선택해주십시오. 전부 입력해야합니다.");
//			System.out.println("1.연식  2.차량번호  3.주행거리  4.가격  5.제조사  6.모델  7.세부모델  8.배기량  9.변속기  10.차종  11.색상  12.연료");
//			System.out.println("색상이 여러 색상을 가지고 있거나, 연료가 하이브리드일 시 쉼표(,)로 구분해주세요.");
//			System.out.println("완료를 원하시면 13, 종료를 원하시면 14를 입력해주세요");
//			String selection = sc.nextLine();
//			if (CheckConditions.isInteger(selection)) {
//				int select = Integer.parseInt(selection);
//				if (select < 1 || select > 14) {
//					continue;
//				} else if (select == 13) {
//					boolean isDone = true;
//					for (int i = 0; i < 12; ++i) {
//						if (input[i].isEmpty()) {
//							System.out.println("아직 " + meta_info[i] + "를 입력하지 않았습니다.");
//							printToBeContinue();
//							isDone = false;
//							break;
//						}
//					}
//					if (isDone)
//						VehicleDB.updateVehicle(Integer.toString(Poid), id, input, colors, fuels);
//				} else if (select == 14) {
//					break;
//					// 제조사 입력
//				} else if (select == 5) {
//					input[select - 1] = getMaker();
//				} else if (select == 6) {
//					if (input[4].isEmpty()) {
//						System.out.println("먼저 제조사를 입력해야 차량 모델을 입력할 수 있습니다.");
//						printToBeContinue();
//						continue;
//					}
//					input[select - 1] = getModel(input[4]);
//				} else if (select == 7) {
//					if ((input[4].isEmpty() || input[5].isEmpty())) {
//						System.out.println("먼저 제조사 또는 차량 모델을 입력해야 차량 세부모델을 입력할 수 있습니다.");
//						printToBeContinue();
//						continue;
//					}
//					input[select - 1] = getDetailedModel(input[5]);
//				} else if (select >= 8 && select <= 12) {
//
//					System.out.println("현재 입력 가능한 " + meta_info[select - 1] + "입니다.");
//					ArrayList<String> list = null;
//					switch (select) {
//					case 8:
//						list = VehicleDB.getEngineDisplacement();
//						break;
//					case 9:
//						list = VehicleDB.getTransmissionName();
//						break;
//					case 10:
//						list = VehicleDB.getCategoryName();
//						break;
//					case 11:
//						list = VehicleDB.getColorType();
//						break;
//					case 12:
//						list = VehicleDB.getFuelType();
//						break;
//					}
//					for (int i = 0; i < list.size(); ++i) {
//						if (i != list.size() - 1) {
//							System.out.print(list.get(i) + ", ");
//						} else {
//							System.out.println(list.get(i));
//						}
//					}
//
//					while (true) {
//						input[select - 1] = sc.nextLine();
//						boolean ret = false;
//						switch (select) {
//						case 8:
//							ret = CheckConditions.isEngineDisplacement(input[select - 1]);
//							break;
//						case 9:
//							ret = CheckConditions.isTransmission(input[select - 1]);
//							break;
//						case 10:
//							ret = CheckConditions.isCategory(input[select - 1]);
//							break;
//						case 11:
//							colors = Utilities.parseMultiValues(input[select - 1]);
//							ret = CheckConditions.isColorType(input[select - 1]);
//							break;
//						case 12:
//							fuels = Utilities.parseMultiValues(input[select - 1]);
//							ret = CheckConditions.isFuelType(input[select - 1]);
//							break;
//						}
//						if (ret)
//							break;
//						System.out.println("잘못된 값을 입력했습니다. 다시 입력해주십시오.");
//					}
//
//				} else {
//					input[select - 1] = FillVehicleRegistration(select);
//				}
//			}
//			printPageMiddle();
//		}
//		printPageEnd();
//	}

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
