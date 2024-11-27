package model;

import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

import model.Set.Unit;

import org.json.JSONArray;
import org.json.JSONObject;



import persistence.Writeable;

// Class modelling a workout logger that is able to store, and retrieve workouts given a date. 
public class WorkoutLogger implements Writeable {
    private HashMap<String, Workout> workoutLogs;

    // EFFECTS: Creates a workout logger with no workouts logged
    public WorkoutLogger() {
        this.workoutLogs = new HashMap<>();
    }

    // REQUIRES: date is in the format dd/mm/year
    // MODIFIES: this
    // EFFECTS: adds a workout at given date into the collection
    //          over rides the workout if there is already one at the given date
    public void addWorkout(String date, Workout workout) {
        EventLog.getInstance().logEvent(new Event("Workout added to workout logger at date: " + date));
        this.workoutLogs.put(date, workout);
    }

    // MODIFIES: this
    // EFFECTS: removes a workout at the given date
    //          if no workout at date, does nothing
    public void removeWorkout(String date) {
        EventLog.getInstance().logEvent(new Event("Workout removed from workout logger at date: " + date));
        this.workoutLogs.remove(date);
    }

    // MODIFIES: this
    // EFFECTS: clears the entire workout logs
    public void clearLogs() {
        this.workoutLogs.clear();
    }

    // REQUIRES: workoutLogs.size() != 0
    // EFFECTS: returns a list of dates to which a workout has been stored
    public Set<String> getDates() {
        return this.workoutLogs.keySet();
    }

    // EFFECTS: retrieves the workout at given date,
    //          otherwise returns nulls
    public Workout getWorkout(String date) {
        return this.workoutLogs.get(date);
    }

    public int getNumWorkoutsLogged() {
        return this.workoutLogs.size();
    }

    public HashMap<String, Workout> getWorkoutLogs() {
        return workoutLogs;
    }

    // REQUIRES: Each date in dates is logged as workout
    // EFFECTS: filters the dates based upon which comes after the given dateCompare, returns the filtered
    // list of dates
    public java.util.Set<String> filterDatesAfterDate(java.util.Set<String> dates, String dateCompare) {
        java.util.Set<String> datesFiltered = new HashSet<>();
        for (String date : dates) {
            if (date.compareTo(dateCompare) >= 0) {
                datesFiltered.add(date);
            }
        }
        String message = "Collection of Dates/Workouts filtered by dates after: " + dateCompare + " returned";
        EventLog.getInstance().logEvent(new Event(message));
        return datesFiltered;
    }

    // REQUIRES: Each date in dates is logged as workout
    // EFFECTS: filters the dates based upon which comes before the given dateCompare, returns the filtered
    // list of dates
    public java.util.Set<String> filterDatesBeforeDate(java.util.Set<String> dates, String dateCompare) {
        java.util.Set<String> datesFiltered = new HashSet<>();
        for (String date : dates) {
            if (date.compareTo(dateCompare) <= 0) {
                datesFiltered.add(date);
            }
        }
        String message = "Collection of Dates/Workouts filtered by dates before: " + dateCompare + " returned";
        EventLog.getInstance().logEvent(new Event(message));
        return datesFiltered;
    }

    // REQUIRES: Each date in dates is logged as workout
    // EFFECTS: filters the dates based upon which workouts have less volume than value, returns
    // the filtered list of dates
    public java.util.Set<String> filterDatesLowerVolume(java.util.Set<String> dates, double compare, Unit unit) {
        java.util.Set<String> datesFiltered = new HashSet<>();
        for (String date : dates) {
            Workout workout = this.getWorkout(date);
            if (workout.getVolume(unit) <= compare) {
                datesFiltered.add(date);
            }
        }
        String message = "Collection of Dates/Workouts filtered by volume (";
        message += (unit.toString() + ") less than: " + compare + " returned");
        EventLog.getInstance().logEvent(new Event(message));
        return datesFiltered;
    }

    // REQUIRES: Each date in dates is logged as workout
    // EFFECTS: filters the dates based upon which workouts have more volume than value, returns
    // the filtered list of dates
    public java.util.Set<String> filterDatesHigherVolume(java.util.Set<String> dates, double compare, Unit unit) {
        java.util.Set<String> datesFiltered = new HashSet<>();
        for (String date : dates) {
            Workout workout = this.getWorkout(date);
            if (workout.getVolume(unit) >= compare) {
                datesFiltered.add(date);
            }
        }
        String message = "Collection of Dates/Workouts filtered by volume (";
        message += (unit.toString() + ") greater than: " + compare + " returned");
        EventLog.getInstance().logEvent(new Event(message));
        return datesFiltered;
    }

    @Override
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        Set<String> dates = workoutLogs.keySet();
        for (String date : dates) {
            JSONObject workoutLogJson = new JSONObject();
            workoutLogJson.put("date", date);
            workoutLogJson.put("workout", workoutLogs.get(date).toJson());

            jsonArray.put(workoutLogJson);
        }

        jsonObject.put("workoutLogs", jsonArray);
        return jsonObject;
    }
}
