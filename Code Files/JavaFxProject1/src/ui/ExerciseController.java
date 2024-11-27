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

public class ExerciseController {
	
    @FXML
    private Button logout;

    @FXML
    private Button back;

    @FXML
    private Label name;

    @FXML
    private Label sets;

    @FXML
    private Label targetmuscles;

    // Initialize label text with default values
    @FXML
    public void initialize() {
        name.setText("name");
        sets.setText("sets");
        targetmuscles.setText("target muscles");
    }

    private PrimaryController primaryController;
    // Reusable method to load a new FXML scene
    private void loadScene(String fxmlFile, ActionEvent event) {
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent root = loader.load();
                                          
            if(fxmlFile == "workoutplan.fxml")
            {
            WorkoutPlanController controller = loader.getController();
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

    // Navigate back to the previous scene
    @FXML
    void gotoback(ActionEvent event) {
        loadScene("workoutplan.fxml", event);
    }

    // Logout and navigate to the login screen
    @FXML
    void gotologout(ActionEvent event) {
        loadScene("scene1.fxml", event); // Assuming "Login.fxml" is the login screen
    }
}
