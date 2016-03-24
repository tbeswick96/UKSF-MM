/*
 * Copyright (c) Tim UKSF 2016.
 * This file is part of UKSF-MM which is released under GPLv3.
 * Go to https://github.com/tbeswick96/UKSF-MM/blob/master/LICENSE for full license details.
 */

package com.uksf.updater.core;


import com.uksf.updater.gui.UI;
import com.uksf.updater.utility.LogHandler;
import com.uksf.updater.utility.loaders.FontLoad;
import com.uksf.updater.utility.loaders.ImageLoad;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * @author Tim
 */
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

        LogHandler.log("Updater started");

        //Set look and feel to OS default, and load fonts
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            LogHandler.log("Updater look & Feel set to " + UIManager.getSystemLookAndFeelClassName());
			FontLoad.instance.loadFonts();
			ImageLoad.instance.loadImages();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException | FontFormatException | IOException e) {
            error(e);
        }

        //Initialise program
        initialise();
    }

    /**
     * Initialise data and UI
     */
    private void initialise() {
        //Create UI
        try {
            SwingUtilities.invokeLater(() -> instanceUI = new UI());
            LogHandler.log("Updater UI Started");
        } catch(Exception exception) {
            error(exception);
        }

		Thread updaterUpdateThread = new Thread(new Update());
		updaterUpdateThread.start();
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
        JOptionPane.showMessageDialog(null, print, "Updater Error", JOptionPane.ERROR_MESSAGE);
        LogHandler.log(builder.toString());
        System.exit(0);
    }
}
