/*
 * Copyright (c) Tim UKSF 2016.
 * This file is part of UKSF-MM which is released under GPLv3.
 * Go to https://github.com/tbeswick96/UKSF-MM/blob/master/LICENSE for full license details.
 */

package com.uksf.mm.core.utility.loaders;

import com.uksf.mm.core.sqm.Mission;
import com.uksf.mm.core.utility.LogHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import static com.uksf.mm.core.utility.Info.HASHSPACE;
import static com.uksf.mm.core.utility.Info.MAPS;
import static com.uksf.mm.core.utility.Info.MISSIONS;
import static com.uksf.mm.core.utility.LogHandler.Severity.INFO;

/**
 * @author Tim
 */
public class MapLoad {

	/**
	 * List containing names of maps from available missions
	 */
	public static void loadMaps() {
		LogHandler.logNoTime(HASHSPACE);
		MAPS.clear();
		ArrayList<String> allMaps = new ArrayList<>();
		LogHandler.logSeverity(INFO, "Loading map names");
		for(Mission mission : MISSIONS) {
			String[] parts = mission.name.split("\\.");
			if(parts.length != 0) {
				String name = parts[parts.length - 1].toLowerCase();
				allMaps.add(name.substring(0, 1).toUpperCase() + name.substring(1));
			}
		}
		LogHandler.logSeverity(INFO, "Found maps: " + allMaps);
		Set<String> maps = new LinkedHashSet<>(allMaps);
		if(maps.size() > 0) maps.add("All");
		MAPS = new ArrayList<>(maps);
		Collections.sort(MAPS);
		LogHandler.logSeverity(INFO, "Using maps: " + MAPS);
	}
}
