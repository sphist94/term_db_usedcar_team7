package Functions;

import java.util.ArrayList;

public class Utilities {
	public static ArrayList<String> parseMultiValues(String input) {
		ArrayList<String> ret = new ArrayList<>();
		input = input.replaceAll(" ", "");
		String temp = "";
		for (int i = 0; i < input.length(); ++i) {
			if (input.charAt(i) != ',') {
				temp += input.charAt(i);
			} else {
				ret.add(temp);
				temp = "";
			}
		}
		if(!temp.isEmpty())
			ret.add(temp);
		return ret;
	}
}
