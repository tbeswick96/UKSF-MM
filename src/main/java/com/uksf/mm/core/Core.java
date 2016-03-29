/*
 * Copyright (c) Tim UKSF 2016.
 * This file is part of UKSF-MM which is released under GPLv3.
 * Go to https://github.com/tbeswick96/UKSF-MM/blob/master/LICENSE for full license details.
 */

package com.uksf.mm.core;


import com.uksf.mm.core.utility.LogHandler;
import com.uksf.mm.core.utility.loaders.FontLoad;
import com.uksf.mm.core.utility.loaders.ImageLoad;
import com.uksf.mm.core.utility.loaders.MapLoad;
import com.uksf.mm.core.utility.loaders.MissionLoad;
import com.uksf.mm.gui.UI;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static com.uksf.mm.core.utility.Info.HASHSPACE;
import static com.uksf.mm.core.utility.LogHandler.Severity.ERROR;
import static com.uksf.mm.core.utility.LogHandler.Severity.INFO;

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
	 * Updateworker instance
	 */
	private static UpdateWorker updateWorker;

    /**
     * Store instance, initialise program
     */
    public Core() {
        instance = this;

        LogHandler.logSeverity(INFO, "Started");

        //Set look and feel to OS default, and load fonts
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            LogHandler.logSeverity(INFO, "Look & Feel set to " + UIManager.getSystemLookAndFeelClassName());
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
        //Get program settings
        Settings.init();

		//Load missions & maps
		MissionLoad.loadMissions();
		MapLoad.loadMaps();

		//Create UI
		try {
			SwingUtilities.invokeLater(() -> instanceUI = new UI());
		} catch(Exception exception) {
			error(exception);
		}

		//Run update
		updateWorker = new UpdateWorker();
		runUpdate();
    }

	public static void runUpdate() {
		LogHandler.logNoTime(HASHSPACE);
		LogHandler.logSeverity(INFO, "Update check running");
		updateWorker.execute();
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
