package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestWeeklyRoutine {
    static final Day monday = Day.MONDAY;
    WeeklyRoutine routine;
    Workout workout;

    @BeforeEach
    void setup() {
        routine = new WeeklyRoutine("Upper Lower");
        workout = createWorkout();
    }

    @Test
    void testConstructor() {
        assertEquals("Upper Lower", routine.getName());
        assertEquals(0, routine.getNumWorkouts());
        assertEquals(7, routine.getNumRestDays());
    }

    @Test
    void testAddOneWorkout() {
        routine.addWorkout(workout, monday);
        assertEquals(1, routine.getNumWorkouts());
        assertEquals(6, routine.getNumRestDays());
    }

    @Test
    void testAddTwoWorkoutsDiffDays() {
        Workout two = createWorkout();
        routine.addWorkout(workout, monday);
        routine.addWorkout(two, Day.TUESDAY);
        assertEquals(2, routine.getNumWorkouts());
        assertEquals(5, routine.getNumRestDays());
    }

    @Test
    void testAddTwoWorkoutsSameDays() {
        Workout one = createWorkout("Upper");
        Workout two = createWorkout("Lower");
        routine.addWorkout(one, monday);
        routine.addWorkout(two, monday);
        Workout getWorkout = routine.getWorkout(monday);
        assertEquals(1, routine.getNumWorkouts());
        assertEquals(6, routine.getNumRestDays());
        assertEquals(two.getName(), getWorkout.getName());
    }

    @Test
    public void removeWorkoutDoesNotExist() {
        routine.removeWorkout(monday);
        assertEquals(0, routine.getNumWorkouts());
        assertEquals(7, routine.getNumRestDays());
    }
    
    @Test
    public void removeWorkoutExist() {
        routine.addWorkout(workout, monday);
        routine.removeWorkout(monday);
        assertEquals(0, routine.getNumWorkouts());
        assertEquals(7, routine.getNumRestDays());
        Workout getWorkout = routine.getWorkout(monday);
        assertEquals(null, getWorkout);
    }

    @Test 
    public void swapWorkoutsBothWorkouts() {
        Workout workout2 = createWorkout("Chest");
        routine.addWorkout(workout, monday);
        routine.addWorkout(workout2, Day.TUESDAY);

        Workout getOne = routine.getWorkout(monday);
        Workout getTwo = routine.getWorkout(Day.TUESDAY);

        assertEquals(workout.getName(), getOne.getName());
        assertEquals(workout2.getName(), getTwo.getName());

        routine.swapWorkouts(monday, Day.TUESDAY);

        getOne = routine.getWorkout(monday);
        getTwo = routine.getWorkout(Day.TUESDAY);

        assertEquals(workout2.getName(), getOne.getName());
        assertEquals(workout.getName(), getTwo.getName());
    }

    @Test 
    public void swapWorkoutsOneRestDay() {
        routine.addWorkout(workout, monday);
        routine.swapWorkouts(monday, Day.TUESDAY);
 
        Workout getOne = routine.getWorkout(monday);
        Workout getTwo = routine.getWorkout(Day.TUESDAY);

        assertEquals(null, getOne);
        assertEquals(getTwo.getName(), workout.getName());
    }

    private Workout createWorkout() {
        return new Workout();
    }

    private Workout createWorkout(String name) {
        return new Workout();
    }
}