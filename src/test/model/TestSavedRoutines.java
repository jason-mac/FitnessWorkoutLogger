package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class TestSavedRoutines {
    private SavedRoutines routines;
    private WeeklyRoutine routine;

    @BeforeEach
    void setup() {
        this.routines = new SavedRoutines();
        this.routine = new WeeklyRoutine("Upper");
    }

    @Test
    void testConstructor() {
        assertEquals(0, routines.getNumRoutinesStored());
        assertTrue(this.routines.isEmpty());
    }

    @Test 
    void testAddOneRoutine() {
        this.routines.addRoutine(routine);
        assertEquals(1, this.routines.getNumRoutinesStored());
        assertFalse(this.routines.isEmpty());
        assertEquals(routine, this.routines.getRoutineByName(routine.getName()));
        ArrayList<WeeklyRoutine> getRoutines = this.routines.getRoutines();
        assertEquals(routine, getRoutines.get(0));
    }

    @Test 
    void testAddTwoRoutines() {
        WeeklyRoutine routineTwo = new WeeklyRoutine("Hello");
        this.routines.addRoutine(routine);
        this.routines.addRoutine(routineTwo);
        assertEquals(2, this.routines.getNumRoutinesStored());

        ArrayList<WeeklyRoutine> getRoutines = this.routines.getRoutines();
        assertEquals(routine, getRoutines.get(0));
        assertEquals(routineTwo, getRoutines.get(1));
    }

    @Test
    void testRemoveOneRoutine() {
        this.routines.addRoutine(routine);
        this.routines.removeRoutine(routine);
        assertEquals(0, this.routines.getNumRoutinesStored());
    }

    @Test
    void testRemoveTwoRoutines() {
        WeeklyRoutine routineTwo = new WeeklyRoutine("Hello");
        this.routines.addRoutine(routine);
        this.routines.addRoutine(routineTwo);

        this.routines.removeRoutine(routine);
        this.routines.removeRoutine(routineTwo);

        assertEquals(0, this.routines.getNumRoutinesStored());
    }

    @Test
    void testClearRoutines() {
        WeeklyRoutine routineTwo = new WeeklyRoutine("Hello");
        this.routines.addRoutine(routine);
        this.routines.addRoutine(routineTwo);
        assertEquals(2, routines.getNumRoutinesStored());

        this.routines.clearSavedRoutines();
        assertTrue(this.routines.isEmpty());
        assertEquals(0, routines.getNumRoutinesStored());

    }

    @Test void getWeeklyRoutineByNameNoMatch() {
        routines.addRoutine(routine);
        assertEquals(null, routines.getRoutineByName("John"));
    }

    @Test void getWeeklyRoutineByNameMatch() {
        WeeklyRoutine routineTwo = new WeeklyRoutine("Hello");
        routines.addRoutine(routine);
        routines.addRoutine(routineTwo);
        assertEquals(routine, routines.getRoutineByName("Upper"));
        assertEquals(routineTwo, routines.getRoutineByName(routineTwo.getName()));
    }
}
