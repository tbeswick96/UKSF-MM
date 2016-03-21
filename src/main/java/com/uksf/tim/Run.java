package com.uksf.tim;

import com.uksf.tim.core.Core;
import com.uksf.tim.utility.LogHandler;

import javax.swing.*;

public class Run {
    public static void main(String args[]) {
        //If not windows, close
        String os = System.getProperty("os.name").toUpperCase();
        if(!os.contains("WIN")) {
            JOptionPane.showMessageDialog(null, "Currently only compatible with Windows", "Currently Incompatible", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

        //Create log handler and start program
        new LogHandler();
        new Core();

        //Catch shutdown event to safely close files
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                LogHandler.closeLog();
            }
        });
    }
}
