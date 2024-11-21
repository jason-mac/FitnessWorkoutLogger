package ui;

import javax.swing.UIManager;

// Entry point for the main application 
public class Main {
    public static void main(String[] args) {

        FitnessLoggerApp workoutLoggerApp = new FitnessLoggerApp();

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        workoutLoggerApp.runGui();

        // try {
        //     workoutLoggerApp.runCli();
        // } catch (FileNotFoundException e) {
        //     System.out.println("Unable to run application: file not found");
        // }
    }
}

