package ui;

import ui.gui.FitnessLoggerAppGui;
import ui.cli.FitnessLoggerAppCli;

import java.util.Scanner;

// Class for interacting with either the Cli or Gui versions of this application
public class FitnessLoggerApp {
    private FitnessLoggerAppCli fitnessLoggerAppCli;
    private FitnessLoggerAppGui fitnessLoggerAppGui;

    // EFFECTS: prompts user to choose to run cli or gui
    public void run() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Gui or Cli, type here: ");
        String input = scanner.nextLine();
        scanner.close();
        if (input.equals("Cli")) {
            runCli();
        } else if (input.equals("Gui")) {
            runGui();
        } else {
            throw new Exception();
        } 
    }

    // EFFECTS: runs cli application
    public void runCli() throws Exception {
        fitnessLoggerAppCli = new FitnessLoggerAppCli();
        fitnessLoggerAppCli.run();
    }

    // EFFECTS: runs gui application
    public void runGui() {
        fitnessLoggerAppGui = new FitnessLoggerAppGui();
    }
}
