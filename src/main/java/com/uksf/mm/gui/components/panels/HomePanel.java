/*
 * Copyright (c) Tim UKSF 2016.
 * This file is part of UKSF-MM which is released under GPLv3.
 * Go to https://github.com/tbeswick96/UKSF-MM/blob/master/LICENSE for full license details.
 */

package com.uksf.mm.gui.components.panels;


import com.uksf.mm.core.sqm.Mission;
import com.uksf.mm.gui.components.buttons.CustomButton;
import com.uksf.mm.gui.components.buttons.CustomButtonText;
import com.uksf.mm.gui.components.dropdown.MapDropdown;
import com.uksf.mm.gui.components.dropdown.MissionDropdown;
import com.uksf.mm.gui.components.labels.CustomLabel;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

import static com.uksf.mm.core.utility.Info.*;

/**
 * @author Tim
 */
public class HomePanel extends JPanel {

	/**
	 * Map selection dropdown
	 */
	private final MapDropdown mapSelection;

	/**
	 * Mission selection dropdown
	 */
	private final MissionDropdown missionSelection;

	/**
	 * Currently displayed mission panel
	 */
	private MissionPanel missionPanel;

	/**
     * Creates home panel
     */
    public HomePanel() {
        setOpaque(false);
        setBackground(COLOUR_TRANSPARENT);
        setLayout(new MigLayout("fill", "0[]0", "0[]0"));

		GenericPanel selectionPanel = new GenericPanel("al left center", "5[]10[]5[]20[]5[]20[]5[]5", "0[]0", true, COLOUR_BACKGROUND_LIGHTER);
		selectionPanel.setPreferredSize(new Dimension(getWidth(), 35));
		CustomButton refresh = new CustomButton(27, 27, 0, ICON_REFRESH.getImage(), ICON_REFRESH_HOVER.getImage(), COLOUR_TRANSPARENT, "refreshMissions", "Refresh Map & Mission list");
		CustomLabel mapText = new CustomLabel("Map:", Font.PLAIN, 16, false, COLOUR_TRANSPARENT, COLOUR_WHITE, "");
		mapSelection = new MapDropdown();
		CustomLabel missionText = new CustomLabel("Mission:", Font.PLAIN, 16, false, COLOUR_TRANSPARENT, COLOUR_WHITE, "");
		missionSelection = new MissionDropdown();
		CustomButtonText loadMission = new CustomButtonText("Load", FONT_STANDARD, 16, "loadSelectedMission", "Load selected mission");
		CustomButtonText saveMission = new CustomButtonText("Save", FONT_STANDARD, 16, "saveSelectedMission", "Save selected mission");
		selectionPanel.add(refresh, "cell 0 0");
		selectionPanel.add(mapText, "cell 1 0");
		selectionPanel.add(mapSelection, "cell 2 0");
		selectionPanel.add(missionText, "cell 3 0");
		selectionPanel.add(missionSelection, "push, cell 4 0");
		selectionPanel.add(loadMission, "cell 5 0");
		selectionPanel.add(saveMission, "cell 6 0");

		add(selectionPanel, "dock north, growx, cell 0 0");
    }

	/**
	 * Update lists
	 */
	public void updateLists() {
		mapSelection.updateList();
		filterMap("");
	}

	/**
	 * Filter mission list based on map name
	 * @param name map to name to filter by
	 */
	public void filterMap(String name) {
		missionSelection.filter(name);
	}

	/**
	 * Gets the currently selected mission
	 * @return mission selected in dropdown
	 */
	public Mission getSelectedMission() {return missionSelection.getSelectedMission();}

	/**
	 * Adds mission panel to view, replacing old
	 */
	public void addMission() {
		remove(missionPanel);
		missionPanel = new MissionPanel();
		add(missionPanel, "grow");
	}
}
