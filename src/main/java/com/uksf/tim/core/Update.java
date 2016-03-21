package com.uksf.tim.core;

import com.uksf.tim.utility.LogHandler;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

import static com.uksf.tim.utility.Info.*;
import static com.uksf.tim.utility.LogHandler.Severity.INFO;

class Update {

    /**
     * Run update check
     */
    static void run() {
        Settings.setString("update_time", Settings.weekAhead());
        if(versionCheck()) {
            LogHandler.logSeverity(INFO, "Update is available. Current version: '" + VERSION + "' Latest version: '" + VERSION_LATEST + "'");
            update();
        }
    }

    /**
     * Check version against online version
     * @return true if version is different
     */
    private static boolean versionCheck() {
        try {
            URL url = new URL("http://www.uk-sf.com/mm/VERSION.txt");
            Scanner scanner = new Scanner(url.openStream());
            VERSION_LATEST = scanner.next();
            System.out.println(VERSION);
            System.out.println(VERSION_LATEST);
            if(VERSION_LATEST.equals(VERSION)) return false;
        } catch(IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * Prompt user for update
     */
    private static void update() {
        String message = "An update is available:\n" +
                "        Current Version: " +
                VERSION +
                "\n" +
                "        Latest Version: " +
                VERSION_LATEST +
                "\n\n" +
                "Would you like to download and update to the latest version?";
        JTextArea text = new JTextArea(message);
        text.setBorder(null); text.setOpaque(false); text.setFont(UIManager.getFont("Label.font"));

        Object[] options = {
                "Download & Update",
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
    }

    /**
     * Runs update tasks
     */
    private static void runUpdate() {

    }

    /**
     * Sets update setting to not show again
     */
    private static void stopShow() {
        Settings.setBool("update_check", false);
    }
}
