package ui;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import controllers.AuthenticationController;
import controllers.PrimaryController;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class loginscenecontroller
{
	private PrimaryController primaryController;
	
	@FXML
	private Button log_in;
	
	@FXML
	private Button back;
	
	@FXML
    private TextField usernameField;  // Username input field
    
    @FXML
    private TextField passwordField;  // Password input field
    
    public void setPrimaryController(PrimaryController primaryController2) {
		this.primaryController = primaryController2;
	}
    
	@FXML
	void log_inAction(ActionEvent event)
	{
		if (primaryController == null) {
            System.err.println("PrimaryController is not set!");
            return;
        }
		
		try 
        {	
            String username = usernameField.getText();
            String password = passwordField.getText();
                            
            boolean success = primaryController.authenticate(username, password);
            
            if(success)
            {
            	if(primaryController.trainer!=null)
            	{
            		// Load the login scene
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/Trainerscene.fxml"));
                    Parent loginSceneRoot = loader.load();
                    
                    Trainerhomecontroller trainerController = loader.getController();
                    trainerController.setPrimaryController(primaryController);

                    // Get the stage
                    Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

                    // Set the new scene
                    Scene loginScene = new Scene(loginSceneRoot);
                    stage.setScene(loginScene);

                    // Show the stage
                    stage.show();
            	}
            	
            	else if(primaryController.user!=null)
            	{
            		// Load the login scene
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/MemberHome.fxml"));
                    Parent loginSceneRoot = loader.load();
                    
                    MemberHomeController trainerController = loader.getController();
                    trainerController.setPrimaryController(primaryController);

                    // Get the stage
                    Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

                    // Set the new scene
                    Scene loginScene = new Scene(loginSceneRoot);
                    stage.setScene(loginScene);

                    // Show the stage
                    stage.show();
            	}
            	
            }
            
            else
            {
            	Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Login Error");
                alert.setHeaderText("Invalid Credentials");
                alert.setContentText("The username or password you entered is incorrect. Please try again.");

                // Show the alert and wait for the user to close it
                alert.showAndWait();
            }
            
            
        }
		
        catch (Exception e) 
        {
            e.printStackTrace();
        }
	}
	
	@FXML
	void backAction(ActionEvent event)
	{
		try 
        {
            // Load the login scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/scene1.fxml"));
            Parent loginSceneRoot = loader.load();
            
            scene1controller controller = loader.getController();
            controller.setPrimaryController(primaryController);

            // Get the stage
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

            // Set the new scene
            Scene loginScene = new Scene(loginSceneRoot);
            stage.setScene(loginScene);

            // Optional: set a title for the new scene
            stage.setTitle("login Scene");

            // Show the stage
            stage.show();
        }
        catch (Exception e) 
        {
            e.printStackTrace();
        }
	}
}