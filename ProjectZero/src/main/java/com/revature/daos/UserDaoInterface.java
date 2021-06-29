package com.revature.daos;

import java.util.ArrayList;

import com.revature.models.Schedule;
import com.revature.models.User;

public interface UserDaoInterface {
	
	public ArrayList<User> getUser();
	
	
	public void addUser(User u);
	

}
