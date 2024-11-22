package ui.gui.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;

// Class that handles the splash screen of the application
public class SplashScreen extends JWindow {
    private ProgressBar bar;

    // EFFECTS: Upon instantiation the splash screen is run
    public SplashScreen() {
        initSplashScreen();
        runSplashScreen();
    }

    // EFFECTS: Initializes fields of the splash screen
    private void initSplashScreen() {
        setSize(1000, 500);
        setForeground(Color.WHITE);
        JPanel panel = initPanel();
        add(panel);
        setLocationRelativeTo(null);
    }

    // MODIFIES: this
    // EFFECTS: Splash screen is run, and then disposed upon
    // the progress bar finishing
    private void runSplashScreen() {
        setVisible(true);
        bar.fill("Opening Application Now");
        try {
            Thread.sleep(1500);
            dispose();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // EFFECTS: Creates a JPanel which holds the Progress bar and Display image and
    // the message
    // returns the created JPanel
    private JPanel initPanel() {
        ImageIcon image = new ImageIcon(getClass().getResource("/resources/icons/Barbell_Icon.png"));
        JPanel panel = new JPanel();
        JLabel labelText = new JLabel("Running FitnessLoggerAppGui...");
        JLabel labelImage = new JLabel();

        panel.setBackground(Color.WHITE);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        bar = new ProgressBar();

        labelImage.setIcon(image);
        labelImage.setAlignmentX(Component.CENTER_ALIGNMENT);

        labelText.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelText.setFont(new Font("Comic Sans", Font.BOLD, 36));

        panel.add(labelText);
        panel.add(labelImage);
        panel.add(bar);
        return panel;
    }
}