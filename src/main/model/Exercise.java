package model;

import model.Set.Unit;
import java.util.ArrayList;

// Stores information of the amount of sets done in a given exercise
public class Exercise {

    private String name;
    private ArrayList<Set> sets; 

    // REQRUIRES: non-empty name
    // EFFECTS: creaates an exercise with given name and no sets initially done
    public Exercise(String name) {
        this.name = name;
        this.sets = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECT: adds a set to the list of sets for this exercise
    public void  addSet(Set set) {
        sets.add(set);
    }

    // EFFECT: returns the accumulative setVolumes done in this exercise for specifed unit 
    public double getVolume(Unit u) {
        double volume = 0.0;
        for (Set set : sets) {
            volume += set.getSetVolume(u);
        }
        return volume;
    }

    // EFFECT: Returns the amount of sets were perfoemd with this exercise
    public int getSetCount() {
        return sets.size();
    }

    // MODIFIES: this
    // EFFECTS: sets name of workout to given name
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}