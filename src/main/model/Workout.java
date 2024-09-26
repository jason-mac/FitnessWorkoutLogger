package model;

import java.util.ArrayList;

public class Workout {
    ArrayList<Exercise> exercises;

    public Workout() {
        this.exercises = new ArrayList<>();
    }

    public ArrayList<Exercise> getExercises() {
        return this.exercises;
    }

    public void addExercise(Exercise exercise) {
        this.exercises.add(exercise);
    }

    public void display() {
        return; // stub
    }
    
}