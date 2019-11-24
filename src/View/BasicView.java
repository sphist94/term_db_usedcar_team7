package View;

import java.util.Scanner;
import Functions.SignInputType;
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
		final int back_int = 999;
		printPageMiddle();
		System.out.println("이전 페이지로 가시려면 " + Integer.toString(back_int) + "을 입력해주십시오.");
		System.out.println("계속 진행을 원하시면 아무 키나 입력해 주십시오.");
		String back = sc.nextLine();
		printPageMiddle();

		// 입력 값이 -1 값이면 return true
		if (CheckConditions.isInteger(back) && Integer.parseInt(back) == back_int) {
			return true;
		}

		// 그 이외 값이면 return false
		printPageEnd();
		return false;
	}

	public String getInput(SignInputType type, String msg) {
		String str = "";
		while (true) {
			System.out.print(msg);
			str = sc.nextLine();
			if (CheckConditions.checkInputType(str, type)) 
				break;
			System.out.println("잘못된 값을 입력했거나, 반드시 입력해야하는 항목입니다.");
			System.out.println("다시 입력해주십시오.");
		}
		return str;
	}
}
