/*
 * Copyright (c) Tim UKSF 2016.
 * This file is part of UKSF-MM which is released under GPLv3.
 * Go to https://github.com/tbeswick96/UKSF-MM/blob/master/LICENSE for full license details.
 */

package com.uksf.mm.core.sqm;

import java.util.ArrayList;

import static com.uksf.mm.core.utility.Info.*;

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
	public SqmList editorData, addons, addonsMeta, scenarioData, missionIntel, missionData;

	/**
	 * Original mission SQM data
	 */
	public String originalBinarized, originalAuthor;
	public SqmList originalAddons, originalAddonsMeta;
	public ArrayList<String> originalIntel;

	/**
	 * Parsed SQM data
	 */
	public String author = DEFAULT_AUTHOR;
	public ArrayList<String> intel;

	/**
	 * Create new mission
	 * @param name name of folder
	 * @param path path of folder
	 */
	public Mission(String name, String path) {
		this.name = name;
		this.path = path;
		System.out.println(name + ", " + path);
		originalPath = path;
	}

	/**
	 * Get mission string as name
	 * @return mission name
	 */
	@Override public String toString() {
		return name;
	}

	/**
	 * Sets new mision file name
	 * @param newName new name of mission
	 */
	public void setName(String newName) {
		name = newName;
		path = FOLDER_MISSIONS + "/" + name + path.substring(path.lastIndexOf("."), path.length());
	}

	/**
	 * Sets new author name
	 * @param newAuthor new author name
	 */
	public void setAuthor(String newAuthor) {
		author = newAuthor;
	}

	/**
	 * Sets whether the mission should be binarized next time it is save in-game
	 * @param isBinarized whether mission should be binarized
	 */
	public void setBinarized(boolean isBinarized) {
		MISSION_SELECTED.binarized = isBinarized ? "binarizationWanted=1;" : "binarizationWanted=0;";
	}
}
