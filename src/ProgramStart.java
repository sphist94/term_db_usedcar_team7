import View.MainView;
import DB.DBConnection;
import DB.InsertionPhase2Sqls;

public class ProgramStart {
	public static void main(String[] args) {
		DBConnection.getConnection();
		
		//����׳� �׽�Ʈ�Ҷ��� �ּ�ó�� �صѰ�
//		System.out.println("������ �ʱ�ȭ ���Դϴ�. ��ø� ��ٷ� �ֽʽÿ�.");
//		InsertionPhase2Sqls.readSqlFiles("Team8_phase1.sql");
//		System.out.println("������ �Է����Դϴ�. ��ø� ��ٷ� �ֽʽÿ�.");
//		InsertionPhase2Sqls.readSqlFiles("Team8_phase2.sql");
//		System.out.println("��ٷ��ּż� �����մϴ�.");
		//�ּ�ó�� �������
		
		MainView mv = new MainView();
		mv.startPage();
	}
}
