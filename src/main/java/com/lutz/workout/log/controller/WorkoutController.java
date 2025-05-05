package com.lutz.workout.log.controller;

import com.lutz.workout.log.model.WorkoutLog;
import com.lutz.workout.log.exception.WorkoutLogNotFoundException;
import com.lutz.workout.log.model.WorkoutLogRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workout-logs")
public class WorkoutController {
    private final WorkoutLogRepository workoutLogRepository;

    public WorkoutController(WorkoutLogRepository workoutLogRepository) {
        this.workoutLogRepository = workoutLogRepository;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    public List<WorkoutLog> getWorkoutLogs() {
        return workoutLogRepository.getWorkoutLogs();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public WorkoutLog getWorkoutLogById(@PathVariable Integer id) {
        return workoutLogRepository.getWorkoutLogs().stream()
                .filter(workoutLog -> workoutLog.id().equals(id))
                .findFirst()
                .orElseThrow(() -> new WorkoutLogNotFoundException());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void addWorkoutLog(@Valid @RequestBody WorkoutLog workoutLog) {
        workoutLogRepository.addWorkoutLog(workoutLog);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{workoutLogId}")
    public void updateWorkoutLog(@Valid @RequestBody WorkoutLog workoutLog, @PathVariable Integer workoutLogId) {
        workoutLogRepository.updateWorkoutLog(workoutLog, workoutLogId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{workoutLogId}")
    public void deleteWorkoutLog(@PathVariable Integer workoutLogId) {
        workoutLogRepository.deleteWorkoutLog(workoutLogId);
    }
}
