package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.revature.models.User;
import com.revature.utils.ConnectionUtil;

public class UserDao implements UserDaoInterface {

	@Override
	public ArrayList<User> getUser() {
		try(Connection conn = ConnectionUtil.getConnection()){ //try to establish a DB connection, so we can run a query
			
			//initialize an empty ResultSet that will store the results of our query 
			ResultSet rs = null; //we need this for select statements, so that the returned data can get stored
			
			//write the query, assign it to a String variable
			String sql = "SELECT * FROM user_info;"; 
			
			//creating an object to send the query to our DB using our Connection object's createStatement() method
			Statement s = conn.createStatement(); 
			
			//execute the query (sql) using our Statement object (s), put it in our ResultSet (rs) 
			rs = s.executeQuery(sql); //again, the ResultSet just holds all the data we get back from the select statement
			
			
			ArrayList<User> userList = new ArrayList<>(); //create a List that will be populated with the returned employees
			
			
			while(rs.next()) { //while there are results left in the ResultSet (rs)
				User tempUser = new User(
						rs.getString("f_name"),
						rs.getString("l_name"),
						rs.getString("date_of_birth"),
						rs.getString("user_name"),
						rs.getString("password")
					);
				
				//add the newly created Employee object into the ArrayList of Employees
				userList.add(tempUser);

			}
			
			return userList; //Finally, if successful, return the List of Employees
			
		} catch (SQLException e) { //if something goes wrong accessing our data, it will get caught
			System.out.println("Something went wrong when trying to access your DB");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void addUser(User u) {
		try(Connection conn = ConnectionUtil.getConnection()) {
		
			
			//we're going to create a SQL statement using parameters to insert a new Employee
			String sql = "insert into user_info (f_name,l_name,date_of_birth,user_name,password)"
					+ "	values(?,?,?,?,?); "; //these are parameters!!! We have to now specify the value of each "?"
		
			
			PreparedStatement ps = conn.prepareStatement(sql); //we use PreparedStatements for SQL commands with parameters
			
			///System.out.println(u.toString());
		
			//System.out.println("1");
			ps.setString(1, u.getFirstName());
			//System.out.println("2");
			ps.setString(2, u.getLastName()); //this takes our Java Date, and turns it into a SQL Date.
			//System.out.println("3");
			ps.setString(3, u.getDateOfBirth());
			ps.setString(4, u.getUserName());
			ps.setString(5, u.getPassword());
			
			//this method actually sends and executes the SQL command that we built
			ps.executeUpdate(); //we use executeUpdate for inserts, updates, and deletes. 
			
			//send confirmation to the console if successful
			System.out.println("New User : " + u.getUserName() );
			
		} catch(SQLException e) {
			System.out.println("Add User failed!");
			e.printStackTrace();
		}
	}

	@Override
	public void updateFirstName(String fName, String user) {
		try(Connection conn = ConnectionUtil.getConnection()) {
			
			String sql = "update user_info set f_name = ? where user_name = ?;"; //write out the SQL query
			
			PreparedStatement ps = conn.prepareStatement(sql); //put the SQL query into a PreparedStatement
			
			//set the values in the prepared statement with the values inputed by the user
			ps.setString(1, fName);
			ps.setString(2, user);
			ps.executeUpdate(); 
		
			
		} catch (SQLException e) {
			System.out.println("you BROKE IT you messed up it's all ruined because of YOU >:(");
			e.printStackTrace();
		}
		
	}
	@Override
	public void updateLastName(String lName, String user) {
		try(Connection conn = ConnectionUtil.getConnection()) {
			
			String sql = "update user_info set l_name = ? where user_name = ?;"; //write out the SQL query
			
			PreparedStatement ps = conn.prepareStatement(sql); //put the SQL query into a PreparedStatement
			
			//set the values in the prepared statement with the values inputed by the user
			ps.setString(1, lName);
			ps.setString(2, user);
			ps.executeUpdate(); 
		
			
		} catch (SQLException e) {
			System.out.println("you BROKE IT you messed up it's all ruined because of YOU >:(");
			e.printStackTrace();
		}
		
	}
}
