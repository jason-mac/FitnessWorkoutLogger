package ui.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Observer;

import javax.swing.*;

import model.WorkoutLogger;
import persistence.DataPersistence;
import ui.gui.components.*;

/*
 * SOURCE CREDITS
 * The code in this gui application takes inspiration from many sources:
 * 
 * 1) Bro Code on YouTube
 * Link: https://www.youtube.com/watch?v=Kmgo00avvEw&t=4660s&pp=ygURYnJvIGNvZGUgamF2YSBndWk%3D
 * 
 * 2) The demo files from "Oracle Java Documentation"  
 * Link: https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html
 * 
 */
public class FitnessLoggerAppGui extends JFrame {
    private static final String JSON_STORE_DATA = "./data/dataPersistence.json";
    private static final int WIDTH = 933;
    private static final int HEIGHT = 740;
    protected WorkoutLogger workoutLogger;
    protected DataPersistence dataPersistence;


    // EFFECTS: Creates an instance of the FitenssLoggerAppGui
    //          and instantiates appropiate fields for GUI and runs the JFrame
    public FitnessLoggerAppGui() {
        this.dataPersistence = new DataPersistence(JSON_STORE_DATA);
        this.workoutLogger = new WorkoutLogger();

        initFrame();



        setVisible(true);
    }

    private void initFrame() {
        setTitle("Fitness Logger App");
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
