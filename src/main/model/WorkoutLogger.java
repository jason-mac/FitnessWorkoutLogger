package model;

import java.util.HashMap;

// Class that is able to store, and retrieve workouts given a date. 
public class WorkoutLogger {
    HashMap<String, Workout> workoutLogs;

    // EFFECTS: Creates a workout logger with no workouts logged
    public WorkoutLogger () {
        this.workoutLogs = new HashMap<>();
    }

    // REQUIRES: date is int the format dd/mm/year
    // MODIFIES: this
    // EFFECTS: adds a workout at given date into the collection
    public void addWorkout(String date, Workout workout) {
        this.workoutLogs.put(date, workout);
    }

    // EFFECTS: retrieves the workout at given date,
    //          otherwise returns nulls
    public Workout getWorkout(String date) {
        return this.workoutLogs.get(date);
    }

    public int getNumWorkoutsLogged() {
        return this.workoutLogs.size();
    }
}
