package ui;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.net.URL;
import java.util.ResourceBundle;

import controllers.PrimaryController;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.fxml.Initializable;

public class Tmyinfocontroller
{
	private PrimaryController primaryController;
	@FXML
	private Button logout;
	@FXML
	private Button back;
	@FXML
	private Label Availability;
	@FXML
	private Label Name;
	@FXML
	private Label Specializtion;
	@FXML
	private Label Experience;
	@FXML
	private Button Available;
	@FXML
	private Button NotAvailable;
	
	public void setPrimaryController(PrimaryController primaryController2) {
		this.primaryController = primaryController2;
		
		String name = primaryController.trainer.getName();
        String specialization = primaryController.trainer.getSpecialization();
        String experience = primaryController.trainer.getExperience();
        boolean availability = primaryController.trainer.getAvailablity();

        Name.setText(name);
        Specializtion.setText(specialization);
        Experience.setText(experience);
        
        String avail = "YES";
        
        if(!availability)
        	avail = "NO";
        
        Availability.setText(avail);
	}
	
	@FXML
	void AvailableAction(ActionEvent event)
	{
		Availability.setText("YES");
	}
	
	@FXML
	void NotAvailableAction(ActionEvent event)
	{
		Availability.setText("NO");
	}
	
	@FXML
	void backAction(ActionEvent event)
	{
		try 
        {
            // Load the login scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/Trainerscene.fxml"));
            Parent loginSceneRoot = loader.load();
            
            Trainerhomecontroller controller = loader.getController();
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
	void logoutAction(ActionEvent event)
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
	
}