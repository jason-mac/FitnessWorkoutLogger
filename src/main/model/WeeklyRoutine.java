package model;

// Class modifying a workout weekly routine, a null workout on a day is
// interpreted as a rest day
public class WeeklyRoutine {
   
    // EFFECTS: Creates a weekly routine with no workouts initially
    //          sets the weekly routine name to given name
    public WeeklyRoutine(String name) {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: Adds a workout into the collection corresponding to the given day
    //          If a workout is already in the slot of day, it overrides it
    public void addWorkout(Workout workout, Day day) {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: removes the workout at given day
    //          if there is no workout, do nothing
    public void removeWorkout(Day day) {
        // stub
    }

    // EFFECTS: returns number of workouts in the weekly routine
    public int getNumWorkouts() {
        return -1;
    }


    // EFFECTS: returns number of rest days in the weekly routine
    public int getNumRestDays() {
        return -1;
    }

    // EFFECTS: returns name of weekly routine
    public String getName() {
        return new String();
    }

    // EFFECTS: returns the total weekly workout volume
    public double getVolume() {
        return 0.0;
    }

    // EFFECTS: returns the workout at the given day
    //          returns null if there is no workout at given day
    public Workout getWorkout(Day day) {
        return new Workout();
    }

    // REQUIRES: dayOne != dayTwo
    // MODIFIES: this
    // EFFECTS: swaps the workouts at given days
    public void swapWorkouts(Day dayOne, Day dayTwo) {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: resets the routine and sets each day workout to "REST DAY"
    public void clearRoutine() {
        // stub
    }


}
