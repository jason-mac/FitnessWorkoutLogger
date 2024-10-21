package persistence;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

import static org.junit.jupiter.api.Assertions.*;

/*
 * This code is based on code provided by JsonSerializationDemo 
 * as part of the course CPSC 210 at University of British Coumbia
 * Source: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 */


public class TestJsonReader extends TestJson {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            WorkoutLogger workoutLogger = reader.readWorkoutLogs();
            SavedRoutines savedRoutines = reader.readSavedRoutines();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkoutLoggerSavedRoutines() {
        JsonReader readerWL = new JsonReader("./data/testReaderEmptyWorkRoom.json");
        JsonReader readerSR = new JsonReader("./data/testReaderEmptySavedRoutines.json");
        try {
            WorkoutLogger workoutLogger = readerWL.readWorkoutLogs();
            SavedRoutines savedRoutines = readerSR.readSavedRoutines();
            assertEquals(0, workoutLogger.getNumWorkoutsLogged());
            assertEquals(0, savedRoutines.getNumRoutinesStored());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkoutLoggerSavedRoutines() {
        JsonReader readerWL = new JsonReader("./data/testReaderGeneralWorkoutLogger.json");
        JsonReader readerSR = new JsonReader("./data/testReaderGeneralSavedRoutines.json");
        try {
            Workout savedWorkoutOne = new Workout("Name");
            Workout savedWorkoutTwo = new Workout("Name2");
            WeeklyRoutine savedWeeklyRoutineOne = new WeeklyRoutine("Upper Lower");
            WeeklyRoutine savedWeeklyRoutineTwo = new WeeklyRoutine("Push Pull Legs");

            savedWeeklyRoutineOne.addWorkout(savedWorkoutOne, Days.MONDAY);
            savedWeeklyRoutineTwo.addWorkout(savedWorkoutTwo, Days.TUESDAY);

            WorkoutLogger workoutLogger = readerWL.readWorkoutLogs();
            SavedRoutines savedRoutines = readerSR.readSavedRoutines();
            HashMap<String, Workout> workoutLogs = workoutLogger.getWorkoutLogs();
            ArrayList<WeeklyRoutine> weeklyRoutines = savedRoutines.getRoutines();

            checkWorkout(savedWorkoutOne, workoutLogs.get("01/01/2001"));
            checkWeeklyRoutine(savedWeeklyRoutineOne, weeklyRoutines.get(0));
            checkWorkout(savedWorkoutTwo, workoutLogs.get("02/01/2001"));
            checkWeeklyRoutine(savedWeeklyRoutineTwo, weeklyRoutines.get(1));

        } catch (IOException e) {
            fail("Couldn't read from file");
        }


    }

}
