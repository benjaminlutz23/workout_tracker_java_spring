CREATE TABLE IF NOT EXISTS Users (
    user_id INT auto_increment,
    username varchar(100) NOT NULL,
    PRIMARY KEY (user_id)
);

CREATE TABLE IF NOT EXISTS Split (
	split_id INT auto_increment,
    split_name ENUM('PUSH', 'PULL', 'LEGS'),
    PRIMARY KEY (split_id)
);

CREATE TABLE IF NOT EXISTS Exercise (
	exercise_id INT auto_increment,
    exercise_name VARCHAR(100),
    PRIMARY KEY (exercise_id)
);

CREATE TABLE IF NOT EXISTS Workout (
	workout_id INT auto_increment,
    user_id INT,
    split_id INT,
    week_start DATE,
    day_name ENUM('SUNDAY', 'MONDAY', 'TUESDAY', 'WEDNESDAY', 'THURSDAY', 'FRIDAY', 'SATURDAY'),
    PRIMARY KEY (workout_id),
    FOREIGN KEY (user_id) REFERENCES Users(user_id),
    FOREIGN KEY (split_id) REFERENCES Split(split_id)
);

CREATE TABLE IF NOT EXISTS ExerciseDetails (
	exercise_detail_id INT auto_increment,
    workout_id INT,
    exercise_id INT,
    sets INT,
    reps INT,
    weight INT,
    PRIMARY KEY (exercise_detail_id),
    FOREIGN KEY (workout_id) REFERENCES Workout(workout_id),
    FOREIGN KEY (exercise_id) REFERENCES Exercise(exercise_id)
);