package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import repositories.PersonRepository;
import ui.scene1controller;
import controllers.AuthenticationController;
import controllers.PrimaryController;
import db.DBHandler;
import handlers.AuthenticationHandler;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            // Load the FXML file and link it to the SampleController
//        	
        	PrimaryController primaryController = new PrimaryController();
        	scene1controller startProject = new scene1controller();      	
        	startProject.start(primaryStage,primaryController);
        	       	
        	//startProject.;
        	            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
