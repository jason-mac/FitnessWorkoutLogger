package ui.gui.components.tabs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

import java.util.List;

import model.*;
import model.Set.Unit;
import ui.gui.FitnessLoggerAppGui;

// Class that handles the feature of filtering the workouts given parameters of date and workout volume
public class FilterWorkoutTab extends JPanel implements ActionListener {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd");
    private FitnessLoggerAppGui fitnessLoggerAppGui;
    private JTextField dateField;
    private JTextField volumeField;
    private JRadioButton beforeDate;
    private JRadioButton afterDate;
    private JRadioButton noDateFilter;
    private JRadioButton higherVolume;
    private JRadioButton lowerVolume;
    private JRadioButton noVolumeFilter;
    private JRadioButton kilograms;
    private JRadioButton pounds;
    private JButton filterButton;
    private JPanel workoutTablePanel;
    private JTable workoutTable;
    private WorkoutTableModel workoutTableModel;
    private JScrollPane workoutScrollPane;

    // EFFECTS: constructor for filterWorkoutTab
    public FilterWorkoutTab(FitnessLoggerAppGui fitnessLoggerAppGui) {
        this.fitnessLoggerAppGui = fitnessLoggerAppGui;
        DATE_FORMAT.setLenient(false);
        setLayout(new BorderLayout(5, 10));
        initTextFields();
        initFilterRadioButtons();
        initUnitRadioButtons();
        initFilterButton();
        initWorkoutTableModel();
        initWorkoutTable();
        initScrollPane();
        initTablePanel();

        add(createFilterPanel(), BorderLayout.NORTH);
        add(workoutTablePanel, BorderLayout.SOUTH);
    }

    // MODIFIES: this
    private void initWorkoutTableModel() {
        workoutTableModel = new WorkoutTableModel();
    }

    // MODIFIES: this
    // EFFECTS: creaets a workout table for displaying the workout data
    private void initWorkoutTable() {
        workoutTable = new JTable(workoutTableModel);
        workoutTable.setLayout(new BoxLayout(workoutTable, BoxLayout.Y_AXIS));
    }

    // MODIFIES: this
    // EFFECTS: creates JScrollPane with workout table
    private void initScrollPane() {
        workoutScrollPane = new JScrollPane(workoutTable);
    }

    // MODIFES: this
    // EFFECTS: creates a jpanel with the scroll pane and label
    private void initTablePanel() {
        workoutTablePanel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("All Workout Data with Filter Applied");
        label.setFont(new Font("Times New Roman", Font.BOLD, 15));
        workoutTablePanel.add(label, BorderLayout.NORTH);
        workoutTablePanel.add(workoutScrollPane);
    }

    // MODIFIES: this
    // EFFECTS: creates JButton for filtering the data
    private void initFilterButton() {
        filterButton = new JButton("Filter");
        filterButton.addActionListener(this);
    }

    // MODIFIES: this
    // EFFECTS: instantiates text fields for the user to enter data
    private void initTextFields() {
        dateField = new JTextField(10);
        volumeField = new JTextField(10);
    }

    // MODIFIES: this
    // EFFECTS: creates filter radio buttons fot each option and groups them in
    // button groups
    private void initFilterRadioButtons() {
        beforeDate = new JRadioButton("Before Date");
        afterDate = new JRadioButton("After Date");
        noDateFilter = new JRadioButton("No Date Filter");
        lowerVolume = new JRadioButton("Less Than Volume");
        higherVolume = new JRadioButton("Higher Than Volume");
        noVolumeFilter = new JRadioButton("No Volume Filter");

        beforeDate.addActionListener(this);
        afterDate.addActionListener(this);
        lowerVolume.addActionListener(this);
        higherVolume.addActionListener(this);
        noDateFilter.addActionListener(this);
        noVolumeFilter.addActionListener(this);

        ButtonGroup buttonGroupDate = new ButtonGroup();
        ButtonGroup buttonGroupVolume = new ButtonGroup();

        buttonGroupDate.add(beforeDate);
        buttonGroupDate.add(afterDate);
        buttonGroupDate.add(noDateFilter);
        buttonGroupVolume.add(lowerVolume);
        buttonGroupVolume.add(higherVolume);
        buttonGroupVolume.add(noVolumeFilter);
    }

    // MODIFES: this
    // EFFECTS: Radio buttons are instantiated for user to select pounds or
    // kilograms
    private void initUnitRadioButtons() {
        pounds = new JRadioButton("Pounds");
        kilograms = new JRadioButton("Kilograms");

        pounds.addActionListener(this);
        kilograms.addActionListener(this);

        ButtonGroup buttonGroupUnit = new ButtonGroup();

        buttonGroupUnit.add(pounds);
        buttonGroupUnit.add(kilograms);
    }

    // MODIFIES: this
    // EFFECTS: creates the main filtering panel for user to interact with at the
    // top
    private JPanel createFilterPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel datePanel = createDatePanelForFilterPanel();
        JPanel volumePanel = createVolumePanelForFilterPanel();
        JPanel unitPanel = createUnitPanelForFilterPanel();

        panel.add(datePanel);
        panel.add(volumePanel);
        panel.add(unitPanel);
        panel.add(filterButton);

        noVolumeFilter.setSelected(true);
        noDateFilter.setSelected(true);
        pounds.setSelected(true);

        return panel;
    }

    // EFFECTS: creates a panel to allow user to select between kilograms and pounds
    //  returns the panel after creating it
    private JPanel createUnitPanelForFilterPanel() {
        JPanel unitPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        JLabel unitLabel = new JLabel("Select Unit: ");
        unitPanel.add(unitLabel);
        unitPanel.add(pounds);
        unitPanel.add(kilograms);
        return unitPanel;
    }

    // MODIFIES: this
    // EFFECTS: creates the panel allowing user to filter the workouts based on a given date
    private JPanel createDatePanelForFilterPanel() {
        JPanel datePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
        JLabel dateLabel = new JLabel("Filter by Date (yyyy/mm/dd): ");
        datePanel.add(dateLabel);
        datePanel.add(dateField);
        datePanel.add(beforeDate);
        datePanel.add(afterDate);
        datePanel.add(noDateFilter);
        return datePanel;
    }

    // EFFECTS: creates a panel to allow user to filter workouts based
    // on workout volume, returns the panel
    private JPanel createVolumePanelForFilterPanel() {
        JPanel volumPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
        JLabel volumeLabel = new JLabel("Filter by Workout Volume (one decimal place): ");
        volumPanel.add(volumeLabel);
        volumPanel.add(volumeField);
        volumPanel.add(lowerVolume);
        volumPanel.add(higherVolume);
        volumPanel.add(noVolumeFilter);
        return volumPanel;
    }

    // EFFECTS: handles the user clicing the filter button depending on entered
    // textfields and buttons chosen
    private void handleFilterButtonAction() {
        if (noDateFilter.isSelected() && noVolumeFilter.isSelected()) {
            workoutTableModel.updateJTable(fitnessLoggerAppGui.getWorkoutLogger().getDates());
        } else if (!isValidDouble(volumeField.getText()) || !isValidDate(dateField.getText())) {
            JOptionPane.showMessageDialog(this, "Please enter valid entries for Volume and Date.");
        } else {
            filterList();
        }
    }

    // EFFECTS; checks if date has valid format
    //  returns true if so, false otherwise
    public boolean isValidDate(String date) {
        try {
            DATE_FORMAT.parse(date);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // MODIFES: this
    // EFFECTS: refreshes all panels, resets textfields and radiobuttons 
    public void refresh() {
        noVolumeFilter.setSelected(true);
        pounds.setSelected(true);
        dateField.setText("");
        volumeField.setText("");
        workoutTableModel.updateJTable(fitnessLoggerAppGui.getWorkoutLogger().getDates());
    }

    // EFFECTS: filters the dates from workoutLogger from given user parameterss
    // then updates the table displaying the workouts 
    private void filterList() {
        WorkoutLogger workoutLogger = fitnessLoggerAppGui.getWorkoutLogger();
        java.util.Set<String> dates = workoutLogger.getDates();
        double compare = Double.parseDouble(volumeField.getText());
        Unit unit = kilograms.isSelected() ? Unit.KILOGRAMS : Unit.POUNDS;
        if (lowerVolume.isSelected()) {
            dates = workoutLogger.filterDatesLowerVolume(dates, compare, unit);
        }
        if (higherVolume.isSelected()) {
            dates = workoutLogger.filterDatesHigherVolume(dates, compare, unit);
        }
        if (beforeDate.isSelected()) {
            dates = workoutLogger.filterDatesBeforeDate(dates, dateField.getText());
        }
        if (afterDate.isSelected()) {
            dates = workoutLogger.filterDatesAfterDate(dates, dateField.getText());
        }
        workoutTableModel.updateJTable(dates);
    }

    // EFFECTS: checks if input as a string can be converted into a valid double
    // returns true if so, false otherwise
    private boolean isValidDouble(String input) {
        try {
            Double.parseDouble(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    // EFFECTS: handles filterButton click event
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == filterButton) {
            handleFilterButtonAction();
        }
    }

    // Class specifically for the JList to display a table
    class WorkoutTableModel extends AbstractTableModel {
        private String[] columnNames = {
                "Date",
                "Exercise",
                "Set",
                "Weight",
                "Rep Count"
        };

        private List<List<String>> data;

        public WorkoutTableModel() {
            data = new ArrayList<>();
        }

        // MODIFIES: this
        // EFFECTS: Clears the current table data and updates it
        // If workout null no changes made
        // If changes are made, table is refreshed
        public void updateJTable(java.util.Set<String> datesSet) {
            WorkoutLogger workoutLogger = fitnessLoggerAppGui.getWorkoutLogger();
            data.clear();
            if (workoutLogger.getNumWorkoutsLogged() == 0) {
                fireTableDataChanged();
                return;
            }
            List<String> dates = new ArrayList<String>(datesSet);
            Collections.sort(dates);
            for (String date : dates) {
                Workout workout = workoutLogger.getWorkout(date);
                addDateAndWorkoutToTable(date, workout);
                data.add(workoutVolumeRow(workout));
                data.add(getEmptyRow());
            }
            fireTableDataChanged();
        }

        // MODIFES: this
        // EFFECTS: adds the date and workout into the table for display
        public void addDateAndWorkoutToTable(String date, Workout workout) {
            List<String> row = new ArrayList<>();
            row.add(date);
            for (Exercise exercise : workout.getExercises()) {
                addExerciseToTable(date, exercise, row);
                row = new ArrayList<>();
            }
        }

        // MODIFES: this
        // EFFECTS: adds the exercise data into the table
        public void addExerciseToTable(String date, Exercise exercise, List<String> row) {
            int rowValue = 1;
            for (Set set : exercise.getSets()) {
                addRowDataForSet(set, rowValue, row, exercise.getName());
                data.add(row);
                rowValue++;
                row = new ArrayList<>();
            }
        }

        // EFFECTS: get the data for the set into a List and return it
        private void addRowDataForSet(Set set, int rowValue, List<String> rowDataForSet, String name) {
            if (rowValue == 1 && rowDataForSet.size() == 1) {
                rowDataForSet.add(name);
            } else if (rowValue == 1 && rowDataForSet.size() == 0) {
                rowDataForSet.add("");
                rowDataForSet.add(name);
            } else {
                rowDataForSet.add("");
                rowDataForSet.add("");
            }
            rowDataForSet.add(Integer.toString(rowValue));
            if (kilograms.isSelected()) {
                rowDataForSet.add(Double.toString(set.getWeightInKilograms()));
            } else {
                rowDataForSet.add(Double.toString(set.getWeightInPounds()));
            }
            rowDataForSet.add(Integer.toString(set.getRepCount()));
        }

        // EFFECTS: creates a list holding the workout volume info
        // returns the list
        public List<String> workoutVolumeRow(Workout workout) {
            List<String> workoutVolumeRow = new ArrayList<>();
            Unit unit = kilograms.isSelected() ? Unit.KILOGRAMS : Unit.POUNDS;
            workoutVolumeRow.add("Total Workout Volume: ");
            workoutVolumeRow.add(String.valueOf(workout.getVolume(unit)));
            workoutVolumeRow.add("");
            workoutVolumeRow.add("");
            workoutVolumeRow.add("");
            return workoutVolumeRow;
        }

        // EFFECTS: returns empty row for spacing
        public List<String> getEmptyRow() {
            List<String> empty = new ArrayList<>();
            empty.add(".........................................................");
            empty.add(".........................................................");
            empty.add(".........................................................");
            empty.add(".........................................................");
            empty.add(".........................................................");
            return empty;
        }

        @Override
        public int getRowCount() {
            return data.size();
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public String getColumnName(int columnIndex) {
            return columnNames[columnIndex];
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return data.get(rowIndex).get(columnIndex);
        }
    }
}
