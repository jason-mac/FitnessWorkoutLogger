package ui.printer;

import model.EventLog;

// Code is copied excatly from UBC Alarm System 
// Source: https://github.students.cs.ubc.ca/CPSC210/AlarmSystem.git

/**
 * Defines behaviours that event log printers must support.
 */
public interface LogPrinter {
	/**
	 * Prints the log
	 * @param el  the event log to be printed
	 * @throws LogException when printing fails for any reason
	 */
    void printLog(EventLog el);
}