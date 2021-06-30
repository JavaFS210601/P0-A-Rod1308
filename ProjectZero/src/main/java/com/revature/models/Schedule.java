package com.revature.models;

public class Schedule extends Train {
	
	String depature_day;
	String current_station;
	String arrival_location;


	public Schedule() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Schedule(String depature_day, String current_station, String arrival_location) {
		super();
		this.depature_day = depature_day;
		this.current_station = current_station;
		this.arrival_location = arrival_location;
	}
	
	
	public String getDepature_day() {
		return depature_day;
	}

	public void setDepature_day(String depature_day) {
		this.depature_day = depature_day;
	}

	public String getCurrent_station() {
		return current_station;
	}

	public void setCurrent_station(String current_station) {
		this.current_station = current_station;
	}

	public String getArrival_location() {
		return arrival_location;
	}

	public void setArrival_location(String arrival_location) {
		this.arrival_location = arrival_location;
	}
	
	public void display() {
		System.out.println(
				"Depature date= " + depature_day + "\nCurrent station= " + current_station + "\nArrival location="
						+ arrival_location 
				);
	}

	@Override
	public String toString() {
		return "Schedule [depature_day=" + depature_day + ", current_station=" + current_station + ", arrival_location="
				+ arrival_location + "]";
	}

	
	

}
