/*
 * Copyright (c) Tim UKSF 2016.
 * This file is part of UKSF-MM which is released under GPLv3.
 * Go to https://github.com/tbeswick96/UKSF-MM/blob/master/LICENSE for full license details.
 */

package com.uksf.mm.core.utility.loaders;

import com.uksf.mm.core.Mission;
import com.uksf.mm.core.utility.LogHandler;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

import static com.uksf.mm.core.utility.Info.FOLDER_MISSIONS;
import static com.uksf.mm.core.utility.Info.HASHSPACE;
import static com.uksf.mm.core.utility.Info.MISSIONS;
import static com.uksf.mm.core.utility.LogHandler.Severity.INFO;
import static java.util.Arrays.asList;

/**
 * @author Tim
 */
public class MissionLoad {

	public static void loadMissions() {
		LogHandler.logNoTime(HASHSPACE);
		MISSIONS.clear();
		File folder = new File(FOLDER_MISSIONS);
		LogHandler.logSeverity(INFO, "Loading missions from '" + folder.getAbsolutePath() + "'");
		ArrayList<String> files = new ArrayList<>(asList(folder.list()));
		files = getMissions(files);
		LogHandler.logSeverity(INFO, "Found missions: " + files);
		MISSIONS.addAll(files.stream().map(mission -> new Mission(mission, FOLDER_MISSIONS + "/" + mission)).collect(Collectors.toList()));
		Collections.sort(MISSIONS, (o1, o2) -> o1.toString().toLowerCase().compareTo(o2.toString().toLowerCase()));
		LogHandler.logSeverity(INFO, "Loaded missions: " + MISSIONS);
	}

	private static ArrayList<String> getMissions(ArrayList<String> files) {
		ArrayList<String> missions = new ArrayList<>();
		for(String file : files) {
			if(file.contains("pbo") || file.contains("sqf") || file.contains("sqm")) continue;
			File mission = new File(FOLDER_MISSIONS + "/" + file);
			String[] missionFiles = mission.list();
			if(missionFiles.length != 0 && Arrays.asList(missionFiles).contains("mission.sqm")) {
				missions.add(file);
			}
		}
		return missions;
	}
}
