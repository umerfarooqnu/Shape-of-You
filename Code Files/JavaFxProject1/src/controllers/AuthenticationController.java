package controllers;

import handlers.AuthenticationHandler;
import models.Person;
import models.Trainer;
import models.User;

public class AuthenticationController {
    private final AuthenticationHandler authHandler;

    public AuthenticationController(AuthenticationHandler authHandler) {
        this.authHandler = authHandler;
    }

   
    public Person login(String email, String password) {
        Person person = authHandler.authenticate(email, password);

        if (person != null) {
            System.out.println("Login successful! Welcome, " + person.getName() + " (" + person.getPersonType() + ")"); 
            return person;
        }
        
        System.out.println("Login failed: Invalid email or password.");
        return null;
    }
    
    public boolean signUp(User user) {
        return authHandler.signUp(user);

    }
    
    public boolean signUp(Trainer trainer) {
        return authHandler.signUp(trainer);

    }
}
