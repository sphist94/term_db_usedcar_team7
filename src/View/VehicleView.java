package View;

import java.util.ArrayList;
import java.util.Arrays;

import DB.AccountInfoDB;
import DB.AccountType;
import DB.VehicleDB;
import Functions.CheckConditions;
import Functions.Utilities;

public class VehicleView extends BasicView {
	public void loadVehicleSearchPage(String id) {
		while (true) {
			printPageStart();
			System.out.println("���� �������� �Ź��˻� ���� �������Դϴ�.");
			System.out.println("���Ͻô� ��ɿ� �´� ���ڸ� �Է����ֽʽÿ�.");
			System.out.println("1.��ü �˻�  2.���� �˻�");
			System.out.println("���� �������� ���÷��� -1�� �Է����ֽʽÿ�.");
			String selection = sc.nextLine();
			printPageEnd();

			if (!CheckConditions.isInteger(selection))
				continue;

			int select = Integer.parseInt(selection);
			switch (select) {
			case -1:
				return;
			case 1:
				loadAllVehicleSearchPage(id);
				break;
			case 2:
				loadConditionedVehicleSearchPage(id);
				break;
			}
		}
	}

	private void loadAllVehicleSearchPage(String id) {
		final int num_list = 10;
		int cnt = 0;
		
		printPageStart();
		System.out.println("���� �������� ��ü �Ź��˻� �������Դϴ�.");
		System.out.println(Integer.toString(num_list) + "���� �Ź� ����� �˻��˴ϴ�.");
		ArrayList<String[]> list = VehicleDB.getVehicleList();

		// 10���� ����Ʈ�� ����Ѵ�.
		int start = 0;
		while (true) {
			for (int i = start; i < list.size() && i < start + num_list; ++i) {
				String[] temp = list.get(i);
				showVehicles(temp[0], temp[1], temp[2], temp[3] + " " + temp[4] + " " + temp[5]);
			}

			if (cnt >= list.size()) {
				System.out.println("��� �Ź��� �˻� �Ϸ�Ǿ����ϴ�.");
				break;
			}

			printPageMiddle();
			System.out.println("�߰� �˻��Ͻðڽ��ϱ�?");
			System.out.println("1.��  2.�ƴϿ�  3.�����Ź� ���λ����� ���ðڽ��ϱ�?");
			String selection = sc.nextLine();
			if (selection.equals("1")) {
				start += num_list;
			} else if (selection.equals("2")) {
				break;
			} else if (selection.equals("3")) {
				loadDetailedVehiclePage(id);
			}
			printPageMiddle();
		}
		printPageEnd();
	}

	private boolean loadDetailedVehiclePage(String id) {
		while (true) {
			System.out.println("�� �ڼ��ϰ� ���ð� ������ �ش� �Ź���ȣ�� �Է����ֽʽÿ�.");
			System.out.println("���� �������� ���÷��� -1�� �Է����ֽʽÿ�.");
			String poid = sc.nextLine();

			if (!CheckConditions.isInteger(poid) || poid.equals("-1"))
				break;

			if (!VehicleDB.isSoldVehicle(poid)) {
				printDetailedVehicleInfo(poid, id);
				return true;
			} else {
				System.out.println("�̹� ���� �Ϸ�� �Ź��Դϴ�.");
			}
		}
		return false;
	}

	private void loadConditionedVehicleSearchPage(String id) {
		String[] meta_info = { "����", "������ȣ", "����Ÿ�", "����", "������", "��", "���θ�", "��ⷮ", "���ӱ�", "����", "����", "����" };
		ArrayList<String> colors = new ArrayList<>();
		ArrayList<String> fuels = new ArrayList<>();
		ArrayList<String[]> conditioned_list = new ArrayList<>();
		String[] input = new String[12];
		Arrays.fill(input, "");
		while (true) {
			printPageStart();
			System.out.println("�Ź� ���ǰ˻� �������Դϴ�.");
			System.out.println("�ش��ϴ� ���׿� �˸°� �������ֽʽÿ�.");
			System.out.println("1.����  2.������ȣ  3.����Ÿ�  4.����  5.������  6.��  7.���θ�  8.��ⷮ  9.���ӱ�  10.����  11.����  12.����");
			System.out.println("�ϷḦ ���Ͻø� 13, ���Ḧ ���Ͻø� 14�� �Է����ּ���");
			String selection = sc.nextLine();
			if (CheckConditions.isInteger(selection)) {
				int select = Integer.parseInt(selection);
				if (select < 1 || select > 14) {
					continue;
				} else if (select == 13) {
					conditioned_list = VehicleDB.getConditionedVehicleList(input, colors, fuels);
					break;
				} else if (select == 14) {
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
							colors = Utilities.parseMultiValues(input[select - 1]);
							ret = CheckConditions.isColorType(input[select - 1]);
							break;
						case 12:
							fuels = Utilities.parseMultiValues(input[select - 1]);
							ret = CheckConditions.isFuelType(input[select - 1]);
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
		}
		printConditionedSearch(conditioned_list, id);
		printPageEnd();
	}

	private void printConditionedSearch(ArrayList<String[]> list, String id) {
		final int num_list = 10;
		int cnt = 0;

		// 10���� ����Ʈ�� ����Ѵ�.
		int start = 0;
		while (true) {
			for (int i = start; i < list.size() && i < start + num_list; ++i) {
				String[] temp = list.get(i);
				showVehicles(temp[0], temp[1], temp[2], temp[3] + " " + temp[4] + " " + temp[5]);
			}

			if (cnt >= list.size()) {
				System.out.println("��� �Ź��� �˻� �Ϸ�Ǿ����ϴ�.");
				break;
			}

			printPageMiddle();
			System.out.println("�߰� �˻��Ͻðڽ��ϱ�?");
			System.out.println("1.��  2.�ƴϿ�  3.�����Ź� ���λ����� ���ðڽ��ϱ�?");
			String selection = sc.nextLine();
			if (selection.equals("1")) {
				start += num_list;
			} else if (selection.equals("2")) {
				break;
			} else if (selection.equals("3")) {
				loadDetailedVehiclePage(id);
			}
			printPageMiddle();
		}
		// printPageEnd();
	}

	// �����Ź� ���λ������� ������
	static public void printDetailedVehicleInfo(String poid, String id) {
		String[] vehicle_info = VehicleDB.getVehicleInfoByPoid(poid);
		ArrayList<String> fuel_info = VehicleDB.getFuelTypesByPoid(poid);
		ArrayList<String> color_info = VehicleDB.getColorsByPoid(poid);

		String[] meta_info = { "�Ź���ȣ", "�Ǹ��� ���̵�", "����", "������ȣ", "����Ÿ�", "������", "��", "���θ�", "��ⷮ", "���ӱ�", "����", "����",
				"����" };

		for (int i = 0; i < meta_info.length; ++i) {
			System.out.print(meta_info[i] + ": ");
			if (i == 11) {
				for (int j = 0; j < color_info.size(); ++j) {
					if (j == color_info.size() - 1) {
						System.out.println(color_info.get(j));
					} else {
						System.out.print(color_info.get(j) + ", ");
					}
				}
			} else if (i == 12) {
				for (int j = 0; j < fuel_info.size(); ++j) {
					if (j == fuel_info.size() - 1) {
						System.out.println(fuel_info.get(j));
					} else {
						System.out.print(fuel_info.get(j) + ", ");
					}
				}
			} else {
				System.out.println(vehicle_info[i]);
			}
		}
		System.out.println("�Ǹ��� �޴���ȭ��ȣ: " + AccountInfoDB.getPhoneNumber(vehicle_info[1]));

		AccountType account_type = AccountInfoDB.getAccountType(id);
		switch (account_type) {
		case CUSTOMER:
			CustomerView.doBuyTheVehicle(poid, id);
			break;
		case ADMINISTRATOR:
			AdminView.doGetOffFakeVehicle(poid, id);
			break;
		default:
		}
		printToBeContinue();
	}
}


