/*
 * Copyright (c) Tim UKSF 2016.
 * This file is part of UKSF-MM which is released under GPLv3.
 * Go to https://github.com/tbeswick96/UKSF-MM/blob/master/LICENSE for full license details.
 */

package com.uksf.mm.gui.components.Dropdown;

import com.uksf.mm.core.Mission;
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

	private String[] names;

	public MissionDropdown() {
		super();
		filter("all");
	}

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
				String missionName = mission.getName();
				String[] missionParts = missionName.split("\\.");
				String mapName = missionParts[missionParts.length - 1];
				if(mapName.toLowerCase().contains(name.toLowerCase()) || name.toLowerCase().equals("all")) {
					names[index] = missionName;
					addItem(index);
					index++;
				}
			}
			LogHandler.logSeverity(INFO, "Filtered missions: '" + Arrays.deepToString(names) + "'");
		}
		setRenderer(new CustomDropDownCellRenderer());
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(150, getHeight());
	}

	private class CustomDropDownCellRenderer extends CustomLabel implements ListCellRenderer {

		CustomDropDownCellRenderer() {
			super("", FONT_STANDARD.getStyle(), 14, true, COLOUR_WHITE, COLOUR_BLACK, "");
		}

		@Override
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
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
