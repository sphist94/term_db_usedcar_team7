package View;

public class HistoryView extends BasicView {
	private void showHistories(String poid, String sold_date) {
		System.out.println("매물번호: " + poid + ", 거래날짜: " + sold_date);
	}

	public void loadHistoriesPage() {
		while (true) {
			boolean isThereHistory = true;
			printPageStart();
			System.out.println("현재 페이지는 히스토리 관련 페이지입니다.");

			if (printBack())
				break;

			System.out.println("현재 계정의 히스토리 내역은 아래와 같습니다.");

			// TODO:
			// 1. DB에서 해당 계정에 대한 히스토리를 불러옴
			// 2. 히스토리가 없으면 없다고 출력하고 종료
			// 3. 히스토리가 있으면 히스토리들을 출력 (showHistories() 이용)

			printPageEnd();

			if (isThereHistory)
				loadDetailedHistoryPage();
		}
	}

	private void loadDetailedHistoryPage() {
		printPageMiddle();
		System.out.println("더 자세하게 보시고 싶으면 해당 히스토리 번호를 입력해주십시오.");
		int posting_id = sc.nextInt();
		printPageMiddle();

		// TODO:
		// 1. 입력받은 매물번호가 자신의 구매, 판매이력이 맞는지 확인
		// 2. 맞으면 해당 매물에 대한 자세히 데이터를 보여줌
		// 3. 틀렸으면 실패메시지를 출력;
	}
}
