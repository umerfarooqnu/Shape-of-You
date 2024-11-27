CREATE TABLE Login (
    UserID INT IDENTITY(1,1) PRIMARY KEY,    -- Auto-incrementing primary key
    Username NVARCHAR(50) NOT NULL,   -- Username, must be unique
    Password NVARCHAR(255) NOT NULL,         -- Password, should be hashed
    Type NVARCHAR(50) NOT NULL               -- User type, e.g., "Admin", "User"
);

CREATE TABLE UserMember (
    ID INT IDENTITY(1,1) PRIMARY KEY,        -- Auto-incrementing primary key
    Name NVARCHAR(100) NOT NULL,             -- User's name
    Height FLOAT NOT NULL,                   -- User's height in cm
    Weight FLOAT NOT NULL,                   -- User's weight in kg
    FitnessGoal NVARCHAR(100) NOT NULL,      -- Fitness goal (e.g., "Weight Loss")
    ProgressID INT,                 -- Foreign key reference (progress tracking)
    StatisticID INT,                -- Foreign key reference (statistics tracking)
    LoginID INT NOT NULL,                    -- Foreign key reference to Login table
    FOREIGN KEY (LoginID) REFERENCES Login(UserID)
        ON UPDATE CASCADE                    -- Update LoginID in User when UserID in Login changes
        ON DELETE CASCADE                    -- Delete User when associated Login is deleted
);

CREATE TABLE Trainer (
    ID INT IDENTITY(1,1) PRIMARY KEY,        -- Auto-incrementing primary key
    Name NVARCHAR(100) NOT NULL,             -- Trainer's name
    Specialization NVARCHAR(100) NOT NULL,   -- Trainer's area of expertise
    Experience NVARCHAR(50) NOT NULL,        -- Trainer's experience level (e.g., "5 years")
    Availability BIT NOT NULL,               -- Trainer's availability (1 for available, 0 for not available)
    LoginID INT NOT NULL,                    -- Foreign key reference to Login table
    FOREIGN KEY (LoginID) REFERENCES Login(UserID)
        ON UPDATE CASCADE                    -- Update LoginID in Trainer when UserID in Login changes
        ON DELETE CASCADE                    -- Delete Trainer when associated Login is deleted
);

CREATE TABLE Notification (
    NotificationID INT IDENTITY(1,1) PRIMARY KEY, -- Auto-incrementing primary key
    Message NVARCHAR(255) NOT NULL,              -- Notification message
    Date DATE NOT NULL,                          -- Notification date
    UserID INT NOT NULL,                         -- Foreign key referencing a user
    Type NVARCHAR(50) NOT NULL,                  -- Notification type (e.g., "Info", "Reminder", etc.)
    FOREIGN KEY (UserID) REFERENCES UserMember(ID) -- Enforce referential integrity
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE Stats (
    StatsID INT IDENTITY(1,1) PRIMARY KEY,         -- Auto-incrementing primary key
    UserID INT NOT NULL,                           -- Foreign key referencing a user
    ExerciseCount INT NOT NULL,                    -- Total exercises performed
    CaloriesBurned INT NOT NULL,                   -- Total calories burned
    AvgWorkoutDuration FLOAT NOT NULL,             -- Average workout duration in minutes
    DietComplianceRate INT NOT NULL,               -- Diet compliance rate in percentage
    WeeklyProgress NVARCHAR(255) NOT NULL,         -- Weekly progress description
    MonthlyProgress NVARCHAR(255) NOT NULL,        -- Monthly progress description
    FOREIGN KEY (UserID) REFERENCES UserMember(ID) -- Enforce referential integrity
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE HealthTip (
    TipID INT IDENTITY(1,1) PRIMARY KEY,          -- Auto-incrementing primary key
    Content NVARCHAR(MAX) NOT NULL,              -- Health tip content
    Category NVARCHAR(100) NOT NULL,             -- Category of the tip (e.g., "Diet", "Exercise")
    Date DATE NOT NULL DEFAULT GETDATE(),        -- Date the tip was added (defaults to current date)
    TrainerID INT NOT NULL,                      -- Foreign key referencing a Trainer
    FOREIGN KEY (TrainerID) REFERENCES Trainer(ID) 
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE Query (
    QueryID INT PRIMARY KEY IDENTITY(1,1),  -- Auto-incrementing primary key
    UserID INT NOT NULL,                    -- ID of the user who submitted the query
    TrainerID INT NOT NULL,                 -- ID of the trainer to respond to the query
    Question NVARCHAR(500) NOT NULL,        -- The question asked by the user
    Response NVARCHAR(500) DEFAULT 'NONE',  -- The response to the query (default: NONE)
    Date DATE NOT NULL,                     -- Date of the query submission
    FOREIGN KEY (UserID) REFERENCES UserMember(ID),  -- Foreign key to UserMember table
    FOREIGN KEY (TrainerID) REFERENCES Trainer(ID)  -- Foreign key to Trainer table
);

CREATE TABLE Lecture (
    LectureID INT IDENTITY(1,1) PRIMARY KEY,   -- Auto-incrementing primary key
    Title NVARCHAR(255) ,             -- Lecture title
    Duration INT,                    -- Duration in minutes
    Date DATE,                       -- Lecture date
    TrainerID INT,                   -- Trainer ID (foreign key)
    FOREIGN KEY (TrainerID) REFERENCES Trainer(ID)
        ON UPDATE CASCADE                     -- Update Lecture when Trainer ID changes
        ON DELETE SET NULL                    -- Set TrainerID to NULL if trainer is deleted
);

CREATE TABLE ProgressReport (
    ReportID INT IDENTITY(1,1) PRIMARY KEY, -- Auto-incrementing primary key
    UserID INT NOT NULL,                   -- User ID (foreign key)
    WorkoutStats NVARCHAR(MAX) NOT NULL,   -- Details of workout statistics
    DietStats NVARCHAR(MAX) NOT NULL,      -- Details of diet statistics
    ProgressSummary NVARCHAR(MAX) NOT NULL,-- Summary of user's progress
    Date DATE NOT NULL,                    -- Date of the report
    FOREIGN KEY (UserID) REFERENCES UserMember(ID)
        ON UPDATE CASCADE                  -- Update Report when User ID changes
        ON DELETE CASCADE                  -- Delete report when associated User is deleted
);

CREATE TABLE Progress (
    ProgressID INT IDENTITY(1,1) PRIMARY KEY, -- Auto-incrementing primary key
    UserID INT NOT NULL,                     -- User ID (foreign key)
    WorkoutStats NVARCHAR(MAX) NOT NULL,     -- Details of workout progress
    DietStats NVARCHAR(MAX) NOT NULL,        -- Details of dietary progress
    WeightChanges INT NOT NULL,              -- Weight changes (in kg)
    GoalsMet INT NOT NULL,                   -- Number of goals met
    Date DATE NOT NULL,                      -- Date of progress entry
    FOREIGN KEY (UserID) REFERENCES UserMember(ID)
        ON UPDATE CASCADE                    -- Update Progress when User ID changes
        ON DELETE CASCADE                    -- Delete Progress when associated User is deleted
);

CREATE TABLE Feedback (
    FeedbackID INT IDENTITY(1,1) PRIMARY KEY, -- Auto-incrementing primary key
    UserID INT NOT NULL,                     -- Foreign key referencing UserMember
    Rating INT NOT NULL CHECK (Rating BETWEEN 1 AND 5), -- Rating between 1 to 5
    Comments NVARCHAR(MAX),                  -- Feedback comments
    Date DATE NOT NULL,                      -- Date of feedback submission
    FOREIGN KEY (UserID) REFERENCES UserMember(ID)
        ON UPDATE CASCADE                    -- Update Feedback when User ID changes
        ON DELETE CASCADE                    -- Delete Feedback when associated User is deleted
);

CREATE TABLE Plans (
    PlanID INT IDENTITY(1,1) PRIMARY KEY, -- Auto-incrementing primary key
    UserID INT NOT NULL,                 -- Foreign key referencing UserMember
    FOREIGN KEY (UserID) REFERENCES UserMember(ID)
        ON UPDATE CASCADE                -- Update Plans when User ID changes
        ON DELETE CASCADE                -- Delete Plans when associated User is deleted
);

CREATE TABLE DietPlan (
    PlanID INT NOT NULL PRIMARY KEY,      -- Foreign key referencing Plans
    Meals NVARCHAR(MAX) NOT NULL,         -- Meal details
    Nutrition INT NOT NULL,               -- Total nutrition score
    FOREIGN KEY (PlanID) REFERENCES Plans(PlanID)
        ON UPDATE CASCADE                 -- Update DietPlan when Plans ID changes
        ON DELETE CASCADE                 -- Delete DietPlan when associated Plans is deleted
);

CREATE TABLE WorkoutPlan (
    PlanID INT NOT NULL PRIMARY KEY,      -- Foreign key referencing Plans
    Exercises NVARCHAR(MAX) NOT NULL,    -- Exercises in the plan
    Duration INT NOT NULL,               -- Duration of the workout in minutes
    Frequency INT NOT NULL,              -- Frequency in days per week
    DifficultyLevel INT NOT NULL 
    FOREIGN KEY (PlanID) REFERENCES Plans(PlanID)
        ON UPDATE CASCADE                 -- Update WorkoutPlan when Plans ID changes
        ON DELETE CASCADE                 -- Delete WorkoutPlan when associated Plans is deleted
);


CREATE TABLE Meal (
    MealID INT IDENTITY(1,1) PRIMARY KEY, -- Auto-incrementing primary key
    MealName NVARCHAR(100) NOT NULL,      -- Name of the meal
    Fats INT NOT NULL,                    -- Fats in grams
    Fibre INT NOT NULL,                   -- Fibre in grams
    Proteins INT NOT NULL,                -- Proteins in grams
    Carbs INT NOT NULL,                   -- Carbohydrates in grams
    Calories INT NOT NULL                 -- Calories in kcal
);

CREATE TABLE Exercise (
    ExerciseID INT IDENTITY(1,1) PRIMARY KEY, -- Auto-incrementing primary key
    ExerciseName NVARCHAR(100) NOT NULL,      -- Name of the exercise
    Sets INT NOT NULL,                        -- Number of sets
    Reps INT NOT NULL                         -- Number of reps
);

CREATE TABLE UserHasTrainer (
    UserID INT NOT NULL,          -- Foreign key referencing User
    TrainerID INT NOT NULL,       -- Foreign key referencing Trainer
    PRIMARY KEY (UserID, TrainerID), -- Composite primary key for uniqueness
);

INSERT INTO UserHasTrainer (UserID, TrainerID) VALUES
(1, 1),   -- User 1 with Trainer 1 (ongoing)
(2, 2),   -- User 2 with Trainer 2 (ongoing)
(3, 3),   -- User 3 with Trainer 3 (relationship ended)
(4, 4),   -- User 4 with Trainer 4 (ongoing)
(5, 5),   -- User 5 with Trainer 5 (relationship ended)
(6, 1),   -- User 6 with Trainer 1 (ongoing)
(7, 2),   -- User 7 with Trainer 2 (ongoing)
(8, 3),   -- User 8 with Trainer 3 (ongoing)
(9, 4),   -- User 9 with Trainer 4 (ongoing)
(10, 5),  -- User 10 with Trainer 5 (relationship ended)
(11, 1),  -- User 11 with Trainer 1 (ongoing)
(12, 2),  -- User 12 with Trainer 2 (ongoing)
(13, 3);  -- User 13 with Trainer 3 (ongoing)

CREATE TABLE UserHasWorkoutPlan (
    UserID INT NOT NULL,               -- Foreign key referencing User
    WorkoutPlanID INT NOT NULL,        -- Foreign key referencing WorkoutPlan
    PRIMARY KEY (UserID, WorkoutPlanID), -- Composite primary key for uniqueness
    FOREIGN KEY (UserID) REFERENCES UserMember(ID),    
    FOREIGN KEY (WorkoutPlanID) REFERENCES WorkoutPlan(PlanID)     
);


CREATE TABLE UserHasDietPlan (
    UserID INT NOT NULL,              -- Foreign key referencing User
    DietPlanID INT NOT NULL,          -- Foreign key referencing DietPlan
    PRIMARY KEY (UserID, DietPlanID), -- Composite primary key for uniqueness
    FOREIGN KEY (UserID) REFERENCES UserMember(ID),
    FOREIGN KEY (DietPlanID) REFERENCES DietPlan(PlanID)
);

INSERT INTO UserHasDietPlan (UserID, DietPlanID) VALUES
(1, 1),   -- User 1 with Diet Plan 1
(2, 2),   -- User 2 with Diet Plan 2
(3, 3),   -- User 3 with Diet Plan 3
(4, 4),   -- User 4 with Diet Plan 4
(5, 5),   -- User 5 with Diet Plan 5
(6, 6),   -- User 6 with Diet Plan 6
(7, 7),   -- User 7 with Diet Plan 7
(8, 8),   -- User 8 with Diet Plan 8
(9, 9),   -- User 9 with Diet Plan 9
(10, 10), -- User 10 with Diet Plan 10
(11, 1),  -- User 11 with Diet Plan 1
(12, 2),  -- User 12 with Diet Plan 2
(13, 3);  -- User 13 with Diet Plan 3


-----------------
INSERT INTO Login (Username, Password, Type) VALUES
-- User Users
('john_doe@gmail.com', 'hashed_password_123', 'User'),
('jane_smith@gmail.com', 'hashed_password_456', 'User'),
('mark_taylor@gmail.com', 'hashed_password_789', 'User'),
('emily_clark@gmail.com', 'hashed_password_abc', 'User'),
('susan_lee@gmail.com', 'hashed_password_def', 'User'),

-- Trainers
('alice_johnson@gmail.com', 'hashed_password_xyz', 'Trainer'),
('bob_smith@gmail.com', 'hashed_password_mno', 'Trainer'),
('eve_miller@gmail.com', 'hashed_password_pqr', 'Trainer'),
('david_white@gmail.com', 'hashed_password_tuv', 'Trainer'),
('lucas_green@gmail.com', 'hashed_password_lmn', 'Trainer'),

-- More Users
('kevin_brown@gmail.com', 'hashed_password_ghi', 'User'),
('olivia_black@gmail.com', 'hashed_password_jkl', 'User'),
('julia_davis@gmail.com', 'hashed_password_efg', 'User'),
('michael_king@gmail.com', 'hashed_password_ghi', 'User'),
('anna_wilson@gmail.com', 'hashed_password_xyz', 'User'),
('ethan_scott@gmail.com', 'hashed_password_mno', 'User'),
('emma_harris@gmail.com', 'hashed_password_pqr', 'User'),
('chris_martin@gmail.com', 'hashed_password_tuv', 'User');


INSERT INTO UserMember (Name, Height, Weight, FitnessGoal, ProgressID, StatisticID, LoginID)
VALUES
('John Doe', 175.5, 70.0, 'Weight Loss', 101, 201, 1),
('Jane Smith', 162.0, 55.5, 'Muscle Gain', 102, 202, 2),
('Mark Taylor', 180.0, 90.0, 'Endurance Training', 103, 203, 3),
('Emily Clark', 158.0, 48.0, 'Toning', 104, 204, 4),
('Susan Lee', 170.0, 60.0, 'Flexibility', 105, 205, 5),
('Kevin Brown', 165.0, 68.0, 'Strength Building', 106, 206, 11),
('Olivia Black', 160.0, 52.0, 'Weight Loss', 107, 207, 12),
('Julia Davis', 177.0, 66.0, 'Muscle Gain', 108, 208, 13),
('Michael King', 182.0, 78.0, 'Endurance Training', 109, 209, 14),
('Anna Wilson', 163.0, 54.0, 'Toning', 110, 210, 15),
('Ethan Scott', 174.0, 65.0, 'Flexibility', 111, 211, 16),
('Emma Harris', 172.0, 58.0, 'Strength Building', 112, 212, 17),
('Chris Martin', 168.0, 64.0, 'Weight Loss', 113, 213, 18);


INSERT INTO Trainer (Name, Specialization, Experience, Availability, LoginID)
VALUES
('Alice Johnson', 'Yoga', '5 years', 1, 6),
('Bob Smith', 'Strength Training', '10 years', 0, 7),
('Eve Miller', 'Cardio', '7 years', 1, 8),
('David White', 'CrossFit', '8 years', 1, 9),
('Lucas Green', 'Personal Training', '12 years', 0, 10);

INSERT INTO Notification (Message, Date, UserID, Type)
VALUES 
('Welcome to the platform!', '2024-11-01', 1, 'Info'),
('Your session is scheduled for tomorrow.', '2024-11-02', 2, 'Reminder'),
('New program updates available.', '2024-11-03', 3, 'Info'),
('Weekly progress report ready.', '2024-11-04', 4, 'Update'),
('Don’t miss your workout today.', '2024-11-05', 5, 'Reminder'),
('Special offer: 10% off on your next subscription!', '2024-11-06', 1, 'Promotion'),
('Your coach has sent you a message.', '2024-11-07', 2, 'Info'),
('Session canceled: Trainer unavailable.', '2024-11-08', 6, 'Alert'),
('Payment reminder for next month.', '2024-11-09', 7, 'Reminder'),
('Congratulations on completing your first month!', '2024-11-10', 8, 'Achievement'),
('Your next session is scheduled for this weekend.', '2024-11-11', 9, 'Reminder'),
('Keep it up! 5 consecutive days of training!', '2024-11-12', 3, 'Motivation'),
('Refer a friend and earn rewards.', '2024-11-13', 10, 'Promotion'),
('Your subscription will expire soon.', '2024-11-14', 4, 'Reminder'),
('Workout challenge starts next week!', '2024-11-15', 1, 'Event'),
('Trainer feedback is available.', '2024-11-16', 5, 'Info'),
('A new feature has been added to the app.', '2024-11-17', 2, 'Update'),
('Don’t forget to log your meals today.', '2024-11-18', 6, 'Reminder'),
('Personalized tips are available now.', '2024-11-19', 7, 'Info'),
('You’ve unlocked a new achievement!', '2024-11-20', 8, 'Achievement');



INSERT INTO Stats (UserID, ExerciseCount, CaloriesBurned, AvgWorkoutDuration, DietComplianceRate, WeeklyProgress, MonthlyProgress)
VALUES 
(1, 15, 2000, 45.5, 85, 'Improved endurance and strength', 'Lost 2 kg and gained muscle'),
(2, 12, 1800, 40.0, 75, 'Consistent progress in workouts', 'Improved stamina and flexibility'),
(3, 20, 2500, 50.0, 90, 'Achieved new personal bests', 'Gained 1 kg muscle'),
(4, 8, 1500, 35.0, 60, 'Needs more consistency', 'Minor weight loss achieved'),
(5, 18, 2200, 48.0, 88, 'Excellent diet compliance', 'Reduced body fat by 3%'),
(6, 10, 1700, 38.5, 70, 'Moderate improvement in performance', 'Maintained current fitness level'),
(7, 22, 2600, 55.0, 92, 'Exceptional progress this week', 'Improved overall physique'),
(8, 5, 1200, 30.0, 50, 'Inconsistent performance', 'Minimal changes noted'),
(9, 16, 2100, 42.5, 80, 'Good effort but room for improvement', 'Lost 1.5 kg'),
(10, 14, 1900, 41.0, 78, 'Steady progress', 'Increased flexibility and core strength'),
(11, 25, 2700, 60.0, 95, 'Outstanding performance', 'Significant strength gains'),
(12, 7, 1400, 33.0, 65, 'Needs to focus on regularity', 'Minor improvements noted'),
(13, 19, 2300, 47.0, 85, 'Very consistent efforts', 'Lost 1 kg and gained stamina');

INSERT INTO HealthTip (Content, Category, TrainerID)
VALUES 
('Stay hydrated by drinking at least 2 liters of water daily.', 'General', 1),
('Include a 30-minute brisk walk in your daily routine.', 'Exercise', 2),
('Eat more fruits and vegetables to increase your fiber intake.', 'Diet', 3),
('Perform strength training exercises 2-3 times a week.', 'Exercise', 4),
('Avoid processed sugars to maintain stable blood sugar levels.', 'Diet', 5),
('Get at least 7-8 hours of sleep each night for better recovery.', 'General', 1),
('Stretch for 10 minutes before and after workouts to prevent injuries.', 'Exercise', 2),
('Include lean proteins like chicken, fish, or tofu in your meals.', 'Diet', 3),
('Reduce stress by practicing mindfulness or meditation.', 'General', 4),
('Avoid eating heavy meals right before bedtime.', 'Diet', 5),
('Set achievable fitness goals to stay motivated.', 'General', 1),
('Practice yoga to improve flexibility and mental clarity.', 'Exercise', 2),
('Drink green tea for its antioxidant properties.', 'Diet', 3),
('Alternate between high-intensity and low-intensity exercises for better results.', 'Exercise', 4),
('Replace sugary drinks with herbal teas or water.', 'Diet', 5),
('Keep a food journal to track your eating habits.', 'General', 1),
('Use proper posture while lifting weights to prevent injuries.', 'Exercise', 2),
('Include omega-3 fatty acids in your diet for heart health.', 'Diet', 3),
('Take a day off each week to allow your muscles to recover.', 'Exercise', 4),
('Chew your food slowly to aid digestion and prevent overeating.', 'Diet', 5);

INSERT INTO Query (UserID, TrainerID, Question, Response, Date)
VALUES
(1, 1, 'What is the best way to lose belly fat?', 'NONE', '2024-11-20'),
(2, 2, 'How many days a week should I exercise?', 'NONE', '2024-11-21'),
(3, 3, 'Are protein supplements necessary for muscle building?', 'NONE', '2024-11-22'),
(4, 4, 'What is the ideal diet for weight loss?', 'NONE', '2024-11-23'),
(5, 5, 'Can I do cardio every day, or should I take rest days?', 'Daily light cardio is fine; intense cardio may need rest days.', '2024-11-24'),
(6, 1, 'How do I know if I am overtraining?', 'NONE', '2024-11-25'),
(7, 2, 'What are some good post-workout recovery tips?', 'Focus on hydration, stretching, and protein intake.', '2024-11-26'),
(8, 3, 'Should I avoid carbs completely to lose weight?', 'NONE', '2024-11-27'),
(9, 4, 'How can I improve my stamina for running?', 'NONE', '2024-11-28'),
(10, 5, 'What exercises are best for back pain relief?', 'Stretching and strengthening exercises like yoga can help.', '2024-11-29'),
(11, 1, 'Is intermittent fasting effective for fat loss?', 'NONE', '2024-11-30'),
(12, 2, 'How can I build upper body strength quickly?', 'NONE', '2024-12-01'),
(13, 3, 'What is the best way to stay hydrated during workouts?', 'Drink water before, during, and after exercise.', '2024-12-02'),
(1, 4, 'How many calories should I eat daily to lose weight?', 'NONE', '2024-12-03'),
(2, 5, 'What should I eat before and after a workout?', 'Have a mix of carbs and protein before, and protein after.', '2024-12-04'),
(3, 1, 'Can I gain muscle and lose fat at the same time?', 'NONE', '2024-12-05'),
(4, 2, 'What is the difference between aerobic and anaerobic exercise?', 'Aerobic uses oxygen; anaerobic relies on energy stored in muscles.', '2024-12-06'),
(5, 3, 'Are home workouts as effective as gym workouts?', 'NONE', '2024-12-07'),
(6, 4, 'What is the best way to measure fitness progress?', 'Track body measurements, strength levels, and endurance.', '2024-12-08'),
(7, 5, 'How long should I rest between sets during strength training?', 'NONE', '2024-12-09'),
(8, 1, 'What foods should I eat to build lean muscle?', 'Focus on lean proteins, whole grains, and vegetables.', '2024-12-10'),
(9, 2, 'Is it safe to work out if I am sore?', 'NONE', '2024-12-11'),
(10, 3, 'How can I increase my flexibility?', 'Include daily stretching or yoga routines.', '2024-12-12'),
(11, 4, 'What is the best time of day to exercise?', 'NONE', '2024-12-13'),
(12, 5, 'Should I exercise when I am sick?', 'Avoid heavy exercise if you have a fever or are fatigued.', '2024-12-14'),
(13, 1, 'What is the best exercise for weight loss?', 'NONE', '2024-12-15'),
(1, 2, 'How can I improve my mental focus during workouts?', 'Practice mindfulness and eliminate distractions.', '2024-12-16'),
(2, 3, 'How do I avoid injury while working out?', 'Warm-up properly and use correct form during exercises.', '2024-12-17'),
(3, 4, 'What is the importance of rest days in a workout plan?', 'NONE', '2024-12-18'),
(4, 5, 'How do I set realistic fitness goals?', 'NONE', '2024-12-19');

INSERT INTO Lecture (Title, Duration, Date, TrainerID)
VALUES
('10 MIN GLUTE WORKOUT',10, '2024-11-02', 2),
('10 MIN PERFECT ABS WORKOUT', 10, '2024-11-01', 1),
('10 MIN STANDING LIFT + TONED THIGHS', 10, '2024-11-03', 3),
('10 MINUTE FAT BURNING MORNING ROUTINE', 10, '2024-11-04', 4),
('15 MIN BEGINNER BOOTY WORKOUT', 15, '2024-11-05', 5),
('15 MIN FAT BURN', 15, '2024-11-06', 1),
('15 MIN FULL BODY STRETCH', 15, '2024-11-07', 2),
('15 Minute Cardio-HIIT', 15, '2024-11-08', 3),
('20 Min Fat Burning HIIT Workout', 20, '2024-11-09', 4),
('20 Minute Full Body Cardio HIIT Workout', 20, '2024-11-10', 5),
('20 Minute Full Body Strength Workout', 20, '2024-11-11', 1),
('25 MIN FULL BODY HIIT', 25, '2024-11-12', 2),
('28 Minute Full Body Workout To Get In Shape', 28, '2024-11-13', 3),
('30 MIN FULL BODY CARDIO HIIT Workout)', 30, '2024-11-14', 4),
('30 MIN FULL BODY HIIT', 30, '2024-11-15', 5),
('30 MIN WALKING CARDIO WORKOUT', 30, '2024-11-16', 1),
('30 Minute Full Body Strength Workout', 30, '2024-11-17', 2),
('Assisted Stretching', 10, '2024-11-18', 3),
('Chest. Shoulder & ABS Workout', 22, '2024-11-19', 4),
('Lose Weight and Belly', 25, '2024-11-20', 5),
('NO GYM FULL BODY WORKOUT', 5, '2024-11-20', 3),
('PILATES FLAT STOMACH in 14 Days', 5, '2024-11-20', 3);


INSERT INTO ProgressReport (UserID, WorkoutStats, DietStats, ProgressSummary, Date)
VALUES
(1, 'Completed 5 workouts this week.', 'Maintained a calorie deficit.', 'Good progress towards weight loss.', '2024-11-01'),
(2, 'Improved strength by 10%.', 'Protein intake increased by 15%.', 'Steady improvement.', '2024-11-02'),
(3, 'Added 2 hours of cardio.', 'Balanced macro intake.', 'Weight remains stable.', '2024-11-03'),
(4, 'Focused on flexibility workouts.', 'Hydration improved.', 'Flexibility is improving.', '2024-11-04'),
(5, 'Missed 1 workout session.', 'Calorie intake exceeded by 10%.', 'Needs consistency.', '2024-11-05'),
(6, 'Increased weightlifting volume.', 'Protein-rich meals daily.', 'Gains in strength.', '2024-11-06'),
(7, 'Started running 3 times a week.', 'Added more vegetables to diet.', 'Endurance is better.', '2024-11-07'),
(8, 'Completed all planned workouts.', 'Skipped one cheat meal.', 'Progress is excellent.', '2024-11-08'),
(9, 'Tried yoga for relaxation.', 'No diet deviations.', 'Stress levels reduced.', '2024-11-09'),
(10, 'Missed workouts due to travel.', 'No major diet issues.', 'Minimal progress this week.', '2024-11-10'),
(11, 'Added more HIIT sessions.', 'Avoided sugar entirely.', 'Improved stamina.', '2024-11-11'),
(12, 'Consistent gym sessions.', 'Meal prep on schedule.', 'Improved consistency.', '2024-11-12'),
(13, 'Introduced a new workout split.', 'Ate out twice.', 'Slight progress in weight loss.', '2024-11-13'),
(1, 'Improved pushup count.', 'Hydration increased by 20%.', 'Upper body strength improving.', '2024-11-14'),
(2, 'Tried swimming for cardio.', 'Calorie intake stable.', 'Learning new skills.', '2024-11-15'),
(3, 'Improved workout intensity.', 'Protein shake daily.', 'Visible strength gains.', '2024-11-16'),
(4, 'Focused on core workouts.', 'Ate 5 meals a day.', 'Core stability better.', '2024-11-17'),
(5, 'Tried outdoor running.', 'Balanced calorie intake.', 'Good endurance development.', '2024-11-18'),
(6, 'Missed no workouts.', 'Low sugar intake.', 'Perfect week.', '2024-11-19'),
(7, 'Added stretching routines.', 'Cut back on junk food.', 'Flexibility improved.', '2024-11-20');

INSERT INTO Progress (UserID, WorkoutStats, DietStats, WeightChanges, GoalsMet, Date)
VALUES
(1, 'Completed 4 strength sessions.', 'High protein, low carbs.', -2, 3, '2024-11-01'),
(2, 'Improved bench press by 5kg.', 'Maintained daily calorie target.', 0, 2, '2024-11-02'),
(3, 'Cardio increased by 30 minutes.', 'Increased water intake.', -1, 1, '2024-11-03'),
(4, 'Focused on yoga and stretching.', 'Ate a balanced diet.', 0, 2, '2024-11-04'),
(5, 'Missed leg day workout.', 'Ate out twice.', 1, 0, '2024-11-05'),
(6, 'Added 10kg to deadlift.', 'Maintained protein intake.', -3, 4, '2024-11-06'),
(7, 'Started running daily.', 'Avoided junk food.', -1, 2, '2024-11-07'),
(8, 'No missed workouts.', 'Meal prepped entire week.', 0, 3, '2024-11-08'),
(9, 'Tried Zumba for cardio.', 'Slightly over calorie limit.', 1, 1, '2024-11-09'),
(10, 'Focused on upper body.', 'Reduced sugar consumption.', 0, 2, '2024-11-10'),
(11, 'Added HIIT sessions.', 'Balanced macros perfectly.', -2, 4, '2024-11-11'),
(12, 'Introduced functional training.', 'Ate healthy snacks.', -1, 3, '2024-11-12'),
(13, 'Improved pushup endurance.', 'Increased protein shakes.', 0, 2, '2024-11-13'),
(1, 'Focused on back workouts.', 'Hydrated sufficiently.', -1, 2, '2024-11-14'),
(2, 'Swam twice a week.', 'No cheat meals.', 0, 3, '2024-11-15'),
(3, 'Consistent gym attendance.', 'Reduced calorie surplus.', -2, 4, '2024-11-16'),
(4, 'Added pilates.', 'Balanced diet improved.', -1, 2, '2024-11-17'),
(5, 'Tried a new workout split.', 'Calorie surplus maintained.', 1, 1, '2024-11-18'),
(6, 'Improved core stability.', 'Hydration was sufficient.', 0, 2, '2024-11-19'),
(7, 'Introduced evening walks.', 'Avoided processed foods.', -2, 3, '2024-11-20');


-- Feedback Table
INSERT INTO Feedback (UserID, Rating, Comments, Date)
VALUES
(1, 5, 'Amazing app! Helps me stay consistent.', '2024-11-01'),
(2, 4, 'Great features, but UI can improve.', '2024-11-02'),
(3, 3, 'Average experience so far.', '2024-11-03'),
(4, 5, 'Really enjoying the diet suggestions.', '2024-11-04'),
(5, 2, 'Not very helpful for advanced users.', '2024-11-05'),
(6, 4, 'Motivating, but progress tracking can be better.', '2024-11-06'),
(7, 5, 'Best fitness app I have used.', '2024-11-07'),
(8, 3, 'Good, but needs more customization options.', '2024-11-08'),
(9, 4, 'The workout plans are spot on.', '2024-11-09'),
(10, 5, 'Excellent for beginners and pros alike.', '2024-11-10');

-- Plan Table
INSERT INTO Plans (UserID)
VALUES
(1), (2), (3), (4), (5), (6), (7), (8), (9), (10);

-- DietPlan Table
INSERT INTO DietPlan (PlanID, Meals, Nutrition)
VALUES
(1, '1,2,3', 85), (2, '4,5,6', 90), (3, '7,8', 80), (4, '9,10', 75), 
(5, '11,12', 88), (6, '13,14', 82), (7, '15,16,17', 92), 
(8, '18,19', 78), (9, '20,21,22', 85), (10, '23,24', 87);

-- WorkoutPlan Table
INSERT INTO WorkoutPlan (PlanID, Exercises, Duration, Frequency, DifficultyLevel)
VALUES
(1, '1,2,3', 60, 5, 7), (2, '4,5,6', 45, 3, 5), (3, '7,8', 30, 4, 6), 
(4, '9,10', 50, 6, 8), (5, '11,12', 40, 4, 5), (6, '13,14', 70, 5, 9), 
(7, '15,16,17', 55, 6, 8), (8, '18,19', 35, 3, 4), (9, '20,21,22', 80, 5, 10), 
(10, '23,24', 65, 4, 7);

-- Meal Table
INSERT INTO Meal (MealName, Fats, Fibre, Proteins, Carbs, Calories)
VALUES
('Chicken Salad', 10, 5, 30, 10, 250), ('Grilled Salmon', 15, 2, 40, 5, 300),
('Avocado Toast', 20, 6, 10, 30, 350), ('Protein Shake', 5, 1, 25, 20, 200),
('Steak with Veggies', 25, 8, 50, 15, 450), ('Quinoa Bowl', 5, 10, 15, 40, 300),
('Egg Omelette', 10, 2, 20, 5, 200), ('Peanut Butter Sandwich', 15, 3, 10, 35, 300);

-- Exercise Table
INSERT INTO Exercise (ExerciseName, Sets, Reps)
VALUES
('Push-Ups', 4, 15), ('Squats', 3, 12), ('Bench Press', 4, 10), 
('Deadlift', 5, 8), ('Bicep Curls', 3, 12), ('Lunges', 3, 10), 
('Plank', 3, 60), ('Pull-Ups', 4, 8), ('Leg Press', 4, 12), 
('Shoulder Press', 3, 10);

INSERT INTO UserHasWorkoutPlan (UserID, WorkoutPlanID) VALUES
(1, 1),   -- User 1 with Workout Plan 1
(2, 2),   -- User 2 with Workout Plan 2
(3, 3),   -- User 3 with Workout Plan 3
(4, 4),   -- User 4 with Workout Plan 4
(5, 5),   -- User 5 with Workout Plan 5
(6, 6),   -- User 6 with Workout Plan 6
(7, 7),   -- User 7 with Workout Plan 7
(8, 8),   -- User 8 with Workout Plan 8
(9, 9),   -- User 9 with Workout Plan 9
(10, 10), -- User 10 with Workout Plan 10
(11, 1),  -- User 11 with Workout Plan 1
(12, 2),  -- User 12 with Workout Plan 2
(13, 3);  -- User 13 with Workout Plan 3

----------------------------------------------------------------------------------------------
