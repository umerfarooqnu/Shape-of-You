package ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.util.List;

import controllers.PrimaryController;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class WorkoutPlanController {

    @FXML
    private Button changeplan;

    @FXML
    private Button exercises;

    @FXML
    private Button logout;

    @FXML
    private Button back;

    @FXML
    private Label purpose;

    @FXML
    private Label duration;

    @FXML
    private Label difficulty;

    @FXML
    private Label frequency;

    // Initialize the label text
    @FXML
    public void initialize() {
        purpose.setText("purpose");
        duration.setText("duration");
        difficulty.setText("difficulty");
        frequency.setText("frequency");
    }
    
    // Initialize the label text
    public void load() {
    	List<String> stats = primaryController.getWorkoutPlan();

        if (!stats.isEmpty()) {
            // Set data for labels
        	purpose.setText(primaryController.user.getFitnessGoal());
            duration.setText(stats.get(1));           
            frequency.setText(stats.get(2));           
            difficulty.setText(stats.get((3)));
            
        } else {
            // Handle case where no stats are found (e.g., default or empty values)
        	frequency.setText("N/A");
            duration.setText("N/A");           
            frequency.setText("N/A");           
            difficulty.setText("N/A");
        }
    }

    private PrimaryController primaryController;
    // Reusable method to load a new FXML scene
    private void loadScene(String fxmlFile, ActionEvent event) {
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent root = loader.load();
                                          
            if(fxmlFile == "changeplan.fxml")
            {
                //ChangePlanController controller = loader.getController();
                //controller.setPrimaryController(primaryController);
            }
            
            else if(fxmlFile == "exercise.fxml")
            {
            	//ExerciseController controller = loader.getController();
            	//controller.setPrimaryController(primaryController);
            }
            
            else if(fxmlFile == "useplan.fxml")
            {
            	UserplanController controller = loader.getController();
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

    // Action for the "Change Plan" button
    @FXML
    void gotochangeplan(ActionEvent event) {
        //loadScene("changeplan.fxml", event);
    }

    // Action for the "Exercises" button
    @FXML
    void gotoexercises(ActionEvent event) {
        //loadScene("exercise.fxml", event);
    }

    // Action for the "Logout" button
    @FXML
    void gotologout(ActionEvent event) {
        loadScene("scene1.fxml", event); // Assuming Login.fxml is used for logout
    }

    // Action for the "Back" button
    @FXML
    void goback(ActionEvent event) {
        loadScene("useplan.fxml", event);
    }
}
