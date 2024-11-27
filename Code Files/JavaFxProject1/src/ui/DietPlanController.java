package ui;

import java.util.List;

import controllers.PrimaryController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class DietPlanController {

    // Labels
    @FXML
    private Label goal;

    @FXML
    private Label nutrition;

    // Buttons (corresponding to fx:id in the FXML)
    @FXML
    private Button meals;

    @FXML
    private Button changeplan;

    @FXML
    private Button logout;

    @FXML
    private Button back;
    
    // Initialize labels with default text
   
    public void load() {
    	List<String> stats = primaryController.getDietPlan();
        
        
        if (!stats.isEmpty()) {
        	goal.setText(primaryController.user.getFitnessGoal());          
            nutrition.setText(stats.get(1));
        } else {
            
        	goal.setText("N/A");          
            nutrition.setText("N/A");
        }
    }

    private PrimaryController primaryController;
    // Reusable method to load a new FXML scene
    private void loadScene(String fxmlFile, ActionEvent event) {
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent root = loader.load();
                                          
            if(fxmlFile == "meal.fxml")
            {
            MealController controller = loader.getController();
                controller.setPrimaryController(primaryController);
            }
            
            else if(fxmlFile == "ChangePlan.fxml")
            {
                //ChangePlanController controller = loader.getController();
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

    // Navigate to Meals screen
    @FXML
    void gotomeals(ActionEvent event) {
        //loadScene("meal.fxml", event);
    }

    // Navigate to Change Plan screen
    @FXML
    void gotochangeplan(ActionEvent event) {
        //loadScene("ChangePlan.fxml", event);
    }

    // Navigate to Logout screen (e.g., Login.fxml)
    @FXML
    void gotologout(ActionEvent event) {
        loadScene("scene1.fxml", event); // Adjust to your actual login screen's FXML file
    }

    // Navigate back to the previous screen (User Plan)
    @FXML
    void gotoback(ActionEvent event) {
        loadScene("useplan.fxml", event);
    }
}
