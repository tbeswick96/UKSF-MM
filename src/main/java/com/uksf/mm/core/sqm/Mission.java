/*
 * Copyright (c) Tim UKSF 2016.
 * This file is part of UKSF-MM which is released under GPLv3.
 * Go to https://github.com/tbeswick96/UKSF-MM/blob/master/LICENSE for full license details.
 */

package com.uksf.mm.core.sqm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tim
 */
public class Mission {

	/**
	 * Name and file path of mission folder
	 */
	public String name, path, originalPath;

	/**
	 * Mission SQM data
	 */
	public String version, binarized, randomSeed;
	public List<String> editorData, addons, scenarioData, customAttributes, missionIntel, missionData;

	/**
	 * Parsed SQM data
	 */
	public String author;
	public ArrayList<String> intel;

	/**
	 * Create new mission
	 * @param name name of folder
	 * @param path path of folder
	 */
	public Mission(String name, String path) {
		this.name = name;
		this.path = path;
		originalPath = path;
	}

	/**
	 * Get mission string as name
	 * @return mission name
	 */
	@Override
	public String toString() {
		return name;
	}
}
