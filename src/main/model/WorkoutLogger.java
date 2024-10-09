package model;

import java.util.HashMap;

public class WorkoutLogger {
    HashMap<String, Workout> workoutLogs;

    public WorkoutLogger () {
        this.workoutLogs = new HashMap<>();
    }

    // MODIFIES: this
    // EFFECTS: adds a workout at given date into the collection
    public void addWorkout(String date, Workout workout) {
        //stub
    }

    // EFFECTS: retrieves the workout at given date,
    //          otherwise returns nulls
    public Workout getWorkout(String date) {
        return null; // stub
    }

    public int getNumWorkoutsLogged() {
        return 0; // stub
    }
}
