/*
 * Copyright (c) Tim UKSF 2016.
 * This file is part of UKSF-MM which is released under GPLv3.
 * Go to https://github.com/tbeswick96/UKSF-MM/blob/master/LICENSE for full license details.
 */

package com.uksf.mm.core.utility.sqm;

import com.uksf.mm.core.Core;
import com.uksf.mm.core.utility.LogHandler;
import org.apache.commons.io.FileUtils;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static com.uksf.mm.core.utility.Info.MISSION_SELECTED;
import static com.uksf.mm.core.utility.LogHandler.Severity.INFO;
import static com.uksf.mm.core.utility.LogHandler.Severity.WARNING;

/**
 * @author Tim
 */
public class SqmLoader {

	/**
	 * Loads selected mission
	 * @return true if load worked, false if an error occurred
	 */
	public static boolean loadMission() {
		LogHandler.logSeverity(INFO, "Loading mission '" + MISSION_SELECTED.getName() + "' at '" + MISSION_SELECTED.getPath() + "'");
		try {
			int state = readSqm();
			switch(state) {
				case 0:
					return true;
				case 1:
					JOptionPane.showMessageDialog(Core.getInstanceUI(), "No mission.sqm file found at '" + MISSION_SELECTED.getPath() + "'", "No mission.sqm", JOptionPane.ERROR_MESSAGE);
					return false;
				case 2:
					JOptionPane.showMessageDialog(Core.getInstanceUI(),
							"Empty mission.sqm file found at '" + MISSION_SELECTED.getPath() + "'", "Empty mission.sqm", JOptionPane.ERROR_MESSAGE);
					return false;
				case 3:
					JOptionPane.showMessageDialog(Core.getInstanceUI(),
							"Invalid mission.sqm file found at '" + MISSION_SELECTED.getPath() + "' \nEnsure mission.sqm is not binarized.", "Invalid mission.sqm", JOptionPane.ERROR_MESSAGE);
					return false;
			}

		} catch(Exception e) {
			Core.error(e);
		}
		return false;
	}

	/**
	 * Reads sqm and stores in mission object
	 * @return return state: 0 = ok, 1 = no sqm, 2 = empty sqm, 3 = binarized sqm
	 * @throws Exception
	 */
	private static int readSqm() throws Exception {
		File sqmFile = new File(MISSION_SELECTED.getPath() + "/mission.sqm");
		if(!sqmFile.exists()) {
			LogHandler.logSeverity(WARNING, "Mission '" + MISSION_SELECTED.getName() + "' at '" + MISSION_SELECTED.getPath() + "' has no mission.sqm file");
			return 1;
		}
		List<String> rawSqm = FileUtils.readLines(sqmFile);
		if(rawSqm.size() == 0) {
			LogHandler.logSeverity(WARNING, "Mission '" + MISSION_SELECTED.getName() + "' at '" + MISSION_SELECTED.getPath() + "' is empty");
			return 2;
		}
		if(!rawSqm.get(0).contains("version=")) {
			LogHandler.logSeverity(WARNING, "Mission '" + MISSION_SELECTED.getName() + "' at '" + MISSION_SELECTED.getPath() + "' has no valid mission.sqm file (probably binarized)");
			return 3;
		}

		LogHandler.logSeverity(INFO, "SQM for mission '" + MISSION_SELECTED.getName() + "' is valid. Reading data...");
		MISSION_SELECTED.setVersion(getSingleData(rawSqm, "version"));
		MISSION_SELECTED.setEditorData(getData(rawSqm, "editordata"));
		MISSION_SELECTED.setBinarized(getSingleData(rawSqm, "binarization"));
		MISSION_SELECTED.setAddons(getData(rawSqm, "addons"));
		MISSION_SELECTED.setRandomSeed(getSingleData(rawSqm, "randomseed"));
		MISSION_SELECTED.setScenarioData(getData(rawSqm, "scenariodata"));
		MISSION_SELECTED.setCustomAttributes(getData(rawSqm, "customattributes"));
		MISSION_SELECTED.setMissionIntel(getData(rawSqm, "intel"));
		MISSION_SELECTED.setMissionData(getData(rawSqm, "entities"));
		LogHandler.logSeverity(INFO, "SQM for mission '" + MISSION_SELECTED.getName() + "' read sucessfully.");

		return 0;
	}

	/**
	 * Get list data from key
	 * @param raw raw sqm as list
	 * @param key name of data to find
	 * @return list with data from key
	 */
	private static List<String> getData(List<? extends String> raw, String key) {
		LogHandler.logSeverity(INFO, "Reading " + key + " for mission '" + MISSION_SELECTED.getName() + "'");
		List<String> data = new ArrayList<>();
		int index = 0;
		while(true) {
			String line = raw.get(index);
			if(line.toLowerCase().contains(key)) {
				break;
			}
			index++;
		}
		data.add(raw.get(index));
		index += 1;
		String opening = raw.get(index);
		Stack<String> stack = new Stack<>();
		stack.push(opening);
		data.add(opening);
		index += 1;
		while(!stack.isEmpty()) {
			String line = raw.get(index);
			String test = line.replace("\t", "");
			if(test.equals("{")) {
				stack.push(line);
			}
			if(test.equals("};")) {
				stack.pop();
			}
			data.add(line);
			index++;
		}
		return data;
	}

	/**
	 * Get single line data from key
	 * @param raw raw sqm as list
	 * @param key name of data to find
	 * @return string with data from key
	 */
	private static String getSingleData(List<String> raw, String key) {
		LogHandler.logSeverity(INFO, "Reading " + key + " for mission '" + MISSION_SELECTED.getName() + "'");
		int index = 0;
		while(true) {
			String line = raw.get(index);
			if(line.toLowerCase().contains(key)) {
				return line;
			}
			index++;
		}
	}
}
