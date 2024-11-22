package ui.gui.components;

import javax.swing.*;

// Class that models the progress bar intended for "loading" sequences
public class ProgressBar extends JProgressBar {
    private int milliSeconds;

    // EFFECTS: Creaetes a progress bar with milliseconds 
    // sets bar to be visible with string painted true
    public ProgressBar(int milliSeconds) {
        this.milliSeconds = milliSeconds;
        setVisible(true);
        setStringPainted(true);
    }

    // EFFECTS: sets the progress bar to be visible with the string painted to true
    public ProgressBar() {
        milliSeconds = 35;
        setVisible(true);
        setStringPainted(true);
    }

    // MODIFIES: this
    // EFFECTS: runs the progress bar, upon reaching 100 displays the endMessage
    public void fill(String endMessage) {
        setString(null);
            
        for (int i = 0; i <= 105; i++) {
            if (i <= 100) {
                try {
                    Thread.sleep(milliSeconds);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                setValue(i);
            }
        }

        setString(endMessage);
    }
}
