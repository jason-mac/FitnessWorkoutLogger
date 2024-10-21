package model;

import java.util.ArrayList;
import java.lang.Math;

import model.Set.Unit;

// Represents an exercise done with a name and a list of sets to be stored associated with the exercise
public class Exercise {

    private String name;            // name of exercise
    private ArrayList<Set> sets;    // list of sets to be stored

    // EFFECTS: creaates an exercise with given name and no sets initially done
    public Exercise(String name) {
        this.name = name;
        this.sets = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECT: adds a set to the list of sets for this exercise
    public void addSet(Set set) {
        sets.add(set);
    }

    // EFFECT: returns the accumulative setVolumes done in this exercise for specifed unit 
    public double getVolume(Unit u) {
        double volume = 0.0;
        for (Set set : sets) {
            volume += set.getSetVolume(u);
        }
        return Math.round(volume * 10.0) / 10.0;
    }

    // EFFECT: Returns the amount of sets were performed with this exercise
    public int getSetCount() {
        return sets.size();
    }

    public ArrayList<Set> getSets() {
        return this.sets;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + sets.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Exercise other = (Exercise) obj;
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        if (!sets.equals(other.sets)) {
            return false;
        }
        return true;
    }
}