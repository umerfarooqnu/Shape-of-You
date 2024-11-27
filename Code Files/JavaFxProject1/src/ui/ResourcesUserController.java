package ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;

import controllers.PrimaryController;

public class ResourcesUserController {
	
	private PrimaryController primaryController;
    // FXML buttons
    @FXML
    private Button lectures;

    @FXML
    private Button healthtips;

    @FXML
    private Button progressreport;

    @FXML
    private Button logout;

    @FXML
    private Button back;

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
            
            else if(fxmlFile == "lectures.fxml")
            {
            	LecturesUserController controller = loader.getController();
                controller.setPrimaryController(primaryController);
            }
            
            else if(fxmlFile == "healthtips.fxml")
            {
            	TipsController controller = loader.getController();
                controller.setPrimaryController(primaryController);
            }
            
            else if(fxmlFile == "progressreport.fxml")
            {
            	ProgressReportController controller = loader.getController();
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
    void gotolectures(ActionEvent event) {
        loadScene("lectures.fxml", event); // Replace with the actual FXML file for lectures
    }

    @FXML
    void gotohealthtips(ActionEvent event) {
        loadScene("healthtips.fxml", event); // Replace with the actual FXML file for health tips
    }

    @FXML
    void gotoprogressreport(ActionEvent event) {
        loadScene("progressreport.fxml", event); // Replace with the actual FXML file for progress report
    }

    @FXML
    void gotologout(ActionEvent event) {
        loadScene("scene1.fxml", event); // Replace with the actual FXML file for login
    }

    @FXML
    void gotoback(ActionEvent event) {
        loadScene("MemberHome.fxml", event); // Replace with the actual FXML file for the previous screen
    }
}
