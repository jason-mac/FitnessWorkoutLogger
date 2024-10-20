package persistence;

import model.*;
import ui.FitnessLoggerApp;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

/*
 * This code is based on code provided by JsonSerializationDemo 
 * as part of the course CPSC 210 at University of British Coumbia
 * Source: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 */

public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        //stub
    }

    // EFFECTS: reads workoutLogs file from file and returns it
    //          throws IOException if an error occurs reading data from file
    public WorkoutLogger readWorkoutLogs() throws IOException {
        //stub
        return null;
    }

    // EFFECTS: reads savedRoutines files and returns it
    //          throws IOexception if an error occurs reading data
    public SavedRoutines readSavedRoutines() throws IOException {
        // stub
        return null;
    }

    private String readFile(String source) throws IOException {
        return null;
        //stub
    }


    //EFFECTS: parses savedroutines from JSON object and returns it
    private SavedRoutines parseSavedRoutines(JSONObject jsonObject) {
        //stub
        return null;
    }

    //EFFECTS: parses workoutlogger from JSON object and returns it
    private WorkoutLogger parseWorkoutLogger(JSONObject jsonObject) {
        //stub
        return null;
    }

    // MODIFIES: sr
    // EFFECTS: parses weekly routines from json object and adds them to saved routines
    private void addWeeklyRoutines(SavedRoutines sr, JSONObject jsonObject) {
       //stub
    }

    // MODIFIES: sr
    // EFFECTS: parses weekly routine from json object and adds them to saved routines
    private void addWeeklyRoutine(SavedRoutines sr, JSONObject jsonObject) {
        //stub
    }
    
    // MODIFIES: wl 
    // EFFECTS: parses workouts from json object and adds them to workout logger 
    private void addWorkouts(WorkoutLogger wl, JSONObject jsonObject) {
       //stub
    }

    // MODIFIES: sr
    // EFFECTS: parses weekly routines from jsob object and adds them to workout logger 
    private void addWorkout(WorkoutLogger wl, JSONObject jsonObject) {
        //stub
    }
}
