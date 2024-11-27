package ui;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;

import controllers.PrimaryController;

public class FeedbackUserController {
	
	private PrimaryController primaryController;
    // FXML components
    @FXML
    private ComboBox<String> rating;

    @FXML
    private TextArea comments;

    @FXML
    private Button post;

    @FXML
    private Button logout; // Note: Consider renaming this to `logout` for clarity.

    @FXML
    private Button back;

    // Initialize method to populate ComboBox
    @FXML
    public void initialize() {
        // Adding rating options to the ComboBox
        rating.getItems().addAll("1 Star", "2 Stars", "3 Stars", "4 Stars", "5 Stars");
    }

    // Generic method to load scenes
    private void loadScene(String fxmlFile, ActionEvent event) {
        try {
        	FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        	Parent root = loader.load();
                                          
        	if(fxmlFile == "MemberHome.fxml")
            {
            	MemberHomeController controller = loader.getController();
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
        // Retrieve user feedback
        String selectedRating = rating.getValue(); // Selected rating
        String feedbackComments = comments.getText(); // User comments

        if (selectedRating == null || feedbackComments.isEmpty()) {        
        	// Show an alert if the text area is empty
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Feedback is incomplete. Please provide a rating and comments.");
            alert.showAndWait();
        } else {
            // Process the query or save it (e.g., database or file)          
            // Show success alert
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Query Sent");
            alert.setHeaderText(null);
            alert.setContentText("Your Feedback has been successfully registered!");
            alert.showAndWait();

            // Clear the text area
            comments.clear();
            
            loadScene("MemberHome.fxml", event);
        }
        
        
    }

    @FXML
    void gotologout(ActionEvent event) {
        loadScene("scene1.fxml", event); // Replace with the login screen FXML
    }

    @FXML
    void gotoback(ActionEvent event) {
        loadScene("MemberHome.fxml", event); // Replace with the previous screen FXML
    }
}
