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
		System.out.println("���� �������� ���÷��� " + Integer.toString(back_int) + "�� �Է����ֽʽÿ�.");
		System.out.println("��� ������ ���Ͻø� �ƹ� Ű�� �Է��� �ֽʽÿ�.");
		String back = sc.nextLine();
		printPageMiddle();

		// �Է� ���� -1 ���̸� return true
		if (CheckConditions.isInteger(back) && Integer.parseInt(back) == back_int) {
			return true;
		}

		// �� �̿� ���̸� return false
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
			System.out.println("�߸��� ���� �Է��߰ų�, �ݵ�� �Է��ؾ��ϴ� �׸��Դϴ�.");
			System.out.println("�ٽ� �Է����ֽʽÿ�.");
		}
		return str;
	}
}
