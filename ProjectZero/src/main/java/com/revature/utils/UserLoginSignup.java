package com.revature.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

import com.revature.daos.UserDao;
import com.revature.models.Menu;
import com.revature.models.User;

public class UserLoginSignup {
	
	static ArrayList<User> users = new ArrayList<User>();
	
	static Scanner sc = new Scanner(System.in);
	static int index = 0;
	static String userName = "";
	
	
	static public void display(Map<String, String> credentials ){
		boolean displayMenu = true;
		
		while(displayMenu) {
						
			System.out.println("Please enter Username, or type 'SignUp' to make an account\n");
			String input = sc.nextLine();
			
			ArrayList<String> user = new ArrayList<>();
			ArrayList<String> pass = new ArrayList<>();
			for(Entry<String, String> mp : credentials.entrySet()) {
				user.add(mp.getKey());
				pass.add(mp.getValue());
			}
			
			if (input.trim().toLowerCase().equals("signup")) {
				makeUser(user);
				break;
			}else 
				if (user.contains(input)) {			 
					index = user.indexOf(input);
					userName = input;
					System.out.println("Password:\n");
					String password = sc.nextLine();
				
					if(password.equals(pass.get(index))) {
						Menu m = new Menu();
						m.loadUsers();
						m.toggleLogin(input,true,index);
						m.display();
				}
				
			}
			else if (input.trim().toLowerCase().equals("Exit")) {
				displayMenu = false;
			}
			else
				System.out.println("Try again");
			
		}
		
		
	}
	
	public static void updateMenu() {
		Menu m = new Menu();
		m.loadUsers();
		m.toggleLogin(userName,true,index); 
		m.display();
	}

	private static void makeUser(ArrayList<String> userNameSet) {
		
		while (true) {
			System.out.println("First name:");
			String fName = sc.nextLine();
			while (!(fName.isEmpty())) {

				System.out.println("Last name:");
				String lName = sc.nextLine();
				while (!(lName.isEmpty())) {

					System.out.println("Date of birth:");
					String dateOfBirth = getDate();
					while (!(dateOfBirth.isEmpty())) {

						System.out.println("Username");
						String userName = sc.nextLine();

						while ((!(userNameSet.contains(userName))) && (userName.trim().length() > 5)) {

							System.out.println(
									"Password (8-20 characters) :\n\t1+ Uppercase\n\t 2+ numbers\n\t allowed symbols [@#$%^&]");
							String pass = sc.nextLine();
							//	while (Validation.validPassword(pass)) {
							while (!(pass.isEmpty())) {

								System.out.println("Confirm Password");
								String temp = sc.nextLine();
								if (temp.equals(pass)) {
									System.out.println("User Created");
									User x = new User(fName, lName, dateOfBirth, userName, pass);
									users.add(x);
									UserDao ud = new UserDao();
									ud.addUser(x);
									
									Menu m = new Menu();
									m.loadUsers();
									m.toggleLogin(userName, true, users.size()-1);
									m.display();
								}

							}

						}

					}

				}

			} 
		}
					
	}

	private static String getDate() {
		while(true) {
			System.out.println("\tYear: (YYYY)");
			String y = sc.next().trim();
			while(!y.isEmpty()) {
				System.out.println("\tMonth: (MM)");
				String m = sc.next().trim();
				while(!m.isEmpty()) {
					System.out.println("\tDay: (DD)");
					String d = sc.next().trim();
					if(!d.isEmpty())
						return (y + "-" + m + "-" + d);
				}
			}
			
			return null;
			}
		
	}

	public static void logOut() {
		Menu menu = new Menu();
		menu.loadUsers();
		menu.toggleLogin("", false,0);
		menu.display();
		
	}

}
