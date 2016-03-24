/*
 * Copyright (c) Tim UKSF 2016.
 * This file is part of UKSF-MM which is released under GPLv3.
 * Go to https://github.com/tbeswick96/UKSF-MM/blob/master/LICENSE for full license details.
 */

package com.uksf.updater.core;


import com.uksf.updater.utility.LogHandler;

import java.util.prefs.Preferences;

import static com.uksf.updater.utility.Info.*;

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
        preferences = Preferences.userNodeForPackage(Core.class);
        LogHandler.logNoTime(HASHSPACE);
        LogHandler.log("Settings loaded: " + preferences.absolutePath());

        HAS_SETUP = preferences.getBoolean("has_setup", false);
        LogHandler.log(TAB + "Setup? " + HAS_SETUP);
        if(HAS_SETUP) {
            getSettings();
        } else {
            setSettings();
        }
        LogHandler.logNoTime(HASHSPACE);
    }

    /**
     * If program has run before, get settings
     */
    private static void getSettings() {
        LogHandler.logNoTime(HASHSPACE);
        LogHandler.log("User settings updated: ");
        //UPDATE_CHECK = preferences.getBoolean("update_check", true);

        //LogHandler.log(TAB + "UpdaterUpdate check: " + UPDATE_CHECK);
    }

    /**
     * If program has not run before, set settings
     */
    private static void setSettings() {
        preferences.putBoolean("has_setup", true);
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
}