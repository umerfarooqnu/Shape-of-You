package ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import controllers.PrimaryController;

import java.util.List;

public class NotificationController {

    private PrimaryController primaryController;

    // ListView to display notifications
    @FXML
    private ListView<String> notificationListView;

    // Buttons
    @FXML
    private Button logout;

    @FXML
    private Button back;

    // Generic method to load scenes
    private void loadScene(String fxmlFile, ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();

            // Set the primary controller for the next scene if needed
            if (fxmlFile.equals("MemberHome.fxml")) {
                MemberHomeController controller = loader.getController();
                controller.setPrimaryController(primaryController);
            } else if (fxmlFile.equals("scene1.fxml")) {
                scene1controller controller = loader.getController();
                controller.setPrimaryController(primaryController);
            }

            // Change the scene
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Set the primary controller
    public void setPrimaryController(PrimaryController primaryController2) {
        this.primaryController = primaryController2;
        loadNotifications();  // Load notifications when the scene is set
    }

    // Load all notifications into the ListView
    void loadNotifications() {
        // Get all notifications for the logged-in user from the primary controller
        List<List<String>> userNotifications = primaryController.getNotifications();  // This method returns the list of notifications

        // Clear the existing items in the ListView before adding new notifications
        notificationListView.getItems().clear(); 

        // Loop through the notifications and add formatted text to the ListView
        for (List<String> notification : userNotifications) {
            // Format the notification details (Message, Date, Type) into a single string
            String formattedNotification = "Type: " + notification.get(2) + "\n" +
                                           "Message: " + notification.get(0) + "\n" +
                                           "Date: " + notification.get(1) + "\n";
            // Add the formatted notification to the ListView
            notificationListView.getItems().add(formattedNotification);
        }
    }


    // Action handlers
    @FXML
    void gotologout(ActionEvent event) {
        loadScene("scene1.fxml", event); // Load the login screen
    }

    @FXML
    void gotoback(ActionEvent event) {
        loadScene("MemberHome.fxml", event); // Load the previous screen
    }
}
