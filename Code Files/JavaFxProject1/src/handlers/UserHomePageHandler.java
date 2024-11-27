package handlers;

import models.Trainer;
import models.User;
import repositories.PersonRepository;

public class UserHomePageHandler {
    private final PersonRepository PersonRepository;
    private final User user;

    public UserHomePageHandler(PersonRepository PersonRepository, User user) {
        this.PersonRepository = PersonRepository;
        this.user  = user;
    }
    
    public User getMyInfo()
    {
    	return user;
    }
    
    public Trainer getTrainer(User user)
    {
    	Trainer trainer = null;
    	
    	
    	
    	return trainer;
    }
    
    

}
