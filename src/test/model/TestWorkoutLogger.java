package model;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Set.Unit;
import model.Set;


public class TestWorkoutLogger {
    private WorkoutLogger logger;
    private Workout work;

    @BeforeEach
    void setup() {
        this.work = new Workout("Name");
        this.logger = new WorkoutLogger();
    }

    @Test
    void testConstructor() {
        assertEquals(0, logger.getWorkoutLogs().size());
        assertEquals(0, logger.getNumWorkoutsLogged());
    }

    @Test 
    void testAddOneWorkout() {
        String date = "12/12/2012";
        this.logger.addWorkout(date, work);
        assertEquals(1, logger.getNumWorkoutsLogged());
        assertEquals(work, logger.getWorkout(date));
    }

    @Test 
    void testAddTwoWorkout() {
        String date = "12/12/2012";
        String dateTwo = "01/01/2001";
        Workout workTwo = new Workout("random");
        this.logger.addWorkout(date, work);
        this.logger.addWorkout(dateTwo, workTwo);
        assertEquals(2, logger.getNumWorkoutsLogged());
        assertEquals(work, logger.getWorkout(date));
        assertEquals(workTwo, logger.getWorkout(dateTwo));
    }

    @Test
    void testRemoveWorkout() {
        String date = "12/12/2012";
        this.logger.addWorkout(date, work);
        assertEquals(1, this.logger.getNumWorkoutsLogged());
        this.logger.removeWorkout(date);
        assertEquals(0, this.logger.getNumWorkoutsLogged());
    }

    @Test
    void testRemoveTwoWorkouts() {
        String date = "12/12/2012";
        String dateTwo = "01/01/2001";
        Workout workTwo = new Workout("random");

        this.logger.addWorkout(date, work);
        this.logger.addWorkout(dateTwo, workTwo);

        this.logger.removeWorkout(date);
        assertEquals(1, this.logger.getNumWorkoutsLogged());

        this.logger.removeWorkout(dateTwo);
        assertEquals(0, this.logger.getNumWorkoutsLogged());
    }

    @Test
    void testClearLogs() {
        String date = "12/12/2012";
        String dateTwo = "01/01/2001";
        Workout workTwo = new Workout("random");

        this.logger.addWorkout(date, work);
        this.logger.addWorkout(dateTwo, workTwo);

        this.logger.clearLogs();
        assertEquals(0, logger.getNumWorkoutsLogged());
    }

    @Test 
    void testGetDates() {

        String date = "12/12/2012";
        String dateTwo = "01/01/2001";
        Workout workTwo = new Workout("random");

        this.logger.addWorkout(date, work);
        this.logger.addWorkout(dateTwo, workTwo);

        java.util.Set<String> dates = this.logger.getDates();
        assertTrue(dates.contains(date));
        assertTrue(dates.contains(dateTwo));

    }

    @Test
    void testFilterDatesBefore() {
        String compare = "2005/01/01";
        Workout w2 = new Workout("");
        Workout w3 = new Workout("");
        Workout w4 = new Workout("");
        Workout w5 = new Workout("");
        logger.addWorkout("2002/12/12", work);
        logger.addWorkout("2001/01/01", w2);
        logger.addWorkout("2006/02/04", w3);
        logger.addWorkout("2009/04/05", w4);
        logger.addWorkout(compare, w5);
        java.util.Set<String> dates = logger.getDates(); 
        java.util.Set<String> datesFiltered = logger.filterDatesBeforeDate(dates, compare);

        assertTrue(datesFiltered.contains("2002/12/12"));
        assertTrue(datesFiltered.contains("2001/01/01"));
        assertFalse(datesFiltered.contains("2006/02/04"));
        assertFalse(datesFiltered.contains("2009/04/05"));
        assertTrue(datesFiltered.contains(compare));
    }

    
    @Test
    void testFilterDatesAfter() {
        String compare = "2005/01/01";
        Workout w2 = new Workout("");
        Workout w3 = new Workout("");
        Workout w4 = new Workout("");
        Workout w5 = new Workout("");
        logger.addWorkout("2002/12/12", work);
        logger.addWorkout("2001/01/01", w2);
        logger.addWorkout("2006/02/04", w3);
        logger.addWorkout("2009/04/05", w4);
        logger.addWorkout(compare, w5);
        java.util.Set<String> dates = logger.getDates(); 
        java.util.Set<String> datesFiltered = logger.filterDatesAfterDate(dates, compare);
        assertFalse(datesFiltered.contains("2002/12/12"));
        assertFalse(datesFiltered.contains("2001/01/01"));
        assertTrue(datesFiltered.contains("2006/02/04"));
        assertTrue(datesFiltered.contains("2009/04/05"));
        assertTrue(datesFiltered.contains(compare));
    }

    @Test
    void testFilterDatesLower() {
        double compare = 1000.0;
        initLoggerForFilterTestLowerUpper();
        java.util.Set<String> dates = logger.getDates();
        java.util.Set<String> datesFilteredKilograms = logger.filterDatesLowerVolume(dates, compare, Unit.KILOGRAMS); 
        java.util.Set<String> datesFilteredPounds = logger.filterDatesLowerVolume(dates, compare, Unit.POUNDS); 
        assertTrue(datesFilteredPounds.contains("2001/01/01"));
        assertFalse(datesFilteredPounds.contains("2012/01/01"));
        assertTrue(datesFilteredPounds.contains("2021/02/01"));
        assertTrue(datesFilteredPounds.contains("2012/03/05"));
        assertTrue(datesFilteredKilograms.contains("2001/01/01"));
        assertFalse(datesFilteredKilograms.contains("2012/01/01"));
        assertTrue(datesFilteredKilograms.contains("2021/02/01"));
        assertTrue(datesFilteredKilograms.contains("2012/03/05"));
    }

    @Test
    void testFilterDatesUpper() {
        double compare = 1000.0;
        initLoggerForFilterTestLowerUpper();
        java.util.Set<String> dates = logger.getDates();
        java.util.Set<String> datesFilteredKilograms = logger.filterDatesHigherVolume(dates, compare, Unit.KILOGRAMS); 
        java.util.Set<String> datesFilteredPounds = logger.filterDatesHigherVolume(dates, compare, Unit.POUNDS); 
        assertFalse(datesFilteredPounds.contains("2001/01/01"));
        assertTrue(datesFilteredPounds.contains("2012/01/01"));
        assertFalse(datesFilteredPounds.contains("2021/02/01"));
        assertFalse(datesFilteredPounds.contains("2012/03/05"));
        assertFalse(datesFilteredKilograms.contains("2001/01/01"));
        assertTrue(datesFilteredKilograms.contains("2012/01/01"));
        assertFalse(datesFilteredKilograms.contains("2021/02/01"));
        assertFalse(datesFilteredKilograms.contains("2012/03/05"));
    }

    private void initLoggerForFilterTestLowerUpper() {
        Workout w2 = new Workout("");
        Workout w3 = new Workout("");
        Workout w4 = new Workout("");
        Exercise e1 = new Exercise("name");
        Exercise e2 = new Exercise("name");
        Exercise e3 = new Exercise("name");
        Exercise e4 = new Exercise("name");
        Set s1 = new Set(10.0, 25, Unit.POUNDS);
        Set s2 = new Set(10.0, 25, Unit.KILOGRAMS);
        e1.addSet(s1);
        e2.addSet(s2);
        e3.addSet(s1);
        e3.addSet(s2);
        e4.addSet(s1);
        e4.addSet(s1);
        e4.addSet(s2);
        e4.addSet(s2);

        work.addExercise(e1);
        work.addExercise(e2);
        w2.addExercise(e3);
        w2.addExercise(e4);
        w3.addExercise(e1);
        w4.addExercise(e3);

        logger.addWorkout("2001/01/01", work);
        logger.addWorkout("2012/01/01", w2);
        logger.addWorkout("2021/02/01", w3);
        logger.addWorkout("2012/03/05", w4);
    }
}
