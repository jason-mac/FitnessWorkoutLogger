package model;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Set.Unit;

public class TestWorkout {
    Workout workout;     
    Exercise exercise;
    Set set;

    @BeforeEach 
    void setup() {    
        workout = new Workout("Upper");
        exercise = new Exercise("Bench Press");
        set = new Set(10.0, 5, Unit.KILOGRAMS);
        exercise.addSet(set);
    }

    @Test
    void testConstructor() {
        assertEquals(0, workout.getNumberOfExercises());
        assertEquals("Upper", workout.getName());
    }

    @Test
    void testAddOneExercise() {
        workout.addExercise(exercise);
        Exercise getExercise = workout.getExerciseByIndex(0);
        assertEquals(1, workout.getNumberOfExercises());
        assertEquals("Bench Press", getExercise.getName());
    }

    @Test
    void testAddTwoExercises() {
        Exercise exercise2 = makeExercise("Lat Pull Down"); 
        workout.addExercise(exercise);
        assertEquals(1, workout.getNumberOfExercises());
        workout.addExercise(exercise2);
        assertEquals(2, workout.getNumberOfExercises());
        Exercise getExercise1 = workout.getExerciseByIndex(0);
        Exercise getExercise2 = workout.getExerciseByIndex(1);
        assertEquals("Bench Press", getExercise1.getName());
        assertEquals("Lat Pull Down", getExercise2.getName());
    }

    @Test
    void testClearExercisesNoExercises() {
        workout.clearExercises();
        ArrayList<Exercise> exercises = workout.getExercises();
        assertEquals(exercises.size(), 0);
        assertTrue(workout.isEmpty());
    }

    @Test
    void testClearExercisesOneExercises() {
        workout.addExercise(exercise);
        ArrayList<Exercise> exercises = workout.getExercises();
        assertEquals(exercise ,exercises.get(0));
        assertEquals(1, workout.getNumberOfExercises());    
        workout.clearExercises();
        assertTrue(workout.isEmpty());
    }

    @Test
    void testClearExercisesTwoExercises() {
        Exercise exercise2 = makeExercise("Lat Pull Down"); 
        workout.addExercise(exercise);
        workout.addExercise(exercise2);
        ArrayList<Exercise> exercises = workout.getExercises();
        assertEquals(exercise ,exercises.get(0));
        assertEquals(exercise2 ,exercises.get(1));
        assertEquals(2, workout.getNumberOfExercises());    
        workout.clearExercises();
        assertTrue(workout.isEmpty());
    }

    @Test
    void testReplaceExerciseEmptyListAndOutOfBounds() {
        assertFalse(workout.replaceExercise(0, exercise));
        assertEquals(0, workout.getNumberOfExercises());
        assertEquals(null, workout.getExerciseByIndex(0));
        assertEquals(null, workout.getExerciseByName(exercise.getName()));
    }

    @Test 
    void testGetExerciseByIndexNonEmptyOutOfBounds() {
        workout.addExercise(exercise);
        Exercise getWorkout = workout.getExerciseByIndex(2);
        assertEquals(null, getWorkout);
    }

    @Test
    void testReplaceExerciseOutOfBounds() {
        workout.replaceExercise(4, exercise);
        assertEquals(0, workout.getNumberOfExercises());
        assertTrue(workout.isEmpty());
    }

    @Test
    void testReplaceExerciseNonEmptyListInBounds() {
        Exercise exercise2 = makeExercise("Lat Pull Down");
        workout.addExercise(exercise);
        workout.replaceExercise(0, exercise2);
        assertEquals(1, workout.getNumberOfExercises());
        assertEquals("Lat Pull Down", workout.getExerciseByIndex(0).getName());
        assertEquals(null, workout.getExerciseByName(exercise.getName()));
    }

    @Test
    void testReplaceExerciseNonEmptyListOutOfBounds() {
        Exercise exercise2 = makeExercise("Lat Pull Down");
        workout.addExercise(exercise);
        assertFalse(workout.replaceExercise(-1, exercise2));
        assertTrue(workout.replaceExercise(0, exercise2));
        assertEquals(1, workout.getNumberOfExercises());
        assertEquals("Lat Pull Down", workout.getExerciseByIndex(0).getName());
        assertEquals(null, workout.getExerciseByName(exercise.getName()));
    }

    @Test
    void testGetExerciseByNameExerciseExists() {
        workout.addExercise(exercise);
        Exercise getExercise = workout.getExerciseByName("Bench Press");
        assertEquals(getExercise.getName(), workout.getExerciseByIndex(0).getName());
    }

    @Test
    void testGetExerciseByNameExerciseNonExistent() {
        Exercise getExercise = workout.getExerciseByName("Bench Press");
        assertEquals(null, getExercise);
    }

    @Test
    void testGetVolumeEmpty() {
        assertEquals(0.0, workout.getVolume(Unit.KILOGRAMS));
        assertEquals(0.0, workout.getVolume(Unit.POUNDS));
    }

    @Test
    void testGetVolumeNonEmpty() {
        Set set2 = new Set(15.0, 5, Unit.KILOGRAMS);
        Set set3 = new Set(20.0, 4, Unit.POUNDS);
        Exercise exercise2 = makeExercise("Lat Pull Down");

        exercise.addSet(set);
        exercise.addSet(set2);
        exercise.addSet(set);
        exercise2.addSet(set3);

        double exercise1volumeKG = exercise.getVolume(Unit.KILOGRAMS);
        double exercise2volumeKG = exercise2.getVolume(Unit.KILOGRAMS);

        double exercise1volumeLBS = exercise.getVolume(Unit.POUNDS);
        double exercise2volumeLBS = exercise2.getVolume(Unit.POUNDS);

        double volumeInKilograms = exercise1volumeKG + exercise2volumeKG;
        double volumeInPounds = exercise1volumeLBS + exercise2volumeLBS;

        workout.addExercise(exercise);
        workout.addExercise(exercise2);

        assertEquals(volumeInKilograms, workout.getVolume(Unit.KILOGRAMS));
        assertEquals(volumeInPounds, workout.getVolume(Unit.POUNDS));
    }

    @Test
    void removeExerciseEmptyList() {
        assertFalse(workout.removeExercise(0));
    }

    @Test
    void removeExerciseEmptyListOutOfBOundsNonEmpty() {
        workout.addExercise(exercise);
        workout.removeExercise(-1);
        assertEquals(1, workout.getNumberOfExercises());
        workout.removeExercise(1);
        assertEquals(1, workout.getNumberOfExercises());
    }

    @Test
    void removeExerciseNonEmptyFail() {
        workout.addExercise(exercise);
        assertFalse(workout.removeExercise(1));
        Exercise getExercise = workout.getExerciseByIndex(0);
        assertEquals(getExercise.getName(), workout.getExerciseByIndex(0).getName());
    }

    @Test
    void removeExerciseNonEmptySuccess() {
        workout.addExercise(exercise);
        assertTrue(workout.removeExercise(0));
        assertTrue(workout.isEmpty());
    }

    @Test 
    void testGetExerciseByNameEmpty() {
        workout.addExercise(exercise);
        Exercise getExercise = workout.getExerciseByName(exercise.getName());
        assertEquals(getExercise.getName(), workout.getExerciseByIndex(0).getName());
    }

    @Test 
    void testGetExerciseByNameNonEmtpy() {
        Exercise getExercise = workout.getExerciseByName("John Cena");
        assertEquals(null, getExercise);
    }

    @Test
    void testIsEmptyActuallyEmpty() {
        assertTrue(workout.isEmpty());
    }

    @Test
    void testIsEmptyNotEmpty() {
        workout.addExercise(exercise);
        assertFalse(workout.isEmpty());
    }

    @Test
    void testSetName() {
        workout.setName("John Cena");
        assertEquals("John Cena", workout.getName());
    }

    Exercise makeExercise(String name) {
        return new Exercise(name);
    }
}
