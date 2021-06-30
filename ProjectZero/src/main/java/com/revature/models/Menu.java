package com.revature.models;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.daos.ScheduleDao;
import com.revature.daos.TicketDao;
import com.revature.daos.TrainDao;
import com.revature.daos.UserDao;
import com.revature.utils.UserLoginSignup;
import com.revature.utils.Validation;

public class Menu {
	
	//initial menu isn't logged into
	boolean login = false;
	String userName = "";
	int index = 0;
	
	final Logger log = LogManager.getLogger(Menu.class);
	
	
	
	TrainDao trainDao = new TrainDao();
	ScheduleDao scheduleDao = new ScheduleDao();
	TicketDao ticketDao = new TicketDao();
	UserDao userDao = new UserDao();
	
	Validation val = new Validation();
	Scanner scan = new Scanner(System.in); //Scanner object to parse user input
	
	ArrayList<User> userList = new ArrayList<>();
	Map<String, String> credentials = new HashMap<String, String>();

	
	
	//Menu message
	private String menuOptions() {
		String y = "'Stations': Search Travel Location\n"
				+ "'Ticket': Search up Ticket\n"
				+ "'Previous': All previous tickets\n"
				+ "'Schedule': stations and stops\n"
				+ "'Account': to view and edit User\n"
				+ "'LogOut'\n\n\n";
		
		String n = "'Login': to Access or make an account\n"
				+ "'Stations': Search Stations\n"
				+ "'Ticket': Search up Ticket\n"			
				+ "'Schedule': stations and stops\n"
				+ "'exit'\n\n\n";
		
		return (login) ? y : n;
		
	}
	
	//displays menu
	public void display() {
		
		
		boolean displayMenu = true; //this will toggle whether the menu continues after user input
	//	System.out.println(login);----------------------------------------------------------------------------------------
		
		//display the menu options as long as displayMenu is true.
		String options = menuOptions();
		while(displayMenu) {		
			
			//menu options
			clearScreen();
			System.out.println("Welcome " + userName +" !\n");
			System.out.println("CHOOSE AN OPTION:\n");
			System.out.println(options);
			
			//checks which option user chose
			String input = scan.nextLine();
			displayMenu = checkSelection(input);
			clearScreen();
			
		}
		
	}
	
	
	//Checks user input
	private boolean checkSelection(String input) {
		
		//Checks for empty string
		if (!(input.isEmpty())) {
			
			//switches over user input
			switch (input.toLowerCase().trim()) {

			case "login": {
				log.info("User Logging in");
				clearScreen();
				UserLoginSignup.display(credentials);
				clearScreen();
				break;
			}
			
			case "logout": {
				log.info("User Logging out");
				clearScreen();
				UserLoginSignup.logOut();
				clearScreen();
				break;
			}

			case "stations": {
				clearScreen();
				caseStation();
				clearScreen();
				break;
			}

			case "ticket": {
				clearScreen();
				Boolean loop = true;
				while(loop) {
					System.out.println("Enter Confirmation Number:\n");
					String s = scan.next().trim();
					int num = Integer.parseInt(s);
					ArrayList<Ticket> allTickets = ticketDao.getAllTickets();
					for (Ticket t : allTickets) {
						if (t.confirmation_num == num) {
							t.display();
							System.out.println();
							loop = false;
						}
					}
				}
				
				pressEnter();
				clearScreen();
				break;
				
			}
			case "previous": {
				clearScreen();
				ArrayList<Ticket> allTickets = ticketDao.getAllTickets();
				String name = userList.get(index-1).firstName + " " + userList.get(index-1).lastName;
				System.out.println(name.toUpperCase() + "\n");
				for (Ticket t : allTickets) {
					if(t.passenger_name.equals(name)) {
						System.out.println("==========================================\n");
						t.display();;
						System.out.println("\n==========================================");
					}
				}
				System.out.println("\nType:\n'Delete': to delete a ticket\n'Exit'");
				String p = scan.nextLine().trim().toLowerCase();
				if (p.equals("delete")) {
					deleteTicket();
				}else if(p.equals("exit"))
					System.out.println("Returning ...");
				clearScreen();
				break;

			}
			

			case "schedule": {

				//ticketDao.addTicket(new Ticket(3, "Added User", "GreenLand", "06:45:00"));
				ArrayList<Schedule> scList = scheduleDao.getSchedule();
				
				for (Schedule s : scList) {
					System.out.println("------------------------------\n");
					s.display();
					System.out.println("\n");
				}
				System.out.println("------------------------------");
				pressEnter();
				break;
				
				
			}
			case "account": {
				clearScreen();
				User account = returnUser();
				account.display();
				caseEditUser(account);
				clearScreen();
				break;
			}

			case "exit": {
				System.out.println("Have a good day!");
				System.exit(0);;
			}
			
			case "admin": {
				
				clearScreen();
				//init();
				for (User u : userList) {
						System.out.println(u.toString());
			
						}
				clearScreen();
			}

			//this default block will catch anything that doesn't match a menu option
			default: {
				System.out.println("Please try again");
				clearScreen();
				break;
			}
			}
			
		}
		else {
			System.out.println("Please try again");
			clearScreen();
			return true;
		}
		return true;

	}
	
	
	private void deleteTicket() {
		System.out.println("Please enter Ticket Confimation nm\n");
		int i = scan.nextInt();
		ArrayList<Ticket> tList = ticketDao.getAllTickets();
		
		for (Ticket t : tList)
			if (i == t.confirmation_num) {
				ticketDao.deleteTicket(t.confirmation_num);
			}
		
		
	}

	private void caseEditUser(User account) {
		boolean loop = true;
		while (loop) {
			System.out.println("----------------\n" + "'Edit' : to edit\n" + "'Exit' : to return\n");
			String e = scan.nextLine().trim();
			if (e.equals("edit")) {
				editMenu(account.userName);
				clearScreen();
				loop = false;
			} else if (e.equals("exit")) {
				System.out.println("Returning...");
				clearScreen();
				loop = false;
			} 
		}
			
	}

	private void editMenu(String u) {
		boolean loop = true;
		while (loop) {
			System.out.println("What field would you like to update?\n" + "'First' to eddit first name\n"
					+ "'Last' to eddit last name\n" + "'Exit'");
			String s = scan.nextLine().trim().toLowerCase();
			switch (s) {
			case "first":{
				System.out.println("new first name:\n");
				String x = scan.nextLine().trim();
				userDao.updateFirstName(x, u);
				System.out.println("Success!");
				clearScreen();
				break;
				}
			case "last":{
				System.out.println("new last name:\n");
				String x = scan.nextLine().trim();
				userDao.updateLastName(x, u);
				System.out.println("Success!");
				clearScreen();
				break;
				}		
			case "exit":{
				System.out.println("Returning...");
				loop = false;
				clearScreen();
				break;
				}		
			default:
				// code block
			}
		}
		
	}

	private void init() {
		
		
		
//		Train t = new Train("t_name","Departure", "new Place");
//		trainDao.addTrain(t);
		System.out.println(1);
		ArrayList<Train> ts = trainDao.getTrains();
		for(Train x : ts)
			System.out.println(x.toString());
		
		System.out.println(2);
		ArrayList<Schedule> sl = scheduleDao.getSchedule();
		for(Schedule s : sl)
			System.out.println(s.toString());
		
		System.out.println(3);
		ArrayList<Ticket> ticket = ticketDao.getAllTickets();
		for(Ticket t : ticket)
			System.out.println(t.toString());
		//ticket.get(0).train_name = 
		
		
	}

	private void caseStation () {
		System.out.println("------------------------------");
		System.out.println("List of stations");
		System.out.println("------------------------------\n");
		ArrayList<Train> trainList = trainDao.getTrains();
		
		
		//selecting station
		boolean x = true;
		while (x) {
			ArrayList<String> avalibleLocations = new ArrayList<>();
			for (Train t : trainList) {
				System.out.println("'" + t.getArrival_location() + "'"); //print them out one by one via the enhanced for loop
				avalibleLocations.add(t.getArrival_location().toLowerCase());
			}
			System.out.println("exit");
			String i = scan.nextLine().trim().toLowerCase();
			
			if(i.equals("exit")) {
				System.out.println("Returning...");
				clearScreen();
				x = false;
			}
			
		}
		
	}



	private User returnUser() {
		return userList.get(index-1);
		//might be -1
	}

	private void clearScreen() {
		for (int i = 0; i < 40; i++) {
			System.out.println(); 
			
		}
	}
	
	public void toggleLogin(String userName, boolean login, int index) {
		
		this.userName = userName;
		this.login = login;
		this.index = index;
	}
	
	 private void pressEnter()
	 { 
	        System.out.println("Press Enter key to continue...");
	        try
	        {System.in.read();}  
	        catch(Exception e)
	        {}  
	 }

	public void loadUsers() {
//		User x = new User("F name","L Name" , "201-08-13","A-Rod","Password");
//		userDao.addUser(x);
		userList = userDao.getUser();
	//	int  i= 0;
		for (User u : userList) {
	//		System.out.println(u.toString()); ----------------------------------------------------------
			credentials.put(u.userName, u.password);
	//		i++;
		}
	//	System.out.println(i);
		//index = i;
//		for (Map.Entry<String, String> k : credentials.entrySet()) {
//			System.out.println(k.getKey() + k.getValue());
//			
//		}
		
	}
}
