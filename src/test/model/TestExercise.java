package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import model.Set.Unit;

public class TestExercise {
    Exercise exercise;
    Set set;

    @BeforeEach
    void setup() {
        this.exercise = new Exercise("Bench Press");
        this.set = new Set(100.0, 10, Unit.POUNDS);
    }

    @Test
    void testConstructor() {
        assertEquals("Bench Press", exercise.getName());
        assertEquals(0, exercise.getSetCount());
    }

    @Test
    void testAddOneSet()  {
        exercise.addSet(set);
        assertEquals(1, exercise.getSetCount());
    }

    @Test
    void testAddTwoSet()  {
        exercise.addSet(set);
        assertEquals(1, exercise.getSetCount());
        Set setTwo = new Set(500.0, 5, Unit.POUNDS);
        exercise.addSet(setTwo);
        assertEquals(2, exercise.getSetCount());
        ArrayList<Set> sets = exercise.getSets();
        assertEquals(set, sets.get(0));
        assertEquals(setTwo, sets.get(1));
    }

    @Test 
    void testGetVolumeNoSets() {
        assertEquals(0.0, exercise.getVolume(Unit.KILOGRAMS));
        assertEquals(0.0, exercise.getVolume(Unit.POUNDS));
    }

    @Test
    void testGetVolumeOneSet() {
        exercise.addSet(set);
        assertEquals(1000.0, exercise.getVolume(Unit.POUNDS));
    }

    @Test
    void testGetVolumeTwoSets()  {
        exercise.addSet(set); 
        assertEquals(1000.0, exercise.getVolume(Unit.POUNDS));
        Set setTwo = new Set(500.0, 5, Unit.POUNDS);
        exercise.addSet(setTwo);
        assertEquals(3500.0, exercise.getVolume(Unit.POUNDS));
    }

    @Test
    void testSetName() {
        assertEquals("Bench Press", exercise.getName());
        exercise.setName("Tricep Pushdown");
        assertEquals("Tricep Pushdown", exercise.getName());
    }

    @Test
    void testEqualsSameObject() {
        Exercise exercise1 = new Exercise("Bench Press");
        assertTrue(exercise1.equals(exercise1));
    }

    @Test
    void testEqualsNull() {
        Exercise exercise1 = new Exercise("Bench Press");
        assertFalse(exercise1.equals(null));
    }

    @Test
    void testEqualsDifferentClass() {
        Exercise exercise1 = new Exercise("Bench Press");
        String notAnExercise = "Not an Exercise";
        assertFalse(exercise1.equals(notAnExercise));
    }

    @Test
    void testEqualsEqualExercisesWithSameNameAndNoSets() {
        Exercise exercise1 = new Exercise("Bench Press");
        Exercise exercise2 = new Exercise("Bench Press");
        assertTrue(exercise1.equals(exercise2));
    }

    @Test
    void testEqualsDifferentName() {
        Exercise exercise1 = new Exercise("Bench Press");
        Exercise exercise2 = new Exercise("Squat");
        assertFalse(exercise1.equals(exercise2));
    }

    @Test
    void testEqualsBothNullNames() {
        Exercise exercise1 = new Exercise(null);
        Exercise exercise2 = new Exercise(null);
        assertTrue(exercise1.equals(exercise2));
    }

    @Test
    void testEqualsWithSameSets() {
        Exercise exercise1 = new Exercise("Bench Press");
        Exercise exercise2 = new Exercise("Bench Press");
        
        Set set1 = new Set(50.0, 10, Set.Unit.KILOGRAMS);
        Set set2 = new Set(50.0, 10, Set.Unit.KILOGRAMS);
        
        exercise1.addSet(set1);
        exercise2.addSet(set2);
        
        assertTrue(exercise1.equals(exercise2));
    }

    @Test
    void testEqualsWithDifferentSets() {
        Exercise exercise1 = new Exercise("Bench Press");
        Exercise exercise2 = new Exercise("Bench Press");
        
        Set set1 = new Set(50.0, 10, Set.Unit.KILOGRAMS);
        Set set2 = new Set(60.0, 10, Set.Unit.KILOGRAMS);
        
        exercise1.addSet(set1);
        exercise2.addSet(set2);
        
        assertFalse(exercise1.equals(exercise2));
    }

    @Test
    void testEqualsWithDifferentSetCount() {
        Exercise exercise1 = new Exercise("Bench Press");
        Exercise exercise2 = new Exercise("Bench Press");
        
        Set set1 = new Set(50.0, 10, Set.Unit.KILOGRAMS);
        
        exercise1.addSet(set1);
        exercise2.addSet(new Set(60.0, 10, Set.Unit.KILOGRAMS));
        exercise2.addSet(new Set(70.0, 10, Set.Unit.KILOGRAMS));

        assertFalse(exercise1.equals(exercise2));
    }

    @Test
    void testEqualsWithBothSetsNull() {
        Exercise exercise1 = new Exercise("Bench Press");
        Exercise exercise2 = new Exercise("Bench Press");
        
        exercise1.addSet(null);
        exercise2.addSet(null);
        
        assertTrue(exercise1.equals(exercise2));
    }

    @Test
    void testHashCodeEqualExercises() {
        Exercise exercise1 = new Exercise("Bench Press");
        Exercise exercise2 = new Exercise("Bench Press");
        
        assertEquals(exercise1.hashCode(), exercise2.hashCode());
    }

    @Test
    void testHashCodeDifferentExercises() {
        Exercise exercise1 = new Exercise("Bench Press");
        Exercise exercise2 = new Exercise("Squat");
        
        assertNotEquals(exercise1.hashCode(), exercise2.hashCode());
    }

    @Test
    void testHashCodeWithNullName() {
        Exercise exercise1 = new Exercise(null);
        Exercise exercise2 = new Exercise(null);
        
        assertEquals(exercise1.hashCode(), exercise2.hashCode());
    }

    @Test
    void testHashCodeWithSets() {
        Exercise exercise1 = new Exercise("Bench Press");
        Exercise exercise2 = new Exercise("Bench Press");
        
        Set set1 = new Set(50.0, 10, Set.Unit.KILOGRAMS);
        Set set2 = new Set(50.0, 10, Set.Unit.KILOGRAMS);
        
        exercise1.addSet(set1);
        exercise2.addSet(set2);
        
        assertEquals(exercise1.hashCode(), exercise2.hashCode());
    }

    @Test
    void testHashCodeWithDifferentSets() {
        Exercise exercise1 = new Exercise("Bench Press");
        Exercise exercise2 = new Exercise("Bench Press");
        
        Set set1 = new Set(50.0, 10, Set.Unit.KILOGRAMS);
        Set set2 = new Set(60.0, 10, Set.Unit.KILOGRAMS);
        
        exercise1.addSet(set1);
        exercise2.addSet(set2);
        
        assertNotEquals(exercise1.hashCode(), exercise2.hashCode());
    }

    @Test
    void testHashCodeWithDifferentSetCount() {
        Exercise exercise1 = new Exercise("Bench Press");
        Exercise exercise2 = new Exercise("Bench Press");
        
        Set set1 = new Set(50.0, 10, Set.Unit.KILOGRAMS);
        
        exercise1.addSet(set1);
        exercise2.addSet(new Set(60.0, 10, Set.Unit.KILOGRAMS)); 
        exercise2.addSet(new Set(70.0, 10, Set.Unit.KILOGRAMS));

        assertNotEquals(exercise1.hashCode(), exercise2.hashCode());
    }

    @Test 
    void testThisNameNullOtherNotNull() {
        Exercise exercise1 = new Exercise(null);
        Exercise exercise2 = new Exercise("Bench Press");
        assertFalse(exercise1.equals(exercise2));
    }

    @Test 
    void testThisSetsNullOtherNotNull() {
        Exercise exercise1 = new Exercise(null);
        Exercise exercise2 = new Exercise("Bench Press");
        assertFalse(exercise1.equals(exercise2));
    }
}