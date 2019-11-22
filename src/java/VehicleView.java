
public class VehicleView extends View {
	public void loadVehicleSearchPage() {
		printPageStart();
		System.out.println("���� �������� �Ź��˻� ���� �������Դϴ�.");
		System.out.println("���Ͻô� ��ɿ� �´� ���ڸ� �Է����ֽʽÿ�.");
		System.out.println("1.��ü �˻�  2.���� �˻�");
		System.out.println("�ڷ� ���÷��� 999�� �Է����ֽʽÿ�.");
		int selection = sc.nextInt();
		printPageEnd();

		switch (selection) {
		case 1:
			loadAllVehicleSearchPage();
			break;
		case 2:
			loadConditionedVehicleSearchPage();
			break;
		}
	}
	
	//full_maker: ������, ��, ���θ��� ��ģ��
	private void showVehicles(int poid, int mileage, int price, String full_maker) {
		System.out.println("�Ź���ȣ: " + poid + ", ����: " + price + ", ����Ÿ�: "+mileage+"����: "+full_maker);
	}
	
	private void loadAdditionVehicleSearchPage() {
		printPageStart();
		System.out.println("�߰� �˻��Ͻðڽ��ϱ�?");
		System.out.println("���׿� �˸°� �������ֽʽÿ�.");
		System.out.print("�˻� ����(�ּ�0��, �ִ� 50��, �� ���� ������ �˻����� ������ �ǹ�): ");
		int num_vehicle = sc.nextInt();
		printPageEnd();
	}

	private void loadAllVehicleSearchPage() {
		printPageStart();
		System.out.println("���� �������� ��ü �Ź��˻� �������Դϴ�.");
		System.out.println("���׿� �˸°� �������ֽʽÿ�.");
		System.out.print("�˻� ����(�ּ�0��, �ִ� 50��, �� ���� ������ �˻����� ������ �ǹ�): ");
		int num_vehicle = sc.nextInt();
		printPageEnd();
		
		if(num_vehicle <= 50 && num_vehicle > 0 ) {
			//TODO: �ش��ϴ� ������ŭ DB���� �ҷ���
		}
		
		//TODO: ����ؼ� �����ϰԲ� �����, ���̻� �Ź��� ���� �� �׸����
		loadAdditionVehicleSearchPage();
		
		
		loadDetailedVehiclePage();
	}


	private boolean loadDetailedVehiclePage() {
		System.out.println("�� �ڼ��ϰ� ���ð� ������ �ش� �Ź���ȣ�� �Է����ֽʽÿ�.");
		System.out.println("�ڷΰ��ð� ������ 999�� �Է����ֽʽÿ�.");
		int posting_id = sc.nextInt();
		printPageEnd();
		
		if(posting_id == 999) {
			return false;
		}
		else {
		// TODO:
		// 1. �Է¹��� �Ź���ȣ�� �ڽ��� ����, �Ǹ��̷��� �´��� Ȯ��
		// 2. ������ �ش� �Ź��� ���� �ڼ��� �����͸� ������
		// 3. Ʋ������ ���и޽����� ���
		}
		
		return true;
		
	}
	
	private void loadConditionedVehicleSearchPage() {
		printPageStart();
		System.out.println("�Ź� ���ǰ˻� �������Դϴ�.");
		System.out.println("�ش��ϴ� ���׿� �˸°� �������ֽʽÿ�.");
		System.out.println("0 �Է½� ���� ����");
		System.out.print("������ȣ: ");
		String vehicle_number = sc.next();
		System.out.print("����Ÿ�: ");
		int mileage = sc.nextInt();
		System.out.print("����: ");
		String age = sc.next();
		System.out.print("����: ");
		String price = sc.next();
		System.out.print("������: ");
		String maker = sc.next();
		System.out.print("��: ");
		String model = sc.next();
		System.out.print("���θ�: ");
		String detailed_model = sc.next();
		System.out.print("����: ");
		String category = sc.next();
		System.out.print("��ⷮ: ");
		String engine = sc.next();
		System.out.println("���ӱ�(���� ���ӱ⸦ ������ ������ �޸�(,)�� �����Ͽ� �Է����ֽʽÿ�.): ");
		String transmission = sc.next();
		System.out.println("����(�������� ������ ������ ������ �޸�(,)�� �����Ͽ� �Է����ֽʽÿ�.): ");
		String color = sc.next();
		System.out.println("����(���̺긮�� �����̸� �޸�(,)�� �����Ͽ� �Է����ֽʽÿ�.): ");
		String fuel = sc.next();
		
		printPageMiddle();
		System.out.println("�������� ������ �Ϸ��Ͻðڽ��ϱ�?");
		System.out.println("1.�Ϸ�  2.�ڷΰ���");
		int selection = sc.nextInt();
		printPageMiddle();
		printPageEnd();
		
		//TODO
		//DB���� �ش��ϴ� ������ �Ź��� �ҷ����� ��� ������ �ʿ�
	}
}
