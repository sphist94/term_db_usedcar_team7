
public class VehicleView extends View{
	public void loadVehicleSearchPage() {
		printPageStart();
		System.out.println("���� �������� �Ź��˻� ���� �������Դϴ�.");
		System.out.println("���Ͻô� ��ɿ� �´� ���ڸ� �Է����ֽʽÿ�.");
		System.out.println("1.��ü �˻�  2.���� �˻�  3.�����丮 ��ȸ  4.ȸ��Ż��");
		System.out.println("�ڷ� ���÷��� 999�� �Է����ֽʽÿ�.");
		int selection = sc.nextInt();
		printPageEnd();
	}
}
