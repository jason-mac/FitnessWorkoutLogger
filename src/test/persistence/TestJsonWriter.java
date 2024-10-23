package persistence;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.*;
import model.Set.Unit;
import java.util.ArrayList;
import java.util.HashMap;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
/*
 * This code is based on code provided by JsonSerializationDemo 
 * as part of the course CPSC 210 at University of British Coumbia
 * Source: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 */

public class TestJsonWriter extends TestJson {
    private SavedRoutines sr;
    private WorkoutLogger wl;

    @BeforeEach
    void setup() {
        sr = new SavedRoutines();
        wl = new WorkoutLogger();
    }

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // expected
        }
    }

    @Test
    void testWriterEmptyWorkoutLogger() {
        try {
            JsonWriter writerWL = new JsonWriter("./data/testWriterEmptyWorkoutLogger.json");
            writerWL.open();
            writerWL.write(wl);
            writerWL.close();

            JsonReader readerWL = new JsonReader("./data/testWriterEmptyWorkoutLogger.json");
            wl = readerWL.readWorkoutLogs();
            assertEquals(0, wl.getNumWorkoutsLogged());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterEmptySavedRoutines() {
        try {
            JsonWriter writerSR = new JsonWriter("./data/testWriterEmptySavedRoutines.json");
            writerSR.open();
            writerSR.write(sr);
            writerSR.close();

            JsonReader readerSR = new JsonReader("./data/testWriterEmptySavedRoutines.json");
            sr = readerSR.readSavedRoutines();
            assertEquals(0, sr.getNumRoutinesStored());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralSavedRoutines() {
        try {
            WeeklyRoutine weeklyRoutine = new WeeklyRoutine("Upper");
            WeeklyRoutine weeklyRoutine2 = new WeeklyRoutine("Lower");
            Workout workoutOne = new Workout("Strong");
            Workout workoutTwo = new Workout("Flexibility");
            Exercise exerciseOne = new Exercise("Bench Press");
            Exercise exerciseTwo = new Exercise("Leg Raises");
            Set set = new Set(12.3, 5, Unit.KILOGRAMS);
            Set setTwo = new Set(32.4, 5, Unit.POUNDS);
            exerciseOne.addSet(set);
            exerciseTwo.addSet(setTwo);
            workoutOne.addExercise(exerciseOne);
            workoutTwo.addExercise(exerciseTwo);
            weeklyRoutine.addWorkout(workoutOne, Days.MONDAY);
            weeklyRoutine2.addWorkout(workoutTwo, Days.TUESDAY);

            sr.addRoutine(weeklyRoutine);
            sr.addRoutine(weeklyRoutine2);

            JsonWriter writerSR = new JsonWriter("./data/testWriterGeneralSavedRoutines.json");
            writerSR.open();
            writerSR.write(sr);
            writerSR.close();

            JsonReader readerSR = new JsonReader("./data/testWriterGeneralSavedRoutines.json");
            sr = readerSR.readSavedRoutines();

            ArrayList<WeeklyRoutine> routinesFromFile = sr.getRoutines();

            assertEquals(2, routinesFromFile.size());

            checkWeeklyRoutine(weeklyRoutine, routinesFromFile.get(0));
            checkWeeklyRoutine(weeklyRoutine2, routinesFromFile.get(1));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }

    }

    @Test
    void testWriterGeneralWorkoutLogger() {
        try {
            Workout workoutOne = new Workout("Strong");
            Workout workoutTwo = new Workout("Flexibility");
            Exercise exerciseOne = new Exercise("Bench Press");
            Exercise exerciseTwo = new Exercise("Leg Raises");
            Set set = new Set(12.3, 5, Unit.KILOGRAMS);
            Set setTwo = new Set(32.4, 5, Unit.POUNDS);
            exerciseOne.addSet(set);
            exerciseTwo.addSet(setTwo);
            workoutOne.addExercise(exerciseOne);
            workoutTwo.addExercise(exerciseTwo);

            wl.addWorkout("01/01/2001", workoutOne);
            wl.addWorkout("02/01/2001", workoutTwo);

            JsonWriter writerWL = new JsonWriter("./data/testWriterGeneralWorkoutLogger.json");
            writerWL.open();
            writerWL.write(wl);
            writerWL.close();

            JsonReader readerWL = new JsonReader("./data/testWriterGeneralWorkoutLogger.json");
            wl = readerWL.readWorkoutLogs();

            HashMap<String, Workout> workoutLogsFromFile = wl.getWorkoutLogs();

            assertEquals(2, workoutLogsFromFile.size());

            checkWorkout(workoutOne, workoutLogsFromFile.get("01/01/2001"));
            checkWorkout(workoutTwo, workoutLogsFromFile.get("02/01/2001"));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
