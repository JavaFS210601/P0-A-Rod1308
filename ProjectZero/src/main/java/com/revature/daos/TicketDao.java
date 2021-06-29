package com.revature.daos;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.revature.models.Ticket;
import com.revature.models.Train;
import com.revature.utils.ConnectionUtil;

public class TicketDao implements TicketDaoInterface{

	@Override
	public ArrayList<Ticket> getAllTickets() {
		try(Connection conn = ConnectionUtil.getConnection()){ //try to establish a DB connection, so we can run a query
			
			//initialize an empty ResultSet that will store the results of our query 
			ResultSet rs = null; //we need this for select statements, so that the returned data can get stored
			
			//write the query, assign it to a String variable
			String sql = "SELECT * FROM ticket;"; 
			
			//creating an object to send the query to our DB using our Connection object's createStatement() method
			Statement s = conn.createStatement(); 
			
			//execute the query (sql) using our Statement object (s), put it in our ResultSet (rs) 
			rs = s.executeQuery(sql); //again, the ResultSet just holds all the data we get back from the select statement
			
			
			ArrayList<Ticket> ticketList = new ArrayList<>(); //create a List that will be populated with the returned employees
			System.out.println(rs);
			
			
			while(rs.next()) { //while there are results left in the ResultSet (rs)
				
				//Create a new Employee Object from each returned record
				//This is the Employee Class's all args constructor
				//And we're filling it with each column of the Employee record
				Ticket tempTicket = new Ticket(
						rs.getInt("confirmation_num"),
						rs.getString("passenger_name"),
						rs.getString("departure_station"),
						rs.getString("departure_time")
					);
				
				//add the newly created Employee object into the ArrayList of Employees
				ticketList.add(tempTicket);

			}
			
			return ticketList; //Finally, if successful, return the List of Employees
			
		} catch (SQLException e) { //if something goes wrong accessing our data, it will get caught
			System.out.println("Something went wrong when trying to access your DB");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<Ticket> getTicketByName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addTicket(Ticket t) {
		
		
	}

}
