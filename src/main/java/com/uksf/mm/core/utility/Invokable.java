/*
 * Copyright (c) Tim UKSF 2016.
 * This file is part of UKSF-MM which is released under GPLv3.
 * Go to https://github.com/tbeswick96/UKSF-MM/blob/master/LICENSE for full license details.
 */

package com.uksf.mm.core.utility;

import com.uksf.mm.core.Core;
import com.uksf.mm.core.utility.loaders.MapLoad;
import com.uksf.mm.core.utility.loaders.MissionLoad;

import javax.swing.*;

import static com.uksf.mm.core.utility.Info.*;
import static com.uksf.mm.core.utility.LogHandler.Severity.ERROR;

/**
 * @author Tim
 */
@SuppressWarnings("All")
public class Invokable {

    /**
     * Invokable instance
     */
    public static Invokable instance = new Invokable();

    /**
     * Displays settings page, state 50
     */
    public void showSettings() {
        Core.getInstanceUI().changeState(50);
    }

    /**
     * Displays home page, state 0
     */
    public void showHome() {
        Core.getInstanceUI().changeState(0);
    }

	/**
	 * Changes missions folder
	 */
	public void changeMissionsFolder() {
		Core.getInstanceUI().changeMissionsFolder();
	}

	/**
	 * Refresh maps and missions
	 */
	public void refreshMissions() {
		MissionLoad.loadMissions();
		MapLoad.loadMaps();
		SwingUtilities.invokeLater(() -> Core.getInstanceUI().updateDropdowns());
	}

	/**
	 * Loads the selected mission
	 */
	public void loadSelectedMission() {
		if(!Core.getInstanceUI().loadSelectedMission()) {
			LogHandler.logSeverity(ERROR, "Failed to load selected mission");
		}
	}

	/**
	 * Saves the selected mission
	 */
	public void saveSelectedMission() {
		Core.getInstanceUI().saveSelectedMission();
	}

	/**
	 * Saves the selected mission
	 */
	public void clearAddons() {
		MISSION_SELECTED.addons = DEFAULT_ADDONS;
		MISSION_SELECTED.addonsMeta = DEFAULT_ADDONSMETA;
	}

	/**
	 * Closes program
	 */
	public void close() {
		System.exit(0);
	}

	/**
	 * Minimizes program
	 */
	public void minimize() {
		Core.getInstanceUI().minimize();
	}

	/**
	 * Maximizes program
	 */
	public void maximize() {
		Core.getInstanceUI().maximize();
	}
}
