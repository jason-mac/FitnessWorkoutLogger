package model;

import model.Set.Unit;

public class Workout {
    
    // EFFECTS: Construct a workout with no exercises
    public Workout() {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: adds the exercise to list of exercises in the workout
    public void addExercise(Exercise exercise) {
        //stub
    }

    // MODIFIES: this
    // EFFECTS: removes the given exercise from list of exercises in workout at index and returns true
    //          if the index is out of bounds or list is empty, do nothing and return false
    public boolean removeExercise(int index) {
        return true; // stub
    }

    // MODIFIES: this
    // EFFECTS: replaces the exercie in the list at the specified index with given exercise, and return true
    //          if the index is out of bounds or list is empty, do nothing and return false
    public boolean replaceExercise(int index, Exercise exercise) {
        return true; // stub
    }

    // EFFECTS: returns the given exercise at index from list of exercises in workout
    //          returns null if index is out of bounds
    public Exercise getExerciseByIndex(int index) {
        return new Exercise(null); // stub
    }

    // EFFECTS: returns the given exercise at index from list of exercises in workout
    //          returns null if exercise does not exist with the given name
    public Exercise getExerciseByName(String name) {
        return new Exercise(null); // stub
    }

    // MODIFIES: this
    // EFFECTS: clears all exercises from this list of exercises
    public void clearExercises() {
        // stub
    }

    // EFFECTS: Return total workout volume in specified unit
    public double getVolume(Unit unit) {
        return 0.0; // stub
    }

    // EFFECTS: returns true if there are no exercises in this workout
    public boolean isEmpty() {
        return true; // stub
    }

    // EFFECTS: return number of exercises in the workout
    public int getNumberOfExercises() {
        return -1; // stub
    }
}
