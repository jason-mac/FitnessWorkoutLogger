package model;

import model.Set.Unit;
import java.util.ArrayList;

// Class that models a workout, which stores a list of exercises and has a name
public class Workout {
    private ArrayList<Exercise> exercises;
    private String name;
    

    // EFFECTS: Construct a workout with no exercises and sets name to given name 
    public Workout(String name) {
        this.name = name;
        this.exercises = new ArrayList<>();
    }

    // REQUIRES: that given exercise with its given name is not alrady in the list
    // MODIFIES: this
    // EFFECTS: adds the exercise to list of exercises in the workout
    public void addExercise(Exercise exercise) {
        exercises.add(exercise);
    }

    // MODIFIES: this
    // EFFECTS: removes the given exercise from list of exercises in workout at index and returns true
    //          if the index is out of bounds or list is empty, do nothing and return false
    public boolean removeExercise(int index) {
        if (exercises.isEmpty() || outOfBounds(index)) {
            return false;
        }
        exercises.remove(index);
        return true;
    }

    // MODIFIES: this
    // EFFECTS: replaces the exercie in the list at the specified index with given exercise, and return true
    //          if the index is out of bounds or list is empty, do nothing and return false
    public boolean replaceExercise(int index, Exercise exercise) {
        if (exercises.isEmpty() || outOfBounds(index)) {
            return false;
        }
        exercises.set(index, exercise);
        return true;
    }

    // EFFECTS: returns the given exercise at index from list of exercises in workout
    //          returns null if index is out of bounds or list is empty
    public Exercise getExerciseByIndex(int index) {
        if (exercises.isEmpty() || outOfBounds(index)) {
            return null;
        }
        return exercises.get(index);
    }

    // EFFECTS: returns the given exercise at index from list of exercises in workout
    //          returns null if exercise does not exist with the given name
    public Exercise getExerciseByName(String name) {
        Exercise toReturn = null;
        for (Exercise exercise : exercises) {
            if (exercise.getName().equals(name)) {
                toReturn = exercise;
                break;
            }
        }
        return toReturn;
    }

    // MODIFIES: this
    // EFFECTS: clears all exercises from this list of exercises
    public void clearExercises() {
        this.exercises.clear();
    }

    // EFFECTS: Return total workout volume in given unit
    public double getVolume(Unit unit) {
        double toReturn = 0;
        for (Exercise exercise : exercises) {
            toReturn += exercise.getVolume(unit);
        }
        return toReturn;
    }

    public String getName() {
        return this.name;
    }

    // EFFECTS: returns true if there are no exercises in this workout
    public boolean isEmpty() {
        return this.exercises.size() == 0;
    }

    public int getNumberOfExercises() {
        return exercises.size();
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Exercise> getExercises() {
        return this.exercises;
    }

    // EFFECTS: returns if true the index is out of bounds of exercises array
    private boolean outOfBounds(int index) {
        return (index < 0 || index >= exercises.size());
    }
}
