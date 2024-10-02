package model;

import java.util.HashMap;

import model.Set.Unit;

// Class modifying a workout weekly routine, a null workout on a day is
// interpreted as a rest day
public class WeeklyRoutine {
    String name;
    HashMap<Day, Workout> routine;

    // EFFECTS: Creates a weekly routine with no workouts initially
    //          sets the weekly routine name to given name
    public WeeklyRoutine(String name) {
        this.name = name;
        routine = new HashMap<>();
    }

    // MODIFIES: this
    // EFFECTS: Adds a workout into the collection corresponding to the given day
    //          If a workout is already in the slot of day, it overrides it
    public void addWorkout(Workout workout, Day day) {
        routine.put(day, workout);
    }

    // MODIFIES: this
    // EFFECTS: removes the workout at given day
    //          if there is no workout, do nothing
    public void removeWorkout(Day day) {
        routine.remove(day);
    }

    // EFFECTS: returns number of workouts in the weekly routine
    public int getNumWorkouts() {
        return routine.size();
    }


    // EFFECTS: returns number of rest days in the weekly routine
    public int getNumRestDays() {
        return 7 - getNumWorkouts();
    }

    // EFFECTS: returns name of weekly routine
    public String getName() {
        return this.name;
    }

    // EFFECTS: returns the total weekly workout volume
    public double getVolume(Unit unit) {
        double toReturn = 0;
        for(Workout workout : routine.values()) {
            toReturn += workout.getVolume(unit);
        }
        return toReturn;
    }

    // EFFECTS: returns the workout at the given day
    //          returns null if there is no workout at given day
    public Workout getWorkout(Day day) {
        return routine.get(day);
    }

    // REQUIRES: dayOne != dayTwo
    // MODIFIES: this
    // EFFECTS: swaps the workouts at given days
    public void swapWorkouts(Day dayOne, Day dayTwo) {
        Workout temp = routine.get(dayOne);
        routine.put(dayOne, routine.get(dayTwo));
        routine.put(dayTwo, temp);
    }

    // MODIFIES: this
    // EFFECTS: resets the routine and sets each day workout to "REST DAY"
    public void clearRoutine() {
        routine.clear();
    }
}
