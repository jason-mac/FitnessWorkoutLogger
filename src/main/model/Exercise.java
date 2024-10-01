package model;

// Stores information of the amount of sets done in a given exercise
public class Exercise {

    // REQRUIRES: non-empty name
    // EFFECTS: creaates an exercise with given name and no sets initially done
    public Exercise(String name) {
        // stub
    }

    // MODIFIES: this
    // EFFECT: adds a set to the list of sets for this exercise
    public void  addSet(Set set) {
        // stub 
    }

    // EFFECT: returns the accumulative setVolumes done in this exercise 
    public double getExerciseVolume() {
        return 0.0; // stub
    }

    // EFFECT: Returns the amount of sets were perfoemd with this exercise
    public int getSetCount() {
        return -1; // stub
    }

    public String getExerciseName() {
        return new String(); // stub
    }
}