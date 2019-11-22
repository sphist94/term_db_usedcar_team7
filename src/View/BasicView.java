package View;
import java.util.Scanner;
import Functions.CheckConditions;

public class BasicView {
	Scanner sc = new Scanner(System.in);
	public void printPageStart() {
		//System.out.println("PAGE START=================================");
		System.out.println("===========================================");
	}
	
	public void printPageEnd() {
		System.out.println("===========================================\n");
		//System.out.println("===================================PAGE END");
	}
	
	public void printPageMiddle() {
		System.out.println("-------------------------------------------");
	}
	
	public boolean printBack() {
		printPageMiddle();
		System.out.println("���� �������� ���÷��� -1�� �Է����ֽʽÿ�.");
		System.out.println("��� ������ ���Ͻø� �ƹ� Ű�� �����ֽʽÿ�.");
		String back = sc.nextLine();
		printPageMiddle();
		
		//�Է� ���� -1 ���̸�  return true
		if(CheckConditions.isInteger(back) && Integer.getInteger(back) == -1) {
			return true;
		}
		
		//�� �̿� ���̸� return false
		printPageEnd();
		return false;
	}
}
