/*
 * Copyright (c) Tim UKSF 2016.
 * This file is part of UKSF-MM which is released under GPLv3.
 * Go to https://github.com/tbeswick96/UKSF-MM/blob/master/LICENSE for full license details.
 */

package com.uksf.mm.core.sqm;

import com.uksf.mm.core.Core;
import com.uksf.mm.core.utility.LogHandler;
import org.apache.commons.io.FileUtils;

import java.io.*;

import static com.uksf.mm.core.utility.Info.MISSION_SELECTED;
import static com.uksf.mm.core.utility.Info.SQM_BACKUP;
import static com.uksf.mm.core.utility.LogHandler.Severity.INFO;
import static com.uksf.mm.core.utility.LogHandler.Severity.WARNING;

/**
 * @author Tim
 */
public class SqmSaver {

	/**
	 * Writer object
	 */
	private static Writer writer;

	/**
	 * SQM file to save to
	 */
	private static File sqmFile;

	/**
	 * Saves mission to sqm using stored object data
	 */
	public static void saveMission() {
		if(MISSION_SELECTED == null || MISSION_SELECTED.version == null) {
			if(! Core.getInstanceUI().loadSelectedMission()) return;
		}
		LogHandler.logSeverity(INFO, "Saving mission '" + MISSION_SELECTED.name + "' at '" + MISSION_SELECTED.path + "'");
		makeFile();
		backup();

		try {
			saveSqm();
		} catch(IOException e) {
			Core.error(e);
		}

		LogHandler.logSeverity(INFO, "Saved mission '" + MISSION_SELECTED.name + "' at '" + MISSION_SELECTED.path + "'");
	}

	/**
	 * Creates the sqm file and creates new directory if given does not exist
	 */
	private static void makeFile() {
		File missionFolder = new File(MISSION_SELECTED.path);
		if(!missionFolder.exists()) {
			LogHandler.logSeverity(INFO, "Mission file not found, creating new folder'" + MISSION_SELECTED.name + "' at '" + MISSION_SELECTED.path + "'");
			missionFolder.mkdir();
			copyOriginal();
		}
		sqmFile = new File(MISSION_SELECTED.path + "/mission.sqm");
	}

	/**
	 * Copies other files from original path to new folder
	 */
	private static void copyOriginal() {
		LogHandler.logSeverity(INFO, "Copying other files from '" + MISSION_SELECTED.originalPath + "' to '" + MISSION_SELECTED.path + "'");
		File original = new File(MISSION_SELECTED.originalPath);
		File newFolder = new File(MISSION_SELECTED.path);
		try {
			FileUtils.copyDirectory(original, newFolder);
			File oldSqm = new File(MISSION_SELECTED.originalPath + "/mission.sqm");
			oldSqm.delete();
		} catch(IOException e) {
			LogHandler.logSeverity(WARNING, "Copying files failed.");
		}
	}

	/**
	 * Checks if backup is enabled and backs up sqm if so
	 */
	private static void backup() {
		if(SQM_BACKUP && sqmFile.exists()) {
			LogHandler.logSeverity(INFO, "SQM Backup enabled, backing up SQM '" + MISSION_SELECTED.name + "/mission.sqm' as '" + MISSION_SELECTED.path + "/mission.sqm.backup'");
			File backup = new File(sqmFile.getAbsolutePath());
			backup.renameTo(new File(sqmFile.getAbsolutePath() + ".backup"));
		} else {
			LogHandler.logSeverity(INFO, "SQM Backup disabled, overwriting SQM '" + MISSION_SELECTED.name + "' at '" + MISSION_SELECTED.path + "/mission.sqm'");
		}
		sqmFile.delete();
	}

	/**
	 * Writes all the mission data to the SQM
	 * @throws IOException file error
	 */
	private static void saveSqm() throws IOException {
		writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(sqmFile, true)));

		writer.append(MISSION_SELECTED.version);
		saveEditorData();
		writer.append("\r\n").append(MISSION_SELECTED.binarized);
		saveAddonData();
		writer.append("\r\n").append(MISSION_SELECTED.randomSeed);
		saveScenarioData();
		saveMissionData();

		writer.close();
	}

	/**
	 * Saves editor data
	 * @throws IOException file exception
	 */
	private static void saveEditorData() throws IOException {
		for(String line : MISSION_SELECTED.editorData) {
			writer.append("\r\n").append(line);
		}
	}


	/**
	 * Saves addon data
	 * @throws IOException file exception
	 */
	private static void saveAddonData() throws IOException {
		for(String line : MISSION_SELECTED.addons) {
			writer.append("\r\n").append(line);
		}
		System.out.println(MISSION_SELECTED.addonsMeta);
		for(String line : MISSION_SELECTED.addonsMeta) {
			writer.append("\r\n").append(line);
		}
	}


	/**
	 * Saves scenario data
	 * @throws IOException file exception
	 */
	private static void saveScenarioData() throws IOException {
		for(String line : MISSION_SELECTED.scenarioData) {
			if(line.contains("author")) {
				writer.append("\r\n\t").append("author=\"").append(MISSION_SELECTED.author).append("\";");
			} else {
				writer.append("\r\n").append(line);
			}
		}
	}


	/**
	 * Saves mission data
	 * @throws IOException file exception
	 */
	private static void saveMissionData() throws IOException {
		writer.append("\r\n").append("class Mission");
		writer.append("\r\n").append("{");
		saveMissionIntel();
		for(String line : MISSION_SELECTED.missionData) {
			writer.append("\r\n").append(line);
		}
		writer.append("\r\n").append("};").append("\r\n");
	}


	/**
	 * Saves mission intel
	 * @throws IOException file exception
	 */
	private static void saveMissionIntel() throws IOException {
		int index = 0;
		boolean intelDone = false;
		for(String line : MISSION_SELECTED.missionIntel) {
			if(line.toLowerCase().contains("customattributes")) intelDone = true;
			if(!intelDone && line.contains("=")) {
				String name = line.split("=")[0];
				String value = index >= MISSION_SELECTED.intel.size() ? "0" : MISSION_SELECTED.intel.get(index);
				writer.append("\r\n").append(name).append("=").append(value).append(";");
				index++;
			} else {
				writer.append("\r\n").append(line);
			}
		}
	}
}
