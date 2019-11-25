package View;

import Functions.CheckConditions;
import Functions.VehicleInputType;

import java.util.ArrayList;
import java.util.Arrays;

import DB.VehicleDB;
import Functions.Utilities;

public class DealerView extends AccountView {
	protected boolean loadAccountPage(String id) {
		boolean isExit = false;
		while (!isExit) {
			printPageStart();
			System.out.println("���� �Ǹ��� �������� �α����� �Ǿ����ϴ�.");
			System.out.println("���Ͻô� ��ɿ� �´� ���ڸ� �Է����ֽʽÿ�.");
			System.out.println("1.ȸ������  2.�Ź��˻�  3.�Ź����  4.�α׾ƿ�");
			int selection = sc.nextInt();
			printPageEnd();

			switch (selection) {
			case 1:
				isExit = loadAccountInformationPage(id);
				break;
			case 2:
				loadVehicleSearchPage();
				break;
			case 3:
				loadVehicleRegistrationPage(id);
				break;
			case 4:
				isExit = signOut();
				break;
			}
		}
		return true;
	}

	private String getInputVehicleInfo(VehicleInputType type, String msg) {
		String str = "";
		while (true) {
			System.out.print(msg);
			str = sc.nextLine();
			if (CheckConditions.checkVehicleInputType(str, type))
				break;
			System.out.println("�߸��� ���� �Է��߽��ϴ�. �ٽ� �Է����ֽʽÿ�.");
		}
		return str;
	}

	private String FillVehicleRegistration(int select) {

		switch (select) {
		case 1:
			return getInputVehicleInfo(VehicleInputType.AGE, "����(YYYY-MM): ");
		case 2:
			return getInputVehicleInfo(VehicleInputType.VEHICLE_NUMBER, "������ȣ: ");
		case 3:
			return getInputVehicleInfo(VehicleInputType.MILEAGE, "����Ÿ�: ");
		case 4:
			return getInputVehicleInfo(VehicleInputType.PRICE, "����: ");

		default:
			return "";
		}
	}

	private String getMaker() {
		ArrayList<String> maker_list = VehicleDB.getMakers();
		System.out.println("���� �Է� ������ �������Դϴ�.");
		for (int i = 0; i < maker_list.size(); ++i) {
			if (i != maker_list.size() - 1)
				System.out.print(maker_list.get(i) + ", ");
			else
				System.out.println(maker_list.get(i));
		}
		System.out.println("�� �߿��� �Է����ֽʽÿ�");
		while (true) {
			String str = sc.nextLine();
			if (CheckConditions.isMaker(str))
				return str;
			System.out.println("�߸��� ���� �Է��߽��ϴ�. �ٽ� �Է����ֽʽÿ�.");
		}
	}

	private String getModel(String maker) {
		ArrayList<String> model_list = VehicleDB.getModel(maker);
		System.out.println("���� �Է� ������ ���Դϴ�.");
		for (int i = 0; i < model_list.size(); ++i) {
			if (i != model_list.size() - 1)
				System.out.print(model_list.get(i) + ", ");
			else
				System.out.println(model_list.get(i));
		}
		System.out.println("�� �߿��� �Է����ֽʽÿ�");
		while (true) {
			String str = sc.nextLine();
			if (CheckConditions.isModel(maker, str))
				return str;
			System.out.println("�߸��� ���� �Է��߽��ϴ�. �ٽ� �Է����ֽʽÿ�.");
		}
	}

	private String getDetailedModel(String model) {
		ArrayList<String> detailedmodel_list = VehicleDB.getDetailedModel(model);
		System.out.println("���� �Է� ������ ���θ��Դϴ�.");
		for (int i = 0; i < detailedmodel_list.size(); ++i) {
			if (i != detailedmodel_list.size() - 1)
				System.out.print(detailedmodel_list.get(i) + ", ");
			else
				System.out.println(detailedmodel_list.get(i));
		}
		System.out.println("�� �߿��� �Է����ֽʽÿ�");
		while (true) {
			String str = sc.nextLine();
			if (CheckConditions.isDetailedModel(model, str))
				return str;
			System.out.println("�߸��� ���� �Է��߽��ϴ�. �ٽ� �Է����ֽʽÿ�.");
		}
	}

	private void loadVehicleRegistrationPage(String id) {
		String[] meta_info = { "����", "������ȣ", "����Ÿ�", "����", "������", "��", "���θ�", "��ⷮ", "���ӱ�", "����", "����", "����" };
		printPageStart();
		int Poid = VehicleDB.getTotalNumVehicle() + 1;
		String[] input = new String[12];
		ArrayList<String> colors = new ArrayList<>();
		ArrayList<String> fuels = new ArrayList<>();
		Arrays.fill(input, "");
		while (true) {
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
					if (isDone)
						VehicleDB.updateVehicle(Integer.toString(Poid), id, input, colors, fuels);
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
			printPageMiddle();
		}
		printPageEnd();
	}

}
