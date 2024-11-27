package ui;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import controllers.PrimaryController;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class T_QAnswerSceneController
{
	private PrimaryController primaryController;
	
	@FXML
	private Button logout;
	@FXML
	private Button back;
	@FXML
	private Button QueryAnswer;
	@FXML
	private TextArea  Query;
	@FXML
	private TextArea  Answer;
	
	public void setPrimaryController(PrimaryController primaryController2) {
		this.primaryController = primaryController2;
	}
	
	// Method to set the query text and date
	public void setQuery(String question, String date) {
	    // Display the query question and date in the Query TextArea
	    Query.setText("Question: " + question + "\nDate: " + date);
	}

	// Method to handle submitting the answer (if required)
	@FXML
	void submitAnswerAction(ActionEvent event) {
	    String answer = Answer.getText();
	    if (answer != null && !answer.trim().isEmpty()) {
	        // Here you can handle the response submission (e.g., save to database)
	        System.out.println("Response Submitted: " + answer);
	        
	        // Optionally, clear the Answer TextArea after submission
	        Answer.clear();
	    } else {
	        // You could show an alert or notify the trainer that the answer is required
	        System.out.println("Please write a response before submitting.");
	    }
	}
	
	
	@FXML
	void backAction(ActionEvent event)
	{
		try 
        {
            // Load the login scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/Trainerscene.fxml"));
            Parent loginSceneRoot = loader.load();
            
            Trainerhomecontroller controller = loader.getController();
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
	
	@FXML
	void QueryAnswerAction(ActionEvent event)
	{
		try 
        {
            // Load the login scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/Trainerscene.fxml"));
            Parent loginSceneRoot = loader.load();
            
            Trainerhomecontroller controller = loader.getController();
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