package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Set.Unit;

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

        Exercise ExOne = new Exercise("");
        Exercise ExTwo = new Exercise("");

        ExOne.addSet(one);
        ExOne.addSet(two);
        ExTwo.addSet(three);
        ExTwo.addSet(four);

        Workout workoutTwo = createWorkout();

        workout.addExercise(ExOne);
        workoutTwo.addExercise(ExTwo);

        routine.addWorkout(workout, monday);
        routine.addWorkout(workoutTwo, Day.TUESDAY);

        double exOneVolumeKG = one.getSetVolume(Unit.KILOGRAMS) + two.getSetVolume(Unit.KILOGRAMS);
        double exTwoVolumeKG = three.getSetVolume(Unit.KILOGRAMS) + four.getSetVolume(Unit.KILOGRAMS);

        double exOneVolumeLBS = one.getSetVolume(Unit.POUNDS) + two.getSetVolume(Unit.POUNDS);
        double exTwoVolumeLBS = three.getSetVolume(Unit.POUNDS) + four.getSetVolume(Unit.POUNDS);
        
        double weeklyRoutineVolumeKG = exOneVolumeKG + exTwoVolumeKG;
        double weeklyRoutineVolumeLBS = exOneVolumeLBS + exTwoVolumeLBS;

        assertEquals(weeklyRoutineVolumeKG, routine.getVolume(Unit.KILOGRAMS));
        assertEquals(weeklyRoutineVolumeLBS, routine.getVolume(Unit.POUNDS));
    }
    private Workout createWorkout() {
        return new Workout();
    }

    private Workout createWorkout(String name) {
        return new Workout();
    }
}