package Functions;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import DB.VehicleDB;

public class CheckConditions {
	static private Pattern pattern;
	static private Matcher matcher;

	public static boolean isNumber(String input) {
		try {
			Double.parseDouble(input);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static boolean isInteger(String input) {
		if (isNumber(input)) {
			if (input.contains("."))
				return false;
			Pattern IntegerPattern = Pattern.compile("[0-9]+");
			Matcher IntegerMatcher = IntegerPattern.matcher(input);
			return IntegerMatcher.find();
		}
		return false;
	}

	public static boolean checkInputType(String input, SignInputType inputType) {
		switch (inputType) {
		case ACCOUNT_TYPE:
			return isAccountType(input);
		case ID:
			return isId(input);
		case PW:
			return isPassword(input);
		case LNAME:
			return isLname(input);
		case FNAME:
			return isFname(input);
		case PHONE:
			return isPhoneNumber(input);
		case GENDER:
			return isGender(input);
		case BIRTHDATE:
			return isBirthdate(input);
		case EMAIL:
			return isEmail(input);
		case ADDRESS:
			return isAddress(input);
		case OCCUPATION:
			return isOccupation(input);
		}
		return false;
	}

	public static boolean isAccountType(String input) {
		pattern = Pattern.compile("[12]");
		matcher = pattern.matcher(input);
		return matcher.find();
	}

	public static boolean isId(String input) {
		pattern = Pattern.compile("[0-9a-zA-Z]+");
		matcher = pattern.matcher(input);
		return matcher.find();
	}

	public static boolean isPassword(String input) {
		pattern = Pattern.compile("[0-9a-zA-Z`~!@#$%^&*()]+");
		matcher = pattern.matcher(input);
		return matcher.find();
	}

	public static boolean isLname(String input) {
		pattern = Pattern.compile("[a-zA-Z]+");
		matcher = pattern.matcher(input);
		return matcher.find();
	}

	public static boolean isFname(String input) {
		pattern = Pattern.compile("[a-zA-Z]+");
		matcher = pattern.matcher(input);
		return matcher.find();
	}

	public static boolean isPhoneNumber(String input) {
		pattern = Pattern.compile("[0-9]{3}-[0-9]{4}-[0-9]{4}");
		matcher = pattern.matcher(input);
		return matcher.find();
	}

	public static boolean isGender(String input) {
		if (input.equals("-"))
			return true;

		pattern = Pattern.compile("[MF]");
		matcher = pattern.matcher(input);
		return matcher.find();
	}

	public static boolean isValidateDate(String input) {
		DateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");
		dataFormat.setLenient(false);
		try {
			dataFormat.parse(input);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}

	public static boolean isBirthdate(String input) {
		if (input.equals("-"))
			return true;

		pattern = Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2}");
		matcher = pattern.matcher(input);
		if (matcher.find())
			return isValidateDate(input);
		return false;
	}

	public static boolean isEmail(String input) {
		if (input.equals("-"))
			return true;

		pattern = Pattern.compile("[0-9a-zA-Z]+@[0-9a-zA-Z]+.[0-9a-zA-Z]+");
		matcher = pattern.matcher(input);
		return matcher.find();
	}

	public static boolean isAddress(String input) {
		if (input.equals("-"))
			return true;

		pattern = Pattern.compile("[0-9, a-zA-Z]*");
		matcher = pattern.matcher(input);
		return matcher.find();
	}

	public static boolean isOccupation(String input) {
		if (input.equals("-"))
			return true;

		pattern = Pattern.compile("[a-zA-Z]*");
		matcher = pattern.matcher(input);
		return matcher.find();
	}

	public static boolean isMaker(String input) {
		ArrayList<String> list = VehicleDB.getMakers();
		return list.contains(input);
	}

	public static boolean isModel(String maker, String input) {
		ArrayList<String> list = VehicleDB.getModel(maker);
		return list.contains(input);
	}

	public static boolean isDetailedModel(String model, String input) {
		ArrayList<String> list = VehicleDB.getDetailedModel(model);
		return list.contains(input);
	}

	public static boolean isColorType(String input) {
		ArrayList<String> list = Utilities.parseMultiValues(input);
		ArrayList<String> color_list = VehicleDB.getColorType();
		for (int i = 0; i < list.size(); ++i) {
			if (!list.contains(color_list.get(i)))
				return false;
		}
		return true;
	}

	public static boolean isFuelType(String input) {
		ArrayList<String> list = Utilities.parseMultiValues(input);
		ArrayList<String> fuel_list = VehicleDB.getFuelType();
		for (int i = 0; i < list.size(); ++i) {
			if (!list.contains(fuel_list.get(i)))
				return false;
		}
		return true;
	}

	public static boolean isTransmission(String input) {
		ArrayList<String> list = VehicleDB.getTransmissionName();
		return list.contains(input);
	}

	public static boolean isEngineDisplacement(String input) {
		ArrayList<String> list = VehicleDB.getEngineDisplacement();
		return list.contains(input);
	}

	public static boolean isCategory(String input) {
		ArrayList<String> list = VehicleDB.getCategoryName();
		return list.contains(input);
	}
	
	public static boolean isAge(String input) {
		if (input.equals("-"))
			return true;

		pattern = Pattern.compile("[0-9]{4}-[0-9]{2}");
		matcher = pattern.matcher(input);
		if (matcher.find())
			return true;
		return false;
	}

	public static boolean checkVehicleInputType(String input, VehicleInputType inputType) {
		switch (inputType) {
		case AGE:
			return isAge(input);
		case VEHICLE_NUMBER:
			return isId(input);
		case MILEAGE:
			return isInteger(input);
		case PRICE:
			return isInteger(input);
		default:
			return false;
		}
	}
}
