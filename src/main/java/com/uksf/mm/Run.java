/*
 * Copyright (c) Tim UKSF 2016.
 * This file is part of UKSF-MM which is released under GPLv3.
 * Go to https://github.com/tbeswick96/UKSF-MM/blob/master/LICENSE for full license details.
 */

package com.uksf.mm;

import com.uksf.mm.core.Core;
import com.uksf.mm.utility.LogHandler;

import javax.swing.*;

/**
 * @author Tim
 */
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
