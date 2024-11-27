package handlers;

import models.Person;
import models.Trainer;
import models.User;
import repositories.PersonRepository;

public class AuthenticationHandler {
    private final PersonRepository PersonRepository;

    public AuthenticationHandler(PersonRepository PersonRepository) {
        this.PersonRepository = PersonRepository;
    }

    /**
     * Authenticates a Person based on email and password.
     *
     * @param email    The Person's email.
     * @param password The Person's password.
     * @return The authenticated Person object if valid, or null otherwise.
     */
    public Person authenticate(String email, String password) {
        Person Person = PersonRepository.findByEmail(email);

        if (Person != null && Person.getPassword().equals(password)) {
            return Person;
        }

        return null; // Authentication failed
    }
    
    public boolean signUp(User user)
    {
    	return PersonRepository.SignUp(user);
    }
    
    public boolean signUp(Trainer trainer)
    {
    	return PersonRepository.SignUp(trainer);
    }
}
