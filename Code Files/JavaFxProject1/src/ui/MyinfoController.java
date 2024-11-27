package ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import controllers.PrimaryController;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MyinfoController {
	
	private PrimaryController primaryController;

    @FXML
    private Label nametext;

    @FXML
    private Label age;

    @FXML
    private Label weight;

    @FXML
    private Label height;

    @FXML
    private Label goal;

    @FXML
    private Label username;

    @FXML
    private Button userstastics;

    @FXML
    private Button logout;

    @FXML
    private Button back;

    // Initialize method to set default text
    
    public void setUp() {
    	
    	String name = primaryController.user.getName();
        double weightInt = primaryController.user.getWeight();
        double heightInt = primaryController.user.getHeight();
        String goals = primaryController.user.getFitnessGoal();
        String email = primaryController.user.getEmail();

        
        nametext.setText(name);
        age.setText("25");
        weight.setText(String.valueOf(weightInt));
        height.setText(String.valueOf(heightInt));
        goal.setText(goals);
        username.setText(email);
    }

    private void loadScene(String fxmlFile, ActionEvent event) {
        try {
        	FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        	Parent root = loader.load();
                                          
            if(fxmlFile == "MemberHome.fxml")
            {
            	MemberHomeController controller = loader.getController();
                controller.setPrimaryController(primaryController);
            }
            
            else if(fxmlFile == "Stastics.fxml")
            {
            	StatsUserController controller = loader.getController();
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
		
		setUp();
		}

    // Action for Back button
    @FXML
    void goback(ActionEvent event) {
        loadScene("MemberHome.fxml", event);
    }

    // Action for Stastics button
    @FXML
    void gotostastics(ActionEvent event) {
        loadScene("Stastics.fxml", event);
    }
    
    @FXML
    void gotologout(ActionEvent event) {
        loadScene("scene1.fxml", event);
    }
}
