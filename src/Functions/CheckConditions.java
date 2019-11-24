package Functions;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
			Pattern IntegerPattern = Pattern.compile("[0-9]*");
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
		if(input.equals("-")) 
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
		if(input.equals("-")) 
			return true;
		
		pattern = Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2}");
		matcher = pattern.matcher(input);
		if (matcher.find())
			return isValidateDate(input);
		return false;
	}
	
	public static boolean isEmail(String input) {
		if(input.equals("-")) 
			return true;
		
		pattern = Pattern.compile("[0-9a-zA-Z]+@[0-9a-zA-Z]+.[0-9a-zA-Z]+");
		matcher = pattern.matcher(input);
		return matcher.find();
	}
	
	public static boolean isAddress(String input) {
		if(input.equals("-")) 
			return true;
		
		pattern = Pattern.compile("[0-9, a-zA-Z]*");
		matcher = pattern.matcher(input);
		return matcher.find();
	}
	
	public static boolean isOccupation(String input) {
		if(input.equals("-")) 
			return true;
		
		pattern = Pattern.compile("[a-zA-Z]*");
		matcher = pattern.matcher(input);
		return matcher.find();
	}
}
