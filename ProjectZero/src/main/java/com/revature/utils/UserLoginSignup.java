package com.revature.utils;

import java.util.ArrayList;
import java.util.Scanner;

import com.revature.models.Menu;
import com.revature.models.User;

public class UserLoginSignup {
	
	static ArrayList<User> users = new ArrayList<User>();
	
	static Scanner sc = new Scanner(System.in);
	
	static public void display(){
		boolean displayMenu = true;
		
		while(displayMenu) {
						
			System.out.println("Please enter Username, or type 'SignUp' to make an account\n");
			String input = sc.nextLine();
			
			if (input.trim().toLowerCase().equals("signup")) {
				makeUser();
				break;
			}else 
				if (input.trim().toLowerCase().equals("user InArray")) {
					String user = sc.nextLine();
					
					System.out.println("Password:\n");
					String pass = sc.nextLine();
					
					
				
					if(pass.trim().toUpperCase().equals("Pass same index as array")) {
						Menu m = new Menu();
						m.toggleLogin(user,true);
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

	private static void makeUser() {
		System.out.println("First name:");
		String fName = sc.nextLine();
		
		System.out.println("Last name:");
		String lName = sc.nextLine();
		
		System.out.println("Date of birth 'yyyy-MM-dd' :");
		String dateOfBirth = sc.nextLine();
		
		System.out.println("Username");
		String userName = sc.nextLine();
		
		System.out.println("Password");
		String pass = sc.nextLine();
		
		System.out.println("Confirm Password");
		String temp = sc.nextLine();
		
		if (temp.equals(pass)) {
			System.out.println("User Created");
			User x = new User(fName,lName,dateOfBirth,userName,pass);
			users.add(x);
			System.out.println(users.get(0).toString());
		}else {
			System.out.println("Wrong'!");
		}
			
		Menu m = new Menu();
		m.toggleLogin(userName,true);
		m.display();
		
		
	}

}
