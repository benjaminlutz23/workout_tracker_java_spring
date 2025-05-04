package com.lutz.workout.Log;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class WorkoutLogRepository {

    public List<WorkoutLog> workoutLogs;

    public WorkoutLogRepository() {
       workoutLogs = new ArrayList<>();
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
