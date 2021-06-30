package com.revature.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

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
				+ "'LogOut'\n";
		
		String n = "'Login': to Access or make an account\n"
				+ "'Stations': Search Stations\n"
				+ "'Ticket': Search up Ticket\n"			
				+ "'Schedule': stations and stops\n"
				+ "'exit'";
		
		return (login) ? y : n;
		
	}
	
	//displays menu
	public void display() {
		
		
		boolean displayMenu = true; //this will toggle whether the menu continues after user input
		System.out.println(login);
		
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
			
		}
		
	}
	
	
	//Checks user input
	private boolean checkSelection(String input) {
		
		//Checks for empty string
		if (!(input.isEmpty())) {
			
			//switches over user input
			switch (input.toLowerCase().trim()) {

			case "login": {
				
				UserLoginSignup.display(credentials);
				break;
			}
			
			case "logout": {

				UserLoginSignup.logOut();
				break;
			}

			case "stations": {
				caseStation();
				break;
			}

			case "ticket": {
				System.out.println("Enter Confirmation Number:\n");
				int num = Integer.parseInt(scan.next().trim());
				ArrayList<Ticket> allTickets = ticketDao.getAllTickets();
				for (Ticket t : allTickets)
					if(t.confirmation_num == num)
						System.out.println(t.toString());
				break;
				
			}
			case "previous": {
				
				
				ArrayList<Ticket> allTickets = ticketDao.getAllTickets();
				String name = userList.get(index).firstName + " " + userList.get(index).lastName;
				System.out.println(name);
				for (Ticket t : allTickets)
					
					if(t.passenger_name.equals(name)) {
						System.out.println(t.toString());
					}
				break;

			}
			

			case "schedule": {

				//ticketDao.addTicket(new Ticket(3, "Added User", "GreenLand", "06:45:00"));
				ArrayList<Schedule> scList = scheduleDao.getSchedule();
				for (Schedule s : scList) 
					System.out.println(s.toString());
				scan.next();
				break;
			}
			case "account": {
				User account = returnUser();
				System.out.println(account.toString());
				caseEditUser(account);
				break;
			}

			case "exit": {
				System.out.println("Have a good day!");
				return false;
			}
			
			case "admin": {
				init();
			}

			//this default block will catch anything that doesn't match a menu option
			default: {
				System.out.println("Please try again");
				break;
			}
			}
			
		}
		else {
			System.out.println("Please try again");
			return true;
		}
		return true;

	}
	
	
	private void caseEditUser(User account) {
		boolean loop = true;
		while (loop) {
			System.out.println("----------------\n" + "'Edit' : to edit\n" + "'Exit' : to return\n");
			String e = scan.nextLine().trim();
			if (e.equals("edit")) {
				editMenu(account.userName);
				loop = false;
			} else if (e.equals("exit")) {
				System.out.println("Returning...");
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
				break;
				}
			case "last":{
				System.out.println("new last name:\n");
				String x = scan.nextLine().trim();
				userDao.updateLastName(x, u);
				System.out.println("Success!");
				break;
				}		
			case "exit":{
				System.out.println("Returning...");
				loop = false;
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
		System.out.println("Pick a station to create a new ticket");
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
			
			if(avalibleLocations.contains(i)) {
				createTicket(i);
			}else if(i.equals("exit")) {
				System.out.println("Returning...");
				clearScreen();
				x = false;
			}
			
		}
		
	}



	private void createTicket(String targetLocation) {
		
		ArrayList<Train> trList = trainDao.getTrains();
		ArrayList<Schedule> scList = scheduleDao.getSchedule();
		ArrayList<Ticket> tList = ticketDao.getAllTickets();
		User u = returnUser();
		String name = u.firstName + " " + u.lastName;
		
//		Ticket t = new Ticket(
//				tList.size(),
//				name,
//				
//				);
	}

	private User returnUser() {
		return userList.get(index);
		//might be -1
	}

	private void clearScreen() {
		for (int i = 0; i < 20; i++) {
			System.out.println(); 
			
		}
	}
	
	public void toggleLogin(String userName, boolean login, int index) {
		
		this.userName = userName;
		this.login = login;
		this.index = index;
	}

	public void loadUsers() {
//		User x = new User("F name","L Name" , "201-08-13","A-Rod","Password");
//		userDao.addUser(x);
		userList = userDao.getUser();
	//	int  i= 0;
		for (User u : userList) {
			System.out.println(u.toString());
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
