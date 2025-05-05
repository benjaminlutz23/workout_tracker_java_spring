package com.lutz.workout.log.services;

import com.lutz.workout.log.domain.WorkoutLog;
import com.lutz.workout.log.model.WorkoutLogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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

    @Transactional
    public void createWorkoutLog(WorkoutLog workoutLog) {
        Integer userId = workoutLogRepository.insertUserIntoUserTable(workoutLog.username());
        Integer exerciseId = workoutLogRepository.insertExerciseIntoExerciseTable(workoutLog.exercise_name());
        Integer splitId = workoutLogRepository.insertSplitIntoSplitTable(userId, workoutLog.week_start(), workoutLog.day_of_week().toString(), workoutLog.split_name().toString());
        Integer workoutLogId = workoutLogRepository.insertWorkoutLogIntoWorkoutLogTable(userId, workoutLog.week_start(), workoutLog.day_of_week().toString(), exerciseId, workoutLog.sets(), workoutLog.reps(), workoutLog.weight());
        logger.info("User ID: {}, Exercise ID: {}", userId, exerciseId);
    }

}
