package persistence;

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
    void testWriterEmptyWorkroom() {
        try {
            SavedRoutines sr = new SavedRoutines();
            WorkoutLogger wl = new WorkoutLogger();
            JsonWriter writerSR = new JsonWriter("./data/testWriterEmptySavedRoutines.json");
            JsonWriter writerWL = new JsonWriter("./data/testWriterEmptyWorkoutLogger.json");
            writerSR.open();
            writerWL.open();
            writerSR.write(sr);
            writerWL.write(wl);
            writerSR.close();
            writerWL.close();

            JsonReader readerSR = new JsonReader("./data/testWriterEmptySavedRoutines.json");
            JsonReader readerWL = new JsonReader("./data/testWriterEmptyWorkoutLogger.json");
            sr = readerSR.readSavedRoutines();
            wl = readerWL.readWorkoutLogs();
            assertEquals(0, sr.getNumRoutinesStored());
            assertEquals(0, wl.getNumWorkoutsLogged());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
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

            SavedRoutines sr = new SavedRoutines();
            WorkoutLogger wl = new WorkoutLogger();

            sr.addRoutine(weeklyRoutine);
            sr.addRoutine(weeklyRoutine2);
            wl.addWorkout("01/01/2001", workoutOne);
            wl.addWorkout("02/01/2001", workoutTwo);

            JsonWriter writerSR = new JsonWriter("./data/testWriterGeneralSavedRoutines.json");
            JsonWriter writerWL = new JsonWriter("./data/testWriterGeneralWorkoutLogger.json");
            writerSR.open();
            writerWL.open();
            writerSR.write(sr);
            writerWL.write(wl);
            writerSR.close();
            writerWL.close();

            JsonReader readerSR = new JsonReader("./data/testWriterGeneralSavedRoutines.json");
            JsonReader readerWL = new JsonReader("./data/testWriterGeneralWorkoutLogger.json");
            sr = readerSR.readSavedRoutines();
            wl = readerWL.readWorkoutLogs();

            HashMap<String, Workout> workoutLogs = wl.getWorkoutLogs();
            ArrayList<WeeklyRoutine> routines = sr.getRoutines();

            assertEquals(2, workoutLogs.size());
            assertEquals(2, routines.size());

            checkWorkout(workoutOne, workoutLogs.get("01/01/2001"));
            checkWorkout(workoutTwo, workoutLogs.get("02/01/2001"));
            checkWeeklyRoutine(weeklyRoutine, routines.get(0));
            checkWeeklyRoutine(weeklyRoutine2, routines.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}
