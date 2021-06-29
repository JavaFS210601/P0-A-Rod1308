package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.revature.models.Train;
import com.revature.utils.ConnectionUtil;

public class TrainDao implements TrainDaoInterface{

	@Override
	public ArrayList<Train> getTrains() {

		try(Connection conn = ConnectionUtil.getConnection()){ //try to establish a DB connection, so we can run a query
			
			//initialize an empty ResultSet that will store the results of our query 
			ResultSet rs = null; //we need this for select statements, so that the returned data can get stored
			
			//write the query, assign it to a String variable
			String sql = "SELECT * FROM train;"; 
			
			//creating an object to send the query to our DB using our Connection object's createStatement() method
			Statement s = conn.createStatement(); 
			
			//execute the query (sql) using our Statement object (s), put it in our ResultSet (rs) 
			rs = s.executeQuery(sql); //again, the ResultSet just holds all the data we get back from the select statement
			
			
			ArrayList<Train> trainList = new ArrayList<>(); //create a List that will be populated with the returned employees
			
			
			while(rs.next()) { //while there are results left in the ResultSet (rs)
				
				//Create a new Employee Object from each returned record
				//This is the Employee Class's all args constructor
				//And we're filling it with each column of the Employee record
				Train tempTrain = new Train(
						rs.getString("train_name"),
						rs.getString("departure_location"),
						rs.getString("arrival_location")
					);
				
				//add the newly created Employee object into the ArrayList of Employees
				trainList.add(tempTrain);

			}
			
			return trainList; //Finally, if successful, return the List of Employees
			
		} catch (SQLException e) { //if something goes wrong accessing our data, it will get caught
			System.out.println("Something went wrong when trying to access your DB");
			e.printStackTrace();
		}
		
		
		return null; //java will yell if we don't have this, cause the try isn't guaranteed to succeed
	}

	@Override
	public void addTrain(Train t) {
		try(Connection conn = ConnectionUtil.getConnection()) {
		
			
			//we're going to create a SQL statement using parameters to insert a new Employee
			String sql = "insert into train (train_name,departure_location,arrival_location)"
					+ "	values(?,?,?); "; //these are parameters!!! We have to now specify the value of each "?"
		
			
			PreparedStatement ps = conn.prepareStatement(sql); //we use PreparedStatements for SQL commands with parameters
			
			//use the PreparedStatement object's methods to insert values into the SQL query's ?s
			//the values will come from the Employee object we sent in
			//this requires two arguments, the number of the "?", and the value to give it
			System.out.println(t.toString());
		
			System.out.println("1");
			ps.setString(1, t.getTrain_name());
			System.out.println("2");
			ps.setString(2, t.getDeparture_location()); //this takes our Java Date, and turns it into a SQL Date.
			System.out.println("3");
			ps.setString(3, t.getArrival_location());
			
			//this method actually sends and executes the SQL command that we built
			ps.executeUpdate(); //we use executeUpdate for inserts, updates, and deletes. 
			
			//send confirmation to the console if successful
			System.out.println("New Train" + t.getTrain_name() );
			
		} catch(SQLException e) {
			System.out.println("Add employee failed!");
			e.printStackTrace();
		}
		
	}

}
