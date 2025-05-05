package com.lutz.workout.log;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

import java.time.DayOfWeek;
import java.time.LocalDate;

public record WorkoutLog(
        Integer id,
        @NotEmpty
        String username,
        LocalDate week_start,
        DayOfWeek day_of_week,
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
