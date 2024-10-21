package persistence;

import model.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

/*
 * This code is based on code provided by JsonSerializationDemo 
 * as part of the course CPSC 210 at University of British Coumbia
 * Source: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 */

public class TestJson {
    protected void checkWorkout(Workout other, Workout workout) {
        assertTrue(other.equals(workout));
    }

    protected void checkWeeklyRoutine(WeeklyRoutine other, WeeklyRoutine weeklyRoutine) {
        assertTrue(other.equals(weeklyRoutine));
    }
}

