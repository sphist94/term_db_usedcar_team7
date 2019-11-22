package View;

abstract public class AccountView extends BasicView {

	abstract protected boolean loadAccountPage();

	protected boolean signOut() {
		return false;
	}

	protected void loadAccountInformationPage() {
		while (true) {
			printPageStart();
			System.out.println("���� �������� ȸ������ ���� �������Դϴ�.");
			if (printBack())
				break;
			
			System.out.println("���Ͻô� ��ɿ� �´� ���ڸ� �Է����ֽʽÿ�.");
			System.out.println("1.ȸ������ ����  2.��й�ȣ ����  3.�����丮 ��ȸ  4.ȸ��Ż��");
			int selection = sc.nextInt();
			printPageEnd();

			switch (selection) {
			case 1:
				loadAccountInfoResetPage();
				break;
			case 2:
				loadPasswordResetPage();
				break;
			case 3:
				loadHistoryLookupPage();
				break;
			case 4:
				loadWithdrawalPage();
				break;
			}
		}
	}

	protected void loadCheckingByPassword() {
		printPageStart();
		System.out.println("����Ȯ���� ���� ��й�ȣ�� �Է����ֽʽÿ�.");
		System.out.print("���� ��й�ȣ: ");
		String password = sc.next();
		printPageEnd();

		// TODO:
		// 1. ���� ID�� ���� ��й�ȣ�� �Է� ���� ��й�ȣ�� ������ Ȯ��
	}

	protected void loadAccountInfoResetPage() {
		loadCheckingByPassword();
		printPageStart();
		System.out.println("���� �������� ȸ������ ���� �������Դϴ�.");
		System.out.println("������ ������ ���� �� \'-\'�� �Է����ֽʽÿ�.");

		System.out.print("Lname: ");
		String last_name = sc.next();
		System.out.print("Fname: ");
		String first_name = sc.next();
		System.out.print("Phone(NNN-NNNN-NNNN): ");
		String phone = sc.next();
		System.out.print("Gender: (M/F) ");
		String gender = sc.next();
		System.out.print("Birthdate(YYYY-MM-DD): ");
		String birthdate = sc.next();
		System.out.print("Email: ");
		String email = sc.next();
		sc.nextLine();
		System.out.print("Address: ");
		String address = sc.nextLine();
		System.out.print("Occupation: ");
		String occupation = sc.nextLine();

		System.out.println("\nȸ������ ������ �Ϸ��Ͻðڽ��ϱ�?");
		System.out.println("���Ͻô� ��ɿ� �´� ���ڸ� �Է����ֽʽÿ�.");
		System.out.println("1.��   2.�ƴϿ�");
		int selection = sc.nextInt();
		printPageEnd();

		if (selection == 1) {
			// TODO:
			// last_name ���� occupation ���� '-'�� �ƴ� �����͸� ������Ʈ
		}
	}

	protected void loadPasswordResetPage() {
		loadCheckingByPassword();

		printPageStart();
		System.out.print("���� ��й�ȣ: ");
		String password1 = sc.next();
		System.out.print("���� ��й�ȣ Ȯ��: ");
		String password2 = sc.next();
		printPageEnd();

		if (password1.equals(password2)) {
			// TODO:
			// 1. ���� ��й�ȣ�� Ȯ���� ������ Ȯ��
			// 2. �̻������ DB�� ��й�ȣ �����͸� ���� ��й�ȣ�� ����
			// 3. �̻������� ���� �޽����� ���
		}
	}

	protected void loadWithdrawalPage() {
		loadCheckingByPassword();

		printPageStart();
		System.out.println("���� �������� ȸ��Ż�� �������Դϴ�.");
		System.out.println("���Ͻô� ��ɿ� �´� ���ڸ� �Է����ֽʽÿ�.");
		System.out.println("������ ȸ��Ż�� �Ͻðڽ��ϱ�?");
		System.out.println("1.��   2.�ƴϿ�");
		int selection = sc.nextInt();
		printPageEnd();

		if (selection == 1) {
			// TODO:
			// 1. DB�� �����Ͽ� ȸ��Ż�� �ϴ� ���
		}
	}

	protected boolean loadVehicleSearchPage() {
		VehicleView vv = new VehicleView();
		vv.loadVehicleSearchPage();
		return true;
	}

	protected void loadHistoryLookupPage() {
		HistoryView hv = new HistoryView();
		hv.loadHistoriesPage();

	}
}
