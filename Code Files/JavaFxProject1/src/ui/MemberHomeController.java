package ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import controllers.PrimaryController;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MemberHomeController {
	
	private PrimaryController primaryController;
    @FXML
    private Button myinfo;

    @FXML
    private Button logout;
    @FXML
    private Button userplan;

    @FXML
    private Button user_askprofessional;

    @FXML
    private Button userfeedbak;

    @FXML
    private Button userrecourses;

    @FXML
    private Button usernotificatins;

    @FXML
    private Button user_mytrainer;
    
    private void loadScene(String fxmlFile, ActionEvent event) {
        try {
        	FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        	Parent root = loader.load();
                                          
            if(fxmlFile == "myinfo.fxml")
            {
            	MyinfoController controller = loader.getController();
                controller.setPrimaryController(primaryController);
            }
            
            else if(fxmlFile == "useplan.fxml")
            {
            	UserplanController controller = loader.getController();
                controller.setPrimaryController(primaryController);
            }
            
            else if(fxmlFile == "user_askprofessional.fxml")
            {
            	QueryUserController controller = loader.getController();
                controller.setPrimaryController(primaryController);
            }
            
            else if(fxmlFile == "userfeedback.fxml")
            {
            	FeedbackUserController controller = loader.getController();
                controller.setPrimaryController(primaryController);
            }
            
            else if(fxmlFile == "userrecources.fxml")
            {
            	ResourcesUserController controller = loader.getController();
                controller.setPrimaryController(primaryController);
            }
            
            else if(fxmlFile == "usernotificatins.fxml")
            {
            	NotificationController controller = loader.getController();
                controller.setPrimaryController(primaryController);
            }
            
            else if(fxmlFile == "mytrainer.fxml")
            {
            	MyTrainerController controller = loader.getController();
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

    @FXML
    void gotomyinfo(ActionEvent event) {
        loadScene("myinfo.fxml", event);      
    }

    @FXML
    void gotoplan(ActionEvent event) {
        loadScene("useplan.fxml", event);
    }

    @FXML
    void gotoaskprofessional(ActionEvent event) {
        loadScene("user_askprofessional.fxml", event);
    }

    @FXML
    void gotouserback(ActionEvent event) {
        loadScene("userfeedback.fxml", event);
    }

    @FXML
    void gotorecources(ActionEvent event) {
        loadScene("userrecources.fxml", event);
    }

    @FXML
    void gotonotifications(ActionEvent event) {
        loadScene("usernotificatins.fxml", event);
    }

    @FXML
    void gotomytrainer(ActionEvent event) {
        loadScene("mytrainer.fxml", event);
    }
    
    @FXML
    void gotologout(ActionEvent event) {
        loadScene("scene1.fxml", event);
    }
}
