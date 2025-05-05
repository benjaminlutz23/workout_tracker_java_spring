package com.lutz.workout.log.model;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class WorkoutLogRepository {

    public List<WorkoutLog> workoutLogs;
    public final JdbcClient jdbcClient;

    public WorkoutLogRepository(JdbcClient jdbcClient) {
       workoutLogs = new ArrayList<>();
       this.jdbcClient = jdbcClient;
    }

    public List<WorkoutLog> getWorkoutLogs() {
        return workoutLogs;
    }

    public void addWorkoutLog(WorkoutLog workoutLog) {
        this.workoutLogs.add(workoutLog);
    }

    public void updateWorkoutLog(WorkoutLog workoutLog, Integer workoutLogId) {
        for (int i = 0; i < workoutLogs.size(); i++) {
            if (workoutLogs.get(i).id().equals(workoutLogId)) {
                workoutLogs.set(i, workoutLog);
            }
        }
    }

    public void deleteWorkoutLog(Integer workoutLogId) {
        workoutLogs.removeIf(workoutLog -> workoutLog.id().equals(workoutLogId));
    }

    public int countWorkoutLogs() {
        if (workoutLogs.isEmpty()) {
            return 0;
        }
        return workoutLogs.size();
    }
}
