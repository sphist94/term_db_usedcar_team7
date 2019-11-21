
public class VehicleView extends View{
	public void loadVehicleSearchPage() {
		printPageStart();
		System.out.println("현재 페이지는 매물검색 관련 페이지입니다.");
		System.out.println("원하시는 기능에 맞는 숫자를 입력해주십시오.");
		System.out.println("1.전체 검색  2.조건 검색  3.히스토리 조회  4.회원탈퇴");
		System.out.println("뒤로 가시려면 999를 입력해주십시오.");
		int selection = sc.nextInt();
		printPageEnd();
	}
}
