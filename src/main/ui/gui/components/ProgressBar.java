package ui.gui.components;

import javax.swing.*;

public class ProgressBar extends JProgressBar {

    // EFFECTS: sets the progress bar to be visible with the string painted to true
    public ProgressBar() {
        setVisible(true);
        setStringPainted(true);
    }

    // EFFECTS: runs the progress bar, upon reaching 100% displays the endMessage
    public void fill(String endMessage) {
        setString(null);

        for (int i = 0; i <= 105; i++) {
            if (i <= 100) {
                try {
                    Thread.sleep(25);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                setValue(i);
            }
        }

        setString(endMessage);
    }
}
