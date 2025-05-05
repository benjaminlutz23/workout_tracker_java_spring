package com.lutz.workout.log.model;

import com.lutz.workout.log.domain.WorkoutLog;
import org.springframework.data.relational.core.sql.In;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class WorkoutLogRepository {

    public final JdbcClient jdbcClient;

    WorkoutLogRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public Integer insertUserIntoUserTable(String username) {
        Optional<Integer> existingUserId = jdbcClient.sql("SELECT user_id FROM Users WHERE username = :username")
                .param("username", username)
                .query(Integer.class)
                .optional();

        if (existingUserId.isPresent()) {
            return existingUserId.get();
        }

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcClient.sql("INSERT INTO Users (username) VALUES (:username)")
                .param("username", username)
                .update(keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    public Integer count() {
        return jdbcClient.sql("SELECT COUNT(*) FROM ExerciseDetails")
                .query()
                .listOfRows()
                .size();
    }

    public Integer insertExerciseIntoExerciseTable(String exerciseName) {
        Optional<Integer> existingExerciseId = jdbcClient.sql("SELECT exercise_id FROM Exercise WHERE exercise_name = :exerciseName")
                .param("exerciseName", exerciseName)
                .query(Integer.class)
                .optional();

        if (existingExerciseId.isPresent()) {
            return existingExerciseId.get();
        }

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcClient.sql("INSERT INTO Exercise (exercise_name) VALUES (:exerciseName)")
                .param("exerciseName", exerciseName)
                .update(keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    public Integer insertSplitIntoSplitTable(String split_name) {
        Optional<Integer> existingSplitId = jdbcClient.sql("SELECT split_id FROM Split WHERE split_name = :split_name")
                .param("split_name", split_name)
                .query(Integer.class)
                .optional();

        if (existingSplitId.isPresent()) {
            return existingSplitId.get();
        }

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcClient.sql("INSERT INTO Split (split_name) VALUES (:split_name)")
                .param("split_name", split_name)
                .update(keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    public Integer insertWorkoutIntoWorkoutTable(Integer user_id, Integer split_id, LocalDate week_start, String day_name) {
        Optional<Integer> existingWorkoutLogId = jdbcClient.sql("SELECT workout_id FROM Workout WHERE user_id = :user_id AND split_id = :split_id AND week_start = :week_start AND day_name = :day_name")
                .param("user_id", user_id)
                .param("split_id", split_id)
                .param("week_start", week_start)
                .param("day_name", day_name)
                .query(Integer.class)
                .optional();

        if (existingWorkoutLogId.isPresent()) {
            return existingWorkoutLogId.get();
        }

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcClient.sql("INSERT INTO Workout (user_id, split_id, week_start, day_name) VALUES (:user_id, :split_id, :week_start, :day_name)")
                .param("user_id", user_id)
                .param("split_id", split_id)
                .param("week_start", week_start)
                .param("day_name", day_name)
                .update(keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    public Integer insertExerciseDetailsIntoExerciseDetailsTable(Integer workout_id, Integer exercise_id, Integer sets, Integer reps, Integer weight) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        Optional<Integer> existingExerciseDetailsId = jdbcClient.sql("SELECT exercise_detail_id FROM ExerciseDetails WHERE workout_id = :workout_id AND exercise_id = :exercise_id AND sets = :sets AND reps = :reps AND weight = :weight")
                .param("workout_id", workout_id)
                .param("exercise_id", exercise_id)
                .param("sets", sets)
                .param("reps", reps)
                .param("weight", weight)
                .query(Integer.class)
                .optional();

        if (existingExerciseDetailsId.isPresent()) {
            return existingExerciseDetailsId.get();
        }

        jdbcClient.sql("INSERT INTO ExerciseDetails (workout_id, exercise_id, sets, reps, weight) VALUES (:workout_id, :exercise_id, :sets, :reps, :weight)")
                .param("workout_id", workout_id)
                .param("exercise_id", exercise_id)
                .param("sets", sets)
                .param("reps", reps)
                .param("weight", weight)
                .update(keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    public List<WorkoutLog> getAllWorkoutLogs() {
        return jdbcClient.sql("SELECT " +
                        "    u.username, " +
                        "    w.week_start, " +
                        "    w.day_name as day_of_week, " +
                        "    s.split_name, " +
                        "    e.exercise_name, " +
                        "    ed.sets, " +
                        "    ed.reps, " +
                        "    ed.weight " +
                        "FROM Users u " +
                        "JOIN Workout w ON u.user_id = w.user_id " +
                        "JOIN `Split` s ON s.split_id = w.split_id " +
                        "JOIN ExerciseDetails ed ON ed.workout_id = w.workout_id " +
                        "JOIN Exercise e ON e.exercise_id = ed.exercise_id")
                .query(WorkoutLog.class)
                .list();
    }

    public void deleteUser(String username) {
        jdbcClient.sql("DELETE FROM Users WHERE username = :username")
                .param("username", username)
                .update();
    }


    public void deleteWorkougLogsByDate(String date, String day_of_week) {
        jdbcClient.sql("DELETE FROM Workout WHERE week_start = :date AND day_name = :day_of_week")
                .param("date", date)
                .param("day_of_week", day_of_week)
                .update();
    }
}
