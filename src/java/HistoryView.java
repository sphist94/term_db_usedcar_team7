
public class HistoryView extends View {
	public void showAHistory(String poid, String sold_date) {
		System.out.println("�Ź���ȣ: " + poid + ", �ŷ���¥: " + sold_date);
	}

	public void loadHistoriesPage() {
		boolean isThereHistory = false;
		printPageStart();
		System.out.println("���� �������� �����丮 ���� �������Դϴ�.");
		System.out.println("���� ������ �����丮 ������ �Ʒ��� �����ϴ�.");
		// TODO:
		// 1. DB���� �ش� ������ ���� �����丮�� �ҷ���
		// 2. �����丮�� ������ ���ٰ� ����ϰ� ����
		// 3. �����丮�� ������ �����丮���� ��� (showAHistory �̿�)
		printPageEnd();

		if (isThereHistory) {
			while(loadDetailedHistoryPage());
		}
	}

	public boolean loadDetailedHistoryPage() {
		printPageStart();
		System.out.println("�� �ڼ��ϰ� ���ð� ������ �ش� �Ź���ȣ�� �Է����ֽʽÿ�.");
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
