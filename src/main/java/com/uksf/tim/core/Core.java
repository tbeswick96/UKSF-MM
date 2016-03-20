package main.java.com.uksf.tim.core;

import main.java.com.uksf.tim.gui.UI;
import main.java.com.uksf.tim.utility.Invokable;

import javax.swing.*;
import java.awt.*;

import static main.java.com.uksf.tim.Info.UPDATE_CHECK;

public class Core {

    /**
     * Program instance
     */
    private static Core instance;
    private static UI instanceUI;

    /**
     * Store instance, initialise program
     */
    public Core() {
        instance = this;

        //Set look and feel to OS default
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
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

        new Invokable();

        //Create UI
        try {
            SwingUtilities.invokeLater(() -> instanceUI = new UI());
        } catch(Exception exception) {
            error(exception);
        }

        //Run update check
        if(UPDATE_CHECK){
            Update.run();
        }
    }

    /**
     * Get instance of program
     * @return Program instance
     */
    public static Core getInstance() {
        return Core.instance;
    }

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
        System.exit(0);
    }
}
