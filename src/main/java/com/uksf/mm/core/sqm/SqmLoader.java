/*
 * Copyright (c) Tim UKSF 2016.
 * This file is part of UKSF-MM which is released under GPLv3.
 * Go to https://github.com/tbeswick96/UKSF-MM/blob/master/LICENSE for full license details.
 */

package com.uksf.mm.core.sqm;

import com.uksf.mm.core.Core;
import com.uksf.mm.core.utility.LogHandler;
import org.apache.commons.io.FileUtils;

import javax.swing.*;
import java.io.File;
import java.util.Stack;

import static com.uksf.mm.core.utility.Info.*;
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
		LogHandler.logSeverity(INFO, "Loading mission '" + MISSION_SELECTED.name + "' at '" + MISSION_SELECTED.path + "'");
		try {
			int state = readSqm();
			switch(state) {
				case 0:
					return true;
				case 1:
					JOptionPane.showMessageDialog(Core.getInstanceUI(), "No mission.sqm file found at '" + MISSION_SELECTED.path + "'", "No mission.sqm", JOptionPane.ERROR_MESSAGE);
					return false;
				case 2:
					JOptionPane.showMessageDialog(Core.getInstanceUI(),
							"Empty mission.sqm file found at '" + MISSION_SELECTED.path + "'", "Empty mission.sqm", JOptionPane.ERROR_MESSAGE);
					return false;
				case 3:
					JOptionPane.showMessageDialog(Core.getInstanceUI(),
							"Invalid mission.sqm file found at '" + MISSION_SELECTED.path
									+ "' \nEnsure mission.sqm is SQM version " + SQM_VERSION, "Invalid mission.sqm", JOptionPane.ERROR_MESSAGE);
					return false;
				case 4:
					JOptionPane.showMessageDialog(Core.getInstanceUI(),
							"Invalid mission.sqm file found at '" + MISSION_SELECTED.path
									+ "' \nEnsure mission.sqm is not binarized.", "Invalid mission.sqm", JOptionPane.ERROR_MESSAGE);
					return false;
			}

		} catch(Exception e) {
			Core.nonFatalError(e);
		}
		return false;
	}

	/**
	 * Reads sqm and stores in mission object
	 * @return return state: 0 = ok, 1 = no sqm, 2 = empty sqm, 3 = incorrect sqm version, 4 = binarized sqm
	 * @throws Exception
	 */
	private static int readSqm() throws Exception {
		File sqmFile = new File(MISSION_SELECTED.path + "/mission.sqm");
		if(!sqmFile.exists()) {
			LogHandler.logSeverity(WARNING, "Mission '" + MISSION_SELECTED.name + "' at '" + MISSION_SELECTED.path + "' has no mission.sqm file");
			return 1;
		}
		SqmList rawSqm = new SqmList(FileUtils.readLines(sqmFile));
		if(rawSqm.size() == 0) {
			LogHandler.logSeverity(WARNING, "Mission '" + MISSION_SELECTED.name + "' at '" + MISSION_SELECTED.path + "' is empty");
			return 2;
		}
		if(!rawSqm.get(0).contains(SQM_VERSION) && rawSqm.get(0).contains("version")) {
			LogHandler.logSeverity(WARNING, "Mission '" + MISSION_SELECTED.name + "' at '" + MISSION_SELECTED.path + "' is not version 52");
			return 3;
		}
		if(!rawSqm.get(0).contains(SQM_VERSION)) {
			LogHandler.logSeverity(WARNING, "Mission '" + MISSION_SELECTED.name + "' at '" + MISSION_SELECTED.path + "' is binarized");
			return 4;
		}

		LogHandler.logSeverity(INFO, "SQM for mission '" + MISSION_SELECTED.name + "' is valid. Reading data...");
		MISSION_SELECTED.version = getSingleData(rawSqm, "version");
		MISSION_SELECTED.editorData = getData(rawSqm, "editordata");
		MISSION_SELECTED.binarized = getSingleData(rawSqm, "binarization");
		MISSION_SELECTED.addons = getData(rawSqm, "addons");
		MISSION_SELECTED.addonsMeta = getData(rawSqm, "addonsmetadata");
		MISSION_SELECTED.randomSeed = getSingleData(rawSqm, "randomseed");
		MISSION_SELECTED.scenarioData = getData(rawSqm, "scenariodata");
		MISSION_SELECTED.missionIntel = getData(rawSqm, "intel");
		MISSION_SELECTED.missionData = getData(rawSqm, "entities");
		defaultCheck();
		LogHandler.logSeverity(INFO, "SQM for mission '" + MISSION_SELECTED.name + "' read successfully");

		return 0;
	}

	/**
	 * Get list data from key
	 * @param raw raw sqm as list
	 * @param key name of data to find
	 * @return list with data from key, or empty list
	 */
	private static SqmList getData(SqmList raw, String key) {
		LogHandler.logSeverity(INFO, "Reading " + key + " for mission '" + MISSION_SELECTED.name + "'");
		SqmList data = new SqmList();
		int index = 0;
		while(true) {
			if(index >= raw.size()) return new SqmList();
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
			if(index >= raw.size()) return new SqmList();
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
	 * @return string with data from key, or empty string
	 */
	private static String getSingleData(SqmList raw, String key) {
		LogHandler.logSeverity(INFO, "Reading " + key + " for mission '" + MISSION_SELECTED.name + "'");
		if(!raw.contains(key)) return "";
		int index = 0;
		while(true) {
			if(index >= raw.size()) return "";
			String line = raw.get(index);
			if(line.toLowerCase().contains(key)) {
				return line;
			}
			index++;
		}
	}

	/**
	 * Checks read data, and if none can be found, sets data as default
	 */
	private static void defaultCheck() {
		MISSION_SELECTED.version = (MISSION_SELECTED.version.equals("")) ? DEFAULT_VERSION : MISSION_SELECTED.version;
		MISSION_SELECTED.editorData = (MISSION_SELECTED.editorData.size() == 0) ? DEFAULT_EDITORDATA : MISSION_SELECTED.editorData;
		MISSION_SELECTED.binarized = (MISSION_SELECTED.binarized.equals("")) ? DEFAULT_BINARIZED : MISSION_SELECTED.binarized;
		MISSION_SELECTED.addons = (MISSION_SELECTED.addons.size() == 0) ? DEFAULT_ADDONS : MISSION_SELECTED.addons;
		MISSION_SELECTED.addonsMeta = (MISSION_SELECTED.addonsMeta.size() == 0) ? DEFAULT_ADDONSMETA : MISSION_SELECTED.addonsMeta;
		MISSION_SELECTED.randomSeed = (MISSION_SELECTED.randomSeed.equals("")) ? DEFAULT_SEED : MISSION_SELECTED.randomSeed;
		MISSION_SELECTED.scenarioData = (MISSION_SELECTED.scenarioData.size() == 0) ? DEFAULT_SCENARIODATA : MISSION_SELECTED.scenarioData;
		MISSION_SELECTED.missionIntel = (MISSION_SELECTED.missionIntel.size() == 0) ? DEFAULT_INTEL : MISSION_SELECTED.missionIntel;
		MISSION_SELECTED.missionData = (MISSION_SELECTED.missionData.size() == 0) ? DEFAULT_MISSIONDATA : MISSION_SELECTED.missionData;
	}
}
