package com.revature.models;

public class Ticket extends Schedule{
	
	int confirmation_num;
	String passenger_name;
	String departure_station;
	String departure_time;
	
	
	public Ticket() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Ticket(int confirmation_num, String passenger_name, String departure_station, String departure_time) {
		super();
		this.confirmation_num = confirmation_num;
		this.passenger_name = passenger_name;
		this.departure_station = departure_station;
		this.departure_time = departure_time;
	}


	public int getConfirmation_num() {
		return confirmation_num;
	}
	public void setConfirmation_num(int confirmation_num) {
		this.confirmation_num = confirmation_num;
	}
	public String getPassenger_name() {
		return passenger_name;
	}
	public void setPassenger_name(String passenger_name) {
		this.passenger_name = passenger_name;
	}
	public String getDeparture_station() {
		return departure_station;
	}
	public void setDeparture_station(String departure_station) {
		this.departure_station = departure_station;
	}
	public String getDeparture_time() {
		return departure_time;
	}
	public void setDeparture_time(String departure_time) {
		this.departure_time = departure_time;
	}
	
	@Override
	public String toString() {
		return "Ticket [confirmation_num=" + confirmation_num + ", passenger_name=" + passenger_name
				+ ", departure_station=" + departure_station + ", departure_time=" + departure_time + "]";
	}
	
	

}
