package ui;

import model.*;

import java.util.*;

import java.util.Scanner;

public class Main {

    public void testScanner() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next(null);
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

    public static void main(String[] args) throws Exception {
        Main main = new Main();

        //main.makeWeeklyRoutine();

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
