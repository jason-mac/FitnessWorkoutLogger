package ui.gui.components;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingWorker;

import model.EventLog;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import ui.gui.FitnessLoggerAppGui;
import ui.printer.*;

// Class that is component for the main JFrame of application 
// This class is a menubar relating to File operations
public class MenuBar extends JMenuBar implements ActionListener {
    private FitnessLoggerAppGui fitnessLoggerAppGui;
    private JMenu fileMenu;
    private JMenuItem saveItem;
    private JMenuItem loadItem;
    private JMenuItem exitItem;
    private ProgressBar bar;
    private JDialog dialog;

    // EFFECTS: Initializes the menu bar and its components
    public MenuBar(FitnessLoggerAppGui fitnessLoggerAppGui) {
        this.fitnessLoggerAppGui = fitnessLoggerAppGui;

        initBar();
        initDialog();
        initSaveItem();
        initLoadItem();
        initExitItem();
        initFileMenu();

        this.add(fileMenu);
    }

    // MODIFIES: this
    // EFFECTS: Gives the fileMenu a title and adds components
    private void initFileMenu() {
        this.fileMenu = new JMenu("File");
        fileMenu.add(saveItem);
        fileMenu.add(loadItem);
        fileMenu.add(exitItem);
    }

    // MODIFIES: this
    // EFFECTS: Initializes bar to a new ProgressBar
    private void initBar() {
        this.bar = new ProgressBar();
    }

    // MODIFIES: this
    // EFFECTS: Creates a dialog menu such that the user cannot 
    //          close it or intereact with the application
    //          until the "ProgressBar" is finished with its
    //          core operation
    private void initDialog() {
        this.dialog = new JDialog(fitnessLoggerAppGui, true);
        dialog.setLayout(new BorderLayout());
        dialog.add(bar, BorderLayout.CENTER);
        dialog.setSize(500, 100);
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
    }

    // MODIFIES: this
    // EFFECTS: initalizes saveItem to a JMenuItem inteded for the saving functionality
    private void initSaveItem() {
        ImageIcon image = new ImageIcon(getClass().getResource("/resources/icons/Save_Icon.png"));
        this.saveItem = new JMenuItem("Save", image);
        saveItem.addActionListener(this);
    }

    // MODIFIES: this
    // EFFECTS: initalizes saveItem to a JMenuItem inteded for the loading functionality
    private void initLoadItem() {
        this.loadItem = new JMenuItem("Load", new ImageIcon(getClass().getResource("/resources/icons/Load_Icon.png")));
        this.loadItem.addActionListener(this);
    }

    // MODIFIES: this
    // EFFECTS: initalizes saveItem to a JMenuItem inteded for the exiting functionality
    private void initExitItem() {
        this.exitItem = new JMenuItem("Exit", new ImageIcon(getClass().getResource("/resources/icons/Exit_Icon.png")));
        this.exitItem.addActionListener(this);
    }

    @Override
    // EFFECTS: given the ActionEvent e does the following
    //  if source comes from saveItem:
    //      saves current state of application to file
    //  if source comes from loadItem:
    //      loads the state of application from file
    //  if source comes from exitItem:
    //      closes the application immediately
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == saveItem) {
            handleSaveItemAction();
        } else if (source == loadItem) {
            handleLoadItemAction();
        } else if (source == exitItem) {
            handleExitItemAction();
        }
    }

    // EFFECTS: handles saving the current state of application to file
    // if cannot be saved user is prompted with window stating as such
    private void handleSaveItemAction() {
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dialog.setLocationRelativeTo(fitnessLoggerAppGui);
        dialog.setTitle("Saving...");
        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                fitnessLoggerAppGui.save();
                bar.fill("Done Saving! Hit close");
                return null;
            }

            protected void done() {
                try {
                    get();
                    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                } catch (Exception e) {
                    dialog.dispose();
                    handleError("Failed to save data to file");
                }
            }
        };
        worker.execute();
        dialog.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: handles loading the application from file
    // if cannot be loaded user is prompted with window stating as such
    private void handleLoadItemAction() {
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dialog.setLocationRelativeTo(fitnessLoggerAppGui);
        dialog.setTitle("Loading...");
        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                bar.fill("Done loading! Hit close");
                fitnessLoggerAppGui.load();
                return null;
            }

            protected void done() {
                try {
                    get();
                    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                } catch (Exception e) {
                    dialog.dispose();
                    handleError("Failed to load data from file");
                }
            }
        };
        worker.execute();
        dialog.setVisible(true);
    }

    // EFFECTS: closes application upon hitting the exitItem
    private void handleExitItemAction() {
        LogPrinter logPrinter = new ConsolePrinter();
        logPrinter.printLog(EventLog.getInstance());
        fitnessLoggerAppGui.dispose();
    }

    // EFFECTS: handles the case for the file not being able to be saved to or loaded from
    //          gui is propmted with a Dialog giving error message
    private void handleError(String message) {
        JDialog error = new JDialog(fitnessLoggerAppGui, "Error", true);
        error.setSize(500, 100);
        error.setLocationRelativeTo(fitnessLoggerAppGui);

        JButton closeButton = new JButton(message);
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                error.dispose();
            }
        });
        error.getContentPane().add(closeButton);
        error.setVisible(true);
    }
}