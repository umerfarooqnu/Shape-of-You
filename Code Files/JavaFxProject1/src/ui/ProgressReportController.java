package ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.ListView;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.util.List;

import controllers.PrimaryController;

public class ProgressReportController {

    private PrimaryController primaryController;

    @FXML
    private Button logout;

    @FXML
    private Button back;

    @FXML
    private ListView<String> reportListView;

    // Generic method to load scenes
    private void loadScene(String fxmlFile, ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();

            if (fxmlFile.equals("userrecources.fxml")) {
                ResourcesUserController controller = loader.getController();
                controller.setPrimaryController(primaryController);
            } else if (fxmlFile.equals("scene1.fxml")) {
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

    public void setPrimaryController(PrimaryController primaryController) {
        this.primaryController = primaryController;
        loadReports();
    }

    // Method to load and display progress reports
    void loadReports() {
        // Get all progress reports from the primary controller
        List<List<String>> allReports = primaryController.getAllProgressReports();

        // Create a string representation of each report and add it to the ListView
        for (List<String> report : allReports) {
            String reportContent = "Workout Stats: " + report.get(0) + "\nDiet Stats: " + report.get(1) + "\nProgress Summary: " + report.get(2)+ "\nDate: " + report.get(3);
            reportListView.getItems().add(reportContent);
        }

        // Customize text size for the ListView items
        reportListView.setStyle("-fx-font-size: 30px;"); // Larger text size
    }

    // Action handlers
    @FXML
    void gotologout(ActionEvent event) {
        loadScene("scene1.fxml", event);
    }

    @FXML
    void gotoback(ActionEvent event) {
        loadScene("userrecources.fxml", event);
    }
}
