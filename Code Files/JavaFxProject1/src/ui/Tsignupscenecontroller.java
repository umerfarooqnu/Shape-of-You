package ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import controllers.PrimaryController;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import models.Trainer;


public class Tsignupscenecontroller
{
	private PrimaryController primaryController;
	@FXML
	private Button back;
	@FXML
	private Button sign_up;
	@FXML
	private TextField Name;
	@FXML
	private TextField Specializtion;
	@FXML
	private TextField Experience;
	@FXML
	private TextField UserName;
	@FXML
	private PasswordField Password;
	@FXML
	private PasswordField Confirm_Password;
	@FXML
	private MenuButton Availability;
	
	public void setPrimaryController(PrimaryController primaryController2) {
		this.primaryController = primaryController2;
	}
	
	@FXML
	void sign_upAction(ActionEvent event)
	{	
        try 
        {
        	// Get input values
            String name = Name.getText();
            String specialization = Specializtion.getText();
            String experienceText = Experience.getText();
            String username = UserName.getText();
            String password = Password.getText();
            String confirmPassword = Confirm_Password.getText();
            String availability = Availability.getText();
            
            boolean avail = true;
            
            if(availability == "NO")
            	avail = false;

            // Validate inputs
            if (name.isEmpty() || specialization.isEmpty() || experienceText.isEmpty() || username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || availability.isEmpty()) {
                System.out.println("Error: All fields must be filled.");
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Sign Up Error");
                alert.setHeaderText("Missing Fields");
                alert.setContentText("All fields must be filled. Please try again.");

                // Show the alert and wait for the user to close it
                alert.showAndWait();
                return;
            }

            // Validate password match
            if (!password.equals(confirmPassword)) {
                System.out.println("Error: Passwords do not match.");
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Sign Up Error");
                alert.setHeaderText("Invalid Credentials");
                alert.setContentText("The Passwords do not match. Please try again.");

                // Show the alert and wait for the user to close it
                alert.showAndWait();
                return;
            }

            // Validate experience is a number
            int experience;
            try {
                experience = Integer.parseInt(experienceText);
            } catch (NumberFormatException e) {
                System.out.println("Error: Experience must be a number.");
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Sign Up Error");
                alert.setHeaderText("Invalid Data Type");
                alert.setContentText("The Experience must be a number. Please try again.");

                // Show the alert and wait for the user to close it
                alert.showAndWait();
                return;
            }
            
            
            
            String exp = Integer.toString(experience);

            // Create Trainer object
            Trainer trainer = new Trainer(0, name, username, password, specialization, exp, avail);

            // Add the trainer to the primary controller
            boolean success = false;
            
            success = primaryController.authController.signUp(trainer);
	        
	        if(!success)
	        {
	        	Alert alert = new Alert(Alert.AlertType.ERROR);
	            alert.setTitle("Sign Up Error");
	            alert.setHeaderText("Username Already Exist!");
	            alert.setContentText("The Username Already Exists. Please try again.");
	            alert.showAndWait();
	            return;
	        }
	        
            // Load the login scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/scene1.fxml"));
            Parent loginSceneRoot = loader.load();
            
            scene1controller controller = loader.getController();
            controller.setPrimaryController(primaryController);

            // Get the stage
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

            // Set the new scene
            Scene loginScene = new Scene(loginSceneRoot);
            stage.setScene(loginScene);

            // Optional: set a title for the new scene
            //stage.setTitle("login Scene");

            // Show the stage
            stage.show();
        }
        
        catch (Exception e) 
        {
            e.printStackTrace();
        }
	}
	
	@FXML
	void backAction(ActionEvent event)
	{
		try 
        {
            // Load the login scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/scene1.fxml"));
            Parent loginSceneRoot = loader.load();
            
            scene1controller controller = loader.getController();
            controller.setPrimaryController(primaryController);

            // Get the stage
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

            // Set the new scene
            Scene loginScene = new Scene(loginSceneRoot);
            stage.setScene(loginScene);

            // Optional: set a title for the new scene
            stage.setTitle("login Scene");

            // Show the stage
            stage.show();
        }
		
        catch (Exception e) 
        {
            e.printStackTrace();
        }
	}
	
	@FXML
    public void menuItemAction(ActionEvent event)
    {
        // Get the source of the event (the MenuItem)
        MenuItem selectedItem = (MenuItem) event.getSource();
        
        // Get the text of the selected MenuItem
        String selectedText = selectedItem.getText();
        Availability.setText(selectedText);
        
        // Display or use the text
        System.out.println("Selected: " + selectedText);
    }
	
}

