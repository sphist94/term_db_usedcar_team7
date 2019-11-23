package Functions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Utilities {
	public static String getPasswordWithinEclipse(String msg) 
            throws IOException 
    {
        // In Eclipse IDE
        System.out.print(msg);
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                System.in));
        String password = reader.readLine();
        if (password != null) {
            if (password.length() <= 0) {
                System.out.println("Invalid input\n");
                throw new IOException("Error reading in password");
            }
        }
        return password;
    }
}
