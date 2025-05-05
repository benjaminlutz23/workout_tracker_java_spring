package com.lutz.workout.log.controller;

import com.lutz.workout.log.domain.WorkoutLog;
import com.lutz.workout.log.services.WorkoutLogService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
//
//    @ResponseStatus(HttpStatus.OK)
//    @GetMapping("/{id}")
//    public WorkoutLog getWorkoutLogById(@PathVariable Integer id) {
//        return workoutLogService.getWorkoutLogs().stream()
//                .filter(workoutLog -> workoutLog.id().equals(id))
//                .findFirst()
//                .orElseThrow(() -> new WorkoutLogNotFoundException());
//    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void addWorkoutLog(@Valid @RequestBody WorkoutLog workoutLog) {
        workoutLogService.createWorkoutLog(workoutLog);
    }

//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    @PutMapping("/{workoutLogId}")
//    public void updateWorkoutLog(@Valid @RequestBody WorkoutLog workoutLog, @PathVariable Integer workoutLogId) {
//        workoutLogService.updateWorkoutLog(workoutLog, workoutLogId);
//    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{username}")
    public void deleteWorkoutLog(@PathVariable String username) {
        workoutLogService.deleteUser(username);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{date}/{dayOfWeek}")
    public void delateWorkoutLogByDate(@PathVariable String date, @PathVariable String dayOfWeek) {
        workoutLogService.deleteWorkoutLogByDate(date, dayOfWeek);
    }
}
