package controllers;

import handlers.UserHomePageHandler;
import models.Person;
import models.Trainer;
import models.User;

public class UserHomePageController {
    private final UserHomePageHandler homeHandler;

    public UserHomePageController(UserHomePageHandler homeHandler) {
        this.homeHandler = homeHandler;
    }

}
