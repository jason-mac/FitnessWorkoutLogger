package model;

import java.lang.Math;

// Class that models a a single set of an exercise in a workout
public class Set {

    // Conversion factors
    private static final double POUNDS_TO_KILOGRAMS = 0.45359;
    private static final double KILOGRAM_TO_POUNDS = 2.2046;

    // public enum for specifying between kilograms and pounds
    public enum Unit {
        KILOGRAMS,
        POUNDS,
    }

    // Private Fields
    private double weight;
    private int repCount;
    private Unit unit;
    
    // REQUIRES: weight > 0 and weight must be at most one decimal place and repCount > 0
    // EFFECT: Creates a set with the current weight, repCount, and unit specified
    public Set(double weight, int repCount, Unit unit) {
        this.weight = weight;
        this.repCount = repCount;
        this.unit = unit;
    }

    // REQUIRES: weight > 0 and weight must be at most one decimal place
    // MODIFIES: this
    // EFFECT: changes the weight used in the set, with a unit specified
    public void setWeight(double weight, Unit unit) {
        this.weight = weight;
        this.unit = unit;
    }

    // REQUIRES: repCount > 0
    // MODIFIES: this
    // EFFECT: changes the rep count in the set with repCount
    public void setRepCount(int repCount) {
        this.repCount = repCount;
    }

    // EFFECT: returns the set "volume", in other words returns weight * rep count with specified unit
    public double getSetVolume(Unit u) {
        double volume;
        if (u == Unit.KILOGRAMS) {
            volume = this.getWeightInKilograms() * this.repCount;
        } else {
            volume = this.getWeightInPounds() * this.repCount;
        }
        return oneDecimal(volume);
    }


    // EFFECT: returns the weight used in the set in kilograms
    public double getWeightInKilograms() {
        if (this.unit == Unit.KILOGRAMS) {
            return this.weight;
        }

        double weightInKilograms = this.weight * POUNDS_TO_KILOGRAMS;
        return oneDecimal(weightInKilograms); // stub
    }

    // EFFECT: returns the weight used in the set in pounds 
    public double getWeightInPounds() {
        if (this.unit == Unit.POUNDS) {
            return this.weight;
        }

        double weightInPounds = this.weight * KILOGRAM_TO_POUNDS;
        return oneDecimal(weightInPounds); 
    }

    public int getRepCount() {
        return this.repCount;
    }

    private double oneDecimal(double value) {
        return Math.round(value * 10.0) / 10.0;
    }
}