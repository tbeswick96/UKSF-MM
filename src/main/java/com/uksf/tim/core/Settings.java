package com.uksf.tim.core;


import com.uksf.tim.utility.LogHandler;

import java.text.ParseException;
import java.util.Calendar;
import java.util.prefs.Preferences;

import static com.uksf.tim.core.Core.error;
import static com.uksf.tim.utility.Info.*;
import static com.uksf.tim.utility.LogHandler.Severity.INFO;

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
        LogHandler.logSeverity(INFO, "Settings loaded: " + preferences.absolutePath());

        HAS_SETUP = preferences.getBoolean("has_setup", false);
        LogHandler.logSeverity(INFO, TAB + "Setup? " + HAS_SETUP);
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
        LogHandler.logSeverity(INFO, "User settings updated: ");
        UPDATE_CHECK = preferences.getBoolean("update_check", true);
        UPDATE_WEEK = preferences.getBoolean("update_week", false);
        UPDATE_TIME = preferences.get("update_time", weekAhead());

        LogHandler.logSeverity(INFO, TAB + "Update check: " + UPDATE_CHECK);
        LogHandler.logSeverity(INFO, TAB + "Update week: " + UPDATE_WEEK);
        LogHandler.logSeverity(INFO, TAB + "Update time: " + UPDATE_TIME);
    }

    /**
     * If program has not run before, set settings
     */
    private static void setSettings() {
        preferences.putBoolean("has_setup", true);
        preferences.putBoolean("update_check", true);
        preferences.putBoolean("update_week", false);
        preferences.put("update_time", weekAhead());
    }

    /**
     * Sets setting at key to value
     * @param key setting key
     * @param value new value
     */
    public static void setBool(String key, boolean value) {
        preferences.putBoolean(key, value);
        getSettings();
    }

    public static void setString(String key, String value) {
        preferences.put(key, value);
        getSettings();
    }

    public static String weekAhead() {
        String date = DATEFORMAT.format(DATE);
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(DATEFORMAT.parse(date));
            calendar.add(Calendar.DATE, 7);
            date = DATEFORMAT.format(calendar.getTime());
        } catch(ParseException e) {
            error(e);
        }
        return date;
    }
}
