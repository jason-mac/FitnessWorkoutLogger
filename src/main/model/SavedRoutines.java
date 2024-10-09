package model;

import java.util.ArrayList;

public class SavedRoutines {
    private ArrayList<WeeklyRoutine> savedRoutines;

    // EFFECTS: creates a a list of saved routines with initially no routines in it
    public void SavedRoutines() {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: adds a weekly routine to saved routines
    public void addRoutine(WeeklyRoutine weeklyRoutine) {
        // stub
    }

    // EFFECTS: returns a weekly routine with given name, otherwise returns null
    public WeeklyRoutine getRoutineByName(String name) {
        return null; // stub
    }

    public ArrayList<WeeklyRoutine> getRoutines() {
        return null; // stub
    }

    // MODIFIES: this
    // EFFECTS: removes the given workout from the saved routines
    public void removeRoutine(WeeklyRoutine weeklyRoutine) {
        //stub
    }

    // MODIFIES: this
    // EFFECTS: clears the entire list of saved routines
    void clearSavedRoutines() {
        // stub
    }
}
