import View.MainView;

import java.util.Scanner;

import DB.DBConnection;
import DB.InsertionPhase2Sqls;

public class ProgramStart {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("�ּ�, ��Ʈ��ȣ, SID, �����ID, ��й�ȣ�� �Է����ּ���.");
		System.out.println("�Է°��� ������ URL�� 'jdbc:oracle:thin:@localhost:1600:xe' �̿� ���� ����˴ϴ�.");
		System.out.println("���� ���� �����Ͻ÷��� �� ���� <Enter> Ű�� �Է����ּ���.");
		System.out.print("�ּ�(default: @localhost): ");
		String address = sc.nextLine();
		System.out.print("��Ʈ��ȣ(default: 1600): ");
		String port = sc.nextLine();
		System.out.print("SID(default: xe): ");
		String sid = sc.nextLine();
		System.out.print("����� ID(default: system): ");
		String user_id = sc.nextLine();
		System.out.print("��й�ȣ(default: oracle): ");
		String password = sc.nextLine();
		DBConnection.getConnection(address, port, sid, user_id, password);
		
		
		//����׳� �׽�Ʈ�Ҷ��� �ּ�ó�� �صѰ�
		System.out.println("\n������ �ʱ�ȭ ���Դϴ�. ��ø� ��ٷ� �ֽʽÿ�.");
		InsertionPhase2Sqls.readSqlFiles("Team8_phase1.sql");
		System.out.println("������ �Է����Դϴ�. ��ø� ��ٷ� �ֽʽÿ�.");
		InsertionPhase2Sqls.readSqlFiles("Team8_phase2.sql");
		System.out.println("��ٷ��ּż� �����մϴ�.");
		//�ּ�ó�� �������
		
		MainView mv = new MainView();
		mv.startPage();
		sc.close();
		
	}
}
