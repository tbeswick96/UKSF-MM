/*
 * Copyright (c) Tim UKSF 2016.
 * This file is part of UKSF-MM which is released under GPLv3.
 * Go to https://github.com/tbeswick96/UKSF-MM/blob/master/LICENSE for full license details.
 */

package com.uksf.mm.core;

import java.util.List;

/**
 * @author Tim
 */
public class Mission {

	/**
	 * Name and file apth of mission folder
	 */
	private String name, path;

	/**
	 * Mission sqm data
	 */
	private String version, binarized, randomSeed;
	private List<String> editorData, addons, scenarioData, customAttributes, missionIntel, missionData;

	/**
	 * Create new mission
	 * @param name name of folder
	 * @param path path of folder
	 */
	public Mission(String name, String path) {
		this.name = name;
		this.path = path;
	}

	/**
	 * Get mission name
	 * @return mission name
	 */
	public String getName() {return name;}

	/**
	 * Get mission folder path
	 * @return mission folder path
	 */
	public String getPath() {return path;}

	/**
	 * Set SQM version line
	 * @param version version line
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * Set SQM editorData list
	 * @param editorData editordata list
	 */
	public void setEditorData(List<String> editorData) {
		this.editorData = editorData;
	}

	/**
	 * Set SQM binarized line
	 * @param binarized binarized line
	 */
	public void setBinarized(String binarized) {
		this.binarized = binarized;
	}

	/**
	 * Set SQM addons list
	 * @param addons addons lise
	 */
	public void setAddons(List<String> addons) {
		this.addons = addons;
	}

	/**
	 * Set SQM randomseed line
	 * @param randomSeed randomseed line
	 */
	public void setRandomSeed(String randomSeed) {
		this.randomSeed = randomSeed;
	}

	/**
	 * Set SQM scenariodata list
	 * @param scenarioData scneariodata list
	 */
	public void setScenarioData(List<String> scenarioData) {
		this.scenarioData = scenarioData;
	}

	/**
	 * Set SQM customattributes list
	 * @param customAttributes customattributes list
	 */
	public void setCustomAttributes(List<String> customAttributes) {
		this.customAttributes = customAttributes;
	}

	/**
	 * Set SQM mission intel list
	 * @param missionIntel mission intel list
	 */
	public void setMissionIntel(List<String> missionIntel) {
		this.missionIntel = missionIntel;
	}

	/**
	 * Set SQM mission data list
	 * @param missionData mission data list
	 */
	public void setMissionData(List<String> missionData) {
		this.missionData = missionData;
	}

	/**
	 * Get SQM version line
	 * @return version line
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * Get SQM editordata list
	 * @return editordata list
	 */
	public List<String> getEditorData() {
		return editorData;
	}

	/**
	 * Get SQM binarized line
	 * @return binarized line
	 */
	public String getBinarized() {
		return binarized;
	}

	/**
	 * Get SQM addons list
	 * @return addons list
	 */
	public List<String> getAddons() {
		return addons;
	}

	/**
	 * Get SQM randomseed line
	 * @return randomseed line
	 */
	public String getRandomSeed() { return randomSeed; }

	/**
	 * Get SQM scenariodata list
	 * @return scenariodata list
	 */
	public List<String> getScenarioData() {
		return scenarioData;
	}

	/**
	 * Get SQM customattributes list
	 * @return customattributes list
	 */
	public List<String> getCustomAttributes() {
		return customAttributes;
	}

	/**
	 * Get SQM mission intel list
	 * @return mission intel list
	 */
	public List<String> getMissionIntel() {
		return missionIntel;
	}

	/**
	 * Get SQM mission data list
	 * @return mission data list
	 */
	public List<String> getMissionData() {
		return missionData;
	}

	/**
	 * Get mission as string
	 * @return mission name
	 */
	@Override
	public String toString() {
		return name;
	}
}
