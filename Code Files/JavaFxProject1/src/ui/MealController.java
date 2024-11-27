package ui;

import controllers.PrimaryController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MealController {

    // Labels
    @FXML
    private Label carbs;

    @FXML
    private Label fats;

    @FXML
    private Label protein;

    @FXML
    private Label calories;

    @FXML
    private Label name;

    @FXML
    private Label fibre;

    // Buttons
    @FXML
    private Button logout;

    @FXML
    private Button back;

    // Initialize labels with default values
    @FXML
    public void initialize() {
        carbs.setText("carbs");       // Default value for carbs label
        fats.setText("fats");         // Default value for fats label
        protein.setText("protein");   // Default value for protein label
        calories.setText("calories"); // Default value for calories label
        name.setText("name");         // Default value for name label
        fibre.setText("fibre");       // Default value for fibre label
    }

    private PrimaryController primaryController;
    
    // Reusable method to load a new FXML scene
    private void loadScene(String fxmlFile, ActionEvent event) {
        try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent root = loader.load();
                                          
            if(fxmlFile == "nutritionplan.fxml")
            {
            DietPlanController controller = loader.getController();
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

    // Navigate to the Logout (Login) screen
    @FXML
    void gotologout(ActionEvent event) {
        loadScene("Login.fxml", event); // Adjust to your login screen's FXML file name
    }

    // Navigate back to the previous screen
    @FXML
    void gotoback(ActionEvent event) {
        //loadScene("nutritionplan.fxml", event); // Adjust to your previous screen's FXML file name
    }
}
