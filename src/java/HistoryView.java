
public class HistoryView extends View {
	private void showHistories(String poid, String sold_date) {
		System.out.println("�Ź���ȣ: " + poid + ", �ŷ���¥: " + sold_date);
	}

	public void loadHistoriesPage() {
		boolean isThereHistory = true;
		printPageStart();
		System.out.println("���� �������� �����丮 ���� �������Դϴ�.");
		System.out.println("���� ������ �����丮 ������ �Ʒ��� �����ϴ�.");
		// TODO:
		// 1. DB���� �ش� ������ ���� �����丮�� �ҷ���
		// 2. �����丮�� ������ ���ٰ� ����ϰ� ����
		// 3. �����丮�� ������ �����丮���� ��� (showHistories() �̿�)
		printPageEnd();

		if (isThereHistory) {
			while(loadDetailedHistoryPage());
		}
	}

	private boolean loadDetailedHistoryPage() {
		printPageStart();
		System.out.println("�� �ڼ��ϰ� ���ð� ������ �ش� �����丮 ��ȣ�� �Է����ֽʽÿ�.");
		System.out.println("�ڷΰ��ð� ������ -1�� �Է����ֽʽÿ�.");
		int posting_id = sc.nextInt();
		printPageEnd();
		
		if(posting_id == -1) {
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
}
