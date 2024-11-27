package ui;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import controllers.PrimaryController;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Trainerhomecontroller
{
	private PrimaryController primaryController;
	@FXML
	private Button logout;
	@FXML
	private Button my_info;
	@FXML
	private Button tips;
	@FXML
	private Button lectures;
	@FXML
	private Button qna;
	
	public void setPrimaryController(PrimaryController primaryController2) {
		this.primaryController = primaryController2;
	}
	
	@FXML
	void my_infoAction(ActionEvent event)
	{		
        try 
        {
            // Load the login scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/Tmyinfoscene.fxml"));
            Parent loginSceneRoot = loader.load();
            
            Tmyinfocontroller trainerController = loader.getController();         
            trainerController.setPrimaryController(primaryController);
            
            // Get the stage
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

            // Set the new scene
            Scene loginScene = new Scene(loginSceneRoot);
            stage.setScene(loginScene);

            stage.show();
        }
        catch (Exception e) 
        {
            e.printStackTrace();
        }
	}
	
	@FXML
	void tipsAction(ActionEvent event)
	{
		
        try 
        {
            // Load the login scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/Trainertipscene.fxml"));
            Parent loginSceneRoot = loader.load();
            
            Ttipscenecontroller trainerController = loader.getController();
            trainerController.setPrimaryController(primaryController);

            // Get the stage
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

            // Set the new scene
            Scene loginScene = new Scene(loginSceneRoot);
            stage.setScene(loginScene);

            // Optional: set a title for the new scene
            //stage.setTitle("login Scene");

            // Show the stage
            stage.show();
        }
        catch (Exception e) 
        {
            e.printStackTrace();
        }
	}
	
	@FXML
	void lecturesAction(ActionEvent event)
	{
		
        try 
        {
            // Load the login scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/Tlecturescene.fxml"));
            Parent loginSceneRoot = loader.load();
            
            Tlecturescenecontroller trainerController = loader.getController();
            trainerController.setPrimaryController(primaryController);

            // Get the stage
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

            // Set the new scene
            Scene loginScene = new Scene(loginSceneRoot);
            stage.setScene(loginScene);

            // Optional: set a title for the new scene
            //stage.setTitle("login Scene");

            // Show the stage
            stage.show();
        }
        catch (Exception e) 
        {
            e.printStackTrace();
        }
	}
	
	@FXML
	void qnaAction(ActionEvent event)
	{
        try 
        {
            // Load the login scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/T_QueryScene.fxml"));
            Parent loginSceneRoot = loader.load();
            
            T_QuerySceneController trainerController = loader.getController();
            trainerController.setPrimaryController(primaryController);

            // Get the stage
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

            // Set the new scene
            Scene loginScene = new Scene(loginSceneRoot);
            stage.setScene(loginScene);

            // Optional: set a title for the new scene
            //stage.setTitle("login Scene");

            // Show the stage
            stage.show();
        }
        catch (Exception e) 
        {
            e.printStackTrace();
        }
	}
	
	@FXML
	void logoutAction(ActionEvent event)
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