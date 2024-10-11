package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Set.Unit;

public class TestSet {
    Set setKilograms;
    Set setPounds;

    @BeforeEach 
    void setup() {
        setKilograms = new Set(100.5, 10, Unit.KILOGRAMS);
        setPounds = new Set(225.5, 5, Unit.POUNDS);
    }

    @Test
    void testConstructor() {
        assertEquals(100.5, setKilograms.getWeightInKilograms()); 
        assertEquals(10, setKilograms.getRepCount());
        assertEquals(225.5, setPounds.getWeightInPounds());
        assertEquals(5, setPounds.getRepCount());
    }

    @Test
    void testKiloToPoundsInitKilo() {
        assertEquals(221.6, setKilograms.getWeightInPounds());
    }

    @Test
    void testPoundsToKiloInitPounds() {
        assertEquals(102.3, setPounds.getWeightInKilograms());
    }

    @Test 
    void testGetSetVolumeKilograms() {
        assertEquals(1005.0, setKilograms.getSetVolume(Unit.KILOGRAMS));
        assertEquals(511.5, setPounds.getSetVolume(Unit.KILOGRAMS));
    }

    @Test
    void testGetSetVolumePounds() {
        assertEquals(2216.0, setKilograms.getSetVolume(Unit.POUNDS));
        assertEquals(1127.5, setPounds.getSetVolume(Unit.POUNDS));
    }

    @Test
    void setWeightKilograms() {
        setKilograms.setWeight(10.0, Unit.KILOGRAMS);
        setPounds.setWeight(15.0, Unit.KILOGRAMS);
        assertEquals(10.0, setKilograms.getWeightInKilograms());
        assertEquals(15.0, setPounds.getWeightInKilograms());
        assertEquals(100.0, setKilograms.getSetVolume(Unit.KILOGRAMS));
        assertEquals(75.0, setPounds.getSetVolume(Unit.KILOGRAMS));
        assertEquals(22.0, setKilograms.getWeightInPounds());
        assertEquals(33.1, setPounds.getWeightInPounds());
    }

    @Test
    void setWeightPounds() {
        setKilograms.setWeight(10.0, Unit.POUNDS);
        setPounds.setWeight(15.0, Unit.POUNDS);
        assertEquals(10.0, setKilograms.getWeightInPounds());
        assertEquals(15.0, setPounds.getWeightInPounds());
        assertEquals(100.0, setKilograms.getSetVolume(Unit.POUNDS));
        assertEquals(75.0, setPounds.getSetVolume(Unit.POUNDS));
        assertEquals(4.5, setKilograms.getWeightInKilograms());
        assertEquals(6.8, setPounds.getWeightInKilograms());
    }

    @Test
    void setRepCount() {
        assertEquals(10, setKilograms.getRepCount());
        setKilograms.setRepCount(12);
        assertEquals(12, setKilograms.getRepCount());
    }
}