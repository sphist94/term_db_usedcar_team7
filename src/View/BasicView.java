package View;

import java.util.Scanner;
import Functions.CheckConditions;

public class BasicView {
	Scanner sc = new Scanner(System.in);

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
		System.out.println("이전 페이지로 가시려면 "+Integer.toString(back_int)+"을 입력해주십시오.");
		System.out.println("계속 진행을 원하시면 아무 키나 입력해 주십시오.");
		String back = sc.nextLine();
		printPageMiddle();
		
		//입력 값이 -1 값이면  return true
		if(CheckConditions.isInteger(back) && Integer.parseInt(back) == back_int) {
			return true;
		}
		
		//그 이외 값이면 return false
		printPageEnd();
		return false;
	}
}
