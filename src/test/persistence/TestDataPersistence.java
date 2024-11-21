package persistence;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

import java.io.FileNotFoundException;
import java.io.IOException;

import model.*;


public class TestDataPersistence extends TestJson {
    private DataPersistence test;
    private WorkoutLogger workoutLogs;
    private JsonReader reader;
    private JsonWriter writer;
    
    @BeforeEach
    void setup() {
        this.workoutLogs = new WorkoutLogger();
    }

    @Test
    void testSaveLoad() {
        String filePath = "./data/testSaveLoadDataPersistence.json";
        test = new DataPersistence(filePath);
        Workout workout = new Workout("2001");
        workout.addExercise(new Exercise("Wassup"));
        workoutLogs.addWorkout("Date", workout);
        reader = new JsonReader(filePath);
        try {
            test.save(workoutLogs);
        } catch (FileNotFoundException e) {
            fail("Exception not expected");
        }
        try {
            workoutLogs = test.load();
        } catch (IOException e) {
            fail("Exception not expected");
        }
        checkWorkout(workout, workoutLogs.getWorkout("Date"));
    }
}
