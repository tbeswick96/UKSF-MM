/*
 * Copyright (c) Tim UKSF 2016.
 * This file is part of UKSF-MM which is released under GPLv3.
 * Go to https://github.com/tbeswick96/UKSF-MM/blob/master/LICENSE for full license details.
 */

package com.uksf.mm.core.utility;

import com.uksf.mm.core.Core;

import static com.uksf.mm.core.utility.LogHandler.Severity.INFO;

/**
 * @author Tim
 */
public class Invokable {

    /**
     * Invokable instance
     */
    public static Invokable instance = new Invokable();

    /**
     * Displays settings page, state 50
     */
    public void showSettings() {
        LogHandler.logSeverity(INFO, "Invokable showSettings called");
        Core.getInstanceUI().changeState(50);
    }

    /**
     * Displays home page, state 0
     */
    public void showHome() {
        LogHandler.logSeverity(INFO, "Invokable showHome called");
        Core.getInstanceUI().changeState(0);
    }

    /**
     * Runs update
     */
    public void updateNow() {
        LogHandler.logSeverity(INFO, "Invokable updateNow called");
        Core.runUpdate();
	}

	/**
	 * Changes missions folder
	 */
	public void changeMissionsFolder() {
		LogHandler.logSeverity(INFO, "Invokable changeMissionsFolder called");
		Core.getInstanceUI().changeMissionsFolder();
	}

	/**
	 * Closes program
	 */
	public void close() {
		LogHandler.logSeverity(INFO, "Closing");
		System.exit(0);
	}

	/**
	 * Minimizes program
	 */
	public void minimize() {
		LogHandler.logSeverity(INFO, "Minimizing");
		Core.getInstanceUI().minimize();
	}

	/**
	 * Maximizes program
	 */
	public void maximize() {
		LogHandler.logSeverity(INFO, "Maximizing");
		Core.getInstanceUI().maximize();
	}
}
