package ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import controllers.PrimaryController;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Tlecturescenecontroller {

    private PrimaryController primaryController;

    @FXML
    private Button logout;
    @FXML
    private Button back;
    @FXML
    private Button upload;
    @FXML
    private ListView<String> lectureListView;  // ListView for lectures
    
    private static final String[] videoFilePaths = {	
            "@../../../Recources/Videos/10 MIN GLUTE WORKOUT for a Lifted and Pumped Booty.mp4",
            "@../../../Recources/Videos/10 MIN PERFECT ABS WORKOUT (RESULTS GUARANTEED!).mp4",
            "@../../../Recources/Videos/10 MIN STANDING BUTT LIFT + TONED THIGHS _ Round Booty _ No Equipment, No Repeat.mp4",
            "@../../../Recources/Videos/10 MINUTE FAT BURNING MORNING ROUTINE _ Do this every day _ Rowan Row.mp4",
            "@../../../Recources/Videos/15 MIN BEGINNER BOOTY WORKOUT (Low Impact, No Squats & Jumps) _ Round & Lifted Booty.mp4",
            "@../../../Recources/Videos/15 MIN FAT BURN (At Home, No Equipment, No Repeats).mp4",
            "@../../../Recources/Videos/15 MIN FULL BODY STRETCH - Improve Mobility and Flexibility.mp4",
            "@../../../Recources/Videos/15 Minute Cardio-HIIT “Dance” To The Beat Workout [Our Engagement Celebration].mp4",
            "@../../../Recources/Videos/20 Min Fat Burning HIIT Workout -  Full body Cardio, No Equipment, No Repeat.mp4",
            "@../../../Recources/Videos/20 Minute Full Body Cardio HIIT Workout [NO REPEAT].mp4",
            "@../../../Recources/Videos/20 Minute Full Body Strength Workout (No Equipment-No Repeat).mp4",
            "@../../../Recources/Videos/25 MIN FULL BODY HIIT for Beginners - No Equipment - No Repeat Home Workout.mp4",
            "@../../../Recources/Videos/28 Minute Full Body Workout To Get In Shape - Do This Workout Every Evening _ EMMA Fitness.mp4",
            "@../../../Recources/Videos/30 MIN FULL BODY CARDIO HIIT Workout (Intense, No Equipment).mp4",
            "@../../../Recources/Videos/30 MIN FULL BODY HIIT (No Jumping + No Equipment).mp4",
            "@../../../Recources/Videos/30 MIN WALKING CARDIO WORKOUT _ Intense Full Body Fat Burn at Home ~ Emi.mp4",
            "@../../../Recources/Videos/30 Minute Full Body Strength Workout [No Equipment + Modifications].mp4",
            "@../../../Recources/Videos/Assisted Stretching - Partner Gymnastics Stretching_ Build Strength and Flexibility Together more.mp4",
            "@../../../Recources/Videos/Chest. Shoulder & ABS Workout (No Equipment, No gym) 집에서 할 수 있는 가슴, 어깨 & 복근 운동 (장비없음).mp4",
            "@../../../Recources/Videos/Lose Weight and Belly.mp4",
            "@../../../Recources/Videos/NO GYM FULL BODY WORKOUT (feat. 5 min Tabata) _ 5분 전신 타바타 운동.mp4",
            "@../../../Recources/Videos/PILATES FLAT STOMACH in 14 Days 🔥 Belly Fat Burn _ 5 min Workout.mp4"
        };

    public void setPrimaryController(PrimaryController primaryController) {
        this.primaryController = primaryController;
        loadLectures();  // Load lectures when the scene is initialized
    }
    
    @FXML
    void initialize() {
        // Add double-click listener to the ListView
        lectureListView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                // Get the selected index
                int selectedIndex = lectureListView.getSelectionModel().getSelectedIndex();
                if (selectedIndex != -1) {
                    // Get the selected lecture's video file path using the index
                    String videoFilePath = getVideoFilePathFromIndex(selectedIndex);
                    openVideo(videoFilePath);  // Open video using the system's default player
                }
            }
        });
    }

 // This method gets the video path from the selected index
    private String getVideoFilePathFromIndex(int index) {
        if (index >= 0 && index < videoFilePaths.length) {
            return videoFilePaths[index];
        }
        return null; // Return null if the index is invalid
    }

    // Open the video using the system's default video player
    private void openVideo(String videoFilePath) {
        try {
            File videoFile = new File(videoFilePath);
            if (videoFile.exists()) {
                Desktop desktop = Desktop.getDesktop();
                desktop.open(videoFile);  // This will open the video with the default system player
            } else {
                System.out.println("Video file not found: " + videoFilePath);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error opening video: " + e.getMessage());
        }
    }

    private void loadLectures() {
        // Get all lectures from primary controller
        List<List<String>> allLectures = primaryController.getAllLectures();

        // Populate the ListView with lecture data
        for (List<String> lecture : allLectures) {
            String lectureData = "Title: " + lecture.get(0) + "\nDuration: " + lecture.get(1) + "\nDate: " + lecture.get(2);
            lectureListView.getItems().add(lectureData);
        }
    }

    @FXML
    void backAction(ActionEvent event) {
        try {
            // Load the trainer scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/Trainerscene.fxml"));
            Parent trainerSceneRoot = loader.load();

            Trainerhomecontroller controller = loader.getController();
            controller.setPrimaryController(primaryController);

            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Scene trainerScene = new Scene(trainerSceneRoot);
            stage.setScene(trainerScene);
            stage.setTitle("Trainer Scene");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void logoutAction(ActionEvent event) {
        try {
            // Load the login scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/scene1.fxml"));
            Parent loginSceneRoot = loader.load();

            scene1controller controller = loader.getController();
            controller.setPrimaryController(primaryController);

            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Scene loginScene = new Scene(loginSceneRoot);
            stage.setScene(loginScene);
            stage.setTitle("Login Scene");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
	void uploadAction(ActionEvent event) {
	    
		// Create a FileChooser
        FileChooser fileChooser = new FileChooser();

        // Set file chooser properties
        fileChooser.setTitle("Select File to Upload");
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Video Files", "*.mp4", "*.webm" , "*.mkv")    
        );

        // Show the file chooser dialog
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            // Handle the selected file (e.g., upload to server or process locally)
            System.out.println("File selected: " + selectedFile.getAbsolutePath());
            
            // Optionally, display a message or update the UI
            //healthTipListView.getItems().add("Uploaded file: " + selectedFile.getName());
        } else {
            System.out.println("File selection canceled.");
        }
    
	}
}
