package ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;
import java.util.List;

import controllers.PrimaryController;

public class MyTrainerController {

    @FXML
    private Label name;

    @FXML
    private Label specialization;

    @FXML
    private Label experience;

    @FXML
    private Label available;

    @FXML
    private Button logout;

    @FXML
    private Button back;

    private PrimaryController primaryController;
    // Reusable method to load a new FXML scene
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
  
        List<String> stats = primaryController.getTrainer();

        if (!stats.isEmpty()) {
            // Set data for labels
        	name.setText(stats.get(0));           // ExerciseCount (e.g., "25 exercises")
        	specialization.setText(stats.get(1));           // CaloriesBurned (e.g., "2000 kcal")
        	experience.setText(stats.get(2));           // AvgWorkoutDuration (e.g., "60 minutes")
        	available.setText(stats.get((3))); // DietComplianceRate (e.g., "90%")
           
        } else {
            // Handle case where no stats are found (e.g., default or empty values)
        	name.setText("No data available");
        	specialization.setText("N/A");
        	experience.setText("N/A");
        	available.setText("N/A");
          
        }
        
        }

    // Action handlers
    @FXML
    void gotologout(ActionEvent event) {
        loadScene("scene1.fxml", event); // Replace with the actual FXML file for login
    }

    @FXML
    void gotoback(ActionEvent event) {
        loadScene("MemberHome.fxml", event); // Replace with the actual FXML file for the previous screen
    }

}
