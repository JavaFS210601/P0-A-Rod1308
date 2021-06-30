package com.revature.utils;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
	Scanner scan = new Scanner(System.in);

	public static boolean validPassword(String pass) {
		String regex = "^(?=.*[0-9])"
                + "(?=.*[a-z])"
                + "(?=.*[A-Z])"
                + "(?=.*[@#$%^&])"
                + "(?=\\S+$).{8,20}$";

		// Compile the ReGex
		Pattern p = Pattern.compile(regex);
		if (pass == null) {
			return false;
		}

		Matcher m = p.matcher(pass);
		
		return m.matches();
	}
	
	

}
