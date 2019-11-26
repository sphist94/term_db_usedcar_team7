package View;

import Functions.CheckConditions;

import java.util.ArrayList;
import java.util.Arrays;

import DB.AccountInfoDB;
import DB.VehicleDB;
import Functions.Utilities;

public class DealerView extends AccountView {
	protected boolean loadAccountPage(String id) {
		boolean isExit = false;
		while (!isExit) {
			printPageStart();
			System.out.println("���� �Ǹ��� �������� �α����� �Ǿ����ϴ�.");
			System.out.println("���Ͻô� ��ɿ� �´� ���ڸ� �Է����ֽʽÿ�.");
			System.out.println("1.ȸ������  2.�Ź��˻�  3.�Ź����  4.��ϸŹ� ��ȸ  5.������� ��ȸ  6.�α׾ƿ�");
			String selection = sc.nextLine();
			printPageEnd();
			
			if(!CheckConditions.isInteger(selection))
				continue;
			
			int select = Integer.parseInt(selection);
			switch (select) {
			case 1:
				isExit = loadAccountInformationPage(id);
				break;
			case 2:
				loadVehicleSearchPage(id);
				break;
			case 3:
				loadVehicleRegistrationPage(id);
				break;
			case 4:
				loadLookupRegisteredVehicle(id);
				break;
			case 5:
				loadStatisticPagesForDealer(id);
				break;
			case 6:
				isExit = signOut();
				break;
			}
		}
		return true;
	}

	private void loadStatisticPagesForDealer(String id) {
		while (true) {
			printPageStart();
			System.out.println("���� �������� ������� ���� �������Դϴ�.");
			System.out.println("���Ͻô� ��ɿ� �´� ���ڸ� �Է����ֽʽÿ�.");
			System.out.println("1.���� �����  2.������ �����  3.�����纰 �����");
			String selection = sc.nextLine();

			ArrayList<String[]> list = new ArrayList<>();
			if (selection.equals("1")) {
				list = AccountInfoDB.getSalesForEachMonth(id);
			} else if (selection.equals("2")) {
				list = AccountInfoDB.getSalesForEachYear(id);
			} else if (selection.equals("3")) {
				list = AccountInfoDB.getSalesForEachMaker(id);
			}
			if (list != null && selection.equals("3")) {
				for (int i = 0; i < list.size(); ++i) {
					System.out.println("������: " + list.get(i)[1] + ", �����: " + list.get(i)[0]);
				}
			}else if (list != null) {
				for (int i = 0; i < list.size(); ++i) {
					System.out.println("��¥: " + list.get(i)[1] + ", �����: " + list.get(i)[0]);
				}
			} else {
				System.out.println("���� ����ڷᰡ �������� �ʽ��ϴ�.");
			}
			printToBeContinue();
		}
	}

	// �Ǹ��ڰ� �ڽ��� ����� �Ź��� ���� �Լ�
	private void loadLookupRegisteredVehicle(String id) {
		ArrayList<String[]> list = VehicleDB.getOwnRegisteredVehicle(id);
		ArrayList<String> poids = new ArrayList<>();

		String[] meta_info = { "�Ź���ȣ", "����Ÿ�", "����", "������", "��", "���θ�" };
		while (true) {
			for (int i = 0; i < list.size(); ++i) {
				poids.add(list.get(i)[0]);
				for (int j = 0; j < 6; ++j) {
					System.out.print(meta_info[j] + ": " + list.get(i)[j]);
					if (j != 5)
						System.out.print(", ");
					else if (j == 5)
						System.out.println();
				}
			}

			System.out.println("���Ͻô� ��ɿ� �´� ���ڸ� �Է����ֽʽÿ�.");
			System.out.println("1.�Ź����� ����  2.�Ź������� ����");
			System.out.println("���� �������� ���÷��� -1�� �Է����ֽʽÿ�.");
			// ���� ����
			String selection = sc.nextLine();
			if (selection.equals("-1"))
				break;
			else if (selection.equals("1")) {
				System.out.println("�����ϱ⸦ ���Ͻô� �Ź���ȣ�� �Է����ֽʽÿ�.");
				String poid = sc.nextLine();
				if (poids.contains(poid)) {
					ResetVehicleInfo(id, poid);
				}
			} else if (selection.equals("2")) {
				System.out.println("�󼼺��⸦ ���Ͻô� �Ź���ȣ�� �Է����ֽʽÿ�.");
				String poid = sc.nextLine();
				if (poids.contains(poid)) {
					VehicleView.printDetailedVehicleInfo(poid, id);
				}
			} else {
				System.out.println("����ڰ� ������� ���� �Ź���ȣ�Դϴ�.");
				System.out.println("�ٽ� �õ����ֽʽÿ�.");
			}
		}
		printToBeContinue();
	}

	private void ResetVehicleInfo(String id, String poid) {
		String[] input = new String[12];
		Arrays.fill(input, "");
		ArrayList<String> colors = new ArrayList<>();
		ArrayList<String> fuels = new ArrayList<>();
		String[] meta_info = { "����", "������ȣ", "����Ÿ�", "����", "������", "��", "���θ�", "��ⷮ", "���ӱ�", "����", "����", "����" };
		printPageStart();
		System.out.println("���� �������� ������� ���� ���� �������Դϴ�.");
		boolean isExit = false;
		while (!isExit) {
			printPageMiddle();
			System.out.println("�Է��ϰ��� �ϴ� ������ �������ֽʽÿ�. ���� �Է��ؾ��մϴ�.");
			System.out.println("1.����  2.������ȣ  3.����Ÿ�  4.����  5.������  6.��  7.���θ�  8.��ⷮ  9.���ӱ�  10.����  11.����  12.����");
			System.out.println("������ ���� ������ ������ �ְų�, ���ᰡ ���̺긮���� �� ��ǥ(,)�� �������ּ���.");
			System.out.println("�ϷḦ ���Ͻø� 13, ���Ḧ ���Ͻø� 14�� �Է����ּ���");
			String selection = sc.nextLine();
			if (CheckConditions.isInteger(selection)) {
				int select = Integer.parseInt(selection);
				if (select < 1 || select > 14) {
					continue;
				} else if (select == 13) {
					boolean isChanged = false;
					for (int i = 0; i < 12; ++i) {
						if (!input[i].isEmpty()) {
							isChanged = true;
							break;
						}
					}
					if (isChanged)
						if (VehicleDB.updateVehicle(poid, input, colors, fuels)) {
							System.out.println("�Ź����� ������ �Ϸ�Ǿ����ϴ�.");
							isExit = true;

						} else {
							System.out.println("��������� �����ϴ�.");
						}
					printToBeContinue();
					continue;
				} else if (select == 14) {
					isExit = true;
					break;
					// ������ �Է�
				} else if (select == 2) {
					System.out.println("������ȣ�� �����Ұ����մϴ�.");
					printToBeContinue();
					continue;
				} else if (select == 5) {

					input[select - 1] = getMaker();
				} else if (select == 6) {
					if (input[4].isEmpty()) {
						System.out.println("���� �����縦 �Է��ؾ� ���� ���� �Է��� �� �ֽ��ϴ�.");
						printToBeContinue();
						continue;
					}
					input[select - 1] = getModel(input[4]);
				} else if (select == 7) {
					if ((input[4].isEmpty() || input[5].isEmpty())) {
						System.out.println("���� ������ �Ǵ� ���� ���� �Է��ؾ� ���� ���θ��� �Է��� �� �ֽ��ϴ�.");
						printToBeContinue();
						continue;
					}
					input[select - 1] = getDetailedModel(input[5]);
				} else if (select >= 8 && select <= 12) {
					System.out.println("���� �Է� ������ " + meta_info[select - 1] + "�Դϴ�.");
					ArrayList<String> list = null;
					switch (select) {
					case 8:
						list = VehicleDB.getEngineDisplacement();
						break;
					case 9:
						list = VehicleDB.getTransmissionName();
						break;
					case 10:
						list = VehicleDB.getCategoryName();
						break;
					case 11:
						list = VehicleDB.getColorType();
						break;
					case 12:
						list = VehicleDB.getFuelType();
						break;
					}
					for (int i = 0; i < list.size(); ++i) {
						if (i != list.size() - 1) {
							System.out.print(list.get(i) + ", ");
						} else {
							System.out.println(list.get(i));
						}
					}

					while (true) {
						input[select - 1] = sc.nextLine();
						boolean ret = false;
						switch (select) {
						case 8:
							ret = CheckConditions.isEngineDisplacement(input[select - 1]);
							break;
						case 9:
							ret = CheckConditions.isTransmission(input[select - 1]);
							break;
						case 10:
							ret = CheckConditions.isCategory(input[select - 1]);
							break;
						case 11:
							if (ret = CheckConditions.isColorType(input[select - 1]))
								colors = Utilities.parseMultiValues(input[select - 1]);
							break;
						case 12:
							if (ret = CheckConditions.isFuelType(input[select - 1]))
								fuels = Utilities.parseMultiValues(input[select - 1]);
							break;
						}
						if (ret)
							break;
						System.out.println("�߸��� ���� �Է��߽��ϴ�. �ٽ� �Է����ֽʽÿ�.");
					}

				} else {
					input[select - 1] = FillVehicleRegistration(select);
				}
			}
			printPageMiddle();
		}
		printPageEnd();
	}

	private void loadVehicleRegistrationPage(String id) {
		String[] meta_info = { "����", "������ȣ", "����Ÿ�", "����", "������", "��", "���θ�", "��ⷮ", "���ӱ�", "����", "����", "����" };
		printPageStart();
		int Poid = VehicleDB.getTotalNumVehicle() + 1;
		String[] input = new String[12];
		ArrayList<String> colors = new ArrayList<>();
		ArrayList<String> fuels = new ArrayList<>();
		Arrays.fill(input, "");
		boolean isExit = false;
		while (!isExit) {
			printPageMiddle();
			System.out.println("���� �������� ������� �������Դϴ�.");
			System.out.println("�Է��ϰ��� �ϴ� ������ �������ֽʽÿ�. ���� �Է��ؾ��մϴ�.");
			System.out.println("1.����  2.������ȣ  3.����Ÿ�  4.����  5.������  6.��  7.���θ�  8.��ⷮ  9.���ӱ�  10.����  11.����  12.����");
			System.out.println("������ ���� ������ ������ �ְų�, ���ᰡ ���̺긮���� �� ��ǥ(,)�� �������ּ���.");
			System.out.println("�ϷḦ ���Ͻø� 13, ���Ḧ ���Ͻø� 14�� �Է����ּ���");
			String selection = sc.nextLine();
			if (CheckConditions.isInteger(selection)) {
				int select = Integer.parseInt(selection);
				if (select < 1 || select > 14) {
					continue;
				} else if (select == 13) {
					boolean isDone = true;
					for (int i = 0; i < 12; ++i) {
						if (input[i].isEmpty()) {
							System.out.println("���� " + meta_info[i] + "�� �Է����� �ʾҽ��ϴ�.");
							printToBeContinue();
							isDone = false;
							break;
						}
					}
					if (isDone) {
						VehicleDB.insertVehicle(Integer.toString(Poid), id, input, colors, fuels);
						isExit = true;
					}
					System.out.println("�����Ź� ����� �Ϸ�ƽ��ϴ�.");
					printToBeContinue();
					continue;
				} else if (select == 14) {
					isExit = true;
					break;
					// ������ �Է�
				} else if (select == 5) {
					input[select - 1] = getMaker();
				} else if (select == 6) {
					if (input[4].isEmpty()) {
						System.out.println("���� �����縦 �Է��ؾ� ���� ���� �Է��� �� �ֽ��ϴ�.");
						printToBeContinue();
						continue;
					}
					input[select - 1] = getModel(input[4]);
				} else if (select == 7) {
					if ((input[4].isEmpty() || input[5].isEmpty())) {
						System.out.println("���� ������ �Ǵ� ���� ���� �Է��ؾ� ���� ���θ��� �Է��� �� �ֽ��ϴ�.");
						printToBeContinue();
						continue;
					}
					input[select - 1] = getDetailedModel(input[5]);
				} else if (select >= 8 && select <= 12) {

					System.out.println("���� �Է� ������ " + meta_info[select - 1] + "�Դϴ�.");
					ArrayList<String> list = null;
					switch (select) {
					case 8:
						list = VehicleDB.getEngineDisplacement();
						break;
					case 9:
						list = VehicleDB.getTransmissionName();
						break;
					case 10:
						list = VehicleDB.getCategoryName();
						break;
					case 11:
						list = VehicleDB.getColorType();
						break;
					case 12:
						list = VehicleDB.getFuelType();
						break;
					}
					for (int i = 0; i < list.size(); ++i) {
						if (i != list.size() - 1) {
							System.out.print(list.get(i) + ", ");
						} else {
							System.out.println(list.get(i));
						}
					}

					while (true) {
						input[select - 1] = sc.nextLine();
						boolean ret = false;
						switch (select) {
						case 8:
							ret = CheckConditions.isEngineDisplacement(input[select - 1]);
							break;
						case 9:
							ret = CheckConditions.isTransmission(input[select - 1]);
							break;
						case 10:
							ret = CheckConditions.isCategory(input[select - 1]);
							break;
						case 11:
							if (ret = CheckConditions.isColorType(input[select - 1]))
								colors = Utilities.parseMultiValues(input[select - 1]);
							break;
						case 12:
							if (ret = CheckConditions.isFuelType(input[select - 1]))
								fuels = Utilities.parseMultiValues(input[select - 1]);
							break;
						}
						if (ret)
							break;
						System.out.println("�߸��� ���� �Է��߽��ϴ�. �ٽ� �Է����ֽʽÿ�.");
					}

				} else {
					input[select - 1] = FillVehicleRegistration(select);
				}
			}
			printPageMiddle();
		}
		printPageEnd();
	}

}
