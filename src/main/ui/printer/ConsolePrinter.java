package ui.printer;

import model.Event;
import model.EventLog;

// Class that implements logprinter for the purpose of 
// printing out log events to the console
public class ConsolePrinter implements LogPrinter {

    @Override
    // EFFECTS: prints out event description for each event in el
    public void printLog(EventLog el) {
        for (Event event : el) {
            System.out.println(event.toString() + "\n");
        }
    }
}
