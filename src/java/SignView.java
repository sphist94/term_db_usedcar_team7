
public class SignView extends View{
//	public enum AccountType{
//		CUSTOMER, DEALER, ADMINISTRATOR
//	};

	public boolean loadSignIn() {
		printPageStart();
		System.out.println("�α��� �������Դϴ�.");
		System.out.print("ID: ");
		String id = sc.next();
		System.out.print("PW: ");
		String pw = sc.next();
		printPageEnd();

		// TODO:
		// 1. �Է� ID�� DB�� ����Ʃ�õ�� ���� �����ϴ��� ��
		// 2. �����Ѵٸ� ��й�ȣ ��ġ�ϴ� ���ϰ�, ��ġ�ϸ� ���� ����(������, �Ǹ���, ������)�� ���� ������ �ε�
		// 3. �Է� ID�� �������� �ʰų� ��й�ȣ�� ��ġ���� �ʴ´ٸ� ���� �޽��� ����ϰ� �ش� ������ ���ε�

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

		return true;
	}

	public boolean loadSignUp() {
		printPageStart();
		System.out.println("ȸ������ �������Դϴ�. �ڷ� ���÷��� 999���� �Է����ֽʽÿ�.");
		System.out.println("�����Ͻð��� �ϴ� ������ �Է����ֽʽÿ�.");
		System.out.println("1. ������   2. �Ǹ��� ");
		int account_type = sc.nextInt();
		if(account_type == 999)
			return false;
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
		if(selection != 1) {
			printPageEnd();
			return true;
		}
		printPageMiddle();
		
		System.out.print("Gender: (M/F) ");
		String gender = sc.next();
		System.out.print("Birthdate(YYYY-MM-DD): ");
		String birthdate = sc.next();
		System.out.print("Email: ");
		String email = sc.next();
		
		//���ۺ���
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
		return true;
	}
}
