/*
 * Copyright (c) Tim UKSF 2016.
 * This file is part of UKSF-MM which is released under GPLv3.
 * Go to https://github.com/tbeswick96/UKSF-MM/blob/master/LICENSE for full license details.
 */

package com.uksf.mm.core;


import com.uksf.mm.core.utility.LogHandler;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.util.prefs.Preferences;

import static com.uksf.mm.core.utility.Info.*;
import static com.uksf.mm.core.utility.LogHandler.Severity.INFO;

/**
 * @author Tim
 */
public class Settings {

    /**
     * Preferences
     */
    private static Preferences preferences;

    /**
     * Check if program has run before and handle settings accordingly
     */
    static void init() {
        preferences = Preferences.userRoot().node("uksf-mm");
        LogHandler.logNoTime(HASHSPACE);
        LogHandler.logSeverity(INFO, "Settings loaded: " + preferences.absolutePath());

        HAS_SETUP = preferences.getBoolean("has_setup", false);
        LogHandler.logSeverity(INFO, TAB + "Setup? " + HAS_SETUP);
        if(!HAS_SETUP) {
			setSettings();
        }
		getSettings();
        LogHandler.logNoTime(HASHSPACE);
    }

    /**
     * If program has run before, get settings
     */
    private static void getSettings() {
        LogHandler.logNoTime(HASHSPACE);
        LogHandler.logSeverity(INFO, "User settings updated: ");
		UPDATER_UPDATED = preferences.getBoolean("updater_updated", false);
		FOLDER_MISSIONS = preferences.get("folder_missions", "");
		SQM_BACKUP = preferences.getBoolean("sqm_backup", true);

		LogHandler.logSeverity(INFO, TAB + "Updater updated: " + UPDATER_UPDATED);
		LogHandler.logSeverity(INFO, TAB + "Missions folder: " + FOLDER_MISSIONS);
		LogHandler.logSeverity(INFO, TAB + "SQM Backup: " + SQM_BACKUP);
    }

	/**
     * If program has not run before, set settings
     */
    private static void setSettings() {
        preferences.putBoolean("has_setup", true);
		preferences.putBoolean("updater_updated", false);
		preferences.put("folder_missions", getDefaultMissionsFolder());
		preferences.putBoolean("sqm_backup", true);
    }

    /**
     * Sets setting at key to value
     * @param key setting key
     * @param value new value
     */
    public static void set(String key, Object value) {
        if(value instanceof String) {
            preferences.put(key, (String) value);
        } else if(value instanceof Boolean) {
            preferences.putBoolean(key, (boolean) value);
        }
        getSettings();
    }

    /**
     * Sets multiple settings from key and value arrays
     * @param keys array of setting keys
     * @param values array of new settings values
     */
    public static void setMultiple(String keys[], Object values[]) {
        for(int i = 0; i < keys.length; i++) {
            String key = keys[i];
            Object value = values[i];
            if(value instanceof Boolean) {
                preferences.putBoolean(key, (boolean) value);
            } else if(value instanceof String) {
                preferences.put(key, (String) value);
            }
        }
        getSettings();
    }

	/**
	 * Finds user's missions folder, by default in documents/arma 3/missions
	 * @return default missions directory
	 */
	private static String getDefaultMissionsFolder() {
		FILE_CHOOSER = new JFileChooser();
		FileSystemView fileSystemView = FILE_CHOOSER.getFileSystemView();
		File path = fileSystemView.getDefaultDirectory();
		String sanitizedPath = path.getAbsolutePath().replace("\\", "/");
		File missionsPath = new File(sanitizedPath + "/Arma 3/missions");
		if(!missionsPath.exists()) {
			return "";
		}
		return missionsPath.getAbsolutePath();
	}
}
