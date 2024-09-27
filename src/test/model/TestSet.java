package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;

import model.Set;

public class TestSet {
    Set set;

    @BeforeEach
    void setup() {
        set = new Set(10.5, 5);
    }

    @Test 
    void testConstructor() {
        assertEquals(10.5, set.getWeight());
        assertEquals(5, set.getReps());
    }
}
