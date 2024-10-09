package ui;

import model.Set.Unit;

import model.*;
import java.util.ArrayList;
import java.util.Scanner;

/* SOURCE CREDITS
* Heavy Influence from TellerApp.java through out the code
* Source: https://github.students.cs.ubc.ca/CPSC210/TellerApp.git
*/

// UI application where a user can store log workouts, create workout plans, and as well as view/delete these 
// workouts as needed
public class FitnessLoggerApp {
    WorkoutLogger workoutLogs;
    SavedRoutines savedRoutines;
    Scanner input;
    boolean running;

    public void run() {
        init();
        while (running) {
            String userInput = null;
            displayMenu();
            userInput = input.nextLine();
            processUserInput(userInput);
        }
    }

    private void init() {
        this.running = true;
        this.input = new Scanner(System.in);
        this.workoutLogs = new WorkoutLogger();
        this.savedRoutines = new SavedRoutines();
    }

    private void displayMenu() {
        System.out.println("Select from following:");
        System.out.println("\tp -> Print logged Workout");
        System.out.println("\tl -> Log Workout");
        System.out.println("\tr -> Removed Logged Workout");
        System.out.println("\tc -> Create Weekly Routine");
        System.out.println("\tv -> View Weekly Routine");
        System.out.println("\td -> Delete Weekly Routine");
        System.out.println("\tq (or any key except for those above) -> quit");
        System.out.println();
    }

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
            case "d":
                deleteWeeklyRoutine();
                break;
            default:
                running = false;
                break;
        }
    }

    private void printLoggedWorkout() {
        if (workoutLogs.getNumWorkoutsLogged() == 0) {
            System.out.println("No logged workouts to display");
            return;
        }

        System.out.println("Which date would you like to view your logged workout (dd/mm/year): ");
        String date = input.nextLine();

        Workout workout = workoutLogs.getWorkout(date);
        if (workout == null) {
            System.out.println("No workout logged for this date.");
            return;
        }

        System.out.println("Which units would you like to view your workout in (lbs or kg): ");
        Unit unit = input.nextLine().equals("kg") ? Unit.KILOGRAMS : Unit.POUNDS;
        printWorkoutInformation(workout, unit);
    }

    private void logWorkout() {
        System.out.println("Which date do you wish to log your workout for (dd/mm/year): ");
        String date = input.nextLine();

        Workout workout = createWorkout();

        workoutLogs.addWorkout(date, workout);
        System.out.println("Workout successfully logged for " + date);
    }

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

    private String getSetInformation(Set set, Unit unit) {
        if (unit == Unit.KILOGRAMS) {
            return "Weight: " + set.getWeightInKilograms() + ", Rep Count: " + set.getRepCount();
        }
        return "Weight: " + set.getWeightInPounds() + ", Rep Count: " + set.getRepCount();
    }


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

    private void displayRoutine(WeeklyRoutine routine) {
        System.out.println("Which unit would you like to planned routine in (kg or lbs): ");
        String userInput = input.nextLine();
        Unit unit = userInput.equals("kg") ? Unit.KILOGRAMS : Unit.POUNDS;
        System.out.println("Information for routine " + routine.getName());

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
    }

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
}