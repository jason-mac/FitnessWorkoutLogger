package model;

import java.util.HashMap;
import java.util.Set;

// Class modelling a workout logger that is able to store, and retrieve workouts given a date. 
public class WorkoutLogger {
    private HashMap<String, Workout> workoutLogs;

    // EFFECTS: Creates a workout logger with no workouts logged
    public WorkoutLogger() {
        this.workoutLogs = new HashMap<>();
    }

    // REQUIRES: date is in the format dd/mm/year
    // MODIFIES: this
    // EFFECTS: adds a workout at given date into the collection
    //          over rides the workout if there is already one at the given date
    public void addWorkout(String date, Workout workout) {
        this.workoutLogs.put(date, workout);
    }

    // MODIFIES: this
    // EFFECTS: removes a workout at the given date
    //          if no workout at date, does nothing
    public void removeWorkout(String date) {
        this.workoutLogs.remove(date);
    }

    // MODIFIES: this
    // EFFECTS: clears the entire workout logs
    public void clearLogs() {
        this.workoutLogs.clear();
    }

    // REQUIRES: workoutLogs.size() != 0
    // EFFECTS: returns a list of dates to which a workout has been stored
    public Set<String> getDates() {
        return this.workoutLogs.keySet();
    }

    // EFFECTS: retrieves the workout at given date,
    //          otherwise returns nulls
    public Workout getWorkout(String date) {
        return this.workoutLogs.get(date);
    }

    public int getNumWorkoutsLogged() {
        return this.workoutLogs.size();
    }

    public HashMap<String, Workout> getWorkoutLogs() {
        return workoutLogs;
    }
}
