package com.lutz.workout.log.model;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.core.simple.JdbcClient.StatementSpec;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

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
}
