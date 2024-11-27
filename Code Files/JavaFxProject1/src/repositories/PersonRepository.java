package repositories;

import models.Person;
import models.Trainer;
import models.User;
import db.DBHandler;

public class PersonRepository {
	
    private final DBHandler db;
    
    public PersonRepository(DBHandler db)
    {
    	this.db = db;	
    }
       
    public Person findByEmail(String email) {
        return db.getPerson(email);
    }

	public boolean SignUp(User user) {
		return db.signUp(user);
	}

	public boolean SignUp(Trainer trainer) {
		return db.signUp(trainer);
	}
}
