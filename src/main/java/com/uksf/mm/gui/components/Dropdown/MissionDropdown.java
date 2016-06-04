/*
 * Copyright (c) Tim UKSF 2016.
 * This file is part of UKSF-MM which is released under GPLv3.
 * Go to https://github.com/tbeswick96/UKSF-MM/blob/master/LICENSE for full license details.
 */

package com.uksf.mm.gui.components.dropdown;

import com.uksf.mm.core.sqm.Mission;
import com.uksf.mm.core.utility.LogHandler;
import com.uksf.mm.gui.components.labels.CustomLabel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

import static com.uksf.mm.core.utility.Info.*;
import static com.uksf.mm.core.utility.LogHandler.Severity.INFO;

/**
 * @author Tim
 */
@SuppressWarnings("unchecked")
public class MissionDropdown extends CustomDropdown {

	/**
	 * Holds the current list of names
	 */
	private String[] names;

	/**
	 * Make the mission dropdown, initialise with a filter of all (show everything)
	 */
	public MissionDropdown() {
		super();
		filter("all");
	}

	/**
	 * Filters according to the given string.
	 * A zero length mission list results in a no mission warning.
	 * Filter 'All' results in displaying all
	 * @param name string to filter with
	 */
	public void filter(String name) {
		removeAllItems();
		ArrayList<Mission> missions = MISSIONS;
		names = new String[missions.size()];
		if(names.length == 0 || name.equals("")) {
			names = new String[1];
			names[0] = "No missions";
			addItem(0);
		} else {
			int index = 0;
			for(Mission mission : missions) {
				String missionName = mission.name;
				String[] missionParts = missionName.split("\\.");
				String mapName = missionParts[missionParts.length - 1];
				if(name.toLowerCase().equals("all")) {
					names[index] = missionName;
					addItem(index);
					index++;
				} else if(mapName.toLowerCase().equals(name.toLowerCase())) {
					names[index] = (missionName.split("\\.")[0]);
					addItem(index);
					index++;
				}
			}
			LogHandler.logSeverity(INFO, "Filtered missions: '" + Arrays.deepToString(names) + "'");
		}
		setRenderer(new CustomDropDownCellRenderer());
	}

	public Mission getSelectedMission() {
		String name = names[getSelectedIndex()];
		for(Mission mission : MISSIONS) {
			if(mission.name.equalsIgnoreCase(name.toLowerCase())) {
				return mission;
			}
		}
		return null;
	}

	/**
	 * Custom cell renderer
	 */
	private class CustomDropDownCellRenderer extends CustomLabel implements ListCellRenderer {

		/**
		 * Call to custom label
		 */
		CustomDropDownCellRenderer() {
			super("", FONT_STANDARD.getStyle(), 14, true, COLOUR_WHITE, COLOUR_BLACK, "");
		}

		/**
		 * Gets component to render
		 * @param list list of objects
		 * @param value current value
		 * @param index current index
		 * @param isSelected true if selected
		 * @param cellHasFocus true if has focus
		 * @return component to render
		 */
		@Override public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
			int selectionIndex = (Integer) value;

			if(isSelected) {
				setBackground(COLOUR_FOREGROUND_DARK);
				setForeground(COLOUR_BLACK);
			} else {
				setBackground(COLOUR_BACKGROUND_LIGHT);
				setForeground(COLOUR_WHITE);
			}
			if(selectionIndex < names.length) {
				String mission = names[selectionIndex];
				if(mission != null) {
					setText(mission);
				}
			}
			return this;
		}
	}
}
