package com.lutz.workout.log.model;

import com.lutz.workout.log.services.LoadWorkoutsFromJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class WorkoutLogService {
    private static final Logger logger = LoggerFactory.getLogger(LoadWorkoutsFromJson.class);

    private final WorkoutLogRepository workoutLogRepository;

    public WorkoutLogService(WorkoutLogRepository workoutLogRepository) {
        this.workoutLogRepository = workoutLogRepository;
    }

    public Integer countWorkoutLogs() {
        return workoutLogRepository.countWorkoutLogs();
    }

    public void createWorkoutLog(WorkoutLog workoutLog) {
        Integer userId = workoutLogRepository.insertUserIntoUserTable(workoutLog.username());
        Integer exerciseId = workoutLogRepository.insertExerciseIntoExerciseTable(workoutLog.exercise_name());
        logger.info("User ID: {}, Exercise ID: {}", userId, exerciseId);
    }

}
