package ui;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;

import controllers.PrimaryController;

public class ComposeUserController {
	
	private PrimaryController primaryController;

    // TextArea
    @FXML
    private TextArea composeTextArea;

    // Buttons
    @FXML
    private Button post;

    @FXML
    private Button logout;

    @FXML
    private Button back;

    private void loadScene(String fxmlFile, ActionEvent event) {
        try {
        	FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        	Parent root = loader.load();
                                          
            if(fxmlFile == "user_askprofessional.fxml")
            {
            	QueryUserController controller = loader.getController();
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
		}

    // Action handlers
    @FXML
    void gotopost(ActionEvent event) {
        // Retrieve the query text
        String userQuery = composeTextArea.getText();

        if (userQuery == null || userQuery.trim().isEmpty()) {
            // Show an alert if the text area is empty
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a query before submitting.");
            alert.showAndWait();
        } else {
            // Process the query or save it (e.g., database or file)
            System.out.println("User Query: " + userQuery);

            // Show success alert
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Query Sent");
            alert.setHeaderText(null);
            alert.setContentText("Your query has been successfully sent to the fitness professionals!");
            alert.showAndWait();

            // Clear the text area
            composeTextArea.clear();
            
            loadScene("user_askprofessional.fxml", event);
        }        
    }


    @FXML
    void gotoback(ActionEvent event) {
        loadScene("user_askprofessional.fxml", event); // Replace with the actual FXML file for the previous screen
    }

    @FXML
    void gotologout(ActionEvent event) {
        loadScene("scene1.fxml", event); // Replace with the actual FXML file for the login screen
    }
}
