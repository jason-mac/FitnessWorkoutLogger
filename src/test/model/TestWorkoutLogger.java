package model;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

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

        Set<String> dates = this.logger.getDates();
        assertTrue(dates.contains(date));
        assertTrue(dates.contains(dateTwo));

    }
}
