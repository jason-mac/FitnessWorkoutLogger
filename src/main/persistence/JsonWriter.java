package persistence;

import model.SavedRoutines;
import model.WorkoutLogger;
import org.json.JSONObject;

import java.io.*;

/*
 * This code is based on code provided by JsonSerializationDemo 
 * as part of the course CPSC 210 at University of British Coumbia
 * Source: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 */

// Represents a wrtier that writes JSON representation of WorkoutLogger and SavedRoutines to file
public class JsonWriter {

    // EFFECTS: constructs a writer to write destination to file
    public JsonWriter(String destination) {
        //stub
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    //          be opened for writing
    public void open() throws FileNotFoundException {
        //stub
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of savedRoutines to file
    public void write(SavedRoutines sr) {
        //stub
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of workout logger to file
    public void write(WorkoutLogger wl) {
        //stub
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        //stub
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        //stub
    }
    
}
