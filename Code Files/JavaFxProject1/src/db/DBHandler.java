package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.Person;
import models.Trainer;
import models.User;

public class DBHandler {
	
	private Connection connection;
	
	public void establishConnection()
	{
		String connectionString = 
	            "jdbc:sqlserver://localhost:1433;databaseName=ShapeOfYou;integratedSecurity=true;encrypt=false;trustServerCertificate=true;";

	        try {
	             connection = DriverManager.getConnection(connectionString);
	            System.out.println("Connection established.");


	        } catch (SQLException e) {
	            System.out.println("Error connecting to the database");
	            e.printStackTrace();
	        }
	}
	
	public void endConnection()
	{
		try {
			connection.close();
		} catch (SQLException e) {		
			e.printStackTrace();
		}
	}
	
	public Person getPerson(String email) {
		establishConnection();
	    Person person = null;  // To store the person object
	    
	    try {
	        Statement statement = connection.createStatement();

	        // Modify the query to filter by username (email)
	        String query = "SELECT um.ID, um.Name, um.Height, um.Weight, um.FitnessGoal, um.ProgressID, um.StatisticID, " +
	                       "l.UserID, l.Username, l.Password, l.Type " +
	                       "FROM UserMember um " +
	                       "JOIN Login l ON um.LoginID = l.UserID " +
	                       "WHERE l.Username = ?";  // Use a placeholder for username (email)

	        // Prepare the statement with the username filter
	        PreparedStatement preparedStatement = connection.prepareStatement(query);
	        preparedStatement.setString(1, email);  // Set the email (username) in the query

	        // Execute the query and get the result
	        ResultSet resultSet = preparedStatement.executeQuery();

	        // If a matching user is found
	        if (resultSet.next()) {
	            // Retrieve all the necessary data
	        	int userMemberID = resultSet.getInt("ID");
	 	            String name = resultSet.getString("Name");
	 	            double height = resultSet.getDouble("Height");
	 	            double weight = resultSet.getDouble("Weight");
	 	            String fitnessGoal = resultSet.getString("FitnessGoal");
	 	            int progressID = resultSet.getInt("ProgressID");
	 	            int statisticID = resultSet.getInt("StatisticID");
	 	            
	 	            String username = resultSet.getString("Username");
	 	            String password = resultSet.getString("Password");
	 	            
	 	            person = new User(userMemberID, name, username, password, height, weight, fitnessGoal, progressID, statisticID);
	            }
	            
	            else
	            {
	            	String query2 = "SELECT t.ID, t.Name, t.Specialization, t.Experience, t.Availability, " +
	                        "l.UserID, l.Username, l.Password, l.Type " +
	                        "FROM Trainer t " +
	                        "JOIN Login l ON t.LoginID = l.UserID " +
	                        "WHERE l.Username = ?";  // Use a placeholder for the username (email)

	        PreparedStatement preparedStatement2 = connection.prepareStatement(query2);
	        preparedStatement2.setString(1, email);  // Set the email (username) in the query

	        // Execute the query and get the result
	        ResultSet resultSet2 = preparedStatement2.executeQuery();

	        // Check if there's a result
	        if (resultSet2.next()) {
	            int trainerID = resultSet2.getInt("ID");
	            String trainerName = resultSet2.getString("Name");
	            String specialization = resultSet2.getString("Specialization");
	            String experience = resultSet2.getString("Experience");
	            boolean availability = resultSet2.getBoolean("Availability");
	            
	            // Login details
	            String username = resultSet2.getString("Username");
	            String password = resultSet2.getString("Password");

	            // Create a new Trainer object using the data retrieved from the query
	            person = new Trainer(trainerID, trainerName, username, password, specialization, experience, availability);
	            
	        }

	        // Close resources
	        resultSet2.close();
	        preparedStatement2.close();
	        connection.close();
          
	        }

	        // Close resources
	        resultSet.close();
	        preparedStatement.close();
	        statement.close();
	        

	    } catch (SQLException e) {
	        System.out.println("Error connecting to the database");
	        e.printStackTrace();
	    }

	    // Return the person object or null if no match was found
	    return person;
	}

	public boolean signUp(User user) {
	    establishConnection();
	    try {
	        // Check if the username already exists
	        String checkQuery = "SELECT COUNT(*) FROM Login WHERE Username = ?";
	        PreparedStatement checkStatement = connection.prepareStatement(checkQuery);
	        checkStatement.setString(1, user.getEmail());

	        ResultSet checkResult = checkStatement.executeQuery();
	        if (checkResult.next() && checkResult.getInt(1) > 0) {
	            return false;  // Username already exists
	        }

	        // Insert into Login table
	        String loginQuery = "INSERT INTO Login (Username, Password, Type) VALUES (?, ?, ?)";
	        PreparedStatement loginStatement = connection.prepareStatement(loginQuery, Statement.RETURN_GENERATED_KEYS);
	        loginStatement.setString(1, user.getEmail());
	        loginStatement.setString(2, user.getPassword());
	        loginStatement.setString(3, "User");

	        int loginRows = loginStatement.executeUpdate();
	        if (loginRows == 0) {
	            return false;  // Failed to insert into Login table
	        }

	        // Get the generated Login ID
	        ResultSet generatedKeys = loginStatement.getGeneratedKeys();
	        if (!generatedKeys.next()) {
	            return false;  // Failed to retrieve Login ID
	        }

	        int loginID = generatedKeys.getInt(1);

	        // Insert into UserMember table
	        String userQuery = "INSERT INTO UserMember (Name, Height, Weight, FitnessGoal, ProgressID, StatisticID, LoginID) " +
	                           "VALUES (?, ?, ?, ?, ?, ?, ?)";
	        PreparedStatement userStatement = connection.prepareStatement(userQuery);
	        userStatement.setString(1, user.getName());
	        userStatement.setDouble(2, user.getHeight());
	        userStatement.setDouble(3, user.getWeight());
	        userStatement.setString(4, user.getFitnessGoal());
	        userStatement.setInt(5, user.getProgressID());
	        userStatement.setInt(6, user.getStatisticID());
	        userStatement.setInt(7, loginID);

	        int userRows = userStatement.executeUpdate();
	        if (userRows == 0) {
	            return false;  // Failed to insert into UserMember table
	        }

	        return true;  // Successfully registered user

	    } catch (SQLException e) {
	        // e.printStackTrace();
	        return false;  // Catch and handle SQL exceptions
	    }
	}



	public boolean signUp(Trainer trainer) {
	    establishConnection();
	    try {
	        // Check if the username already exists
	        String checkQuery = "SELECT COUNT(*) FROM Login WHERE Username = ?";
	        PreparedStatement checkStatement = connection.prepareStatement(checkQuery);
	        checkStatement.setString(1, trainer.getEmail());

	        ResultSet checkResult = checkStatement.executeQuery();
	        if (checkResult.next() && checkResult.getInt(1) > 0) {
	            return false;  // Username already exists
	        }

	        // Insert into Login table
	        String loginQuery = "INSERT INTO Login (Username, Password, Type) VALUES (?, ?, ?)";
	        PreparedStatement loginStatement = connection.prepareStatement(loginQuery, Statement.RETURN_GENERATED_KEYS);
	        loginStatement.setString(1, trainer.getEmail());
	        loginStatement.setString(2, trainer.getPassword());
	        loginStatement.setString(3, "Trainer");

	        int loginRows = loginStatement.executeUpdate();
	        if (loginRows == 0) {
	            return false;  // Failed to insert into Login table
	        }

	        // Get the generated Login ID
	        ResultSet generatedKeys = loginStatement.getGeneratedKeys();
	        if (!generatedKeys.next()) {
	            return false;  // Failed to retrieve Login ID
	        }

	        int loginID = generatedKeys.getInt(1);

	        // Insert into Trainer table
	        String trainerQuery = "INSERT INTO Trainer (Name, Specialization, Experience, Availability, LoginID) " +
	                              "VALUES (?, ?, ?, ?, ?)";
	        PreparedStatement trainerStatement = connection.prepareStatement(trainerQuery);
	        trainerStatement.setString(1, trainer.getName());
	        trainerStatement.setString(2, trainer.getSpecialization());
	        trainerStatement.setString(3, trainer.getExperience());
	        trainerStatement.setBoolean(4, trainer.getAvailablity());
	        trainerStatement.setInt(5, loginID);

	        int trainerRows = trainerStatement.executeUpdate();
	        if (trainerRows == 0) {
	            return false;  // Failed to insert into Trainer table
	        }

	        return true;  // Successfully registered trainer

	    } catch (SQLException e) {
	         e.printStackTrace();
	        return false;  // Catch and handle SQL exceptions
	    }
	}



	public List<List<String>> getAllHealthTips() {
        establishConnection();
        
        // List to hold all the health tips data
        List<List<String>> allHealthTips = new ArrayList<>();
        
        try {
            // Query to fetch health tips along with trainer information
            String query = "SELECT Content, Category, Date, TrainerID FROM HealthTip";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            // Process the result set
            while (resultSet.next()) {
                // Create a list to hold individual health tip details
                List<String> healthTip = new ArrayList<>();
                
                // Add health tip information to the list
                healthTip.add(resultSet.getString("Content"));
                healthTip.add(resultSet.getString("Category"));
                healthTip.add(resultSet.getString("Date"));  // Date as String, could format it if needed
                
                // Add this health tip list to the main list
                allHealthTips.add(healthTip);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Optionally handle the exception (e.g., log it, rethrow it, etc.)
        }
        
        return allHealthTips;  // Return the list of health tips
    }
	
	public List<List<String>> getAllLectures() {
	    establishConnection();
	    
	    // List to hold all the lectures data
	    List<List<String>> allLectures = new ArrayList<>();
	    
	    try {
	        // Query to fetch lecture data along with trainer information (if needed)
	        String query = "SELECT Title, Duration, Date, TrainerID FROM Lecture";
	        Statement statement = connection.createStatement();
	        ResultSet resultSet = statement.executeQuery(query);

	        // Process the result set
	        while (resultSet.next()) {
	            // Create a list to hold individual lecture details
	            List<String> lecture = new ArrayList<>();
	            
	            // Add lecture information to the list
	            lecture.add(resultSet.getString("Title"));    // Add title
	            lecture.add(resultSet.getString("Duration")); // Add duration
	            lecture.add(resultSet.getString("Date"));     // Add date          
	            
	            // Add this lecture list to the main list
	            allLectures.add(lecture);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        // Optionally handle the exception (e.g., log it, rethrow it, etc.)
	    }
	    
	    return allLectures;  // Return the list of lectures
	}
	
	public List<List<String>> getUnansweredQueries() {
	    establishConnection();
	    
	    // List to hold all unanswered query data
	    List<List<String>> unansweredQueries = new ArrayList<>();
	    
	    try {
	        // SQL query to fetch all queries where the response is not 'NONE'
	        String query = "SELECT QueryID, UserID, TrainerID, Question, Response, Date FROM Query WHERE Response = 'NONE'";
	        
	        // Create statement and execute the query
	        Statement statement = connection.createStatement();
	        ResultSet resultSet = statement.executeQuery(query);

	        // Process the result set
	        while (resultSet.next()) {
	            // Create a list to hold the individual query details
	            List<String> queryData = new ArrayList<>();
	            
	            // Add query information to the list
	            queryData.add(String.valueOf(resultSet.getInt("QueryID")));  // Add QueryID                        
	            queryData.add(resultSet.getString("Question"));  // Add Question	   
	            queryData.add(resultSet.getString("Date"));      // Add Date
	            
	            // Add this query's data to the main list
	            unansweredQueries.add(queryData);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        // Optionally handle the exception (e.g., log it, rethrow it, etc.)
	    }
	    
	    return unansweredQueries;  // Return the list of unanswered queries
	}
	
	public List<List<String>> getAnsweredQueries() {
	    establishConnection();
	    
	    // List to hold all answered query data
	    List<List<String>> answeredQueries = new ArrayList<>();
	    
	    try {
	        // SQL query to fetch all queries where the response is NOT 'NONE'
	        String query = "SELECT QueryID, UserID, TrainerID, Question, Response, Date FROM Query WHERE Response != 'NONE'";
	        
	        // Create statement and execute the query
	        Statement statement = connection.createStatement();
	        ResultSet resultSet = statement.executeQuery(query);

	        // Process the result set
	        while (resultSet.next()) {
	            // Create a list to hold the individual query details
	            List<String> queryData = new ArrayList<>();
	            
	            // Add query information to the list
	            queryData.add(String.valueOf(resultSet.getInt("QueryID")));  // Add QueryID
	            queryData.add(resultSet.getString("Question"));             // Add Question
	            queryData.add(resultSet.getString("Response"));             // Add Response
	            queryData.add(resultSet.getString("Date"));                 // Add Date
	            
	            // Add this query's data to the main list
	            answeredQueries.add(queryData);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        // Optionally handle the exception (e.g., log it, rethrow it, etc.)
	    }
	    
	    return answeredQueries;  // Return the list of answered queries
	}
	
	public List<List<String>> getAllProgressReports(User user) {
	    establishConnection();

	    // List to hold all progress report data
	    List<List<String>> progressReports = new ArrayList<>();

	    try {
	        // SQL query to fetch all progress reports for the specified user
	        String query = "SELECT ReportID, WorkoutStats, DietStats, ProgressSummary, Date " +
	                       "FROM ProgressReport WHERE UserID = ?";
	        
	        // Prepare a statement to avoid SQL injection and set the parameter
	        PreparedStatement preparedStatement = connection.prepareStatement(query);
	        preparedStatement.setInt(1, user.getId()); // Assuming User class has a getID() method

	        // Execute the query
	        ResultSet resultSet = preparedStatement.executeQuery();

	        // Process the result set
	        while (resultSet.next()) {
	            // Create a list to hold the individual progress report details
	            List<String> reportData = new ArrayList<>();

	            // Add report information to the list	           
	            reportData.add(resultSet.getString("WorkoutStats"));                // Add WorkoutStats
	            reportData.add(resultSet.getString("DietStats"));                   // Add DietStats
	            reportData.add(resultSet.getString("ProgressSummary"));             // Add ProgressSummary
	            reportData.add(resultSet.getString("Date"));                        // Add Date

	            // Add this report's data to the main list
	            progressReports.add(reportData);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        // Optionally handle the exception (e.g., log it, rethrow it, etc.)
	    }

	    return progressReports;  // Return the list of progress reports
	}

	public List<String> getStats(User user) {
	    establishConnection();

	    // List to hold the user's stats
	    List<String> stats = new ArrayList<>();

	    try {
	        // SQL query to fetch the latest stats for the specified user
	        String query = "SELECT ExerciseCount, CaloriesBurned, AvgWorkoutDuration, DietComplianceRate, WeeklyProgress, MonthlyProgress " +
	                       "FROM Stats WHERE UserID = ?";
	        
	        // Prepare a statement to avoid SQL injection and set the parameter
	        PreparedStatement preparedStatement = connection.prepareStatement(query);
	        preparedStatement.setInt(1, user.getId()); // Assuming User class has a getId() method

	        // Execute the query
	        ResultSet resultSet = preparedStatement.executeQuery();

	        // Process the result set (assuming one result, the most recent)
	        if (resultSet.next()) {
	            stats.add(resultSet.getInt("ExerciseCount") + " exercises");     // Add ExerciseCount
	            stats.add(resultSet.getInt("CaloriesBurned") + " kcal");         // Add CaloriesBurned
	            stats.add(resultSet.getFloat("AvgWorkoutDuration") + " minutes"); // Add AvgWorkoutDuration
	            stats.add(resultSet.getInt("DietComplianceRate") + "%");          // Add DietComplianceRate
	            stats.add(resultSet.getString("WeeklyProgress"));                 // Add WeeklyProgress
	            stats.add(resultSet.getString("MonthlyProgress"));                // Add MonthlyProgress
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        // Optionally handle the exception (e.g., log it, rethrow it, etc.)
	    }

	    return stats; // Return the stats as a list
	}

	public List<List<String>> getNotifications(User user) {
	    establishConnection();

	    // List to hold all notifications for the specified user
	    List<List<String>> notifications = new ArrayList<>();

	    try {
	        // SQL query to fetch all notifications for the specified user
	        String query = "SELECT Message, Date, Type FROM Notification WHERE UserID = ? ORDER BY Date DESC";
	        
	        // Prepare a statement to avoid SQL injection and set the parameter
	        PreparedStatement preparedStatement = connection.prepareStatement(query);
	        preparedStatement.setInt(1, user.getId()); // Assuming User class has a getId() method

	        // Execute the query
	        ResultSet resultSet = preparedStatement.executeQuery();

	        // Process the result set and populate the notifications list
	        while (resultSet.next()) {
	            // For each notification, add its details to the list
	            List<String> notificationDetails = new ArrayList<>();
	            notificationDetails.add(resultSet.getString("Message"));  // Add the message
	            notificationDetails.add(resultSet.getString("Date"));     // Add the date
	            notificationDetails.add(resultSet.getString("Type"));     // Add the notification type

	            notifications.add(notificationDetails);  // Add the notification to the list
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        // Optionally handle the exception (e.g., log it, rethrow it, etc.)
	    }

	    return notifications; // Return the list of notifications
	}
	
	public List<String> getTrainer(User user) {
	    establishConnection();  // Establish the database connection

	    // List to hold the trainer's details
	    List<String> trainerDetails = new ArrayList<>();

	    try {
	        // SQL query to fetch the TrainerID for the specified user from the UserHasTrainer table
	        String query = "SELECT TrainerID FROM UserHasTrainer WHERE UserID = ?";
	        
	        // Prepare a statement to avoid SQL injection and set the parameter
	        PreparedStatement preparedStatement = connection.prepareStatement(query);
	        preparedStatement.setInt(1, user.getId());  // Assuming User class has a getId() method

	        // Execute the query to get the TrainerID
	        ResultSet resultSet = preparedStatement.executeQuery();

	        // If the user has a trainer (TrainerID exists), fetch trainer details
	        if (resultSet.next()) {
	            int trainerId = resultSet.getInt("TrainerID");  // Get the TrainerID for the user

	            // SQL query to fetch the trainer details using TrainerID
	            String trainerQuery = "SELECT Name, Specialization, Experience, Availability FROM Trainer WHERE ID = ?";
	            PreparedStatement trainerPreparedStatement = connection.prepareStatement(trainerQuery);
	            trainerPreparedStatement.setInt(1, trainerId);  // Set the TrainerID

	            // Execute the query to get the trainer details
	            ResultSet trainerResultSet = trainerPreparedStatement.executeQuery();

	            // If the trainer exists, fetch their details
	            if (trainerResultSet.next()) {
	                trainerDetails.add(trainerResultSet.getString("Name"));          // Add trainer's name
	                trainerDetails.add(trainerResultSet.getString("Specialization")); // Add specialization
	                trainerDetails.add(trainerResultSet.getString("Experience"));     // Add experience
	                trainerDetails.add(trainerResultSet.getBoolean("Availability") ? "Available" : "Not Available"); // Add availability status
	            }
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        // Optionally handle the exception (e.g., log it, rethrow it, etc.)
	    }

	    return trainerDetails;  // Return the list of trainer details
	}
	
	public List<String> getWorkoutPlan(User user) {
	    establishConnection();  // Establish connection to the database

	    List<String> workoutPlanDetails = new ArrayList<>();

	    try {
	        // SQL query to get the WorkoutPlanID associated with the user
	        String query = "SELECT WorkoutPlanID FROM UserHasWorkoutPlan WHERE UserID = ?";
	        
	        PreparedStatement preparedStatement = connection.prepareStatement(query);
	        preparedStatement.setInt(1, user.getId());  // Set the UserID

	        ResultSet resultSet = preparedStatement.executeQuery();

	        // If a workout plan is associated with the user, fetch the details
	        if (resultSet.next()) {
	            int workoutPlanId = resultSet.getInt("WorkoutPlanID");

	            // Fetch workout plan details from the WorkoutPlan table
	            String planQuery = "SELECT Exercises, Duration, Frequency, DifficultyLevel FROM WorkoutPlan WHERE PlanID = ?";
	            PreparedStatement planPreparedStatement = connection.prepareStatement(planQuery);
	            planPreparedStatement.setInt(1, workoutPlanId);  // Set the WorkoutPlanID

	            ResultSet planResultSet = planPreparedStatement.executeQuery();

	            if (planResultSet.next()) {
	                String exercises = planResultSet.getString("Exercises");
	                int duration = planResultSet.getInt("Duration");
	                int frequency = planResultSet.getInt("Frequency");
	                int difficultyLevel = planResultSet.getInt("DifficultyLevel");

	                workoutPlanDetails.add(exercises);
	                workoutPlanDetails.add(duration + " minutes");
	                workoutPlanDetails.add(frequency + " days/week");
	                workoutPlanDetails.add("" + difficultyLevel);
	            }
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return workoutPlanDetails;  // Return the list of workout plan details
	}

	public List<String> getDietPlan(User user) {
	    establishConnection();  // Establish connection to the database

	    List<String> dietPlanDetails = new ArrayList<>();

	    try {
	        // SQL query to get the DietPlanID associated with the user
	        String query = "SELECT DietPlanID FROM UserHasDietPlan WHERE UserID = ?";
	        
	        PreparedStatement preparedStatement = connection.prepareStatement(query);
	        preparedStatement.setInt(1, user.getId());  // Set the UserID

	        ResultSet resultSet = preparedStatement.executeQuery();

	        // If a diet plan is associated with the user, fetch the details
	        if (resultSet.next()) {
	            int dietPlanId = resultSet.getInt("DietPlanID");

	            // Fetch diet plan details from the DietPlan table
	            String planQuery = "SELECT Meals, Nutrition FROM DietPlan WHERE PlanID = ?";
	            PreparedStatement planPreparedStatement = connection.prepareStatement(planQuery);
	            planPreparedStatement.setInt(1, dietPlanId);  // Set the DietPlanID

	            ResultSet planResultSet = planPreparedStatement.executeQuery();

	            if (planResultSet.next()) {
	                String meals = planResultSet.getString("Meals");
	                int nutrition = planResultSet.getInt("Nutrition");

	                dietPlanDetails.add("Meals: " + meals);
	                dietPlanDetails.add(nutrition + " points");
	            }
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return dietPlanDetails;  // Return the list of diet plan details
	}


}
