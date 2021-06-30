package com.revature;

import java.sql.Connection;
import java.sql.SQLException;

import com.revature.models.Menu;
import com.revature.utils.ConnectionUtil;

public class Launcher {

	public static void main(String[] args) {
		
		try (Connection conn = ConnectionUtil.getConnection())
		{
			System.out.println("Conected!");
		}catch(SQLException e)	
		{
			System.out.println("Error Conection faild");
		}
		
		
		Menu menu = new Menu();
		menu.loadUsers();
		menu.display();

	}

}
