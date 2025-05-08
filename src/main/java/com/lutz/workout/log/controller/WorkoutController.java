package com.lutz.workout.log.controller;

import com.lutz.workout.log.domain.WorkoutLog;
import com.lutz.workout.log.services.WorkoutLogService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/workout-logs")
public class WorkoutController {
    private final WorkoutLogService workoutLogService;

    public WorkoutController(WorkoutLogService workoutLogService) {
        this.workoutLogService = workoutLogService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    public List<WorkoutLog> getWorkoutLogs() {
        return workoutLogService.getall();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void addWorkoutLog(@Valid @RequestBody WorkoutLog workoutLog) {
        workoutLogService.createWorkoutLog(workoutLog);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("")
    public void updateExericseDetails(@Valid @RequestBody WorkoutLog workoutLog, @RequestParam String username, @RequestParam LocalDate weekStart, @RequestParam DayOfWeek dayOfWeek, @RequestParam String splitName, @RequestParam String exerciseName) {
        workoutLogService.updateExerciseDetails(workoutLog, username, weekStart, dayOfWeek, splitName, exerciseName);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{username}")
    public void deleteWorkoutLog(@PathVariable String username) {
        workoutLogService.deleteUser(username);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("")
    public void delateWorkoutLogByDate(@RequestParam String weekStart, @RequestParam String dayOfWeek) {
        workoutLogService.deleteWorkoutLogByDate(weekStart, dayOfWeek);
    }
}
