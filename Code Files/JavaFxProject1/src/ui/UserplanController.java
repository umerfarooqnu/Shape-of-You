package ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import controllers.PrimaryController;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class UserplanController {

    @FXML
    private Button workoutplan;

    @FXML
    private Button nutritionplan;

    @FXML
    private Button back;

    @FXML
    private Button logout;

    private PrimaryController primaryController;
    
    // Reusable method to load a new FXML scene
    private void loadScene(String fxmlFile, ActionEvent event) {
        try {
        	
        	FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        	Parent root = loader.load();
                                          
            if(fxmlFile == "WorkoutPlan.fxml")
            {
            	WorkoutPlanController controller = loader.getController();
                controller.setPrimaryController(primaryController);
            }
            
            else if(fxmlFile == "NutritionPlan.fxml")
            {
            	DietPlanController controller = loader.getController();
                controller.setPrimaryController(primaryController);
            }
            
            else if(fxmlFile == "MemberHome.fxml")
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

    // Action for the "Workout Plan" button
    @FXML
    void gotoworkoutplan(ActionEvent event) {
        loadScene("WorkoutPlan.fxml", event);
    }

    // Action for the "Nutrition Plan" button
    @FXML
    void gotonutritionplan(ActionEvent event) {
        loadScene("NutritionPlan.fxml", event);
    }

    // Action for the "Back" button
    @FXML
    void goback(ActionEvent event) {
        loadScene("MemberHome.fxml", event);
    }

    // Action for the "Logout" button
    @FXML
    void logout(ActionEvent event) {
        loadScene("scene1.fxml", event); // Assuming Login.fxml is used for the login/logout screen
    }
}
