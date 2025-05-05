package com.lutz.workout.log.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lutz.workout.WorkoutApplication;
import com.lutz.workout.log.model.WorkoutLog;
import com.lutz.workout.log.model.WorkoutLogService;
import com.lutz.workout.log.model.WorkoutLogs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
public class LoadWorkoutsFromJson implements CommandLineRunner {
    public static final Logger logger = LoggerFactory.getLogger(LoadWorkoutsFromJson.class);

    private final ObjectMapper objectMapper;
    private final WorkoutLogService workoutLogService;

    LoadWorkoutsFromJson(WorkoutLogService workoutLogService, ObjectMapper objectMapper) {
        this.workoutLogService = workoutLogService;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("Number of workout logs in the database: {}", workoutLogService.countWorkoutLogs());

        try (InputStream inputStream = WorkoutApplication.class.getResourceAsStream("/data/workouts.json")) {

            if (inputStream == null) {
                throw new RuntimeException("Failed to load workoutLogs.json file");
            }
            WorkoutLogs allWorkouts = objectMapper.readValue(inputStream, WorkoutLogs.class);
            logger.info("Loading workoutLogs from json file: ", allWorkouts.workoutLogs().size());

            for (WorkoutLog workoutLog : allWorkouts.workoutLogs()) {
                workoutLogService.createWorkoutLog(workoutLog);
            }
        }
        catch (Exception e) {
            throw new RuntimeException("Failed to read JSON data", e);
        }
    }

}
