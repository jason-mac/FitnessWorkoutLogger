package model;

import java.lang.Math;

import org.json.JSONObject;

import persistence.Writeable;

// Class that models a single set done of an exercise in a workout, storing information on weights used
// rep count and a specified unit
// Offers conversion between kilograms and pounds
public class Set implements Writeable {

    // Conversion factors
    private static final double POUNDS_TO_KILOGRAMS = 0.45359;
    private static final double KILOGRAM_TO_POUNDS = 2.2046;

    // public enum for specifying between kilograms and pounds
    public enum Unit {
        KILOGRAMS,
        POUNDS,
    }

    private double weight; // weight associated with the set
    private int repCount;  // number of reps done in the set
    private Unit unit;     // the working unit of this specific set
    
    // REQUIRES: weight > 0 and weight must be at most one decimal place and repCount > 0
    // EFFECT: Creates a set with the weight, rep count, and unit specified
    public Set(double weight, int repCount, Unit unit) {
        this.weight = weight;
        this.repCount = repCount;
        this.unit = unit;
    }

    // REQUIRES: weight > 0.0 and weight must be exactly one decimal place
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
        return oneDecimal(weightInKilograms); 
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(weight);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + repCount;
        result = prime * result + ((unit == null) ? 0 : unit.hashCode());
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
        Set other = (Set) obj;
        if (Double.doubleToLongBits(weight) != Double.doubleToLongBits(other.weight)) {
            return false;
        }
        if (repCount != other.repCount) {
            return false;
        }
        if (unit != other.unit) {
            return false;
        }
        return true;
    }

    // EFFECTS: returns the given value back in one decimal place only 
    private double oneDecimal(double value) {
        return Math.round(value * 10.0) / 10.0;
    }

    @Override 
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("weight", weight);
        jsonObject.put("repCount", repCount);
        jsonObject.put("unit", unit);
        return jsonObject;
    }
}