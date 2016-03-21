package com.uksf.tim.core;


import com.uksf.tim.gui.UI;
import com.uksf.tim.utility.LogHandler;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;

import static com.uksf.tim.utility.Info.*;
import static com.uksf.tim.utility.Info.DATE;
import static com.uksf.tim.utility.Info.DATEFORMAT;
import static com.uksf.tim.utility.LogHandler.Severity.ERROR;
import static com.uksf.tim.utility.LogHandler.Severity.INFO;

public class Core {

    /**
     * Program instance
     */
    private static Core instance;
    /**
     * UI Instance
     */
    private static UI instanceUI;

    /**
     * Store instance, initialise program
     */
    public Core() {
        instance = this;

        LogHandler.logSeverity(INFO, "Started");

        //Set look and feel to OS default
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            LogHandler.logSeverity(INFO, "Look & Feel set to " + UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            error(e);
        }

        //Initialise program
        initialise();
    }

    /**
     * Initialise data and UI
     */
    private void initialise() {
        //Get program settings
        Settings.init();

        //Create UI
        try {
            SwingUtilities.invokeLater(() -> instanceUI = new UI());
            LogHandler.logSeverity(INFO, "UI Started");
        } catch(Exception exception) {
            error(exception);
        }

        LogHandler.logSeverity(INFO, "Update check running");
        //Run update check
        if(UPDATE_CHECK || (UPDATE_WEEK && isWeekAhead())){
            Update.run();
        }
    }

    private boolean isWeekAhead() {
        try {
            if(DATEFORMAT.parse(UPDATE_TIME).before(DATEFORMAT.parse(DATEFORMAT.format(DATE)))) {
                return true;
            }
        } catch(ParseException e) {
            error(e);
        }
        return false;
    }

    /**
     * Get instance of program
     * @return Program instance
     */
    public static Core getInstance() {
        return Core.instance;
    }

    /**
     * Get instance of UI
     * @return UI Instance
     */
    public static UI getInstanceUI() {
        return Core.instanceUI;
    }

    /**
     * Global method to display error as stacktrace before closing program
     * @param exception error to display
     */
    public static void error(Exception exception) {
        exception.printStackTrace();
        StringBuilder builder = new StringBuilder();
        builder.append(exception.getMessage());
        builder.append("\n");
        for (StackTraceElement element : exception.getStackTrace()) {
            builder.append(element.toString());
            builder.append("\n");
        }
        JTextArea printText = new JTextArea("Something went wrong.\n\n" + builder.toString());
        JScrollPane print = new JScrollPane(printText){
            @Override public Dimension getPreferredSize() {
                return new Dimension(500, 300);
            }
        };
        JOptionPane.showMessageDialog(null, print, "Error", JOptionPane.ERROR_MESSAGE);
        LogHandler.logSeverity(ERROR, builder.toString());
        System.exit(0);
    }
}
