package com.revature.daos;

import java.util.ArrayList;

import com.revature.models.Ticket;

public interface TicketDaoInterface {
	
	public ArrayList<Ticket> getAllTickets();
	
	public ArrayList<Ticket> getTicketByName();
	
	public void addTicket(Ticket t);
	
	public void deleteTicket(int confirmationNum);

}
