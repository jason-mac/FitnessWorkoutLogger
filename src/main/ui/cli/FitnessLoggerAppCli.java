package ui.cli;

import model.Set.Unit;

import model.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;


import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;

/* SOURCE CREDITS
* Heavy Influence from TellerApp.java through out the code
* Source: https://github.students.cs.ubc.ca/CPSC210/TellerApp.git
*/


// UI application where a user can store log workouts, create workout plans, and as well as view/delete these 
// workouts as needed
public class FitnessLoggerAppCli {
    private static final String JSON_STORE_WORKOUTLOGS = "./data/workoutLogger.json";
    private static final String JSON_STORE_SAVED_ROUTINES = "./data/savedRoutines.json";
    private WorkoutLogger workoutLogs;
    private SavedRoutines savedRoutines;
    private Scanner input;
    private boolean running;
    private JsonReader readerWorkoutLogs;
    private JsonReader readerSavedRoutines;
    private JsonWriter writerWorkoutLogs;
    private JsonWriter writerSavedRoutines;


    // EFFECTS: runs the program until running becomes false based off user input
    public void run() throws FileNotFoundException {
        init();
        while (running) {
            String userInput = null;
            displayMenu();
            userInput = input.nextLine();
            processUserInput(userInput);
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes the data fields for running the program 
    private void init() throws FileNotFoundException {
        this.running = true;
        this.input = new Scanner(System.in);
        this.workoutLogs = new WorkoutLogger();
        this.savedRoutines = new SavedRoutines();
        this.writerWorkoutLogs = new JsonWriter(JSON_STORE_WORKOUTLOGS);
        this.readerWorkoutLogs = new JsonReader(JSON_STORE_WORKOUTLOGS);
        this.writerSavedRoutines = new JsonWriter(JSON_STORE_SAVED_ROUTINES);
        this.readerSavedRoutines = new JsonReader(JSON_STORE_SAVED_ROUTINES);
    }

    // EFFECTS: displays a menu for user to interact with program
    private void displayMenu() {
        System.out.println("Select from following:");
        System.out.println("\tp -> Print logged Workout");
        System.out.println("\tl -> Log Workout");
        System.out.println("\tr -> Removed Logged Workout");
        System.out.println("\tc -> Create Weekly Routine");
        System.out.println("\tv -> View Weekly Routine");
        System.out.println("\td -> Delete Weekly Routine");
        System.out.println("\tsave -> Save your workout logs and saved routines to file");
        System.out.println("\tload -> Load your saved workout logs and saved routines from file");
        System.out.println("\tq -> quit");
        System.out.println();
    }

    // MODIFIES: this
    // EFFECTS: process user input and branches code out to corresponding input
    @SuppressWarnings("methodlength")
    private void processUserInput(String userInput) {
        switch (userInput) {
            case "p":
                printLoggedWorkout();
                break; 
            case "l":
                logWorkout();
                break;
            case "r":
                removeLoggedWorkout();
                break; 
            case "c":
                createWeeklyRoutine();
                break;
            case "v":
                viewWeeklyRoutines();
                break;
            case "save":
                saveLogsAndRoutines();
                break;
            case "load":
                loadLogsAndRoutines();
                break;
            case "d":
                deleteWeeklyRoutine();
                break;
            case "q":
                running = false;
                break;
            default:
                System.out.println("Invalid Input");
                break;
        }
    }

    // EFFECTS: prints out the logged workout given a date from user input
    //          if workout logs is empty, tells user as such, end function
    //          if date is not valid, tells user as such
    private void printLoggedWorkout() {
        if (workoutLogs.getNumWorkoutsLogged() == 0) {
            System.out.println("No logged workouts to display");
            return;
        }

        System.out.println("Which date would you like to view a workout from (type in 'list' for possible dates): ");
        String userInput = input.nextLine();
        if (userInput.equals("list")) {
            ArrayList<String> sortedDates = sortedDates();
            for (String date : sortedDates) {
                System.out.println("\t-" + date);
            }
            userInput = input.nextLine();
        }
        String date = userInput;

        Workout workout = workoutLogs.getWorkout(date);
        if (workout == null) {
            System.out.println("No workout logged for this date.");
            return;
        }

        System.out.println("Which units would you like to view your workout in (lbs or kg): ");
        Unit unit = input.nextLine().equals("kg") ? Unit.KILOGRAMS : Unit.POUNDS;
        printWorkoutInformation(workout, unit);
    }

    // EFFECTS: returns a sorted array list of the dates in the workout logs
    private ArrayList<String> sortedDates() {
        ArrayList<String> sortedDates = new ArrayList<>();
        for (String date : workoutLogs.getDates()) {
            sortedDates.add(date);
        }
        Collections.sort(sortedDates);
        return sortedDates;
    }

    // MODIFES: this
    // EFFECTS: gets date from user to log a workout at the date
    //          creates a workout and adds it to workoutLogs
    private void logWorkout() {
        System.out.println("Which date do you wish to log your workout for (dd/mm/year): ");
        String date = input.nextLine();

        Workout workout = createWorkout();

        workoutLogs.addWorkout(date, workout);
        System.out.println("Workout successfully logged for " + date);
    }

    // MODIFIES: this
    // EFFECTS: removes a logged workout from loggedWorkouts given a date from user
    //          if workoutLogs is empty, tells user as such, ends function
    //          if date is not valid, tells user as such 
    private void removeLoggedWorkout() {
        if (this.workoutLogs.getNumWorkoutsLogged() == 0) {
            System.out.println("No workouts to remove.");
            return;
        }
        System.out.println("Which date would you like to remove a workout from (type in 'list' for possible dates): ");
        String userInput = input.nextLine();
        if (userInput.equals("list")) {
            for (String date : this.workoutLogs.getDates()) {
                System.out.println("\t-" + date);
            }
            userInput = input.nextLine();
        }

        if (workoutLogs.getWorkout(userInput) == null) {
            System.out.println("Sorry, no logged workout at that date");
            return;
        }

        workoutLogs.removeWorkout(userInput);
        System.out.println("Workout at " + userInput + " successfully removed!");
    }

    // MODIFIES: this
    // EFFECTS: creates a weekly routine given input from user stores it in savedRoutines 
    private void createWeeklyRoutine() {
        String userInput = null;
        Workout workout = null;

        System.out.println("Creating new weekly routine... ");
        System.out.println("Input name of new weekly routine: ");
        userInput = input.nextLine();
        WeeklyRoutine weeklyRoutine = new WeeklyRoutine(userInput);
        for (Days day : Days.values()) {
            System.out.println("Would you like to add a planned workout for " + day.toString().toLowerCase() + " y/n:");
            userInput = input.nextLine();
            if (userInput.equals("y")) {
                workout = createWorkout();
                weeklyRoutine.addWorkout(workout, day);
            }
        }
        savedRoutines.addRoutine(weeklyRoutine);
        System.out.println("Weekly routine: " + weeklyRoutine.getName() + " has been added to your planned routines.");
    }

    // MODIFIES: this
    // EFFECTS: deletes a planned weekly routine from savedRoutines given name of weekly routine from user
    //          if savedRoutines empty, tells user as such
    //          if weekly routine name does not exist, tells user as such
    private void deleteWeeklyRoutine() {
        if (savedRoutines.isEmpty()) {
            System.out.println("No routines to remove");
            return;
        }
        String message = "Which weekly routine would you like to remove (type in 'list' for possible stored routines):";
        String weeklyRoutineName = getWeeklyRoutineName(message);
        WeeklyRoutine weeklyRoutineToDelete = findRoutineByName(weeklyRoutineName);
        if (weeklyRoutineToDelete == null) {
            System.out.println("Sorry, no such routine named " + weeklyRoutineName + " has beend found.");
            return;
        }

        savedRoutines.removeRoutine(weeklyRoutineToDelete);
        System.out.println("Workout at " + weeklyRoutineName + " successfully removed!");
    }

    // MODIFIES: this
    // EFFECTS: creates a workout given input from user and returns it
    private Workout createWorkout() {
        Workout workout = null;
        String userInput = null;
        int numExercises;

        System.out.println("Would you like to name your workout?");
        System.out.println("y/n");
        userInput = input.nextLine();

        if (userInput.equals("y")) {
            System.out.println("Input name of your workout: ");
            userInput = input.nextLine();
        } else {
            userInput = "";
        }

        workout = new Workout(userInput);

        System.out.println("Input count of exercises: ");
        numExercises = input.nextInt();
        input.nextLine();

        System.out.println("Creating workout...");

        for (int i = 0; i < numExercises; i++) { 
            System.out.println("Please provide information for exercise: " + (i + 1));
            Exercise exercise = createExercise();
            workout.addExercise(exercise);
        }

        return workout;
    }

    // REQUIRES: unit != null and workout != null
    // EFFECTS: prints out the workout information in the given unit (kg or lbs)
    private void printWorkoutInformation(Workout workout, Unit unit) {
        ArrayList<Exercise> exercises = workout.getExercises();
        for (Exercise exercise : exercises) {
            ArrayList<Set> sets = exercise.getSets();
            System.out.println(exercise.getName());
            for (Set set : sets) {
                System.out.println(getSetInformation(set, unit));
            }
            System.out.println();
        }

        System.out.println("Would you like to see total workout volume (y/n): ");
        String userInput = input.nextLine();
        if (userInput.equals("y")) {
            System.out.println("Workout Volume: " + workout.getVolume(unit) + " in " + unit.toString());
        } 
        System.out.println("END OF WORKOUT LOG");
        System.out.println();
    }

    // REQUIRES: unit != null, set != null
    // EFFECTS: returns the set information in given unit as a string
    private String getSetInformation(Set set, Unit unit) {
        if (unit == Unit.KILOGRAMS) {
            return "Weight: " + set.getWeightInKilograms() + ", Rep Count: " + set.getRepCount();
        }
        return "Weight: " + set.getWeightInPounds() + ", Rep Count: " + set.getRepCount();
    }


    // EFFECTS: allows user to view their weekly routines given the name of a weekly routine
    //          continues to ask user if they wish to view further routines
    //          if savedRoutines is empty, tells user as such
    private void viewWeeklyRoutines() {
        boolean continueViewing = true;
        if (savedRoutines.isEmpty()) {
            System.out.println("No routines to view.");
            return;
        }
        while (continueViewing) {
            String message = "Enter name of the weekly routine to view details (or type 'list' to see all routines):";
            String userInput = getWeeklyRoutineName(message);
            WeeklyRoutine viewRoutine = findRoutineByName(userInput);
            if (viewRoutine == null) {
                System.out.println("Sorry, no weekly routine with name '" + userInput + "' has been found");
                return;
            }

            displayRoutine(viewRoutine);
            System.out.println("Would you like to view another routine? (y/n)");
            userInput = input.nextLine();
            continueViewing = userInput.equals("y") ? true : false;
        }
    }

    // EFFECTS: prints out the given message and returns the name of the desired
    //          weekly routine to view from user, otherwise if user inputs list
    //          it will display a list of all weekly routine names
    private String getWeeklyRoutineName(String message) {
        System.out.println(message);
        String userInput = input.nextLine();
        if (userInput.equals("list")) {
            for (WeeklyRoutine weeklyRoutine : savedRoutines.getRoutines()) { 
                System.out.println("- " + weeklyRoutine.getName());
            }
            System.out.println("Enter name of routine: ");
            userInput = input.nextLine();
        }
        return userInput;
    }

    // EFFECTS: returns a weekly routine from savedRoutines with the given name, 
    //          otherwise returns null
    private WeeklyRoutine findRoutineByName(String name) {
        ArrayList<WeeklyRoutine> routinesList = savedRoutines.getRoutines();
        WeeklyRoutine routineByName = null;
        for (int i = 0; i < routinesList.size() && routineByName == null; i++) {
            if (routinesList.get(i).getName().equals(name)) {
                routineByName = routinesList.get(i);
            }
        }
        return routineByName;
    }

    // REQUIRES: routine != null
    // EFFECTS: displays the weekly routine information given the routine in a specified unit from user input
    private void displayRoutine(WeeklyRoutine routine) {
        System.out.println("Which unit would you like to see planned routine in (kg or lbs): ");
        String userInput = input.nextLine();
        Unit unit = userInput.equals("kg") ? Unit.KILOGRAMS : Unit.POUNDS;
        System.out.println("Information for routine '" + routine.getName() + "'");
        for (Days day: Days.values()) {
            Workout workout = routine.getWorkout(day);
            if (workout == null) {
                continue;
            }
            System.out.println("Displaying data for workout on " + day);
            ArrayList<Exercise> exercises = workout.getExercises();
            System.out.println("List of exercises and sets: ");
            for (Exercise exercise : exercises) {
                ArrayList<Set> sets = exercise.getSets();
                System.out.println("\t" + exercise.getName());
                for (Set set : sets) {
                    System.out.println(getSetInformation(set, unit));
                }
                System.out.println();
            }
        }
        System.out.println("Weekly volume for this routine is " + routine.getVolume(unit) + " in " + unit);
    }

    // EFFECTS: creates an exercise object given user input and returns it
    private Exercise createExercise() {
        Exercise exercise = null;
        Unit unit;
        int numSets;
        String userInput = null;

        System.out.println("Name of the exercise: ");
        userInput = input.nextLine();
        exercise = new Exercise(userInput);

        System.out.println("Number of sets: ");
        numSets = input.nextInt();
        input.nextLine();
        System.out.println("Which units? (lbs or kg)");
        unit = input.nextLine().equals("kg") ? Unit.KILOGRAMS : Unit.POUNDS;

        System.out.println("Please provide information for your " + numSets + " set(s): ");
        for (int i = 0; i < numSets; i++) {
            System.out.println("Storing information for set: " + (i + 1));
            Set set = createSet(unit);
            exercise.addSet(set);
            System.out.println("Set " + (i + 1) + " successfully added!");
            System.out.println();
        }
        return exercise;
    }

    // REQUIRES: unit != null
    // EFFECTS: creates a set with user input and returns it, the set is created 
    //          with the given unit
    private Set createSet(Unit unit) {
        double weight;
        int repCount;

        System.out.println("Weight for the set: ");
        weight = input.nextDouble();
        input.nextLine();

        System.out.println("Rep count for set: ");
        repCount = input.nextInt();
        input.nextLine();

        return new Set(weight, repCount, unit);
    }

    // EFFECTS: saves savedRoutines and workoutLogs to file
    private void saveLogsAndRoutines() {
        try {
            writerSavedRoutines.open();
            writerWorkoutLogs.open();
            writerSavedRoutines.write(savedRoutines);;
            writerWorkoutLogs.write(workoutLogs);
            writerSavedRoutines.close();
            writerWorkoutLogs.close();

            System.out.println("Saved your saved routines to " + JSON_STORE_SAVED_ROUTINES);
            System.out.println("Saved your workout logs to " + JSON_STORE_WORKOUTLOGS);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to files: ");
            System.out.println("\t -" + JSON_STORE_SAVED_ROUTINES);
            System.out.println("\t -" + JSON_STORE_WORKOUTLOGS);
        }
    }


    // MODIFIES: this
    // EFFECTS: load savedRoutines and workoutLogs from file
    private void loadLogsAndRoutines() {
        try {
            workoutLogs = readerWorkoutLogs.readWorkoutLogs();
            savedRoutines = readerSavedRoutines.readSavedRoutines();
            System.out.println("Loaded your previous workout logs and saved routines from: ");
            System.out.println("\t -" + JSON_STORE_SAVED_ROUTINES);
            System.out.println("\t -" + JSON_STORE_WORKOUTLOGS);
        } catch (IOException e) {
            System.out.println("Unable to read from files: ");
            System.out.println("\t -" + JSON_STORE_SAVED_ROUTINES);
            System.out.println("\t -" + JSON_STORE_WORKOUTLOGS);
        }
    }
}