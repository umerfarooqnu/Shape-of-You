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

public class QueryUserController {
	
	private PrimaryController primaryController;

    // TextArea
	@FXML
    private ListView<String> queryListView;

    // Buttons
    @FXML
    private Button composequery;

    @FXML
    private Button logout;

    @FXML
    private Button back;

    // Generic method for loading scenes
    private void loadScene(String fxmlFile, ActionEvent event) {
        try {
        	FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        	Parent root = loader.load();
                                          
            if(fxmlFile == "MemberHome.fxml")
            {
            	MemberHomeController controller = loader.getController();
                controller.setPrimaryController(primaryController);
            }
            
            else if(fxmlFile == "composequery.fxml")
            {
            	ComposeUserController controller = loader.getController();
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
		loadQueries();
    }

    // Load user-specific queries into the ListView
    void loadQueries() {
        // Get all queries for the logged-in user from the primary controller
        List<List<String>> userQueries = primaryController.getAnsweredQueries();

        // Create a string representation of each query and add it to the ListView
        for (List<String> query : userQueries) {
            String content = "Question: " + query.get(1) + "\nResponse: " + query.get(2) + "\nDate: " + query.get(3);
            queryListView.getItems().add(content);
        }
    }

    // Action handlers
    @FXML
    void gotocomposequery(ActionEvent event) {
        loadScene("composequery.fxml", event); // Load compose query screen
    }

    @FXML
    void gotoback(ActionEvent event) {
        loadScene("MemberHome.fxml", event); // Load the previous screen
    }

    @FXML
    void gotologout(ActionEvent event) {
        loadScene("scene1.fxml", event); // Load the login screen
    }
}
