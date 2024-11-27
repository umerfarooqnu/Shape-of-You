package ui;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.util.List;

import controllers.PrimaryController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class T_QuerySceneController
{
	private PrimaryController primaryController;
	
	@FXML
	private ListView<String> queryListView;  // ListView for displaying queries
	@FXML
	private Button logout;
	@FXML
	private Button back;
	@FXML
	private Button QueryResponse;
	
	public void setPrimaryController(PrimaryController primaryController2) {
		this.primaryController = primaryController2;
		loadQueries();
	}
	
	void loadQueries()
	{
		// Get all tips from primary controller
        List<List<String>> queries = primaryController.getUnansweredQueries();

        // Create a string representation of each health tip and add it to the ListView
        for (List<String> q : queries) {
            String content = "Question: " + q.get(1) + "\nDate: " + q.get(2);
            queryListView.getItems().add(content);
        }    
	}
	
	@FXML
	void queryListViewAction() {
	    // Get the selected query text
	    String selectedQuery = queryListView.getSelectionModel().getSelectedItem();
	    
	    if (selectedQuery != null) {
	        // Extract the query details, assuming that the format is:
	        // "Question: <question_text>\nDate: <date>"
	        String question = selectedQuery.split("\n")[0].replace("Question: ", "");
	        String date = selectedQuery.split("\n")[1].replace("Date: ", "");
	        
	        // Pass the selected query to the next scene
	        openQueryAnswerScene(question, date);
	    }
	}

	// Method to open the Query Answer Scene
	private void openQueryAnswerScene(String question, String date) {
	    try {
	        // Load the Query Answer Scene
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/T_QAnswerScene.fxml"));
	        Parent queryAnswerSceneRoot = loader.load();
	        
	        T_QAnswerSceneController controller = loader.getController();
	        controller.setPrimaryController(primaryController);
	        
	        // Set the selected question in the Query Answer Scene
	        controller.setQuery(question, date);
	        
	        // Get the stage
	        Stage stage = (Stage) queryListView.getScene().getWindow();
	        
	        // Set the new scene
	        Scene queryAnswerScene = new Scene(queryAnswerSceneRoot);
	        stage.setScene(queryAnswerScene);
	        
	        // Show the stage
	        stage.show();
	    } catch (Exception e) {
	        e.printStackTrace();
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
	void QueryResponseAction(ActionEvent event)
	{
		try 
        {
            // Load the login scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/T_QAnswerScene.fxml"));
            Parent loginSceneRoot = loader.load();
            
            T_QAnswerSceneController controller = loader.getController();
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