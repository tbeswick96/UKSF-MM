/*
 * Copyright (c) Tim UKSF 2016.
 * This file is part of UKSF-MM which is released under GPLv3.
 * Go to https://github.com/tbeswick96/UKSF-MM/blob/master/LICENSE for full license details.
 */

package com.uksf.mm;

import com.uksf.mm.core.Core;
import com.uksf.mm.core.Settings;
import com.uksf.mm.core.utility.LogHandler;

import javax.swing.*;

/**
 * @author Tim
 */
public class Run {
	/**
	 * Starts program
	 * @param args command line arguments
	 */
    public static void main(String args[]) {
		//Check windows and java version
		if(checkWindows() && checkJava()) {
			//Initialise settings, create log handler, and start program
			Settings.init();
			new LogHandler();
			new Core();
		} else {
			System.exit(1);
		}

		//Catch shutdown event to safely close files
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				LogHandler.closeLog();
			}
		});
    }

	/**
	 * Checks if OS is windows. Incompatible with OSX, linux etc
	 * @return true if OS is windows
	 */
	private static boolean checkWindows() {
		String os = System.getProperty("os.name").toUpperCase();
		if(!os.contains("WIN")) {
			JOptionPane.showMessageDialog(null, "Currently only compatible with Windows", "Currently Incompatible", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	/**
	 * Checks java version. Only compatible with 1.8
	 * @return true if java is 1.8
	 */
	private static boolean checkJava() {
		String java = System.getProperty("java.specification.version");
		if(!java.equals("1.8")) {
			JOptionPane.showMessageDialog(null, "Incompatible Java version.\n " + java + " detected, 1.8 required", "Incompatible Java version", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
}
