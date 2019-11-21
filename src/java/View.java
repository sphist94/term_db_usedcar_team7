import java.util.Scanner;

public class View {
	static Scanner sc = new Scanner(System.in);
	public void printPageStart() {
		//System.out.println("PAGE START=================================");
		System.out.println("===========================================");
	}
	
	public void printPageEnd() {
		System.out.println("===========================================\n");
		//System.out.println("===================================PAGE END");
	}
	
	public void printPageMiddle() {
		System.out.println("-------------------------------------------");
	}
}
