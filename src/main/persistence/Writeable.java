package persistence;

import org.json.JSONObject;


/*
 * This code is based on code provided by JsonSerializationDemo 
 * as part of the course CPSC 210 at University of British Coumbia
 * Source: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 */

public interface Writeable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
