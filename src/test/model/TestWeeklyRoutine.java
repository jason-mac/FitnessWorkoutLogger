package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Set.Unit;

public class TestWeeklyRoutine {
    static final Days monday = Days.MONDAY;
    WeeklyRoutine routine;
    Workout workout;

    @BeforeEach
    void setup() {
        routine = new WeeklyRoutine("Upper Lower");
        workout = createWorkout("");
    }

    @Test
    void testConstructor() {
        assertEquals("Upper Lower", routine.getName());
        assertEquals(0, routine.getNumWorkouts());
    }

    @Test
    void testAddOneWorkout() {
        routine.addWorkout(workout, monday);
        assertEquals(1, routine.getNumWorkouts());
    }

    @Test
    void testAddTwoWorkoutsDiffDays() {
        Workout two = createWorkout("");
        routine.addWorkout(workout, monday);
        routine.addWorkout(two, Days.TUESDAY);
        assertEquals(2, routine.getNumWorkouts());
    }

    @Test
    void testAddTwoWorkoutsSameDays() {
        Workout one = createWorkout("Upper");
        Workout two = createWorkout("Lower");
        routine.addWorkout(one, monday);
        routine.addWorkout(two, monday);
        Workout getWorkout = routine.getWorkout(monday);
        assertEquals(1, routine.getNumWorkouts());
        assertEquals(two.getName(), getWorkout.getName());
    }

    @Test
    public void removeWorkoutDoesNotExist() {
        routine.removeWorkout(monday);
        assertEquals(0, routine.getNumWorkouts());
    }
    
    @Test
    public void removeWorkoutExist() {
        routine.addWorkout(workout, monday);
        routine.removeWorkout(monday);
        assertEquals(0, routine.getNumWorkouts());
        Workout getWorkout = routine.getWorkout(monday);
        assertEquals(null, getWorkout);
    }

    @Test 
    public void swapWorkoutsBothWorkouts() {
        Workout workout2 = createWorkout("Chest");
        routine.addWorkout(workout, monday);
        routine.addWorkout(workout2, Days.TUESDAY);

        Workout getOne = routine.getWorkout(monday);
        Workout getTwo = routine.getWorkout(Days.TUESDAY);

        assertEquals(workout.getName(), getOne.getName());
        assertEquals(workout2.getName(), getTwo.getName());

        routine.swapWorkouts(monday, Days.TUESDAY);

        getOne = routine.getWorkout(monday);
        getTwo = routine.getWorkout(Days.TUESDAY);

        assertEquals(workout2.getName(), getOne.getName());
        assertEquals(workout.getName(), getTwo.getName());
    }

    @Test 
    public void swapWorkoutsOneRestDay() {
        routine.addWorkout(workout, monday);
        routine.swapWorkouts(monday, Days.TUESDAY);
 
        Workout getOne = routine.getWorkout(monday);
        Workout getTwo = routine.getWorkout(Days.TUESDAY);

        assertEquals(null, getOne);
        assertEquals(getTwo.getName(), workout.getName());
    }

    @Test 
    void testGetVolumeEmpty() {
        assertEquals(0.0, routine.getVolume(Unit.KILOGRAMS));
    }

    @Test 
    void testGetVolumeNonEmptyMult() {
        Set one = new Set(10.0, 4, Unit.KILOGRAMS);
        Set two = new Set(15.5, 5, Unit.KILOGRAMS);
        Set three = new Set(5.2, 9, Unit.POUNDS);
        Set four = new Set(20.0, 3, Unit.POUNDS);

        Exercise exOne = new Exercise("");
        Exercise exTwo = new Exercise("");

        exOne.addSet(one);
        exOne.addSet(two);
        exTwo.addSet(three);
        exTwo.addSet(four);

        Workout workoutTwo = createWorkout("");

        workout.addExercise(exOne);
        workoutTwo.addExercise(exTwo);

        routine.addWorkout(workout, monday);
        routine.addWorkout(workoutTwo, Days.TUESDAY);

        double exOneVolumeKG = one.getSetVolume(Unit.KILOGRAMS) + two.getSetVolume(Unit.KILOGRAMS);
        double exTwoVolumeKG = three.getSetVolume(Unit.KILOGRAMS) + four.getSetVolume(Unit.KILOGRAMS);

        double exOneVolumeLBS = one.getSetVolume(Unit.POUNDS) + two.getSetVolume(Unit.POUNDS);
        double exTwoVolumeLBS = three.getSetVolume(Unit.POUNDS) + four.getSetVolume(Unit.POUNDS);
        
        double weeklyRoutineVolumeKG = exOneVolumeKG + exTwoVolumeKG;
        double weeklyRoutineVolumeLBS = exOneVolumeLBS + exTwoVolumeLBS;

        assertEquals(weeklyRoutineVolumeKG, routine.getVolume(Unit.KILOGRAMS));
        assertEquals(weeklyRoutineVolumeLBS, routine.getVolume(Unit.POUNDS));
    }

    @Test
    void testClear() {
        for (Days day : Days.values()) {
            this.routine.addWorkout(createWorkout(day.toString()), day);
        }

        assertEquals(7, routine.getNumWorkouts());
        routine.clearRoutine();
        assertEquals(0, routine.getNumWorkouts());
    }

    @Test 
    void testEqualsSameObject() {
        assertTrue(routine.equals(routine));
    }

    @Test 
    void testEqualsSameName() {
        WeeklyRoutine w2 = new WeeklyRoutine("Upper Lower");
        assertTrue(routine.equals(w2));
        assertEquals(w2.hashCode(), routine.hashCode());
    }

    @Test
    void testEqualsNullObject() {
        WeeklyRoutine w2 = null;
        assertFalse(routine.equals(w2));
    }

    @Test 
    void testEqualsDifferentClass() {
        assertFalse(routine.equals(workout));
    }

    @Test
    void testEqualsDifferentName() {
        WeeklyRoutine wr2 = new WeeklyRoutine("NOT SAME NAME");
        assertFalse(routine.equals(wr2));
        assertNotEquals(routine.hashCode(), wr2.hashCode());
    }

    @Test
    void testEqualsNullName() {
        WeeklyRoutine wr2 = new WeeklyRoutine(null);
        assertFalse(wr2.equals(routine));
        assertNotEquals(wr2.hashCode(), routine.hashCode());
    }

    @Test
    void testEqualsBothNull() {
        WeeklyRoutine wr1 = new WeeklyRoutine(null);
        WeeklyRoutine wr2 = new WeeklyRoutine(null);
        assertTrue(wr2.equals(wr1));
        assertEquals(wr2.hashCode(), wr1.hashCode());
    }

    @Test 
    void testEqualsDifferentRoutine() {
        WeeklyRoutine wr2 = new WeeklyRoutine("Upper Lower");
        wr2.addWorkout(new Workout("Back"), Days.TUESDAY);
        assertFalse(wr2.equals(routine));

    }

    private Workout createWorkout(String name) {
        return new Workout(name);
    }
}