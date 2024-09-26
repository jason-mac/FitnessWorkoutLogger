package model;

import java.util.HashMap;


public class WeeklyRoutine {
    String name;
    private HashMap<Day, Workout> routine;

    public WeeklyRoutine() {
        this.routine = new HashMap<Day, Workout>();
    }


    public Workout getWorkoutForDay(Day day) {
        return routine.get(day);
    }

    public void setWorkoutForDay(Workout workout, Day day) {
        return; // stub
    }

    public void removeWorkoutForDay(Day day) {
        return; // stub
    }

    public void swapWorkouts(Day dayOne, Day dayTwo) {
        return; // stub
    }

    public void display() {
        return; // stub
    }
}

