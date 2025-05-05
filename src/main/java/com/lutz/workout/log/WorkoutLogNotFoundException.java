package com.lutz.workout.log;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class WorkoutLogNotFoundException extends RuntimeException {
    public WorkoutLogNotFoundException() {
        super("Workout log not found: ");
    }
}
