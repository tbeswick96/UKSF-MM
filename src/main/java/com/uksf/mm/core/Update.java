/*
 * Copyright (c) Tim UKSF 2016.
 * This file is part of UKSF-MM which is released under GPLv3.
 * Go to https://github.com/tbeswick96/UKSF-MM/blob/master/LICENSE for full license details.
 */

package com.uksf.mm.core;

import com.uksf.mm.utility.LogHandler;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import static com.uksf.mm.core.Core.error;
import static com.uksf.mm.utility.Info.*;
import static com.uksf.mm.utility.LogHandler.Severity.INFO;

/**
 * @author Tim
 */
public class Update {

    /**
     * Run update check
     */
    public static void run() {
        Settings.set("update_time", Settings.weekAhead());
        if(versionCheck()) {
            LogHandler.logNoTime(HASHSPACE);
            LogHandler.logSeverity(INFO, "Update is available. Current version: '" + VERSION + "' Latest version: '" + VERSION_LATEST + "'");
            update();
			Core.getInstanceUI().enableUpdate(true);
		}
		if(UPDATER_UPDATED) {updateUpdater();}
    }

	/**
     * Check version against online version
     * @return true if version is different
     */
    private static boolean versionCheck() {
        try {
            URL url = new URL("http://www.uk-sf.com/mm/LATEST.txt");
            InputStream stream = url.openStream();
			StringBuilder buffer = new StringBuilder("");
			int character;
			while(true) {
				if((character = stream.read()) == -1) break;
				buffer.append((char) character);
			}
			VERSION_LATEST = buffer.substring(buffer.indexOf("<Version>") + "<Version>".length(), buffer.indexOf("</Version>"));
            if(VERSION_LATEST.equals(VERSION)) return false;
        } catch(IOException e) {
            error(e);
        }
        return true;
    }

    /**
     * Prompt user for update
     */
    private static void update() {
        String message =
				"Current Version: " +
				VERSION +
				"\nLatest Version: " +
				VERSION_LATEST +
				"\n\nWould you like to download and update to the latest version?";
        JTextArea text = new JTextArea(message);
        text.setBorder(null); text.setOpaque(false); text.setFont(UIManager.getFont("Label.font"));

        Object[] options = {
                "Update",
                "Cancel",
                "Cancel, don't ask again",
        };

        int input = JOptionPane.showOptionDialog(
                null, //Parent frame
                text, //Message
                "Update Available", //Title
                JOptionPane.YES_NO_CANCEL_OPTION, //Option type
                JOptionPane.INFORMATION_MESSAGE, //Message type
                LOGO_LIGHT_64, //Icon
                options, //Options
                options[0] //Default Option
        );

        handleInput(input);
    }

    /**
     * Handles input from dialog
     * @param input state of input (ok, cancel)
     */
    private static void handleInput(int input) {
        switch(input) {
            case JOptionPane.OK_OPTION:
                runUpdate();
                break;
            case JOptionPane.CANCEL_OPTION:
                stopShow();
                break;
        }
		Core.getInstanceUI().requestFocus();
    }

    /**
     * Runs update tasks
     */
    private static void runUpdate() {
		LogHandler.logSeverity(INFO, "Updating");
		try {
			Runtime.getRuntime().exec("UKSF-MM-Updater.exe");
		} catch (Exception exception) {
			error(exception);
		}
		System.exit(0);
    }

    /**
     * Sets update setting to not show again
     */
    private static void stopShow() {
		Settings.setMultiple(new String[]{"update_check", "update_week"}, new Object[]{false, false});
    }

	private static void updateUpdater() {
		LogHandler.logSeverity(INFO, "Updating the updater");
		final InstallWorker installWorker = new InstallWorker();
		installWorker.addPropertyChangeListener(pcEvt -> {
			if(pcEvt.getNewValue() == SwingWorker.StateValue.DONE) {
				Settings.set("updater_updated", false);
			}
		});
		installWorker.execute();
	}
}
