package com.lutz.workout;

import com.lutz.workout.Log.Split;
import com.lutz.workout.Log.WorkoutLog;
import com.lutz.workout.Log.WorkoutLogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.DayOfWeek;
import java.time.LocalDate;

@SpringBootApplication
public class WorkoutApplication {
    private static final Logger logger = LoggerFactory.getLogger(WorkoutApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(WorkoutApplication.class, args);
    }

//    @Bean
//    CommandLineRunner runner(WorkoutLogRepository workoutLogRepository) {
//        return args -> {
//            WorkoutLog workoutLog1 = new WorkoutLog(1, "JohnDoe", LocalDate.now(), DayOfWeek.FRIDAY, Split.PUSH, "Bench Press", 3, 10, 80);
//            workoutLogRepository.addWorkoutLog(workoutLog1);
//            logger.info("Workout log added: {}", workoutLogRepository.getWorkoutLogs());
//        };
//    }

}
