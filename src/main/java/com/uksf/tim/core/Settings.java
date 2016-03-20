package main.java.com.uksf.tim.core;

import java.util.prefs.Preferences;

import static main.java.com.uksf.tim.Info.*;

class Settings {

    /**
     * Preferences
     */
    private static Preferences preferences;

    /**
     * Check if program has run before and handle settings accordingly
     */
    static void init() {
        preferences = Preferences.userNodeForPackage(Core.class);

        HAS_SETUP = preferences.getBoolean("has_setup", false);
        if(HAS_SETUP) {
            getSettings();
        } else {
            setSettings();
        }
        debug();
    }

    /**
     * If program has run before, get settings
     */
    private static void getSettings() {
        UPDATE_CHECK = preferences.getBoolean("update_check", true);
    }

    /**
     * If program has not run before, set settings
     */
    private static void setSettings() {
        preferences.putBoolean("has_setup", true);
        preferences.putBoolean("update_check", true);
    }

    static void set(String key, Object value) {
        if(value instanceof String) {
            preferences.put(key, (String) value);
        } else if(value instanceof Boolean) {
            preferences.putBoolean(key, (boolean) value);
        }
        getSettings();
        debug();
    }

    private static void debug() {
        System.out.println(preferences.getBoolean("has_setup", true));
        System.out.println(HAS_SETUP);
        System.out.println(preferences.getBoolean("update_check", true));
        System.out.println(VERSION);
    }
}
