package ui.gui.components.tabs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import model.*;
import model.Set.Unit;

import java.util.Map;
import java.util.HashMap;

import ui.gui.FitnessLoggerAppGui;

// Class that handles the AddWorkoutTab part of TabbedPanes (Add X to Y function)
public class AddWorkoutTab extends JPanel implements ActionListener {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd");
    private FitnessLoggerAppGui fitnessLoggerAppGui;
    private JTextField dateField;
    private JTextField numExercisesField;
    private JTextField nameExerciseField;
    private JTextField weightField;
    private JTextField repCountField;
    private JPanel datePanel;
    private JPanel numExercisesPanel;
    private JPanel unitPanel;
    private JRadioButton kilograms;
    private JRadioButton pounds;
    private JDialog exerciseInfo;
    private JDialog setInfo;
    private ImageIcon check;
    private ImageIcon incorrect;
    private JButton confirmButton;
    private Map<JDialog, JButton> submitButtons;
    private Map<JPanel, JLabel> imageLabels;
    private JComboBox<Integer> numSetsDropdown;
    private int numSets;
    private String exerciseName; 
    private Workout workoutToAdd;
    private Exercise exerciseToAdd;

    // EFFECTS: Initializes the AddWorkoutTab with the given FitnessLoggerAppGui, 
    // sets the DATE_FORMAT to be non-lenient, ensuring strict date parsing, 
    // and calls the init() method to initialize the components of the tab.
    public AddWorkoutTab(FitnessLoggerAppGui fitnessLoggerAppGui) {
        this.fitnessLoggerAppGui = fitnessLoggerAppGui;
        DATE_FORMAT.setLenient(false);
        init();
    }

    // MODIFES: this
    // EFFECTS: Initializes the components of the AddWorkoutTab, including setting up 
    // the imageLabels and submitButtons maps, initializing the workoutToAdd object, 
    // and setting the layout. It also sets up the panels for exercises, date, number 
    // of exercises, and units, then adds the top panel to the layout.
    private void init() {
        this.imageLabels = new HashMap<>();
        this.submitButtons = new HashMap<>();
        this.workoutToAdd = new Workout("");
        initConfirmButton();
        initImageIcons();
        setLayout(new BorderLayout());
        initDatePanel();
        initNumExercisesPanel();
        initUnitPanel();
        add(createTopPanel(), BorderLayout.CENTER);
    }

    // MODIFES: this
    // EFFECTS: Retrieves all all exercise information from user and logs it 
    //          to the logger and refreshes the panel 
    private void getAllExercisesInfo() {
        for(int i = 1; i <= Integer.parseInt(numExercisesField.getText()); i++) {
            getExerciseInfo(i);
        }
        fitnessLoggerAppGui.getWorkoutLogger().addWorkout(dateField.getText(), workoutToAdd);
        fitnessLoggerAppGui.refresh();
    }

    // MODIFIES: this
    // EFFECTS: Resets the panel to original format upon openning application 
    public void refresh() {
        dateField.setText("");
        numExercisesField.setText("");
        confirmButton.setForeground(Color.BLACK);
        imageLabels.get(datePanel).setIcon(null);
        imageLabels.get(numExercisesPanel).setIcon(null);
    }

    // MODIFES: this
    // EFFECTS: Retrieves exercise info for one exercise
    private void getExerciseInfo(int i) {
        exerciseInfo = new JDialog(fitnessLoggerAppGui, true);
        exerciseInfo.setTitle("Enter Info for Exercise Number " + i);
        exerciseInfo.setLayout(new GridLayout(0, 1, 5, 5));
        setupExerciseInfoDialog(exerciseInfo, 0);
        exerciseInfo.setLocationRelativeTo(fitnessLoggerAppGui);
        exerciseInfo.setSize(500, 250);
        exerciseInfo.setVisible(true); 
    }


    // MODIFES: exerciseInfo 
    // EFFECTS: Sets up the info dialog for retrieving information on exercises 
    private void setupExerciseInfoDialog(JDialog exerciseInfo, int number) {
        numSetsDropdown = new JComboBox<Integer>();
        nameExerciseField = new JTextField(15);
        for(int i = 1; i <= 9; i++) {
            numSetsDropdown.addItem(i);
        }
        JButton submitButton = new JButton("Submit");
        JPanel panelOne = new JPanel();
        JPanel panelTwo = new JPanel();
        submitButton.addActionListener(this);
        panelOne.add(new JLabel("Enter exercise name: "));
        panelOne.add(nameExerciseField);
        panelTwo.add(new JLabel("How many sets for this exercise"));
        panelTwo.add(numSetsDropdown);
        exerciseInfo.add(panelOne);
        exerciseInfo.add(panelTwo);
        exerciseInfo.add(submitButton);
        submitButtons.put(exerciseInfo, submitButton);
    }

    // MODIFES: setInfo
    // EFFECTS: sets up info dialog for retrieving information on sets 
    private void setupSetInfoDialog(JDialog setInfo) {
        JButton submitButton = new JButton("Submit");
        weightField = new JTextField(10);
        repCountField = new JTextField(5);
        JPanel panelOne = new JPanel();
        JPanel panelTwo = new JPanel();
        submitButton.addActionListener(this);
        panelOne.add(new JLabel("Enter amount of weight (ONE DECIMAL PLACE ONLY): "));
        panelOne.add(weightField);
        panelTwo.add(new JLabel("Enter rep count: "));
        panelTwo.add(repCountField);
        setInfo.add(panelOne);
        setInfo.add(panelTwo);
        setInfo.add(submitButton);
        submitButtons.put(setInfo, submitButton);
    }


    // EFFECTS: Retrieves information for all sets about one exercise
    private void getAllSetsInfo() {
        for(int i = 1; i <= numSets; i++) {
            getSetInfo(i);
        }
    }

    // EFFECTS: Sets up dialog and pops it up for user to insert information on set
    private void getSetInfo(int i) {
        setInfo = new JDialog(fitnessLoggerAppGui, true);
        setInfo.setTitle("Enter Set Info " + i + " for exercise: " + exerciseName);
        setInfo.setLayout(new GridLayout(0, 1, 5, 5));
        setupSetInfoDialog(setInfo);
        setInfo.setLocationRelativeTo(fitnessLoggerAppGui);
        setInfo.setSize(500, 250);
        setInfo.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: Creates the top panel for the main panel with user prompts for 
    //          date, num exercises and kilogram/pounds
    private JPanel createTopPanel() {
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
        topPanel.add(datePanel);
        topPanel.add(numExercisesPanel);
        topPanel.add(unitPanel);
        topPanel.add(confirmButton);
        return topPanel;
    }

    // MODIFIES: this
    // EFFECTS: Inits the confirm button for use
    private void initConfirmButton() {
        confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(this);
        confirmButton.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: Creaates the panel for retrieving information about the date
    private void initDatePanel() {
        dateField = new JTextField(10);
        dateField.addActionListener(this);
        datePanel = new JPanel();
        datePanel.add(new JLabel("Enter Workout Date (yyyy/mm/dd):"));
        datePanel.add(dateField);

        JLabel imageLabel = new JLabel();
        imageLabels.put(datePanel, imageLabel);
        datePanel.add(imageLabel);
        imageLabel.setVisible(true);
    }

    // MODIFES: this
    // EFFECTS: Creates panel for retrieving information about the number of exercises
    private void initNumExercisesPanel() {
        numExercisesField = new JTextField(3);
        numExercisesField.addActionListener(this);
        numExercisesPanel = new JPanel();
        numExercisesPanel.add(new JLabel("Number of exercises:"));
        numExercisesPanel.add(numExercisesField);

        JLabel imageLabel = new JLabel();
        imageLabels.put(numExercisesPanel, imageLabel);
        imageLabel.setVisible(true);

        numExercisesPanel.add(imageLabel);
    }

    // MODIFIES: this
    // EFFECTS: Initializes the unit panel with radio buttons for selecting either 
    //          kilograms or pounds as the unit of measurement 
    private void initUnitPanel() {
        unitPanel = new JPanel();
        unitPanel.setLayout(new BoxLayout(unitPanel, BoxLayout.X_AXIS));

        kilograms = new JRadioButton("Kilograms");
        pounds = new JRadioButton("Pounds");
        pounds.setSelected(true);

        pounds.addActionListener(this);
        kilograms.addActionListener(this);

        ButtonGroup group = new ButtonGroup();
        group.add(kilograms);
        group.add(pounds);

        unitPanel.add(new JLabel("Select Unit: "));
        unitPanel.add(pounds);
        unitPanel.add(Box.createHorizontalStrut(12));
        unitPanel.add(kilograms);
    }

    // MODIFIES: this
    // EFFECTS: Creates image icons for check and red x
    private void initImageIcons() {
        int iconSize = 20; 
        check = scaleImageIcon(new ImageIcon(getClass().getResource("/resources/icons/Green_Check_Icon.png")), iconSize, iconSize);
        incorrect = scaleImageIcon(new ImageIcon(getClass().getResource("/resources/icons/Red_X_Icon.png")), iconSize, iconSize);
    }
    
    // EFFECTS: given icon, and dimension, scales down the image and the returns it
    private ImageIcon scaleImageIcon(ImageIcon icon, int width, int height) {
        Image img = icon.getImage(); 
        Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImg); 
    }

    // EFFECTS: given date checks if its valid (yyyy/mm/dd)
    //          returns true if valid, false otherwise
    private boolean isValidDate(String date) {
        if(date == null) {
            return false;
        }
        try {
            DATE_FORMAT.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    // EFFECTS: returns true of the num string is valid number
    private boolean isValidNum(String num) {
        if(num == null || num.length() != 1 || !Character.isDigit(num.charAt(0)))  {
            return false;
        }
        return true;
    }
    

    @Override
    // EFFECTS: Given the ActionEvent e, does the following:
    //  if source comes from dateField:
    //      handles the input for the date field
    //  if source comes from numExercisesField:
    //      handles the input for the number of exercises field
    //  if source comes from confirmButton:
    //      processes the confirmation button action
    //  if source comes from submit button in exerciseInfo dialog:
    //      handles the submission of exercise information
    //  if source comes from submit button in setInfo dialog:
    //      handles the submission of set information
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == dateField) {
            handleDateField();
        } else if (source == numExercisesField) {
            handleNumExercisesField();
        } else if(source == confirmButton) {
            handleConfirmButton();
        } else if(source == submitButtons.get(exerciseInfo)) {
            handleSubmitButtonExerciseInfo();
        } else if(source == submitButtons.get(setInfo)) {
            handleSubmitButtonSetInfo();
        }
    }

    //  MODIFIES: this
    //  EFFECTS: Given the action from the submit button in the setInfo dialog, does the following:
    //  Creates a new Set object with the parsed values adds it to exercis and closes the setInfo dialog.
    private void handleSubmitButtonSetInfo() {
        double weight = Double.parseDouble(weightField.getText());
        int repCount = Integer.parseInt(repCountField.getText());
        Unit unit = kilograms.isSelected() ? Unit.KILOGRAMS : Unit.POUNDS;
        Set set = new Set(weight, repCount, unit);
        exerciseToAdd.addSet(set);
        setInfo.dispose();
    }

    // MODIFIES: this
    // EFFECTS: given the date field from textField, if it is valid
    //          pop up check icon, pop up incorrect icon otherwise
    private void handleDateField() {
            String date = dateField.getText();
            Boolean isValid = isValidDate(date);
            if(isValid) {
                imageLabels.get(datePanel).setIcon(check);  
            } else {
                imageLabels.get(datePanel).setIcon(incorrect);
            }
    }

    // MODIFIES: this
    // EFFECTS: given the numExercises field from textField, if it is valid
    //          pop up check icon, pop up incorrect icon otherwise
    private void handleNumExercisesField() {
            String num = numExercisesField.getText();
            Boolean isValid = isValidNum(num);
            if(isValid) {
                imageLabels.get(numExercisesPanel).setIcon(check);
            } else {
                imageLabels.get(numExercisesPanel).setIcon(incorrect);
            }
    }

    // MODIFES: this
    // EFFECTS: validates the date and number of exercises, creates a new Workout if valid, 
    // and updates the confirm button's color accordingly.
    private void handleConfirmButton() {
        String date = dateField.getText();
        String num = numExercisesField.getText();
        if(isValidNum(num) && isValidDate(date)) {
            workoutToAdd = new Workout("");
            confirmButton.setForeground(Color.GREEN);
            getAllExercisesInfo();
        } else {
            confirmButton.setForeground(Color.RED);
        }
    }

    // EFFECTS: Retrieves the exercise name, creates a new Exercise, adds it to the workout, 
    // retrieves the number of sets, and then opens dialogs for the sets.
    private void handleSubmitButtonExerciseInfo() {
        exerciseName = nameExerciseField.getText();
        exerciseToAdd = new Exercise(exerciseName);
        workoutToAdd.addExercise(exerciseToAdd);
        numSets = (Integer) numSetsDropdown.getSelectedItem();
        exerciseInfo.dispose();
        getAllSetsInfo();
    }

}