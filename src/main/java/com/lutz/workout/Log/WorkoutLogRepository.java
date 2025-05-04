package com.lutz.workout.Log;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class WorkoutLogRepository {

    public List<WorkoutLog> workoutLogs = new ArrayList<>();

    public List<WorkoutLog> getWorkoutLogs() {
        return workoutLogs;
    }

    public void addWorkoutLog(WorkoutLog workoutLog) {
        this.workoutLogs.add(workoutLog);
    }
}
