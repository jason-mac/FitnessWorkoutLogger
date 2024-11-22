package ui.gui.components;

import javax.swing.JTabbedPane;

import ui.gui.FitnessLoggerAppGui;
import ui.gui.components.tabs.*;

// Class that handles the JTabbedPanes component of the main gui frame
public class TabbedPanes extends JTabbedPane {
    protected FitnessLoggerAppGui fitnessLoggerAppGui;
    protected AddWorkoutTab addWorkoutTab;
    protected DisplayWorkoutTab displayWorkoutTab;
    protected FilterWorkoutTab filterWorkoutTab;

    // EFFECTS: Initilizes data members and sets up tabbed panes
    // with tabs for adding, displaying, and filtering workouts
    public TabbedPanes(FitnessLoggerAppGui fitnessLoggerAppGui) {
        this.fitnessLoggerAppGui = fitnessLoggerAppGui;
        initPanels();
        initTabs();
    }

    // EFFECTS: Initializes the panels data members for each tab
    private void initPanels() {
        addWorkoutTab = new AddWorkoutTab(fitnessLoggerAppGui);
        displayWorkoutTab = new DisplayWorkoutTab(fitnessLoggerAppGui);
        filterWorkoutTab = new FilterWorkoutTab(fitnessLoggerAppGui);
    }

    // MODIFES: this
    // EFFECTS: adds the tabs to this
    private void initTabs() {
        addTab("Add Workout", null, addWorkoutTab, "Add a workout to your logger");
        addTab("Display Workout", null, displayWorkoutTab, "Display your workouts");
        addTab("Filter Workout", null, filterWorkoutTab, "View your workouts with filering");
    }

    // MODIFIES: this
    // EFFECTS: refreshes all of tabs panels 
    public void refreshTabs() {
        displayWorkoutTab.refresh();
        addWorkoutTab.refresh();
        filterWorkoutTab.refresh();
    }
}
