# Workout Logger Application 
This project is a workout logging application designed to help users easily track and analyze their gym routines. The application will allow users to log their workouts, including exercises, sets, reps, and weights used. Additionally, users will have access to other useful features such as being able to create different weekly workout plans for any given week.

## Who Is This Application For
The app is tailored for **anyone** who enjoys working out—whether they’re seasoned gym-goers or beginners looking to stay organized, improve their routines, and seeking continuous improvement.

## Why I Chose This Project
I chose this project because I frequent the gym myself, but I've often found it difficult to stay consistent with logging my workouts. I believe this application will not only help me improve my own performance but also provide value to others who want to stay on top of their fitness goals. By offering detailed insights and feedback, the app will encourage users to push themselves to do better than their last workout, which will endorse continuous progress and motivation.

## User Stories
- As a user, I want to log the number of reps and the weight for each set of a specific exercise I perform, so I can accurately track and view my workout details.

- As a user, I want the ability to convert between kilograms and pounds, so I can view my workout data in my preferred unit of measurement.

- As a user, I want to create and save multiple workout plans for the week (e.g., Push-Pull-Legs or Upper-Lower splits) so that I can organize and switch up my training effectively.

- As a user, I want to be able to view the workout volume for any logged workout and also view the planned weekly workout volume for any stored weekly workout plan

- As a user, I want to be able to save my workout logs and created weekly routines to file if I choose to do so 

- As a user, I want to be able to be able to load workout logs and created weekly routines from file if choose to do so 

- As a user, I want to be able to delete logged workouts from my logger

# Instructions for End User

- You can generate the first required action related to the user story of "adding multiple X's to a Y" by selecting the tabbed pane labbelled, "Add Workout", you will then be prompted to create a logged Workout and can then add it to the Workout Logger. You will be prompted to do the following:
    1) Insert a date for the workout
    2) Insert number of exercises performed
    3) Insert name for each exercise
    4) Insert number of sets performed for each exercises
    5) Insert data for each set 

- You can generate the second required action related to the user story of "Displaying all X's that have been added to Y" by selecting the tabbed pane labelled, "Display Workout". It will take you to a new panel, to which on the left there will be a selection of dates to which you can select, and then your workout data for that date will be displayed. Additionally, there is the option to view your workout data in either Kilograms or Pounds by selectiong the radio buttons on the bottom right of the application labelled "Kilograms" and "Pounds" accordingly.

- You can generate the first related action of "Deleting a logged workout from the workout logger" by selecting on the tabbed pane labelled, "Display Workout". Select the date on the left for which workout you would like to remove from the workout logger. On the bottom left of the application there will be a button labelled, "Delete". Click on delete once you have selected the workout you would like to delete. Once done, the workout will be removed from your logger.

- You can generate the second related action of "Filtering Workouts" by selecting the tabbed pane labelled, "Filter Workouts". In this panel, there will be options to filter your worksouts by the following:
    1)  By Date 
        * Only show workouts before this date, or after this date
    2) By Workout Volume 
        * Only show workouts with lower volume, or higher volume 

- You can find the visual component by starting the application and there will be a splash screen pop up with a picture of a barbell 

- You can find another visual component in the tab labelled, "Add Workout" by doing the following 
    1) Insert a date NOT in the format, (yyyy/mm/dd) and hitting enter
    2) Insert a value for number of exercises that is NOT a valid positive integer and hitting enter
        - Upon doing so, a red "X" mark will appear to the right of the text field 
        - Conversely you can also do the following: 
    3) Insert a date CORRECTLY in the format, (yyyy/mm/dd) and hitting enter
    4) Insert a value for number of exercises that IS a valid positive integer and hitting enter
        -  Upon doing so, a green "check mark" will appear to the right of the text field

- You can save the state of the application to file by clicking "File" in the menu bar and then selecting "Save" in the drop down menu 

- You can reload the state of the application from file by clicking "File" in the menu bar and then selecting "Load" in the drop down menu 

## Phase 4: Task 2
- Workout added to workout logger at date: 2024/12/02
- Workout added to workout logger at date: 2001/11/02
- Workout added to workout logger at date: 2006/12/01
- Workout added to workout logger at date: 2004/02/12
- Workout added to workout logger at date: 2024/01/01
- Workout added to workout logger at date: 2001/12/12
- Workout removed from workout logger at date: 2024/01/01
- Workout removed from workout logger at date: 2001/11/02
- Collection of Dates/Workouts filtered by volume (POUNDS) greater than: 55.5 returned
- Collection of Dates/Workouts filtered by volume (POUNDS) less than: 55.5 returned
- Collection of Dates/Workouts filtered by dates after: 2004/12/12 returned
- Collection of Dates/Workouts filtered by dates before: 2004/12/12 returned
- Collection of Dates/Workouts filtered by volume (KILOGRAMS) greater than: 55.5 returned
- Collection of Dates/Workouts filtered by dates after: 2004/12/12 returned
- Collection of Dates/Workouts filtered by volume (KILOGRAMS) less than: 55.5 returned
- Collection of Dates/Workouts filtered by dates after: 2004/12/12 returned
- Collection of Dates/Workouts filtered by volume (POUNDS) greater than: 55.5 returned
- Collection of Dates/Workouts filtered by dates before: 2004/12/12 returned
- Collection of Dates/Workouts filtered by volume (KILOGRAMS) less than: 55.5 returned
- Collection of Dates/Workouts filtered by dates before: 2004/12/12 returned

## Phase 4: Task 3
1) Refactoring the Association Between `AddWorkoutTab`, `Exercise`, and `Workout`  

    I would refactor the association from `AddWorkoutTab` to `Exercise` and `Workout`. I would remove `Exercise` and `Workout` as data memebers and instantiate `Exercise` and `Workout` as needed in the `AddWorkoutTab` class and use them as dependencies. This change would reduce the coupling between the classes, making the design more cohesive as it would become less confusing for which repsonsibilities in `AddWorkoutTab` requires associations to the classes by just removing it entirely. Additionally, it would simplify the UML diagram, improving readability and reducing clutter.

2) Refactoring the Associations to `FitnessLoggerAppGui`  

     I would refactor the associations of `AddWorkoutTab`, `DisplayWorkoutTab`, and `FilterWorkoutTab` to `FitnessLoggerAppGui` by removing `FitnessLoggerAppGui` as a data member in the Tab classes. Instead, I would establish bidirectional associations between each of the Tab classes and `TabbedPanes`. This would result in a simpler and more intuitive structure, as the Tab classes would interact with `FitnessLoggerAppGui` through the `TabbedPanes` class. This would also reduce unnecessary associations, promoting cleaner interactions and creating an overall more simple structure in the UML, as opposed to its current "circular" structure.
3) Refactor Filtering Functions  

    In the `WorkoutLogger` class, I currently have multiple filtering functions (e.g., `filterDatesAfterDate(Set<String> dates, String dateCompare)`, `filterDatesLowerVolume(Set<String> dates, int volume, Unit unit)`), which handles filtering based on different comparison variables. I would refactor this by creating a `FilterWorkoutLogger` interface with the method to be overwritten by subclasses:  

    `Set<String> filterDates(Set<String> dates, Object[] comparisonObjects);` . 

    Subclasses would implement this interface, each providing its own filtering logic through overriding the `filterDates` method.  
     The `WorkoutLogger` class would then only need a single filtering method:  
    ```java 
    // REQUIRES: each date in dates is logged as a workout in the logger
    // EFFECTS: returns a new Set of filtered dates based on the provided dates, filter, and comparisonObjects
    public Set<String> filterDates(Set<String> dates, FilterWorkoutLogger filter, Object[] comparisonObjects) {
        return filter.fiterDates(dates, comparisonObjects);
    }
    ``` 
    This approach reduces code duplication, improves readability, and enhances modularity. Adding new filters would simply require creating a new class that implements `FilterWorkoutLogger` and creating appropiate tests classes. This would avoid modifications to `WorkoutLogger` and its tests, reducing the risk of introducing new bugs into `WorkoutLogger`.
4) Refactoring the `Set` class name  
I would rename the `Set` class to `ExerciseSet`. Currently, the name `Set` clashes with the `java.util.Set` class. Using both classes in the same file would require explicitly specifying, `java.util.Set<Object> set = ...` making the code more difficult to write. The new name better represents the class's purpose in tracking a single exercise set, reducing ambiguity and improve readability for other developers, myself included. This change would also improve the clarity of the UML diagram.