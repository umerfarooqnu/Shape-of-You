package ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

import controllers.PrimaryController;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;

public class scene1controller {
	
	private PrimaryController primaryController;

    @FXML
    private Button signup;

    @FXML
    private Button login;
    
    @FXML
    private Hyperlink trainer_signup;
    
    
    public void setPrimaryController(PrimaryController primaryController2) {
		this.primaryController = primaryController2;
		primaryController.user = null;
		primaryController.trainer = null;
	}

	public void start(Stage primaryStage, PrimaryController primaryController2 )
    {
		//primaryController = new PrimaryController();
		setPrimaryController(primaryController2);
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/scene1.fxml"));
        Parent root = null;
		try {
			root = loader.load();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		// Set the PrimaryController
        scene1controller controller = loader.getController();
        controller.setPrimaryController(this.primaryController);
		     
        // Create the scene and set it to the stage
        Scene scene = new Scene(root);
        primaryStage.setTitle("JavaFX with Controller");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        
        
        if (this.primaryController == null) {
            System.err.println("PrimaryController is not set!");
            return;
        }           
    }

    @FXML
    void signupAction(ActionEvent event)
    {      
        try 
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/usignupscene.fxml"));
            Parent loginSceneRoot = loader.load();
            
            usersignupscenecontroller userController = loader.getController();
            userController.setPrimaryController(primaryController);
                                
            // Get the stage
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            // Set the new scene
            Scene loginScene = new Scene(loginSceneRoot);
            stage.setScene(loginScene);
            // Show the stage
            stage.show();
        }
        catch (Exception e) 
        {
        	System.err.println("Error loading scene: User Signup");
            e.printStackTrace();
        }
    }

    
    @FXML
    void Trainer_signupAction(ActionEvent event)
    {   
        try 
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/Tsignupscene.fxml"));
            Parent loginSceneRoot = loader.load();
            
            Tsignupscenecontroller trainerController = loader.getController();
            trainerController.setPrimaryController(primaryController);
            
            // Get the stage
            Stage stage = (Stage) ((Hyperlink) event.getSource()).getScene().getWindow();
            // Set the new scene
            Scene loginScene = new Scene(loginSceneRoot);
            stage.setScene(loginScene);
            // Show the stage
            stage.show();
        }
        catch (Exception e) 
        {
        	System.err.println("Error loading scene: Trainer Signup");
            e.printStackTrace();
        }
    }

    
    @FXML
    void loginAction(ActionEvent event)
    {  	
        try 
        {
        	
            // Load the login scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/loginscene.fxml"));
            
            Parent loginSceneRoot = loader.load();
            
            loginscenecontroller trainerController = loader.getController();
            trainerController.setPrimaryController(primaryController);

            // Get the stage
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

            // Set the new scene
            Scene loginScene = new Scene(loginSceneRoot);
            stage.setScene(loginScene);

            // set a title for the new scene
            stage.setTitle("login Scene");

            // Show the stage
            stage.show();
        }
        catch (Exception e) 
        {
        	System.err.println("Error loading scene: login");
            e.printStackTrace();
        }
    }

}
