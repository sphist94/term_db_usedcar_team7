package Functions;

public class CheckConditions {
	public static boolean isNumber(String input) {
		try {
			Double.parseDouble(input);
			return true;
		}catch(NumberFormatException e) {
			return false;
		}
	}
	
	public static boolean isInteger(String input) {
		boolean ret = false;
		if(isNumber(input)) {
			ret = true;
			for(int i=0;i<input.length();++i) {
				char chr = input.charAt(i);
				if(chr < '0' || chr > '9') {
					ret = false;
				}
			}
		}
		return ret;
	}
	
	public static boolean isId(String input) {
		boolean ret = false;
				
		return ret;
	}
}
