package model;

import java.util.HashMap;
import model.Set.Unit;

import java.lang.Math;

// Class modelling a workout weekly routine storing a name and can store a workout for each day of the week
public class WeeklyRoutine {
    private String name;
    private HashMap<Days, Workout> routine;

    // EFFECTS: Creates a weekly routine with no workouts initially
    //          sets the weekly routine name to given name
    public WeeklyRoutine(String name) {
        this.name = name;
        routine = new HashMap<>();
    }

    // REQUIRES: workout is not already in the routine
    // MODIFIES: this
    // EFFECTS: Adds a workout into the collection corresponding to the given day
    //          If a workout is already in the slot of day, it overrides it
    public void addWorkout(Workout workout, Days day) {
        routine.put(day, workout);
    }

    // MODIFIES: this
    // EFFECTS: removes the workout at given day
    //          if there is no workout, do nothing
    public void removeWorkout(Days day) {
        routine.remove(day);
    }

    // EFFECTS: returns number of workouts in the weekly routine (Max is 7)
    public int getNumWorkouts() {
        return routine.size();
    }

    public String getName() {
        return this.name;
    }

    // EFFECTS: returns the total weekly workout volume 
    public double getVolume(Unit unit) {
        double volume = 0.0;
        for (Workout workout : routine.values()) {
            volume += workout.getVolume(unit);
        }
        return Math.round(volume * 10.0) / 10.0;
    }

    // EFFECTS: returns the workout at the given day
    //          returns null if there is no workout at given day
    public Workout getWorkout(Days day) {
        return routine.get(day);
    }

    // REQUIRES: dayOne != dayTwo
    // MODIFIES: this
    // EFFECTS: swaps the workouts at given days
    public void swapWorkouts(Days dayOne, Days dayTwo) {
        Workout temp = routine.get(dayOne);
        routine.put(dayOne, routine.get(dayTwo));
        routine.put(dayTwo, temp);
    }

    // MODIFIES: this
    // EFFECTS: clears the entire workout routine
    public void clearRoutine() {
        routine.clear();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + routine.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        WeeklyRoutine other = (WeeklyRoutine) obj;
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        if (!routine.equals(other.routine)) {
            return false;
        }
        return true;
    }
}
