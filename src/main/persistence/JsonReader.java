package persistence;

import model.*;
import model.Set.Unit;

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
        this.source = source;
    }

    // EFFECTS: reads workoutLogs file from file and returns it
    //          throws IOException if an error occurs reading data from file
    public WorkoutLogger readWorkoutLogs() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseWorkoutLogger(jsonObject);
    }

    // EFFECTS: reads savedRoutines from files and returns it
    //          throws IOexception if an error occurs reading data
    public SavedRoutines readSavedRoutines() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseSavedRoutines(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contenBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contenBuilder.append(s));
        }

        return contenBuilder.toString();
    }


    //EFFECTS: parses savedroutines from JSON object and returns it
    private SavedRoutines parseSavedRoutines(JSONObject jsonObject) {
        SavedRoutines sr = new SavedRoutines();
        addRoutines(sr, jsonObject);
        return sr;
    }

    // MODIFIES: sr
    // EFFECTS: parses weekly routines from JSON object and adds them to saved routines
    private void addRoutines(SavedRoutines sr, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("savedRoutines");
        for (Object json : jsonArray) {
            JSONObject nextRoutine = (JSONObject) json;
            addRoutine(sr, nextRoutine);
        }
    } 

    // MODIFIES: sr
    // EFFECTS: parses weekly routine from JSON object and adds it to saved routines
    private void addRoutine(SavedRoutines sr, JSONObject jsonObject) {
        String name = jsonObject.getString("name");

        WeeklyRoutine weeklyRoutine = new WeeklyRoutine(name);
        JSONArray routineJsonArray = jsonObject.getJSONArray("routine");
        for (Object json : routineJsonArray) {
            JSONObject workoutDayPairJson = (JSONObject) json;
            addWorkoutDayPair(weeklyRoutine, workoutDayPairJson);
        }
        sr.addRoutine(weeklyRoutine);
    }

    // MODIFIES: weeklyRoutine
    // EFFECTS: parses day/workouts key value pairs from json object and adds them to weekly routine
    private void addWorkoutDayPair(WeeklyRoutine weeklyRoutine, JSONObject jsonObject) {
        Days day = Days.valueOf(jsonObject.getString("day"));
        JSONObject workoutJsonData = jsonObject.getJSONObject("workout");
        Workout workout = jsonToWorkout(workoutJsonData);
        weeklyRoutine.addWorkout(workout, day);
    }

    // EFFECTS: parses a workout from json object and returns it as workout object
    private Workout jsonToWorkout(JSONObject jsonObject) {
        JSONArray jsonExercisesArray = jsonObject.getJSONArray("exercises");
        String name = jsonObject.getString("name");
        Workout workout = new Workout(name);
        for (Object json : jsonExercisesArray) {
            JSONObject exerciseJson = (JSONObject) json;
            addExercise(workout, exerciseJson);
        }
        return workout;
    }

    // MODIFIES: workout 
    // EFFECTS: parses exercise from json object and adds it to workout 
    private void addExercise(Workout workout, JSONObject jsonObject) {
        JSONArray jsonSetsArray = jsonObject.getJSONArray("sets");
        String name = jsonObject.getString("name");
        Exercise exercise = new Exercise(name);
        for (Object json : jsonSetsArray) {
            JSONObject setJson = (JSONObject) json;
            addSet(exercise, setJson);
        }
        workout.addExercise(exercise);
    }

    // MODIFIES: exercise
    // EFFECTS: parses sets from json object and adds it to exercise
    private void addSet(Exercise exercise, JSONObject jsonObject) {
        double weight = jsonObject.getDouble("weight");
        int repCount = jsonObject.getInt("repCount");
        Unit unit = Unit.valueOf(jsonObject.getString("unit"));
        exercise.addSet(new Set(weight, repCount, unit));
    }

    //EFFECTS: parses workoutlogger from JSON object and returns it
    private WorkoutLogger parseWorkoutLogger(JSONObject jsonObject) {
        WorkoutLogger wl = new WorkoutLogger();
        addWorkouts(wl, jsonObject);
        return wl;
    }

    
    // MODIFIES: wl 
    // EFFECTS: parses date/workout pairs from json object and adds them to workout logger 
    private void addWorkouts(WorkoutLogger wl, JSONObject jsonObject) {
        JSONArray jsonDateWorkoutPairs = jsonObject.getJSONArray("workoutLogs");
        for (Object json : jsonDateWorkoutPairs) {
            JSONObject jsonDateWorkoutPair = (JSONObject) json;
            String date = jsonDateWorkoutPair.getString("date");
            JSONObject jsonWorkout = jsonDateWorkoutPair.getJSONObject("workout");
            Workout workout = jsonToWorkout(jsonWorkout);
            wl.addWorkout(date, workout);
        }
    }
}
