package ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;
import java.util.List;

import controllers.PrimaryController;

public class StatsUserController {
	
	private PrimaryController primaryController;

    // Labels
    @FXML
    private Label exercise;

    @FXML
    private Label calories;

    @FXML
    private Label duration;

    @FXML
    private Label diet;

    @FXML
    private Label weeklyprogress;

    @FXML
    private Label monthlyprogress;

    // Buttons
    @FXML
    private Button back;

    @FXML
    private Button logout;

    // Initialize method
   
    public void load() {
    	List<String> stats = primaryController.getStats();

        if (!stats.isEmpty()) {
            // Set data for labels
            exercise.setText(stats.get(0));           // ExerciseCount (e.g., "25 exercises")
            calories.setText(stats.get(1));           // CaloriesBurned (e.g., "2000 kcal")
            duration.setText(stats.get(2));           // AvgWorkoutDuration (e.g., "60 minutes")
            diet.setText(stats.get((3))); 			  // DietComplianceRate (e.g., "90%")
            weeklyprogress.setText(stats.get(4));     // WeeklyProgress (e.g., "10% Improvement")
            monthlyprogress.setText(stats.get(5));    // MonthlyProgress (e.g., "20% Improvement")
        } else {
            // Handle case where no stats are found (e.g., default or empty values)
            exercise.setText("No data available");
            calories.setText("N/A");
            duration.setText("N/A");
            diet.setText("N/A");
            weeklyprogress.setText("N/A");
            monthlyprogress.setText("N/A");
        }
    }
    
    
    private void loadScene(String fxmlFile, ActionEvent event) {
        try {
        	FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        	Parent root = loader.load();
                                          
        	if(fxmlFile == "myinfo.fxml")
            {
            	MyinfoController controller = loader.getController();
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
		load();
		}


    // Navigate to the Logout (Login) screen
    @FXML
    void gotologout(ActionEvent event) {
        loadScene("scene1.fxml", event); // Adjust to your login screen's FXML file name
    }

    // Navigate back to the previous screen
    @FXML
    void gotoback(ActionEvent event) {
        loadScene("myinfo.fxml", event); // Adjust to your previous screen's FXML file name
    }

}
