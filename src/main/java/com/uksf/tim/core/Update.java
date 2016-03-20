package main.java.com.uksf.tim.core;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

import static main.java.com.uksf.tim.Info.*;

class Update {

    /**
     * Run update check
     */
    public static void run() {
        if(versionCheck()) {
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

    private static void runUpdate() {

    }

    private static void stopShow() {
        Settings.set("update_check", false);
    }
}
