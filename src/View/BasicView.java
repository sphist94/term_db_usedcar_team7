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
		System.out.println("���� �������� ���÷��� "+Integer.toString(back_int)+"�� �Է����ֽʽÿ�.");
		System.out.println("��� ������ ���Ͻø� �ƹ� Ű�� �Է��� �ֽʽÿ�.");
		String back = sc.nextLine();
		printPageMiddle();
		
		//�Է� ���� -1 ���̸�  return true
		if(CheckConditions.isInteger(back) && Integer.parseInt(back) == back_int) {
			return true;
		}
		
		//�� �̿� ���̸� return false
		printPageEnd();
		return false;
	}
}
