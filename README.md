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
Fri Nov 29 10:38:17 PST 2024  
Workout added to workout logger at date: 2021/05/01

Fri Nov 29 10:38:17 PST 2024  
Workout added to workout logger at date: 2001/11/02

Fri Nov 29 10:38:17 PST 2024  
Workout added to workout logger at date: 2001/12/12

Fri Nov 29 10:38:17 PST 2024  
Workout added to workout logger at date: 2005/12/12

Fri Nov 29 10:38:17 PST 2024  
Workout added to workout logger at date: 2020/01/25

Fri Nov 29 10:38:23 PST 2024   
Workout removed from workout logger at date: 2021/05/01

Fri Nov 29 10:38:34 PST 2024  
Collection of Dates/Workouts filtered by dates after: 2005/12/12 returned

Fri Nov 29 10:38:35 PST 2024  
Collection of Dates/Workouts filtered by dates before: 2005/12/12 returned

Fri Nov 29 10:38:43 PST 2024  
Collection of Dates/Workouts filtered by volume (POUNDS) greater than: 35.5 returned

Fri Nov 29 10:38:44 PST 2024  
Collection of Dates/Workouts filtered by volume (POUNDS) less than: 35.5 returned

Fri Nov 29 10:38:48 PST 2024  
Collection of Dates/Workouts filtered by volume (KILOGRAMS) greater than: 35.5 returned

Fri Nov 29 10:38:48 PST 2024  
Collection of Dates/Workouts filtered by dates after: 2005/12/12 returned

Fri Nov 29 10:38:50 PST 2024  
Collection of Dates/Workouts filtered by volume (KILOGRAMS) greater than: 35.5 returned

Fri Nov 29 10:38:50 PST 2024  
Collection of Dates/Workouts filtered by dates before: 2005/12/12 returned

Fri Nov 29 10:38:55 PST 2024  
Collection of Dates/Workouts filtered by volume (POUNDS) less than: 35.5 returned

Fri Nov 29 10:39:02 PST 2024  
Collection of Dates/Workouts filtered by volume (POUNDS) greater than: 35.5 returned

Fri Nov 29 10:39:05 PST 2024  
Workout removed from workout logger at date: 2020/01/25

Fri Nov 29 10:39:19 PST 2024  
Workout added to workout logger at date: 2012/12/25
