package com.revature.daos;

import java.util.ArrayList;

import com.revature.models.Schedule;

public interface ScheduleDaoInterface {

	public ArrayList<Schedule> getSchedule();
	
	public ArrayList<String> getCurrentStation();
	
	public void addSchedule(Schedule s);
	
}
