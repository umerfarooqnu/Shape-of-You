package ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

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
import javafx.stage.Stage;
import models.User;

public class usersignupscenecontroller
{
	private PrimaryController primaryController;
	@FXML
	private Button back;
	@FXML
	private Button sign_up;
	@FXML
	private TextField Name;
	@FXML
	private TextField Age;
	@FXML
	private TextField Weight;
	@FXML
	private TextField Height;
	@FXML
	private TextField UserName;
	@FXML
	private PasswordField Password;
	@FXML
	private PasswordField Confirm_Password;
	@FXML
	private MenuButton Goal;
	
	public void setPrimaryController(PrimaryController primaryController2) {
		this.primaryController = primaryController2;
	}
	
	@FXML
	void sign_upAction(ActionEvent event) {	
	    try {
	        // Get input values
	        String name = Name.getText();
	        String ageText = Age.getText();
	        String weightText = Weight.getText();
	        String heightText = Height.getText();
	        String username = UserName.getText();
	        String password = Password.getText();
	        String confirmPassword = Confirm_Password.getText();
	        String goal = Goal.getText();

	        // Validate inputs
	        if (name.isEmpty() || ageText.isEmpty() || weightText.isEmpty() || heightText.isEmpty() || 
	            username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || goal.equals("Goal")) {
	            System.out.println("Error: All fields must be filled.");
	            Alert alert = new Alert(Alert.AlertType.ERROR);
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
	            Alert alert = new Alert(Alert.AlertType.ERROR);
	            alert.setTitle("Sign Up Error");
	            alert.setHeaderText("Invalid Credentials");
	            alert.setContentText("The passwords do not match. Please try again.");

	            // Show the alert and wait for the user to close it
	            alert.showAndWait();
	            return;
	        }

	        // Validate age, weight, and height are numbers
	        int age;
	        double weight, height;
	        try {
	            age = Integer.parseInt(ageText);
	            weight = Double.parseDouble(weightText);
	            height = Double.parseDouble(heightText);
	        } catch (NumberFormatException e) {
	            System.out.println("Error: Age, weight, and height must be numbers.");
	            Alert alert = new Alert(Alert.AlertType.ERROR);
	            alert.setTitle("Sign Up Error");
	            alert.setHeaderText("Invalid Data Type");
	            alert.setContentText("Age, weight, and height must be valid numbers. Please try again.");

	            // Show the alert and wait for the user to close it
	            alert.showAndWait();
	            return;
	        }

	        // Create User object
	        User user = new User(0, name, username, password, height, weight, goal, 0, 0);

	        // Add the user to the primary controller
	        boolean success = primaryController.authController.signUp(user);
	        
	        if(!success)
	        {
	        	Alert alert = new Alert(Alert.AlertType.ERROR);
	            alert.setTitle("Sign Up Error");
	            alert.setHeaderText("Username Already Exist!");
	            alert.setContentText("The Username Already Exists. Please try again.");
	            alert.showAndWait();
	            return;
	        }

	        // Transition to the login scene
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/scene1.fxml"));
	        Parent loginSceneRoot = loader.load();
	        
	        scene1controller controller = loader.getController();
	        controller.setPrimaryController(primaryController);

	        // Get the stage
	        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

	        // Set the new scene
	        Scene loginScene = new Scene(loginSceneRoot);
	        stage.setScene(loginScene);

	        // Show the stage
	        stage.show();
	    } catch (Exception e) {
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
	        Goal.setText(selectedText);
	        
	        // Display or use the text
	        System.out.println("Selected: " + selectedText);
	    }
	
}