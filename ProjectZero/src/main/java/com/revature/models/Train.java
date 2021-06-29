package com.revature.models;


public class Train {
	
	String departure_location;
	String train_name;	
	String arrival_location;
	
	public Train() {
		super();
		// TODO Auto-generated constructor stub
	}
		
	public Train(String departure_location, String train_name, String arrival_location) {
		this.departure_location = departure_location;
		this.train_name = train_name;
		this.arrival_location = arrival_location;
	}




	public String getTrain_name() {
		return train_name;
	}
	public void setTrain_name(String train_name) {
		this.train_name = train_name;
	}
	public String getArrival_location() {
		return arrival_location;
	}
	public void setArrival_location(String arrival_location) {
		this.arrival_location = arrival_location;
	}
	public String getDeparture_location() {
		return departure_location;
	}
	public void setDeparture_location(String departure_location) {
		this.departure_location = departure_location;
	}
	@Override
	public String toString() {
		return "Train [departure_location=" + departure_location + ", train_name=" + train_name + ", arrival_location="
				+ arrival_location + "]";
	}
	
	
	
	
}
