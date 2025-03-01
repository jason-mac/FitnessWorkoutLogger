package persistence;

import java.io.FileNotFoundException;
import java.io.IOException;

import model.WorkoutLogger;

// Data persistence class that will deal with both saving and loading workoutLogger
public class DataPersistence {
    private JsonReader readerWorkoutLogs;
    private JsonWriter writerWorkoutLogs;

    // EFFECTS: Instantiates JsonWriter and JsonReader for workout logger and sets the filePath 
    //          for which the Json file will be saved
    public DataPersistence(String filePath) {
        this.writerWorkoutLogs = new JsonWriter(filePath);
        this.readerWorkoutLogs = new JsonReader(filePath);
    }

    // EFFECTS: Given a workout logger, saves it to the appropiate file 
    //          throws FileNotFoundException if cannot be saved
    public void save(WorkoutLogger workoutLogs) throws FileNotFoundException {
        writerWorkoutLogs.open();
        writerWorkoutLogs.write(workoutLogs);
        writerWorkoutLogs.close();
    }

    // EFFECTS: Retrives the workoutLogger from the Json file given the filePath
    //          and returns the workoutLogger
    public WorkoutLogger load() throws IOException {
        return readerWorkoutLogs.readWorkoutLogs();
    }
}