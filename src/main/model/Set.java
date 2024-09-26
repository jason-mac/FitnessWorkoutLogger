package model;

public class Set {
    enum Unit {
        KILOGRAMS,
        POUNDS,
    }

    private Unit unit;
    private double weight;
    private int repCount;
    
    // REQUIRES:
    // MODIFIES:
    // EFFECT:

    // EFFECT: Creates a set object with given 
    public Set(double weight, int repCount) {
        this.weight = weight;
        this.repCount = repCount;
    }

    public void changeToKilograms() {
        if(this.unit == Unit.KILOGRAMS) {
            return;
        }
        // for loop change all of them lol
    }

    public double getWeight() {
        return this.weight;
    }

    public int getReps() {
        return this.repCount;
    }
}