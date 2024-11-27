package ui;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.io.File;
import java.util.List;

import controllers.PrimaryController;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Ttipscenecontroller
{
	private PrimaryController primaryController;
	@FXML
	private Button logout;
	@FXML
	private Button back;
	@FXML
	private Button upload;
	
	@FXML
    private ListView<String> healthTipListView;
	
	public void setPrimaryController(PrimaryController primaryController2) {
		this.primaryController = primaryController2;
	
		loadTips();		
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
	
	@FXML
	void uploadAction(ActionEvent event) {
	    
		// Create a FileChooser
        FileChooser fileChooser = new FileChooser();

        // Set file chooser properties
        fileChooser.setTitle("Select File to Upload");
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Text Files", "*.txt"),
            new FileChooser.ExtensionFilter("Image Files", ".png", ".jpg", "*.jpeg"),
            new FileChooser.ExtensionFilter("All Files", ".")
        );

        // Show the file chooser dialog
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            // Handle the selected file (e.g., upload to server or process locally)
            System.out.println("File selected: " + selectedFile.getAbsolutePath());
            
            // Optionally, display a message or update the UI
            healthTipListView.getItems().add("Uploaded file: " + selectedFile.getName());
        } else {
            System.out.println("File selection canceled.");
        }
    
	}
	
	void loadTips()
	{
		// Get all tips from primary controller
        List<List<String>> allTips = primaryController.getAllTips();

        // Create a string representation of each health tip and add it to the ListView
        for (List<String> tip : allTips) {
            String tipContent = "Tip: " + tip.get(0) + "\nCategory: " + tip.get(1) + "\nDate: " + tip.get(2);
            healthTipListView.getItems().add(tipContent);
        }

        
	}
}