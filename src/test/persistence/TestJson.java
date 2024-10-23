package persistence;

import model.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

/*
 * This code is based on code provided by JsonSerializationDemo 
 * as part of the course CPSC 210 at University of British Coumbia
 * Source: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 */

public class TestJson {
    protected void checkWorkout(Workout workout, Workout other) {
        assertTrue(workout.equals(other));
    }

    protected void checkWeeklyRoutine(WeeklyRoutine weeklyRoutine, WeeklyRoutine other) {
        assertTrue(weeklyRoutine.equals(other));
    }
}

