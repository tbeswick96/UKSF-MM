/*
 * Copyright (c) Tim UKSF 2016.
 * This file is part of UKSF-MM which is released under GPLv3.
 * Go to https://github.com/tbeswick96/UKSF-MM/blob/master/LICENSE for full license details.
 */

package com.uksf.mm.core.utility.sqm;

import com.uksf.mm.core.Core;
import com.uksf.mm.core.utility.LogHandler;

import java.io.*;

import static com.uksf.mm.core.utility.Info.MISSION_SELECTED;
import static com.uksf.mm.core.utility.Info.SQM_BACKUP;
import static com.uksf.mm.core.utility.LogHandler.Severity.INFO;

/**
 * @author Tim
 */
public class SqmSaver {

	/**
	 * Saves mission to sqm using stored object data
	 */
	public static void saveMission() {
		if(MISSION_SELECTED == null || MISSION_SELECTED.getVersion() == null) {
			if(!Core.getInstanceUI().loadSelectedMission()) return;
		}
		LogHandler.logSeverity(INFO, "Saving mission '" + MISSION_SELECTED.getName() + "' at '" + MISSION_SELECTED.getPath() + "'");
		File sqmFile = new File(MISSION_SELECTED.getPath() + "/mission.sqm");

		if(SQM_BACKUP) {
			LogHandler.logSeverity(INFO, "SQM Backup enabled, backing up SQM '" + MISSION_SELECTED.getName() + "' as '" + MISSION_SELECTED.getPath() + "/mission.sqm.backup'");
			File backup = new File(sqmFile.getAbsolutePath());
			backup.renameTo(new File(sqmFile.getAbsolutePath() + ".backup"));
		} else {
			LogHandler.logSeverity(INFO, "SQM Backup disabled, overwriting SQM '" + MISSION_SELECTED.getName() + "' at '" + MISSION_SELECTED.getPath() + "/mission.sqm'");
			sqmFile.delete();
		}

		try {
			Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(sqmFile, true)));

			writer.append(MISSION_SELECTED.getVersion());
			for(String line : MISSION_SELECTED.getEditorData()) {
				writer.append("\r\n").append(line);
			}
			writer.append("\r\n").append(MISSION_SELECTED.getBinarized());
			for(String line : MISSION_SELECTED.getAddons()) {
				writer.append("\r\n").append(line);
			}
			writer.append("\r\n").append(MISSION_SELECTED.getRandomSeed());
			for(String line : MISSION_SELECTED.getScenarioData()) {
				writer.append("\r\n").append(line);
			}
			for(String line : MISSION_SELECTED.getCustomAttributes()) {
				writer.append("\r\n").append(line);
			}
			writer.append("\r\n").append("class Mission");
			writer.append("\r\n").append("{");
			for(String line : MISSION_SELECTED.getMissionIntel()) {
				writer.append("\r\n").append(line);
			}
			for(String line : MISSION_SELECTED.getMissionData()) {
				writer.append("\r\n").append(line);
			}
			writer.append("\r\n").append("};").append("\r\n");

			writer.close();
		} catch(IOException e) {
			Core.error(e);
		}
		LogHandler.logSeverity(INFO, "Saved mission '" + MISSION_SELECTED.getName() + "' at '" + MISSION_SELECTED.getPath() + "'");
	}
}
