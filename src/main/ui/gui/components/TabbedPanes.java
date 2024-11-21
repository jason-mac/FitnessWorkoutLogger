package ui.gui.components;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;


import java.awt.Font;
import ui.gui.FitnessLoggerAppGui;
import ui.gui.components.tabs.*;

// Class that handles the JTabbedPanes component of the main gui
public class TabbedPanes extends JTabbedPane {
    protected FitnessLoggerAppGui fitnessLoggerAppGui;
    protected AddWorkoutTab addWorkoutTab;
    protected DisplayWorkoutTab displayWorkoutTab;
    protected FilterWorkoutTab filterWorkoutTab;

    // EFFECTS: Initilizes data members and sets up tabbed panes 
    //          with tabs for adding, displaying, and filtering workouts
    public TabbedPanes (FitnessLoggerAppGui fitnessLoggerAppGui) {
        this.fitnessLoggerAppGui = fitnessLoggerAppGui;
        initPanels();
        initTabs();
    }

    // EFFECTS: Initializes the panels data members for each tab
    private void initPanels() {
        addWorkoutTab = new AddWorkoutTab();
        displayWorkoutTab = new DisplayWorkoutTab();
        filterWorkoutTab = new FilterWorkoutTab();
    }

    // MODIFES: this
    // EFFECTS: adds the tabs to this  
    private void initTabs() {
        addTab("Add Workout", null, addWorkoutTab, "Add a workout to your logger");
        addTab("Display Workout", null, displayWorkoutTab, "Delete a workout");
        addTab("Filter Workout", null, filterWorkoutTab, "View your workouts with filering");
    } 
}
