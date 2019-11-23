package Functions;

public class CheckConditions {
	public static boolean isNumber(String input) {
		try {
			Double.parseDouble(input);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static boolean isInteger(String input) {
		boolean ret = false;
		if (isNumber(input)) {
			ret = true;
			for (int i = 0; i < input.length(); ++i) {
				char chr = input.charAt(i);
				if (chr < '0' || chr > '9') {
					ret = false;
				}
			}
		}
		return ret;
	}
	
	public static boolean checkInputType(String input) {
		return true;
	}

	public static boolean isId(String input) {
		boolean ret = false;

		return ret;
	}

	public static boolean isGender(String input) {
		boolean ret = false;
		if (input.equals("M") || input.equals("F") || input.equals("-"))
			ret = true;
		return ret;
	}

	public static boolean isAccountType(String input) {
		boolean ret = false;
		if (input.equals("1") || input.equals("2"))
			ret = true;
		return ret;
	}

	public static boolean isPhoneNumber(String input) {
		boolean ret = true;
		if(input.length() != 13)
			return false;
		else {
			if(!isInteger(input.substring(0, 2)))
				return false;
			if(!isInteger(input.substring(4, 7)))
				return false;
			if(!isInteger(input.substring(9, 12)))
				return false;
			if(input.charAt(3) != '-')
				return false;
			if(input.charAt(3) != '-')
				return false;
		}
		return ret;
	}
}
