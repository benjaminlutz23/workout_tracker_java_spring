package com.lutz.workout.log.model;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.core.simple.JdbcClient.StatementSpec;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
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

    public Integer countWorkoutLogs() {
        return jdbcClient.sql("SELECT COUNT(*) FROM Users")
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

    public Integer insertSplitIntoSplitTable(Integer user_id, LocalDate week_start, String day_of_week, String split_name) {
        Optional<Integer> existingSplitId = jdbcClient.sql("SELECT split_id FROM WeeklySplit WHERE user_id = :user_id AND week_start = :week_start AND day_name = :day_of_week AND split_name = :split_name")
                .param("user_id", user_id)
                .param("week_start", week_start)
                .param("day_of_week", day_of_week)
                .param("split_name", split_name)
                .query(Integer.class)
                .optional();

        if (existingSplitId.isPresent()) {
            return existingSplitId.get();
        }

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcClient.sql("INSERT INTO WeeklySplit (user_id, week_start, day_name, split_name) VALUES (:user_id, :week_start, :day_of_week, :split_name)")
                .param("user_id", user_id)
                .param("week_start", week_start)
                .param("day_of_week", day_of_week)
                .param("split_name", split_name)
                .update(keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }
}
