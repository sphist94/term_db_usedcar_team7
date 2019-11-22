package View;

public class SignView extends BasicView {
//	public enum AccountType{
//		CUSTOMER, DEALER, ADMINISTRATOR
//	};

	public void loadSignIn() {
		boolean isSignInSuccess = false;

		// �α��� ���н� ����ؼ� �� �������� �ӹ��鼭 �α����� �õ�
		while (!isSignInSuccess) {
			printPageStart();
			System.out.println("�α��� �������Դϴ�.");
			System.out.println("�ش��ϴ� ���׿� �˸°� �������ֽʽÿ�.");

			if (printBack())
				break;

			System.out.print("ID: ");
			String id = sc.next();
			System.out.print("PW: ");
			String pw = sc.next();
			printPageEnd();

			// TODO:
			// 1. �Է� ID�� DB�� ����Ʃ�õ�� ���� �����ϴ��� ��
			// 2. �����Ѵٸ� ��й�ȣ ��ġ�ϴ� ���ϰ�, ��ġ�ϸ� ���� ����(������, �Ǹ���, ������)�� ���� ������ �ε�
			// 3. �Է� ID�� �������� �ʰų� ��й�ȣ�� ��ġ���� �ʴ´ٸ� ���� �޽��� ����ϰ� �ش� ������ ���ε�
			// if(){
			{
				isSignInSuccess = true;
			}
		}

		if (isSignInSuccess) {
			// int account_type = getAccountType(id);
			int account_type = 1;
			switch (account_type) {
			case 1:
				CustomerView cv = new CustomerView();
				cv.loadAccountPage();
				break;
			case 2:
				DealerView dv = new DealerView();
				dv.loadAccountPage();
				break;
			case 3:
				AdminView av = new AdminView();
				av.loadAccountPage();
				break;
			}
		}
	}

	public void loadSignUp() {
		while (true) {
			printPageStart();
			System.out.println("ȸ������ �������Դϴ�.");
			System.out.println("�����Ͻð��� �ϴ� ������ �Է����ֽʽÿ�.");

			if (printBack())
				break;

			System.out.println("1. ������   2. �Ǹ��� ");
			int account_type = sc.nextInt();
			System.out.println("�ش��ϴ� ���׿� �˸°� �������ֽʽÿ�. (*�� �ʼ� ����)");
			System.out.print("*ID: ");
			String id = sc.next();
			System.out.print("*PW: ");
			String pw = sc.next();
			System.out.print("*Lname: ");
			String last_name = sc.next();
			System.out.print("*Fname: ");
			String first_name = sc.next();
			System.out.print("*Phone(NNN-NNNN-NNNN): ");
			String phone = sc.next();

			printPageMiddle();
			System.out.println("�ʼ� ������ ���� �Է��ϼ̽��ϴ�. �߰� ������ �Է��Ͻðڽ��ϱ�?");
			System.out.println("1.��  2. �ƴϿ�");

			int selection = sc.nextInt();
			if (selection != 1) {
				printPageEnd();
				break;
			}

			printPageMiddle();

			System.out.print("Gender: (M/F) ");
			String gender = sc.next();
			System.out.print("Birthdate(YYYY-MM-DD): ");
			String birthdate = sc.next();
			System.out.print("Email: ");
			String email = sc.next();

			// ���ۺ���
			sc.nextLine();

			System.out.print("Address: ");
			String address = sc.nextLine();
			System.out.print("Occupation: ");
			String occupation = sc.nextLine();
			printPageEnd();

			// TODO:
			// 1. ID �Է½� DB�� �����Ϳ� ���� �̹� �����ϴ� ���̵����� Ȯ���� �ʿ�
			// 2. Phone, Gender, Birthdate�� �Է� ���� ()�� ��ġ���� ������ �ٽ� ����
			// 3. ȸ�������� ���������� ���� �޽����� ���
		}
	}
}
