package View;

import java.util.Scanner;
import Functions.CheckConditions;

public class MainView {
	Scanner sc = new Scanner(System.in);
	public void startPage() {
		boolean programState = true;
		BasicView view = new BasicView();
		
		while (programState) {
			view.printPageStart();
			System.out.println("   �߰��� �Ǹ� ����Ʈ \"���Ǵ�\"�� ���Ű� ȯ���մϴ�.");
			System.out.println("1.�α���  2.ȸ������  3.���α׷� ����  (������ ������ ���õ˴ϴ�.) ");
			System.out.println("���Ͻô� ��ɿ� �´� ���ڸ� �Է����ֽʽÿ�.");
			String selection = sc.nextLine();

			// �Է°��� �������� �ƴ� ��� ���α׷� �ٽ� ����
			if (!CheckConditions.isInteger(selection))
				continue;

			view.printPageEnd();

			SignView sv = new SignView();
			int select = Integer.parseInt(selection);
			switch (select) {
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
