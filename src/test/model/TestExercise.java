package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}