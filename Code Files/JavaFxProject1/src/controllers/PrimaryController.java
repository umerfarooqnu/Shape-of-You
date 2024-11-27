package controllers;

import java.util.List;

import db.DBHandler;
import handlers.AuthenticationHandler;
import models.Person;
import models.Trainer;
import models.User;
import repositories.PersonRepository;

public class PrimaryController
{
	private final DBHandler db;
	private final PersonRepository personRepo;
	private final AuthenticationHandler authHandler;
	
	public final AuthenticationController authController;
	public User user;
	public Trainer trainer;
	
	public PrimaryController()
	{		
		db = new DBHandler(); 	
    	personRepo = new PersonRepository(db);   	
    	authHandler = new AuthenticationHandler (personRepo);  	
    	authController = new AuthenticationController (authHandler);
    	
	}
	
	public boolean authenticate(String username, String password)
	{
		Person temp = authController.login(username, password);
		
		if(temp instanceof User)
		{
			user = (User)temp;
		}
		
		else if(temp instanceof Trainer)
		{
			trainer = (Trainer)temp;
		}
			
		if(temp == null)
		{
			return false;
		}
		
		return true;
			
	}
	
	public void openConnection()
	{
		db.establishConnection();
	}
	
	public void closeConnection()
	{
		db.endConnection();
	}
	
	public void editAvailablity()
	{
		
	}
	
	public List<List<String>> getAllTips()
	{
		return db.getAllHealthTips();
	}
	
	public List<List<String>> getAllLectures()
	{
		return db.getAllLectures();
	}
	
	public List<List<String>> getUnansweredQueries()
	{
		return db.getUnansweredQueries();
	}
	
	public List<List<String>> getAnsweredQueries()
	{
		return db.getAnsweredQueries();
	}
	
	public List<List<String>> getAllProgressReports()
	{
		return db.getAllProgressReports(user);
	}
	
	public List<String> getStats()
	{
		return db.getStats(user);
	}

	public List<List<String>> getNotifications() {	
		return db.getNotifications(user);
	}
	
	public List<String> getTrainer()
	{
		return db.getTrainer(user);
	}
	
	public List<String> getWorkoutPlan()
	{
		return db.getWorkoutPlan(user);
	}
	
	public List<String> getDietPlan()
	{
		return db.getDietPlan(user);
	}
	
}