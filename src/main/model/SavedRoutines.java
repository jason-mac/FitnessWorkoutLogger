package model;

import java.util.ArrayList;

import org.json.JSONObject;
import org.json.JSONArray;

import persistence.Writeable;

// Class modelling a list of saved workout routines, able to be retrieved from and added to
public class SavedRoutines implements Writeable {
    private ArrayList<WeeklyRoutine> savedRoutines;

    // EFFECTS: creates a a list of saved routines with initially no routines in it
    public SavedRoutines() {
        this.savedRoutines = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds a weekly routine to saved routines
    public void addRoutine(WeeklyRoutine weeklyRoutine) {
        this.savedRoutines.add(weeklyRoutine);
    }

    // EFFECTS: returns a weekly routine with given name, otherwise returns null
    public WeeklyRoutine getRoutineByName(String name) {
        WeeklyRoutine toReturn = null;
        for (WeeklyRoutine weeklyRoutine : savedRoutines) {
            if (weeklyRoutine.getName().equals(name)) {
                toReturn = weeklyRoutine;
                break;
            }
        }
        return toReturn;
    }

    public ArrayList<WeeklyRoutine> getRoutines() {
        return this.savedRoutines;
    }

    // MODIFIES: this
    // EFFECTS: removes the given workout from the saved routines
    public void removeRoutine(WeeklyRoutine weeklyRoutine) {
        this.savedRoutines.remove(weeklyRoutine);
    }

    // MODIFIES: this
    // EFFECTS: clears the entire list of saved routines
    public void clearSavedRoutines() {
        this.savedRoutines.clear();
    }

    public boolean isEmpty() {
        return this.savedRoutines.size() == 0;
    }

    public int getNumRoutinesStored() {
        return this.savedRoutines.size();
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("savedRoutines", savedRoutinesToJson());
        return json;
    }

    // EFFECTS: returns weekly routines in this savedRoutines as a Json Array
    private JSONArray savedRoutinesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (WeeklyRoutine weeklyRoutine : savedRoutines) {
            jsonArray.put(weeklyRoutine.toJson());
        }

        return jsonArray;
    }
}
