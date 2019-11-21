import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		//programState : ���α׷��� ���������� ���������� ǥ��
		boolean programState = true;
		View view = new View();
		while(programState) {
			view.printPageStart();
			System.out.println("   �߰��� �Ǹ� ����Ʈ \"���Ǵ�\"�� ���Ű� ȯ���մϴ�.");
			System.out.println("1. �α���   2. ȸ������  3. ���α׷� ����");
			System.out.println("���Ͻô� ��ɿ� �´� ���ڸ� �Է����ֽʽÿ�.");
			int selection = sc.nextInt();
			view.printPageEnd();
			
			SignView sv = new SignView();
			switch(selection) {
			case 1:
				sv.loadSignIn();
				break;
			case 2:
				sv.loadSignUp();
				break;
			case 3:
				programState = false;
			}
		}
	}

}
