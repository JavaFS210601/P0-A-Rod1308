package com.revature.models;

import java.util.ArrayList;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.revature.daos.ScheduleDao;
import com.revature.daos.TicketDao;
import com.revature.daos.TrainDao;
import com.revature.utils.UserLoginSignup;
import com.revature.utils.Validation;

public class Menu {
	
	//initial menu isn't logged into
	boolean login = false;
	
	
	TrainDao trainDao = new TrainDao();
	ScheduleDao scheduleDao = new ScheduleDao();
	TicketDao ticketDao = new TicketDao();
	
	Validation val = new Validation();
	Scanner scan = new Scanner(System.in); //Scanner object to parse user input
	
	
	//Menu message
	private String menuOptions() {
		String y = "'Stations': Search Travel Location\n"
				+ "'Ticket': Search up Ticket\n"
				+ "'Previous Ticket': All previous tickets\n"
				+ "'Schedule': stations and stops\n"
				+ "'Sign out'\n"
				+ "'exit'";
		
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
		
		
			
		System.out.println("Welcome !\n");
		
		//display the menu options as long as displayMenu is true.
		while(displayMenu) {		
			
			System.out.println("CHOOSE AN OPTION:\n\n");
			
			//menu options
			String options = menuOptions();
			System.out.println(options);
			
			//checks which option user chose
			String input = scan.nextLine();
			displayMenu = checkSelection(input);
			
		}
		
	}
	
	
	//Checks user input
	private boolean checkSelection(String input) {
		
		//Checks for empty string
		if (!(input.equals(null))) {
			
			//switches over user input
			switch (input.toLowerCase().trim()) {

			case "login": {
				UserLoginSignup.display();
				break;
			}

			case "stations": {
				caseStation();
				break;
			}

			case "ticket": {
				System.out.println("prompt for confirmation num and return ticket info");
				ArrayList<Ticket> allTickets = ticketDao.getAllTickets();
				for (Ticket t : allTickets)
					System.out.println(t.toString());
				break;
				
			}

			case "schedule": {

				ticketDao.addTicket(new Ticket(3, "Added User", "GreenLand", "06:45:00"));
				//	cd.addCar(new Car(tireInput, colorInput)); 	
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
				//searchAvalibleTickets();
			}else if(i.equals("exit")) {
				System.out.println("Returning...");
				x = false;
			}
			
		}
		
	}



	private User returnUser() {
		return null;
	}

	private void clearScreen() {
		System.out.print("\033[H\033[2J");  
	    System.out.flush();  
		
	}
	
	public void toggleLogin(String userName, boolean x) {
		login = x;
	}
}
