package View;

public class HistoryView extends BasicView {
	private void showHistories(String poid, String sold_date) {
		System.out.println("�Ź���ȣ: " + poid + ", �ŷ���¥: " + sold_date);
	}

	public void loadHistoriesPage() {
		while (true) {
			boolean isThereHistory = true;
			printPageStart();
			System.out.println("���� �������� �����丮 ���� �������Դϴ�.");

			if (printBack())
				break;

			System.out.println("���� ������ �����丮 ������ �Ʒ��� �����ϴ�.");

			// TODO:
			// 1. DB���� �ش� ������ ���� �����丮�� �ҷ���
			// 2. �����丮�� ������ ���ٰ� ����ϰ� ����
			// 3. �����丮�� ������ �����丮���� ��� (showHistories() �̿�)

			printPageEnd();

			if (isThereHistory)
				loadDetailedHistoryPage();
		}
	}

	private void loadDetailedHistoryPage() {
		printPageMiddle();
		System.out.println("�� �ڼ��ϰ� ���ð� ������ �ش� �����丮 ��ȣ�� �Է����ֽʽÿ�.");
		int posting_id = sc.nextInt();
		printPageMiddle();

		// TODO:
		// 1. �Է¹��� �Ź���ȣ�� �ڽ��� ����, �Ǹ��̷��� �´��� Ȯ��
		// 2. ������ �ش� �Ź��� ���� �ڼ��� �����͸� ������
		// 3. Ʋ������ ���и޽����� ���;
	}
}
