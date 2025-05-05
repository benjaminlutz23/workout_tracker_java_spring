package com.lutz.workout.log.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lutz.workout.WorkoutApplication;
import com.lutz.workout.log.model.WorkoutLog;
import com.lutz.workout.log.model.WorkoutLogRepository;
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
    private final WorkoutLogRepository workoutLogRepository;

    LoadWorkoutsFromJson(WorkoutLogRepository workoutLogRepository, ObjectMapper objectMapper) {
        this.workoutLogRepository = workoutLogRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws Exception {

        if (workoutLogRepository.countWorkoutLogs() == 0) {
            try (InputStream inputStream = WorkoutApplication.class.getResourceAsStream("/data/workouts.json")) {

                if (inputStream == null) {
                    throw new RuntimeException("Failed to load workoutLogs.json file");
                }
                WorkoutLogs allWorkouts = objectMapper.readValue(inputStream, WorkoutLogs.class);
                logger.info("Loading workoutLogs from json file: ", allWorkouts.workoutLogs().size());

                for (WorkoutLog workoutLog : allWorkouts.workoutLogs()) {
                    workoutLogRepository.addWorkoutLog(workoutLog);
                }
            }
            catch (Exception e) {
                throw new RuntimeException("Failed to read JSON data", e);
            }
        }
    }

}
