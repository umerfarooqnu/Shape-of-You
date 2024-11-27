package ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;
import java.util.List;

import controllers.PrimaryController;

public class TipsController {
	
	private PrimaryController primaryController;

    @FXML
    private Button logout;

    @FXML
    private Button back;

    @FXML
    private ListView<String> healthTipListView;
    
    // Generic method to load scenes
    private void loadScene(String fxmlFile, ActionEvent event) {
        try {
        	FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        	Parent root = loader.load();
                                          
            if(fxmlFile == "userrecources.fxml")
            {
            	ResourcesUserController controller = loader.getController();
                controller.setPrimaryController(primaryController);
            }
                          
            else if(fxmlFile == "scene1.fxml")
            {
            	scene1controller controller = loader.getController();
                controller.setPrimaryController(primaryController);
            }
                                
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void setPrimaryController(PrimaryController primaryController2) {
		this.primaryController = primaryController2;
	
		loadTips();		
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

    // Action handlers
    @FXML
    void gotologout(ActionEvent event) {
        loadScene("scene1.fxml", event); // Replace with the actual FXML file for login
    }

    @FXML
    void gotoback(ActionEvent event) {
        loadScene("userrecources.fxml", event); // Replace with the actual FXML file for the previous screen
    }
}
