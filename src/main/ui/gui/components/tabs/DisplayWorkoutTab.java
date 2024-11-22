package ui.gui.components.tabs;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;

import java.util.List;

import model.*;
import model.Set.Unit;
import ui.gui.FitnessLoggerAppGui;

// Class that handles the displaying workouts in a JPanel 
// Includes other functionality such as deleting workouts
public class DisplayWorkoutTab extends JPanel implements ActionListener, ListSelectionListener {
    private FitnessLoggerAppGui fitnessLoggerAppGui;
    private JList<String> list;
    private DefaultListModel<String> listModel;
    private JScrollPane scrollPaneDates;
    private JScrollPane scrollPaneWorkout;
    private JPanel buttonPane;
    private JPanel workoutTablePanel;
    private JPanel datesTablePanel;
    private JButton deleteButton;
    private JRadioButton kilograms;
    private JRadioButton pounds;
    private JTable workoutTable;
    private WorkoutTableModel workoutTableModel;

    // EFFECTS: Initializes variables for use in gui
    public DisplayWorkoutTab(FitnessLoggerAppGui fitnessLoggerAppGui) {
        this.fitnessLoggerAppGui = fitnessLoggerAppGui;
        setLayout(new BorderLayout());
        initList();
        initButtonPane();
        initSetsTableModel();
        initWorkoutDisplayPanel();
        initScrollPane();
        initTablePanel();

        add(datesTablePanel, BorderLayout.WEST);
        add(buttonPane, BorderLayout.SOUTH);
        add(workoutTablePanel, BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: Initializes the workout and dates table panels, sets their layout, 
    //          adds labels and scroll panes for displaying workout and date data
    private void initTablePanel() {
        workoutTablePanel = new JPanel(new BorderLayout());
        datesTablePanel = new JPanel(new BorderLayout());

        JLabel labelOne = new JLabel("Workout Data");
        JLabel labelTwo = new JLabel("Dates");

        labelOne.setFont(new Font("Times New Roman", Font.BOLD, 15));
        labelTwo.setFont(new Font("Times New Roman", Font.BOLD, 15));

        workoutTablePanel.add(labelOne, BorderLayout.NORTH);
        datesTablePanel.add(labelTwo, BorderLayout.NORTH);

        workoutTablePanel.add(scrollPaneWorkout, BorderLayout.CENTER);
        datesTablePanel.add(scrollPaneDates, BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: Initializes the workout table model to manage the workout data display
    void initSetsTableModel() {
        workoutTableModel = new WorkoutTableModel();
    }

    // MODIFIES: this
    // EFFECTS: Initializes the workout display panel by setting up a JTable with the workout table model
    //          and configures the layout to a vertical box layout
    private void initWorkoutDisplayPanel() {
        workoutTable = new JTable(workoutTableModel);
        workoutTable.setLayout(new BoxLayout(workoutTable, BoxLayout.Y_AXIS));
    }

    // MODIFIES: this
    // EFFECTS: Initializes the button pane by creating and setting up the delete button
    private void initButtonPane() {
        JPanel rightPanel = createRightPanel();
        buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
        buttonPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        deleteButton = new JButton("Delete");
        deleteButton.addActionListener(this);
        buttonPane.add(deleteButton);
        buttonPane.add(Box.createHorizontalGlue());
        buttonPane.add(rightPanel);
    }

    // MODIFIES: this
    // EFFECTS: Creates and returns a right panel containing radio buttons for selecting between Kilograms and Pounds
    private JPanel createRightPanel() {
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.X_AXIS));

        kilograms = new JRadioButton("Kilograms");
        pounds = new JRadioButton("Pounds");
        pounds.setSelected(true);

        pounds.addActionListener(this);
        kilograms.addActionListener(this);

        ButtonGroup group = new ButtonGroup();
        group.add(kilograms);
        group.add(pounds);

        rightPanel.add(pounds);
        rightPanel.add(Box.createHorizontalStrut(20));
        rightPanel.add(kilograms);

        return rightPanel;
    }

    // MODIFIES: this
    // EFFECTS: Initializes the scroll panes for displaying dates and workout data, 
    //          sets their names, and associates them with the appropriate components (list and workout table)
    private void initScrollPane() {
        scrollPaneDates = new JScrollPane(list);
        scrollPaneWorkout = new JScrollPane(workoutTable);
        scrollPaneDates.setName("Dates");
        scrollPaneWorkout.setName("Workout Data");
    }


    // MODIFIES: this
    // EFFECTS: Initializes the list by creating a DefaultListModel, 
    //          setting it to the JList, and adding a list selection listener for user interaction
    private void initList() {
        listModel = new DefaultListModel<String>();
        list = new JList<String>(listModel);
        list.addListSelectionListener(this);
    }

    // MODIFIES: this
    // EFFECTS: Refreshes the list of workout dates by retrieving the dates from the workout logger,
    //          sorting them, and adding them to the list model for display
    public void refresh() {
        WorkoutLogger workoutLogger = fitnessLoggerAppGui.getWorkoutLogger();
        ArrayList<String> datesList = new ArrayList<>(workoutLogger.getDates());
        Collections.sort(datesList);
        listModel.clear();
        for (String date : datesList) {
            listModel.addElement(date);
        }
    }

    // MODIFIES: this
    // EFFECTS: Updates the workout panel by retrieving the workout for the given date from the workout logger 
    //          and updating the workout table model to reflect the new data
    private void updateWorkoutPanel(String date) {
        Workout workout = fitnessLoggerAppGui.getWorkoutLogger().getWorkout(date);
        workoutTableModel.updateJTable(workout);
    }

    // MODIFIES: this
    // EFFECTS: Given the current selection in the list, removes all current content, 
    //          revalidates and repaints, and then updates the workout panel based on the selected value
    private void updateWorkoutPanel() {
        String selectedValue = list.getSelectedValue();
        workoutTable.removeAll();
        workoutTable.revalidate();
        workoutTable.repaint();
        updateWorkoutPanel(selectedValue);
    }


    @Override
    // EFFECTS: Given the ActionEvent e, does the following:
    //  if source comes from deleteButton:
    //      removes the selected workout date from the list and workout logger
    //  if source comes from kilograms:
    //      updates the workout panel with kilograms selected
    //  if source comes from pounds:
    //      updates the workout panel with pounds selected
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == deleteButton) {
            String dateToRemove = list.getSelectedValue();
            listModel.removeElement(dateToRemove);
            fitnessLoggerAppGui.removeWorkoutAtDate(dateToRemove);
        } else if (source == kilograms) {
            updateWorkoutPanel();
        } else if (source == pounds) {
            updateWorkoutPanel();
        }
    }

    @Override
    // EFFECTS: Given the ListSelectionEvent e, does the following:
    //  updates the workout panel based on the current selection in the list
    public void valueChanged(ListSelectionEvent e) {
        updateWorkoutPanel();
    }

    // Class specifically for the JList to display a table
    class WorkoutTableModel extends AbstractTableModel {
        private String[] columnNames = {
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
        //          If workout null no changes made 
        //          If changes are made, table is refreshed 
        public void updateJTable(Workout workout) {
            data.clear();
            if (workout == null) {
                return;
            }

            for (Exercise exercise : workout.getExercises()) {
                addExerciseToTable(exercise);
                data.add(getEmptyRow());
            }
            data.add(workoutVolumeRow(workout));
            fireTableDataChanged();
        }

        public List<String> workoutVolumeRow(Workout workout) {
            List<String> workoutVolumeRow = new ArrayList<>();
            Unit unit = kilograms.isSelected() ? Unit.KILOGRAMS : Unit.POUNDS;
            workoutVolumeRow.add("Total Workout Volume: ");
            workoutVolumeRow.add(String.valueOf(workout.getVolume(unit)));
            workoutVolumeRow.add("");
            workoutVolumeRow.add("");
            return workoutVolumeRow;
        }

        // EFFECTS: returns empty row for spacing
        public List<String> getEmptyRow() {
            List<String> empty = new ArrayList<>();
            empty.add("");
            empty.add("");
            empty.add("");
            empty.add("");
            return empty;
        }

        // MODIFES: this
        // EFFECTS: adds the exercise data into the table
        public void addExerciseToTable(Exercise exercise) {
            int rowValue = 1;
            for (Set set : exercise.getSets()) {
                List<String> row = getRowDataForSet(set, rowValue, exercise.getName());
                data.add(row);
                rowValue++;
            }
        }

        // EFFECTS: get the data for the set into a List and return it
        private List<String> getRowDataForSet(Set set, int rowValue, String name) {
            List<String> rowDataForSet = new ArrayList<>();
            if (rowValue == 1) {
                rowDataForSet.add(name);
            } else {
                rowDataForSet.add("");
            }
            rowDataForSet.add(Integer.toString(rowValue));
            if (kilograms.isSelected()) {
                rowDataForSet.add(Double.toString(set.getWeightInKilograms()));
            } else {
                rowDataForSet.add(Double.toString(set.getWeightInPounds()));
            }
            rowDataForSet.add(Integer.toString(set.getRepCount()));
            return rowDataForSet;
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