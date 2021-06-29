package com.revature.daos;

import java.util.ArrayList;

import com.revature.models.Train;

public interface TrainDaoInterface {
	
	public ArrayList<Train> getTrains();
	
	
	public void addTrain(Train t);
	

}
