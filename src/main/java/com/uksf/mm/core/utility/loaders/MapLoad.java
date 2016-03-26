/*
 * Copyright (c) Tim UKSF 2016.
 * This file is part of UKSF-MM which is released under GPLv3.
 * Go to https://github.com/tbeswick96/UKSF-MM/blob/master/LICENSE for full license details.
 */

package com.uksf.mm.core.utility.loaders;

import com.uksf.mm.core.utility.LogHandler;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static com.uksf.mm.core.utility.Info.FOLDER_MISSIONS;
import static com.uksf.mm.core.utility.LogHandler.Severity.INFO;

/**
 * @author Tim
 */
public class MapLoad {

	/**
	 * List containing names of maps from available missions
	 * @return list of map names
	 */
	public static ArrayList<String> loadMaps() {
		ArrayList<String> mapNames;
		File folder = new File(FOLDER_MISSIONS);
		LogHandler.logSeverity(INFO, "Loading map names from '" + folder.getAbsolutePath() + "'");
		ArrayList<String> files = new ArrayList<>(Arrays.asList(folder.list()));
		files = removePbos(files);
		LogHandler.logSeverity(INFO, "Found files: " + files);
		ArrayList<String> allMaps = files.stream().map(MapLoad:: getMap).collect(Collectors.toCollection(ArrayList::new));
		LogHandler.logSeverity(INFO, "Found maps: " + allMaps);
		Set<String> maps = new LinkedHashSet<>(allMaps);
		LogHandler.logSeverity(INFO, "Using maps: " + maps);
		mapNames = new ArrayList<>(maps);
		mapNames.add("All");
		return mapNames;
	}

	/**
	 * Get list of just map names
	 * @param files list of found files
	 * @return list of maps
	 */
	private static ArrayList<String> removePbos( ArrayList<String> files) {
		return files.stream().filter(file -> ! (file.contains("pbo") || file.contains("sqf"))).collect(Collectors.toCollection(ArrayList::new));
	}

	/**
	 * Get map from given filename
	 * @param fileName file to get map from
	 * @return map name
	 */
	private static String getMap(String fileName) {
		LogHandler.logSeverity(INFO, "Getting map from '" + fileName + "'");
		String[] strings = fileName.split("\\.");
		return strings[strings.length - 1];
	}
}
