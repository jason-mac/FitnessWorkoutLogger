package ui.gui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.*;

import model.EventLog;
import model.WorkoutLogger;
import persistence.DataPersistence;
import ui.gui.components.*;
import ui.printer.ConsolePrinter;
import ui.printer.LogPrinter;

/*
 * SOURCE CREDITS
 * I would like to give a shoutout to Kobe Bryant, for when I was down he inspired me to keep going.
 * The code in this gui application and all files in the gui folder takes inspiration from many sources:
 * 
 * 1) Bro Code on YouTube
 * Link: https://www.youtube.com/watch?v=Kmgo00avvEw&t=4660s&pp=ygURYnJvIGNvZGUgamF2YSBndWk%3D
 * 
 * 2) The demo files from "Oracle Java Documentation"  
 * Link: https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html
 * 
 * 3) Video series from Java Code Junkie, the first video is linked below
 * Link: https://www.youtube.com/watch?v=1vVJPzVzaK8&list=PL3bGLnkkGnuV699lP_f9DvxyK5lMFpq6U 
 * 
 * 4) AlarmSystem application from UBC github repo, the application is linked below
 * Link: https://github.students.cs.ubc.ca/CPSC210/AlarmSystem 
 */

 // Class that is the Main Frame for the entire gui application
public class FitnessLoggerAppGui extends JFrame {
    private static final String JSON_STORE_DATA = "./data/dataPersistence.json";
    private static final int WIDTH = 933;
    private static final int HEIGHT = 740;
    protected WorkoutLogger workoutLogger;
    protected DataPersistence dataPersistence;
    protected TabbedPanes tabbedPanes;


    // EFFECTS: Initializes an instance of the FitenssLoggerAppGui
    //          and instantiates appropiate fields for GUI and runs the JFrame
    public FitnessLoggerAppGui() {
        new SplashScreen();
        this.dataPersistence = new DataPersistence(JSON_STORE_DATA);
        this.workoutLogger = new WorkoutLogger();

        initFrame();
        initTabbedPanes();

        add(tabbedPanes);
        addWindowListner();
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: Initializes this frame's size and title 
    private void initFrame() {
        setTitle("Fitness Logger App");
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setJMenuBar(new MenuBar(this));
    }

    // MODIFIES: this
    // EFFECTS: Instantiates tabbedPanes
    private void initTabbedPanes() {
        tabbedPanes = new TabbedPanes(this);
    }


    // EFFECTS: saves the workoutLogger data into Json
    public void save() throws FileNotFoundException {
        this.dataPersistence.save(workoutLogger);
    }

    // MODIFIES: this
    // EFFECTS: loads workoutLogger from JSon
    //          throws IOException if it cannot be done
    public void load() throws IOException {
        this.workoutLogger = dataPersistence.load();
        refresh();
    }

    // MODIFES: this
    // EFFECTS: refreshes the tabs and its panels
    public void refresh() {
        tabbedPanes.refreshTabs();
    }

    // MODIFIES: this
    // EFFECTS: removes workout from logger at given date
    public void removeWorkoutAtDate(String date) {
        workoutLogger.removeWorkout(date);
    }

    public WorkoutLogger getWorkoutLogger() {
        return this.workoutLogger;
    }


    // MODIFIES: this
    // EFFECTS: adds a window listeners for closing the application
    private void addWindowListner() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                LogPrinter logPrinter = new ConsolePrinter();
                logPrinter.printLog(EventLog.getInstance());
                dispose();
            }
        });
    }
}
