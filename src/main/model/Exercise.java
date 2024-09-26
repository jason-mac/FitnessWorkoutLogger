package model;

import java.util.ArrayList;

public class Exercise {
    String name;
    private ArrayList<Set> sets;
   
    public Exercise(String name) {
        this.name = name;
        this.sets = new ArrayList<>();
    }

    void addSet(Set set) {
        sets.add(set);
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<Set> getSets() {
        return this.sets;
    }
}