package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    }

    @Test 
    void testGetVolumeNoSets() {
        assertEquals(0.0, exercise.getVolume(null));
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
        assertEquals(12500.0, exercise.getVolume(Unit.POUNDS));
    }


    void createSetsAndAddToExercise() {
        Set set1 = new Set(50, 12, Unit.POUNDS);
        Set set2 = new Set(60, 10, Unit.POUNDS);
        Set set3 = new Set(70, 8,  Unit.POUNDS);
        exercise.addSet(set1);
        exercise.addSet(set2);
        exercise.addSet(set3);
    }
}