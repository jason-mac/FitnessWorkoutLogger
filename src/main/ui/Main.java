package ui;

import model.*;

import java.util.*;

import java.util.Scanner;

public class Main {

    public void testScanner() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("yo what u trynna do");
        String input = scanner.nextLine();
        System.out.println("i am doing: " + input);
    }

    // Functions expressing the functionality 
    public void logWorkout(WorkoutLog log, Workout workout) {
        log.logWorkout(workout);
    }
        // adding a workout to a weekly routine example code/functionaltiy/idea
    public void addWorkoutToWeeklyRoutine(WeeklyRoutine weeklyRoutine, Day day) {
        // create a weekly routein called push pull legs

        // create a workout for push pull legs
        Workout workout = new Workout();

        // specify the day from user

        // create exercsises for the workout
        Exercise latPullDown = new Exercise("Lat Pulldown");
        Exercise  barbellRow = new Exercise("Barbell Row");

        // add the exercises to the workout plan
        workout.addExercise(latPullDown);
        workout.addExercise(barbellRow);

        // add to the weekly routine with specifed day
        weeklyRoutine.setWorkoutForDay(workout, day);

        // display the workout for given day
        weeklyRoutine.getWorkoutForDay(day).display();
    }

    public void mainMenu() {
        String input = null;
        Scanner scanner = new Scanner(System.in);

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
    }

    public Day getDay() {
        String input;
        Day toReturn = null;
        Scanner scanner = new Scanner(System.in);
        System.out.println("What Day?");
        System.out.println("Monday (0)");
        System.out.println("Tuesday (1)");
        System.out.println("Wednesday (2)");
        input = scanner.nextLine();
        if(input.equals("0")) {
            toReturn = Day.MONDAY;
        }

        if(input.equals("1")) {
            toReturn = Day.TUESDAY;
        }
        
        if(input.equals("2")) {
            toReturn = Day.WEDNESDAY;
        }

        scanner.close();
        return toReturn;
    }

    public static void main(String[] args) throws Exception {
        Main main = new Main();

        main.testScanner();
        Day myDay = main.getDay();

        // user should be able to make as many weekly routines as they like stored in some collection
        ArrayList<WeeklyRoutine> weeklyRoutines = new ArrayList<>();

        // user should be able make a new weekly routine
        WeeklyRoutine pushPullLegs = new WeeklyRoutine();
        WeeklyRoutine powerLiftingBenchFocus = new WeeklyRoutine();

        Day monday = Day.MONDAY;
        Day tuesday = Day.TUESDAY;
        Day wednesday = Day.WEDNESDAY;
        Day thursday = Day.THURSDAY;
        Day friday = Day.FRIDAY;

        // user should be able to add workouts to their weekly routines
        main.addWorkoutToWeeklyRoutine(pushPullLegs, monday);
        main.addWorkoutToWeeklyRoutine(pushPullLegs, tuesday);
        main.addWorkoutToWeeklyRoutine(pushPullLegs, wednesday);
        main.addWorkoutToWeeklyRoutine(pushPullLegs, thursday);
        main.addWorkoutToWeeklyRoutine(pushPullLegs, friday);

        main.addWorkoutToWeeklyRoutine(powerLiftingBenchFocus, monday);
        main.addWorkoutToWeeklyRoutine(powerLiftingBenchFocus, tuesday);
        main.addWorkoutToWeeklyRoutine(powerLiftingBenchFocus, wednesday);
        main.addWorkoutToWeeklyRoutine(powerLiftingBenchFocus, thursday);
        main.addWorkoutToWeeklyRoutine(powerLiftingBenchFocus, friday);

        // user should be able to add workouts to their weekly routines
        weeklyRoutines.add(pushPullLegs);
        weeklyRoutines.add(powerLiftingBenchFocus);

        // display all the weeklyroutines the user has made
        for(WeeklyRoutine weeklyRoutine : weeklyRoutines) {
            weeklyRoutine.display();
        }

        FitnessTrackerApp newMain = new FitnessTrackerApp();
        newMain.fakeMain();;
    }
}
