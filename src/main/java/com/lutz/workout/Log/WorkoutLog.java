package com.lutz.workout.Log;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;

public record WorkoutLog(
        @NotEmpty
        String username,
        @NotEmpty
        LocalDate week_start,
        DayOfWeek day_of_week,
        @NotEmpty
        Split split_name,
        @NotEmpty
        String exercise_name,
        @Positive
        Integer sets,
        @Positive
        Integer reps,
        @Positive
        Integer weight
) {
    public WorkoutLog {
        if (day_of_week == null) {
            throw new IllegalArgumentException("Must provide a day of the week");
        }
    }
}
