package model;

// Class that models a a single set of an exercise in a workout
public class Set {

    // Conversion factors
    private static final double POUND_TO_KILOGRAMS = 0.45359;
    private static final double KILOGRAM_TO_SPOUND = 2.2046;

    // public enum for specifying between kilograms and pounds
    public enum Unit {
        KILOGRAMS,
        POUNDS,
    }
    
    // EFFECT: Creates a set with the current weight, repCount, and unit specified
    public Set(double weight, int repCount, Unit unit) {
        // stub
    }

    // REQUIRES: weight > 0
    // MODIFIES: this
    // EFFECT: changes the weight used in the set, with a unit specified
    public void setWeight (double weight, Unit unit) {
        // stub
    }

    // REQUIRES: repCount > 0
    // MODIFIES: this
    // EFFECT: changes the rep count in the set with repCount
    public void setRepCount(int repCount) {
        // stub
    }


    // EFFECT: returns the weight used in the set in kilograms
    public double getWeightInKilograms() {
        return 0.0; // stub
    }

    // EFFECT: returns the weight used in the set in pounds 
    public double getWeightInPounds() {
        return 0.0; // stub
    }

    public int getRepCount() {
        return -1; // stub
    }
}