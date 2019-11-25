package View;

import java.util.ArrayList;
import java.util.Scanner;

import DB.VehicleDB;
import Functions.SignInputType;
import Functions.VehicleInputType;
import Functions.CheckConditions;

public class BasicView {
	static Scanner sc = new Scanner(System.in);

	public void printPageStart() {
		// System.out.println("PAGE START=================================");
		System.out.println("===========================================");
	}

	public void printPageEnd() {
		System.out.println("===========================================\n");
		// System.out.println("===================================PAGE END");
	}

	public void printPageMiddle() {
		System.out.println("-------------------------------------------");
	}

	public boolean printBack() {
		final int back_int = -1;
		printPageMiddle();
		System.out.println("이전 페이지로 가시려면 " + Integer.toString(back_int) + "을 입력해주십시오.");
		System.out.println("계속 진행을 원하시면 아무 키나 입력해 주십시오.");
		String back = sc.nextLine();
		printPageMiddle();

		// 입력 값이 999값이면 return true
		if (CheckConditions.isInteger(back) && Integer.parseInt(back) == back_int) {
			return true;
		}

		// 그 이외 값이면 return false
		return false;
	}

	public String getInput(SignInputType type, String msg) {
		String str = "";
		while (true) {
			System.out.print(msg);

//			//버퍼에 \n이 존재하면 비움
//			while(sc.hasNextLine())
//				sc.nextLine();
			
			str = sc.nextLine();
			if (CheckConditions.checkInputType(str, type)) 
				break;
			System.out.println("잘못된 값을 입력했거나, 반드시 입력해야하는 항목입니다.");
			System.out.println("다시 입력해주십시오.");
		}
		return str;
	}
	
	public static void printToBeContinue() {
		System.out.println("계속 진행을 원하시면 아무 키나 입력하십시오.");
		sc.nextLine();
	}
	
	protected String getMaker() {
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

	protected String getModel(String maker) {
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

	protected String getDetailedModel(String model) {
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
	
	protected String FillVehicleRegistration(int select) {

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
	
	protected String getInputVehicleInfo(VehicleInputType type, String msg) {
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
	
	protected void showVehicles(String poid, String mileage, String price, String full_maker) {
		System.out.println("매물번호: " + poid + ", 가격: " + price + ", 주행거리: " + mileage + ", 제조사/모델/등급: " + full_maker);
	}
}
